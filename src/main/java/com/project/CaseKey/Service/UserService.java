package com.project.CaseKey.Service;

import com.google.gson.Gson;
import com.project.CaseKey.JsonModel.UserInfo;
import com.project.CaseKey.JsonReader;
import com.project.CaseKey.SteamOpenID;
import com.project.CaseKey.TemporaryView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@PropertySource("classpath:config.properties")
@Service
public class UserService {
    private final SteamOpenID openid = new SteamOpenID();

    private final String GET_PLAYER_SUMMARIES = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=%s&steamids=%s";

    @Value("${webapi.key}")
    private String WEB_API;

    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect(openid.login("http://localhost:8080/authenticate"));
    }

    public String authenticate(String callBackURL, String responseQuery) {
        return openid.verify(callBackURL, responseQuery);
    }

    public void createSessionForAuthenticateUser(HttpServletRequest request, HttpServletResponse response, String userSteamId) {
        try {
            if (userSteamId == null) {
                response.sendRedirect("http://localhost:8080/");
            }
            request.getSession(true).setAttribute("steamId", userSteamId);
            response.sendRedirect("http://localhost:8080/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeAttributeFromSession(HttpServletRequest request, HttpServletResponse response, String steamId) {
        request.getSession().removeAttribute(steamId);
        try {
            response.sendRedirect("http://localhost:8080/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createViewForHomePage(HttpServletRequest request) {
        UserInfo userInfo = new UserInfo();
        Object userSteamId = request.getSession().getAttribute("steamId");
        if (userSteamId != null) {
            userInfo = getUserInformationFromApi(userSteamId.toString());
        }
        return new TemporaryView().createViewForHomePage(userSteamId, userInfo);
    }

    private UserInfo getUserInformationFromApi(String userSteamId) {
        String urlToApi = String.format(GET_PLAYER_SUMMARIES, WEB_API, userSteamId);
        return getUserInformationFromJson(urlToApi);
    }

    private UserInfo getUserInformationFromJson(String urlToApi) {
        JsonReader reader = new JsonReader();
        JSONObject userInfoAsJson = reader.readJsonFromUrl(urlToApi);
        return mapUserInfoFromJson(userInfoAsJson);
    }

    private UserInfo mapUserInfoFromJson (JSONObject userInfoAsJson) {
        JSONArray userInfosAsJson = userInfoAsJson.getJSONObject("response").getJSONArray("players");
        UserInfo userInfo = new Gson().fromJson(userInfosAsJson.get(0).toString(), UserInfo.class);
        return userInfo;
    }
}
