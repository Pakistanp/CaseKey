package com.project.CaseKey;

import com.project.CaseKey.JsonModel.UserInfo;
import com.project.CaseKey.Model.*;

import java.util.List;
import java.util.stream.Collectors;

public class TemporaryView {

    private final String STEAM_ITEM_IMAGE_URL = "https://steamcommunity-a.akamaihd.net/economy/image/";

    private Double priceToDouble(String price) {
        String withoutDolarSign = price.replace("$","");
        return Double.parseDouble(withoutDolarSign.replace(",",""));
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

    public String createViewForUpgradeItem(User user, InventoryItem item, List<Skin> skins, int startShowFrom) {
        StringBuilder bodyString = new StringBuilder();
        if( user != null ) {
            bodyString.append("<h3>Upgrader</h3><br>");
            bodyString.append("<a href=\"/inventory/\">Inventory</a><br>");
            bodyString.append("<div style=\"width: 154px; height: 115px; background-image: url('" + STEAM_ITEM_IMAGE_URL + item.getInventorySkin().getIconUrl() + "'); background-size: 154px 115px; display:inline-block; position: relative\" /></div><hr><br>");

            int counter = 0;
            int lastShowned = 0;
            for (int i = startShowFrom; i < skins.size(); i++) {
                double chance = priceToDouble(item.getInventorySkin().getPrice()) * 100.00 / priceToDouble(skins.get(i).getPrice());
                if (chance <= 70.0) {
                    counter++;
                    bodyString.append("<div style=\"border-radius: 25px;\n" +
                            "  border: 2px solid;\n" +
                            " background: #A9A9A9; " +
                            "width: 154px; height: 115px; background-image: url('" + STEAM_ITEM_IMAGE_URL + skins.get(i).getIconUrl() + "'); background-size: 154px 115px; display:inline-block; position: relative\" />");
                    bodyString.append("<a style=\"padding-left: 10px\" href=\"/upgrade/" + item.getId() + "/" + skins.get(i).getHashName() + "\"><button>Upgrade to " + skins.get(i).getPrice() + "</button></a>");
                    bodyString.append("<div style=\"position: absolute; top: 20px; right: 4px;\">" + String.format("%,.2f", chance) + "%</div>");
                    bodyString.append("<div style=\"position: absolute; bottom: 4px; left: 4px; color: white;\">" + skins.get(i).getHashName() + "</div></div>");
                }
                if (counter == 50) {
                    lastShowned = i;
                    break;
                }
            }

            bodyString.append("<a href=\"/upgrade/" + item.getId() + "/page/" + (lastShowned - 1) + "\"><button><-</button></a>");
            bodyString.append("<a href=\"/upgrade/" + item.getId() + "/page/" + (lastShowned + 1) + "\"><button>-></button></a>");
        }
        return bodyString.toString();
    }

    public String createViewForCaseCreator(List<Skin> skins) {
        StringBuilder bodyString = new StringBuilder();
        bodyString.append("<style>" +
                " input:checked + label{" +
                " border-radius: 25px;" +
                " border: 3px solid red;" +
                "}" +
                "label { display: inline-block; width: 158px}" +
                "</style>");
        bodyString.append("<form action=\"/case/creator/chance\" method=\"get\">");
        for (Skin skin : skins) {
            bodyString.append("<input type=\"checkbox\" id=\""+ skin.getHashName() +"\" name=\"checkedSkins\" value =\""+ skin.getHashName() +"\" hidden>");
            bodyString.append("<label for=\""+ skin.getHashName() +"\">");
            bodyString.append("<div style=\"" +
                    " border-radius: 25px;\n" +
                    "  border: 2px solid;\n" +
                    " background: #A9A9A9; " +
                    "width: 154px; height: 115px; background-image: url('" + STEAM_ITEM_IMAGE_URL + skin.getIconUrl() + "'); background-size: 154px 115px; display:inline-block; position: relative\" />");
            bodyString.append("<div style=\"position: absolute; top: 4px; right: 4px;\">" + skin.getPrice() + "</div>");
            bodyString.append("<div style=\"position: absolute; bottom: 4px; left: 4px; color: white;\">" + skin.getHashName() + "</div></div></label>");
        }
        bodyString.append("<input type=\"submit\" value=\"Create\">");
        bodyString.append("</form>");
        return bodyString.toString();
    }

    public String createViewForCaseChanceCreator(List<Skin> skins) {
        StringBuilder bodyString = new StringBuilder();
        bodyString.append("<form action=\"/case/creator/create\" method=\"post\">");
        bodyString.append("<label for=\"caseName\">Let's submit case name</label>");
        bodyString.append("<input id=\"caseName\" type=\"text\" name=\"caseName\"><br>");
        for (Skin skin : skins) {
            bodyString.append("<div style=\"border-radius: 25px;\n" +
                    "  border: 2px solid;\n" +
                    " background: #A9A9A9; " +
                    "width: 154px; height: 115px; background-image: url('" + STEAM_ITEM_IMAGE_URL + skin.getIconUrl() + "'); background-size: 154px 115px; display:block; position: relative\" />");
            bodyString.append("<div style=\"position: absolute; top: 4px; right: 4px;\">" + skin.getPrice() + "</div>");
            bodyString.append("<div style=\"position: absolute; bottom: 4px; left: 4px; color: white;\">" + skin.getHashName() + "</div></div>");
            bodyString.append("<input type=\"range\" min=\"1\" max=\"100\" name=\"chances\" oninput=\"this.nextElementSibling.value = this.value\"><output></output>%");
        }
        bodyString.append("<input type=\"submit\" value=\"Create\">");
        bodyString.append("</form>");
        return bodyString.toString();
    }

    public String createViewForContract(List<InventoryItem> items) {
        StringBuilder bodyString = new StringBuilder();
        bodyString.append("<script type=\"text/javascript\">");
        bodyString.append("$(\"input:checkbox\").click(function() {" +
                "var bol = $(\"input:checkbox:checked\").length >= 2;" +
                "$(\"input:checkbox\").not(\":checked\").attr(\"disabled\",bol);" +
                "});");
        bodyString.append("</script>");
        bodyString.append("<style>" +
                " input:checked + label{" +
                " border-radius: 25px;" +
                " border: 3px solid red;" +
                "}" +
                "label { display: inline-block; width: 158px}" +
                "</style>");

        bodyString.append("<form action=\"/contract/sign\" method=\"post\">");
        for (InventoryItem item : items) {
            bodyString.append("<input type=\"checkbox\" id=\""+ item.getId() +"\" name=\"checkedSkins\" value =\""+ item.getId() +"\" hidden>");
            bodyString.append("<label for=\""+ item.getId() +"\">");
            bodyString.append("<div style=\"" +
                    " border-radius: 25px;\n" +
                    "  border: 2px solid;\n" +
                    " background: #A9A9A9; " +
                    "width: 154px; height: 115px; background-image: url('" + STEAM_ITEM_IMAGE_URL + item.getInventorySkin().getIconUrl() + "'); background-size: 154px 115px; display:inline-block; position: relative\" />");
            bodyString.append("<div style=\"position: absolute; top: 4px; right: 4px;\">" + item.getInventorySkin().getPrice() + "</div>");
            bodyString.append("<div style=\"position: absolute; bottom: 4px; left: 4px; color: white;\">" + item.getInventorySkin().getHashName() + "</div></div></label>");
        }
        bodyString.append("<input type=\"submit\" value=\"SIGN\">");
        bodyString.append("</form>");

        return bodyString.toString();
    }
}
