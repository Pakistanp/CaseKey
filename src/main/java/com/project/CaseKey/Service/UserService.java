package com.project.CaseKey.Service;

import com.google.gson.Gson;
import com.project.CaseKey.JsonModel.UserInfo;
import com.project.CaseKey.JsonReader;
import com.project.CaseKey.Model.User;
import com.project.CaseKey.Repository.UserRepository;
import com.project.CaseKey.SteamOpenID;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@PropertySource("classpath:config.properties")
@Service
public class UserService {
    private final SteamOpenID openid = new SteamOpenID();

    private final String GET_PLAYER_SUMMARIES = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=%s&steamids=%s";
    private final String GET_PLAYER_OWNED_GAMES = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=%s&steamid=%s&format=json";
    private final int CSGO_GAME_CODE = 730;
    private final int CSGO_PLAYED_TIME = 10;

    @Autowired
    UserRepository userRepository;

    @Value("${webapi.key}")
    private String WEB_API;

    public String login(String websiteAddress) {
        return openid.login(websiteAddress);
    }

    public String authenticate(String callBackURL, String responseQuery) {
        return openid.verify(callBackURL, responseQuery);
    }

    public UserInfo getUserInformationFromApi(String userSteamId) {
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

    private boolean hasCsGoGameAndEnougthTimePlayed(String userSteamId) {
        String urlToApi = String.format(GET_PLAYER_OWNED_GAMES, WEB_API, userSteamId);
        getUserGamesFromJson(urlToApi);
        return true;
    }

    private void getUserGamesFromJson(String urlToApi) {
        JsonReader reader = new JsonReader();
        JSONObject gamesInfoAsJson = reader.readJsonFromUrl(urlToApi);
        JSONArray gamesInfosAsJson = gamesInfoAsJson.getJSONObject("response").getJSONArray("games");
    }

    public User authenticateUser(String userSteamId) {
        User user = userRepository.findUserById(userSteamId);
        if(user == null) {
            UserInfo userInfo = getUserInformationFromApi(userSteamId);
            user = new User();
            user.setId(userSteamId);
            user.setBalance(0.0);
            user.setName(userInfo.getPersonaName());
            user.setAvatarUrl(userInfo.getAvatarFull());
            user.setCanOpenCase(hasCsGoGameAndEnougthTimePlayed(userSteamId));
            userRepository.save(user);
        }
        else {
            user.setCanOpenCase(hasCsGoGameAndEnougthTimePlayed(userSteamId));
            userRepository.save(user);
        }
        return user;
    }
}
