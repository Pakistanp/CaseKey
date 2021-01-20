package com.project.CaseKey.Repository;

import com.project.CaseKey.Model.InventoryItem;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Integer> {
    @Query(value = "select i from InventoryItem i where i.user.id = :#{#user.id} and i.type = 'steam'")
    List<InventoryItem> findSteamInventoryItemsByUser(@Param("user") User user);
    @Transactional
    @Modifying
    @Query(value = "update InventoryItem i set i.count = :#{#inventoryItem.count} where i.user.id = :#{#user.id}")
    void updateInventory(@Param("user") User user, @Param("inventoryItem") InventoryItem inventoryItem);

    @Query(value = "select i from InventoryItem i where i.user.id = :#{#user.id} and i.inventorySkin.hashName = :#{#skin.hashName} and i.type = 'website'")
    InventoryItem findWebsiteInventoryItemByUserSkin(@Param("user") User user, @Param("skin") Skin skin);
}
