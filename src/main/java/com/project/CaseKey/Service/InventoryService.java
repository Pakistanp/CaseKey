package com.project.CaseKey.Service;

import com.project.CaseKey.JsonReader;
import com.project.CaseKey.Model.InventoryItem;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.User;
import com.project.CaseKey.Repository.InventoryRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InventoryService {

    private final String GET_USER_STEAM_CSGO_INVENTORY = "https://steamcommunity.com/inventory/%s/730/2?l=english";

    @Autowired
    private SkinService skinService;
    @Autowired
    private UserService userService;
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryItem> getSteamInventoryFromApi(User user) {
        String urlToApi = String.format(GET_USER_STEAM_CSGO_INVENTORY, user.getId());
        JsonReader reader = new JsonReader();
        JSONObject itemInfoAsJson = reader.readJsonFromUrl(urlToApi);
        HashMap<String, Integer> hashNamesWithCount = getHashNamesWithCount(itemInfoAsJson);
        List<InventoryItem> userItems = new ArrayList<>();
        for( Map.Entry<String, Integer> nameWithCount : hashNamesWithCount.entrySet()) {
            Skin skin = skinService.getSkinByHashName(nameWithCount.getKey());
            userItems.add(new InventoryItem(user, skin, nameWithCount.getValue(), "steam"));
        }
        updateUserItemsToDatabase(userItems, user);
        return userItems;
    }

    public HashMap<String, Integer> getHashNamesWithCount(JSONObject itemInfoAsJson) {
        JSONArray skinAssetsAsJson = itemInfoAsJson.getJSONArray("assets");
        JSONArray skinDesctiptionAsJson = itemInfoAsJson.getJSONArray("descriptions");
        HashMap<String, Integer> hashNamesWithCount = new HashMap<>();
        for (Object skinAssetJ: skinAssetsAsJson) {
            String classId = ((JSONObject)skinAssetJ).get("classid").toString();
            String instanceId = ((JSONObject)skinAssetJ).get("instanceid").toString();
            String hashName = getFromDescriptionHashName(skinDesctiptionAsJson, classId, instanceId);
            if (!hashName.isEmpty()) {
                if (!hashNamesWithCount.containsKey(hashName)) {
                    hashNamesWithCount.put(hashName, 1);
                } else {
                    hashNamesWithCount.replace(hashName, hashNamesWithCount.get(hashName) + 1);
                }
            }
        }
        return hashNamesWithCount;
    }

    private String getFromDescriptionHashName(JSONArray skinDesctiptionAsJson, String classId, String instanceId) {
        for (Object skinDescJ: skinDesctiptionAsJson) {
            if (classId.equals(((JSONObject)skinDescJ).get("classid").toString()) && instanceId.equals(((JSONObject)skinDescJ).get("instanceid").toString())
                    && "1".equals(((JSONObject)skinDescJ).get("tradable").toString())) {
                return ((JSONObject)skinDescJ).get("market_hash_name").toString();
            }
        }
        return "";
    }

    private void updateUserItemsToDatabase(List<InventoryItem> userNewItems, User user) {
        List<InventoryItem> userSteamItems = getSteamInventory(user);
        if(!userSteamItems.containsAll(userNewItems) || !userNewItems.containsAll(userSteamItems)){
            List <InventoryItem> itemsToDelete = getItemsToDelete(userSteamItems, userNewItems);
            inventoryRepository.deleteAll(itemsToDelete);
            userSteamItems = getSteamInventory(user);
            for (InventoryItem item : userNewItems) {
                if(userSteamItems.contains(item)) {
                    inventoryRepository.updateInventory(user, item);
                }
                else {
                    inventoryRepository.save(item);
                }
            }
        }
    }

    private List<InventoryItem> getItemsToDelete(List<InventoryItem> userSteamItems, List<InventoryItem> userNewItems) {
        for (InventoryItem newItem : userNewItems) {
            if (!userSteamItems.contains(newItem))
                userSteamItems.remove(newItem);
        }
        return userSteamItems;
    }

    public List<InventoryItem> getSteamInventory(User user) {
        return inventoryRepository.findSteamInventoryItemsByUser(user);
    }

    public List<InventoryItem> getDropInventory(User user) {
        return inventoryRepository.findDropInventoryItemsByUser(user);
    }

    public void giveVirtualSkin(User user, Skin skin) {
        InventoryItem item = inventoryRepository.findWebsiteInventoryItemByUserSkin(user, skin);
        if(item != null) {
            item.setCount(item.getCount() + 1);
            inventoryRepository.save(item);
        }
        else {
            inventoryRepository.save(new InventoryItem(user, skin, 1, "website"));
        }
    }

    public void sellItem(User user, int itemId) {
        InventoryItem item = inventoryRepository.findById(itemId);
        if (item != null) {
            userService.updateUserBalance(user, user.getBalance() + priceToDouble(item.getInventorySkin().getPrice()));
            if (item.getCount() == 1) {
                inventoryRepository.delete(item);
            }
            else {
                item.setCount(item.getCount() - 1);
                inventoryRepository.save(item);
            }
        }
    }
    private Double priceToDouble(String price) {
        return Double.parseDouble(price.replace("$",""));
    }
}
