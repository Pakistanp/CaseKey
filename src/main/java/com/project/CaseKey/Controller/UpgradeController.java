package com.project.CaseKey.Controller;

import com.project.CaseKey.Model.InventoryItem;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.User;
import com.project.CaseKey.Service.UpgradeService;
import com.project.CaseKey.TemporaryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class UpgradeController {

    @Value("${website.address}")
    private String WEBSITE_ADDRESS;

    @Autowired
    UpgradeService upgradeService;

    TemporaryView temporaryView = new TemporaryView();

    @GetMapping(value = "/upgrade/{itemId}/page/{page}")
    public String upgradePage(HttpServletRequest request, HttpServletResponse response,
                          @PathVariable int itemId, @PathVariable int page) {
        User user = (User) request.getSession().getAttribute("user");
        List<Skin> skins = (List<Skin>) request.getSession().getAttribute("allSkins");
        if (skins == null) {
            skins = upgradeService.getAllSkins();
            request.getSession().setAttribute("allSkins", skins);
        }
        InventoryItem item = upgradeService.getItemById(itemId);
        return temporaryView.createViewForUpgradeItem(user, item, skins, page);
    }

    @GetMapping(value = "/upgrade/{itemId}")
    public String upgrade(HttpServletRequest request, HttpServletResponse response,
                          @PathVariable int itemId) {
        User user = (User) request.getSession().getAttribute("user");
        List<Skin> skins = (List<Skin>) request.getSession().getAttribute("allSkins");
        if (skins == null) {
            skins = upgradeService.getAllSkins();
            request.getSession().setAttribute("allSkins", skins);
        }
        InventoryItem item = upgradeService.getItemById(itemId);
        return temporaryView.createViewForUpgradeItem(user, item, skins, 1);
    }

    @GetMapping(value = "/upgrade/{itemId}/{descIdItem}")
    public String upgradeSelect(HttpServletRequest request, HttpServletResponse response,
                          @PathVariable int itemId, @PathVariable String descIdItem) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Skin> allSkins = (List<Skin>) request.getSession().getAttribute("allSkins");
        int newItemId = upgradeService.upgrade(user, itemId, descIdItem, allSkins);
        if (newItemId > 0) {
            response.sendRedirect(WEBSITE_ADDRESS + "/upgrade/" + newItemId);
        }
        else {
            response.sendRedirect(WEBSITE_ADDRESS + "/inventory/");
        }
        return "";
    }
}
