package com.project.CaseKey.Controller;

import com.project.CaseKey.Service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class SkinController {

    @Autowired
    SkinService skinService;

    @GetMapping(value = "/skin/{skinName}")
    public String skinDetails (HttpServletRequest request, HttpServletResponse response,
                               @PathVariable String skinName) {
        skinService.getSkinByHashName(skinName);
        return "";
    }

    @GetMapping(value = "/skin/updateAll")
    public String skinUpdateAll (HttpServletRequest request, HttpServletResponse response) {
        skinService.updateAllSkinsWithDelay();
        return "";
    }
}
