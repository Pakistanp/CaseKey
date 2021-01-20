package com.project.CaseKey;

import com.project.CaseKey.JsonModel.UserInfo;
import com.project.CaseKey.Model.Case;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.SkinInCase;
import com.project.CaseKey.Model.User;

public class TemporaryView {

    private final String STEAM_ITEM_IMAGE_URL = "https://steamcommunity-a.akamaihd.net/economy/image/";

    public String createViewForHomePage(User user) {
        StringBuilder bodyString = new StringBuilder();
        if( user != null ) {
            bodyString.append("Welcome " + user.getName());
            bodyString.append("<img src=\"" + user.getAvatarUrl() + "\">");
            bodyString.append("<a href=\"logout\">Logout</a>");
            bodyString.append("<a href=\"/inventory/\">Inventory</a>");
        }
        else {
            bodyString.append("<a href=\"login\">Login</a>");
        }
        return bodyString.toString();
    }
    public String createViewForInventory(User user) {
        StringBuilder bodyString = new StringBuilder();
        if( user != null ) {
            bodyString.append("Inventory " + user.getName());
            bodyString.append("<a href=\"logout\">Logout</a>");
            bodyString.append("<a href=\"/inventory/steam\">Steam Inventory</a>");
        }
        else {
            bodyString.append("<a href=\"login\">Login</a>");
        }
        return bodyString.toString();
    }

    public String createViewForSteamInventory(User user) {
        StringBuilder bodyString = new StringBuilder();
        if( user != null ) {
            bodyString.append("Steam Inventory " + user.getName());
            bodyString.append("<a href=\"logout\">Logout</a>");
            bodyString.append("<a href=\"/inventory/steam/refresh\">REFRESH</a>");
            bodyString.append("<a href=\"/inventory\">Inventory</a>");
        }
        else {
            bodyString.append("<a href=\"login\">Login</a>");
        }
        return bodyString.toString();
    }

    public String createViewForCase(Case findedCase, User user) {
        StringBuilder bodyString = new StringBuilder();
        bodyString.append(findedCase.getName());
        if (user != null) {
            bodyString.append("<br><a href=\"#\"><button>Open for " + findedCase.getCost().toString() +  "</button></a>");
        }
        else {
            bodyString.append("<br><a href=\"/login\"><button>Login to open a case</button></a>");
        }
        bodyString.append("<hr><br>Skins in case: <br>");
        for (SkinInCase skinInCase : findedCase.getCaseSkins()) {
            bodyString.append("<img src=\"" + STEAM_ITEM_IMAGE_URL + skinInCase.getSkinCase().getIconUrl() + "\">");
        }
        return bodyString.toString();
    }
}
