package com.project.CaseKey.Service;

import com.project.CaseKey.Model.InventoryItem;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ContractService {

    final private double LOWER_VALUE_LIMIT = 0.8;
    final private double UPPER_VALUE_LIMIT = 1.5;

    @Autowired
    InventoryService inventoryService;
    @Autowired
    SkinService skinService;

    public List<InventoryItem> getUserWebsiteItems(User user) {
        return inventoryService.getDropInventory(user);
    }

    public void signContract(User user, List<String> skinsId) {
        List<InventoryItem> items = getItems(skinsId);
        double minimumValue = calculateMinimalSkinValue(items);
        double maximumValue = calculateMaximumSkinValue(items);
        Skin skin = drawSkin(minimumValue, maximumValue);
        removeItemsFromInventory(items);
        giveUserItem(skin, user);
    }

    private void giveUserItem(Skin skin, User user) {
        inventoryService.giveVirtualSkin(user, skin);
    }

    private void removeItemsFromInventory(List<InventoryItem> items) {
        for (InventoryItem item : items) {
            inventoryService.removeItem(item);
        }
    }

    private Skin drawSkin(double minimumValue, double maximumValue) {
        List<Skin> allSkins = skinService.getAllSkins();
        List<Skin> filteredSkins = filterSkins(allSkins, minimumValue, maximumValue);
        return draw(filteredSkins);
    }

    private Skin draw(List<Skin> filteredSkins) {
        Random random = new Random();
        return filteredSkins.get(random.nextInt(filteredSkins.size()));
    }

    private List<Skin> filterSkins(List<Skin> allSkins, double minimumValue, double maximumValue) {
        return allSkins.stream().filter(skin -> priceToDouble(skin.getPrice()) >= minimumValue && priceToDouble(skin.getPrice()) <= maximumValue).collect(Collectors.toList());
    }

    private List<InventoryItem> getItems(List<String> skinsId) {
        List<InventoryItem> items = new ArrayList<>();
        for (String id : skinsId) {
            items.add(inventoryService.getItemById(Integer.parseInt(id)));
        }
        return items;
    }

    private double calculateMinimalSkinValue(List<InventoryItem> items) {
        return LOWER_VALUE_LIMIT * getSumOfSkinPrice(items);
    }

    private double calculateMaximumSkinValue(List<InventoryItem> items) {
        return UPPER_VALUE_LIMIT * getSumOfSkinPrice(items);
    }

    private double getSumOfSkinPrice(List<InventoryItem> items) {
        return items.stream().mapToDouble(item -> priceToDouble(item.getInventorySkin().getPrice())).sum();
    }

    private Double priceToDouble(String price) {
        String withoutDolarSign = price.replace("$","");
        return Double.parseDouble(withoutDolarSign.replace(",",""));
    }
}
