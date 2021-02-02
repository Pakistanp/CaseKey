package com.project.CaseKey;

import com.project.CaseKey.JsonModel.UserInfo;
import com.project.CaseKey.Model.*;

import java.util.List;

public class TemporaryView {

    private final String STEAM_ITEM_IMAGE_URL = "https://steamcommunity-a.akamaihd.net/economy/image/";

    private Double priceToDouble(String price) {
        String noDolarSign = price.replace("$","");
        return Double.parseDouble(noDolarSign.replace(",",""));
    }

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
    public String createViewForInventory(User user, List<InventoryItem> inventoryItems) {
        StringBuilder bodyString = new StringBuilder();
        if( user != null ) {
            bodyString.append("Inventory " + user.getName());
            bodyString.append(" Balance: " + String.format("%,.2f", user.getBalance()));
            bodyString.append("<br><a href=\"logout\">Logout</a>");
            bodyString.append("<a href=\"/inventory/steam\">Steam Inventory</a>");
            bodyString.append("<br><hr><br><br>");
            for (InventoryItem item : inventoryItems) {
                bodyString.append("<div style=\"width: 154px; height: 115px; background-image: url('" + STEAM_ITEM_IMAGE_URL + item.getInventorySkin().getIconUrl() + "'); background-size: 154px 115px; display:inline-block; position: relative\" />");
                bodyString.append("<a href=\"/inventory/" + item.getId() + "\"><button>Sell for " + item.getInventorySkin().getPrice() + "</button></a>");
                bodyString.append("<a href=\"/upgrade/" + item.getId() + "\"><button>Upgrade</button></a>");
                bodyString.append("<div style=\"position: absolute; top: 4px; right: 4px;\">x" + item.getCount() + "</div></div>");
            }
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
            bodyString.append("<br><a href=\"/case/" + findedCase.getId() + "/open\"><button>Open for " + findedCase.getCost().toString() +  "</button></a>");
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

    public String createViewForOpenedCase(Case openedCase, User user, Skin wonSkin) {
        StringBuilder bodyString = new StringBuilder();
        if (wonSkin.getHashName() == null) {
            bodyString.append("You don't have enough dup to open this case.<br>Deposit more dup.");
        }
        else {
            bodyString.append("You won: " + wonSkin.getHashName() + "<br>");
        }
        bodyString.append(openedCase.getName());
        if (user != null) {
            bodyString.append("<br><a href=\"/case/" + openedCase.getId() + "/open\"><button>Open for " + openedCase.getCost().toString() +  "</button></a>");
        }
        else {
            bodyString.append("<br><a href=\"/login\"><button>Login to open a case</button></a>");
        }
        bodyString.append("<hr><br>Skins in case: <br>");
        for (SkinInCase skinInCase : openedCase.getCaseSkins()) {
            bodyString.append("<img src=\"" + STEAM_ITEM_IMAGE_URL + skinInCase.getSkinCase().getIconUrl() + "\">");
        }
        return bodyString.toString();
    }

    public String createViewForUpgradeItem(User user, InventoryItem item, List<Skin> skins) {
        StringBuilder bodyString = new StringBuilder();
        if( user != null ) {
            bodyString.append("<h3>Upgrader</h3><br>");
            bodyString.append("<div style=\"width: 154px; height: 115px; background-image: url('" + STEAM_ITEM_IMAGE_URL + item.getInventorySkin().getIconUrl() + "'); background-size: 154px 115px; display:inline-block; position: relative\" /></div><hr><br>");

            for (int i = 0; i < 300; i++) {
                double chance = priceToDouble(item.getInventorySkin().getPrice()) * 100.00 / priceToDouble(skins.get(i).getPrice());
                if (chance <= 70.0) {
                    bodyString.append("<div style=\"border-radius: 25px;\n" +
                            "  border: 2px solid;\n" +
                            " background: #A9A9A9; " +
                            "width: 154px; height: 115px; background-image: url('" + STEAM_ITEM_IMAGE_URL + skins.get(i).getIconUrl() + "'); background-size: 154px 115px; display:inline-block; position: relative\" />");
                    bodyString.append("<a style=\"padding-left: 10px\" href=\"/upgrade/" + item.getId() + "/" + skins.get(i).getHashName() + "\"><button>Upgrade to " + skins.get(i).getPrice() + "</button></a>");
                    bodyString.append("<div style=\"position: absolute; top: 20px; right: 4px;\">" + String.format("%,.2f", chance) + "%</div>");
                    bodyString.append("<div style=\"position: absolute; bottom: 4px; left: 4px; color: white;\">" + skins.get(i).getHashName() + "</div></div>");
                }
            }
        }
        return bodyString.toString();
    }

    public String createViewForCaseCreator() {
        StringBuilder bodyString = new StringBuilder();
        bodyString.append("<form action=\"/createCase\" method=\"post\">");
        bodyString.append("<input type=\"range\" min=\"1\" max=\"100\" value=\"1\" oninput=\"this.nextElementSibling.value = this.value\"><output></output>%</form>");
        return bodyString.toString();
    }
}
