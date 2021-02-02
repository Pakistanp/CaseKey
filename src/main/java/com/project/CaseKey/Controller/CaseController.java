package com.project.CaseKey.Controller;

import com.project.CaseKey.JsonModel.UserInfo;
import com.project.CaseKey.Model.Case;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.User;
import com.project.CaseKey.Service.CaseService;
import com.project.CaseKey.TemporaryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/case")
public class CaseController {

    @Autowired
    CaseService caseService;


    TemporaryView temporaryView = new TemporaryView();

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

    @GetMapping(value = "/creator")
    public String creator(HttpServletRequest request, HttpServletResponse response) {
        return temporaryView.createViewForCaseCreator();
    }

    @PostMapping(value = "/create")
    public String createCase(HttpServletRequest request, HttpServletResponse response, Case newCase) {
        caseService.createCase(newCase);
        return temporaryView.createViewForCaseCreator();
    }
}
