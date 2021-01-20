package com.project.CaseKey.Controller;

import com.project.CaseKey.JsonModel.UserInfo;
import com.project.CaseKey.Model.Case;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.User;
import com.project.CaseKey.Service.CaseService;
import com.project.CaseKey.TemporaryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class CaseController {

    @Autowired
    CaseService caseService;


    TemporaryView temporaryView = new TemporaryView();

    @GetMapping(value = "/case/{caseId}")
    public String showCase(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable int caseId) {
        User user = (User) request.getSession().getAttribute("user");
        Case findedCase = caseService.getCase(caseId);
        return temporaryView.createViewForCase(findedCase, user);
    }

    @GetMapping(value = "/case/{caseId}/open")
    public String opeCase(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable int caseId) {
        User user = (User) request.getSession().getAttribute("user");
        caseService.openCase(caseId, user);
        Case findedCase = caseService.getCase(caseId);
        return temporaryView.createViewForCase(findedCase, user);
    }
}
