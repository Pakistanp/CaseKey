package com.project.CaseKey.Controller;

import com.project.CaseKey.JsonModel.UserInfo;
import com.project.CaseKey.Service.UserService;
import com.project.CaseKey.TemporaryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${website.address}")
    private String WEBSITE_ADDRESS;

    @GetMapping(value = "/")
    public String home(HttpServletRequest request, HttpServletResponse response) {
        UserInfo userInfo = new UserInfo();
        Object userSteamId = request.getSession().getAttribute("steamId");
        if (userSteamId != null) {
            userInfo = userService.getUserInformationFromApi(userSteamId.toString());
        }
        return new TemporaryView().createViewForHomePage(userSteamId, userInfo);
    }
    @GetMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(userService.login(WEBSITE_ADDRESS + "/authenticate"));
        return "";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("steamId");
        response.sendRedirect(WEBSITE_ADDRESS);
        return "";
    }

    @GetMapping(value = "/authenticate")
    public String authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fullUrl = WEBSITE_ADDRESS + request.getRequestURI();
        String userSteamId = userService.authenticate(fullUrl, request.getQueryString());
        if (userSteamId == null) {
            response.sendRedirect(WEBSITE_ADDRESS);
        }
        request.getSession(true).setAttribute("steamId", userSteamId);
        response.sendRedirect(WEBSITE_ADDRESS);
        return "";
    }
}
