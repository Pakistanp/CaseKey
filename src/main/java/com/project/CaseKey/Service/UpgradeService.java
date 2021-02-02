package com.project.CaseKey.Service;

import com.project.CaseKey.Model.InventoryItem;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UpgradeService {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    SkinService skinService;


    private double calculateChanceToUpdate(InventoryItem item, Skin newItem) {
        double oldItemPrice = inventoryService.priceToDouble(item.getInventorySkin().getPrice());
        double newItemPrice = inventoryService.priceToDouble(newItem.getPrice());
        return oldItemPrice * 100.00 / newItemPrice;
    }

    public List<Skin> getAllSkins() {
        return skinService.getAllSkins();
    }

    public InventoryItem getItemById(int itemId) {
        return inventoryService.getItemById(itemId);
    }

    public int upgrade(User user, int inventoryItemId, String destinationSkinHashName, List<Skin> allSkins) {
        int upgradedItemId = 0;
        InventoryItem item = getItemById(inventoryItemId);
        Skin newItem = skinService.getSkinByHashNameFromList(allSkins, destinationSkinHashName);
        double chanceToUpgrade = calculateChanceToUpdate(item, newItem);
        inventoryService.removeItem(item);
        if (draw(chanceToUpgrade)) {
            InventoryItem upgradedItem = inventoryService.giveVirtualSkin(user, newItem);
            upgradedItemId = upgradedItem.getId();
        }
        return upgradedItemId;
    }

    private boolean draw(double chance) {
        Random random = new Random();
        random.nextDouble();
        return (100.0 * random.nextDouble()) <= chance;
    }
}
