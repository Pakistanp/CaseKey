package com.project.CaseKey.Controller;

import com.project.CaseKey.JsonModel.UserInfo;
import com.project.CaseKey.Model.InventoryItem;
import com.project.CaseKey.Model.User;
import com.project.CaseKey.Service.InventoryService;
import com.project.CaseKey.Service.UserService;
import com.project.CaseKey.TemporaryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private UserService userService;

    private TemporaryView temporaryView = new TemporaryView();

    @GetMapping(value = "/inventory/steam/refresh")
    public String refreshSteamInventory (HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        inventoryService.getSteamInventoryFromApi(user);
        return "";
    }

    @GetMapping(value = "/inventory/steam")
    public String showSteamInventory (HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        return temporaryView.createViewForSteamInventory(user);
    }

    @GetMapping(value = "/inventory")
    public String showInventory (HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        List<InventoryItem> inventoryItems = inventoryService.getDropInventory(user);
        return temporaryView.createViewForInventory(user, inventoryItems);
    }

    @GetMapping(value = "/inventory/{itemId}")
    public String deleteItem (HttpServletRequest request, HttpServletResponse response,
                              @PathVariable int itemId) {
        User user = (User) request.getSession().getAttribute("user");
        inventoryService.sellItem(user, itemId);

        List<InventoryItem> inventoryItems = inventoryService.getDropInventory(user);
        return temporaryView.createViewForInventory(user, inventoryItems);
    }
}
