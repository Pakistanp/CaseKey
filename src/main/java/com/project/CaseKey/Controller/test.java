package com.project.CaseKey.Controller;

import com.project.CaseKey.Tools.CommonTools;
import com.project.CaseKey.Tools.SteamTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @GetMapping(value = "/test")
    public String test() {
        SteamTool.userHaveGame("76561198042001813",730);
        return "test";
    }
}
