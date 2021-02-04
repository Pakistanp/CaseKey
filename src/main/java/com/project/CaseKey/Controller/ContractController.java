package com.project.CaseKey.Controller;

import com.project.CaseKey.Model.InventoryItem;
import com.project.CaseKey.Model.User;
import com.project.CaseKey.Service.ContractService;
import com.project.CaseKey.TemporaryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ContractController {

    @Autowired
    ContractService contractService;

    private TemporaryView temporaryView = new TemporaryView();

    @GetMapping(value = "/contract")
    public String contract (HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        List<InventoryItem> items = contractService.getUserWebsiteItems(user);
        return temporaryView.createViewForContract(items);
    }

    @PostMapping(value = "/contract/sign")
    public String sign (HttpServletRequest request, HttpServletResponse response,
                        @RequestParam List<String> checkedSkins){
        User user = (User) request.getSession().getAttribute("user");
        contractService.signContract(user, checkedSkins);
        return temporaryView.createViewForHomePage(user);
    }
}
