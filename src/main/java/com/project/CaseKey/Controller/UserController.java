package com.project.CaseKey.Controller;

import com.project.CaseKey.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String home(HttpServletRequest request, HttpServletResponse response) {
        return userService.createViewForHomePage(request);
    }
    @GetMapping(value = "/login")
    public String loginBySteam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.login(response);
        return "";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.removeAttributeFromSession(request, response, "steamId");
        return "";
    }

    @GetMapping(value = "/authenticate")
    public String authenticate(HttpServletRequest request, HttpServletResponse response) {
        String fullUrl = request.getRequestURL().toString();
        String userSteamId = userService.authenticate(fullUrl, request.getQueryString());
        userService.createSessionForAuthenticateUser(request, response, userSteamId);
        return "";
    }
}
