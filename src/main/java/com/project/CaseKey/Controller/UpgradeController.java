package com.project.CaseKey.Controller;

import com.project.CaseKey.Model.InventoryItem;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.User;
import com.project.CaseKey.Service.UpgradeService;
import com.project.CaseKey.TemporaryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UpgradeController {

    @Autowired
    UpgradeService upgradeService;

    TemporaryView temporaryView = new TemporaryView();

    @GetMapping(value = "/upgrade/{itemId}")
    public String upgrade(HttpServletRequest request, HttpServletResponse response,
                          @PathVariable int itemId) {
        User user = (User) request.getSession().getAttribute("user");
        List<Skin> skins = (List<Skin>) request.getSession().getAttribute("allSkins");
        if (skins == null) {
            skins = upgradeService.getAllSkins();
        }
        request.getSession().setAttribute("allSkins", skins);
        InventoryItem item = upgradeService.getItemById(itemId);
        return temporaryView.createViewForUpgradeItem(user, item, skins, 0.0);
    }

    @GetMapping(value = "/upgrade/{itemId}/{descIdItem}")
    public String upgradeSelect(HttpServletRequest request, HttpServletResponse response,
                          @PathVariable int itemId, @PathVariable String descIdItem) {
        User user = (User) request.getSession().getAttribute("user");
        List<Skin> allSkins = (List<Skin>) request.getSession().getAttribute("allSkins");
        boolean isSuccess = upgradeService.upgrade(user, itemId, descIdItem, allSkins);
        return "";
    }
}
