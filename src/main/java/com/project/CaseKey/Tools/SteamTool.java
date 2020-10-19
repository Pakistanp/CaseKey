package com.project.CaseKey.Tools;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SteamTool {

    private static final String GET_OWNED_GAMES_URL = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/";
    private static final String WEB_API_KEY = "";

    public static boolean userHaveGame(String steamId, HashMap<String, Integer> game) {
        if (game.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean playedEnoughTime(HashMap<String, Integer> game) {
        int timePlayedInSeconds = game.get("playtime_forever");
        if (timePlayedInSeconds/60 >= 600) {
            return true;
        }
        else {
            return false;
        }
    }

    private static HashMap<String, Integer> getGameByAppId(String steamId, int gameId) {
        HashMap<String, Integer> game = new HashMap<String, Integer>();
        List<Object> gameList = getUserGameList(steamId);
        for (Object gameObject: gameList) {
            HashMap<String, Integer> gameHashMap = (HashMap<String, Integer>) gameObject;
            if ( gameHashMap.containsKey("appid") && gameHashMap.containsValue(gameId)) {
                game = gameHashMap;
                break;
            }
        }
        return game;
    }

    private static List<Object> getUserGameList(String steamId) {
        StringBuilder sb = new StringBuilder(GET_OWNED_GAMES_URL);
        sb.append("?key=").append(WEB_API_KEY)
                .append("&steamid=").append(steamId);
        JSONObject jsonObject = CommonTools.readJsonFromUrl(sb.toString());
        JSONArray jsonArray = ((JSONObject)jsonObject.get("response")).getJSONArray("games");
        return jsonArray.toList();
    }
}
