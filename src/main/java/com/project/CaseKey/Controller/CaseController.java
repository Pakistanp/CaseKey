package com.project.CaseKey.Controller;

import com.project.CaseKey.JsonModel.UserInfo;
import com.project.CaseKey.Model.Case;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.User;
import com.project.CaseKey.Service.CaseService;
import com.project.CaseKey.TemporaryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/case")
public class CaseController {

    @Autowired
    CaseService caseService;


    TemporaryView temporaryView = new TemporaryView();

    @Value("${website.address}")
    private String WEBSITE_ADDRESS;

    @GetMapping(value = "/{caseId}")
    public String showCase(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable int caseId) {
        User user = (User) request.getSession().getAttribute("user");
        Case findedCase = caseService.getCase(caseId);
        request.getSession().setAttribute("case", findedCase);
        return temporaryView.createViewForCase(findedCase, user);
    }

    @GetMapping(value = "/{caseId}/open")
    public String openCase(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable int caseId) {
        User user = (User) request.getSession().getAttribute("user");
        Case openedCase = (Case) request.getSession().getAttribute("case");
        Skin wonSkin = caseService.openCase(openedCase, user);
        return temporaryView.createViewForOpenedCase(openedCase, user, wonSkin);
    }

    @GetMapping(value = "/creator/skins")
    public String creatorShowSkins(HttpServletRequest request, HttpServletResponse response) {
        List<Skin> skins = (List<Skin>) request.getSession().getAttribute("allSkins");
        if (skins == null) {
            skins = caseService.getAllSkins();
            request.getSession().setAttribute("allSkins", skins);
        }
        return temporaryView.createViewForCaseCreator(skins);
    }

    @GetMapping(value = "/creator/chance")
    public String creatorChance(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam List<String> checkedSkins) {
        List<Skin> skins = (List<Skin>) request.getSession().getAttribute("allSkins");
        List<Skin> skinsInCase = caseService.getSkinsFromList(skins, checkedSkins);
        request.getSession().setAttribute("skinsInCase", skinsInCase);
        //caseService.createCase(newCase);
        return temporaryView.createViewForCaseChanceCreator(skinsInCase);
    }

    @PostMapping(value = "/creator/create")
    public String createReport(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam List<String> chances, @RequestParam String caseName) throws ServletException, IOException {
        List<Skin> skinsInCase = (List<Skin>) request.getSession().getAttribute("skinsInCase");
        if(caseService.verifySkinsChances(chances)) {
            caseService.createCase(skinsInCase, chances, caseName);
        }
        else {
            request.getRequestDispatcher("/case/creator/chance").forward(request, response);
        }
        return "dupa";
    }
}
