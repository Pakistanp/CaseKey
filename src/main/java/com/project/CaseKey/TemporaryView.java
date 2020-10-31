package com.project.CaseKey;

import com.project.CaseKey.JsonModel.UserInfo;

public class TemporaryView {
    public String createViewForHomePage(Object request, UserInfo userInfo) {
        StringBuilder bodyString = new StringBuilder();
        if( request != null ) {
            bodyString.append("Welcome " + userInfo.getPersonaName());
            bodyString.append("<img src=\"" + userInfo.getAvatarFull() + "\">");
            bodyString.append("<a href=\"logout\">Logout</a>");
        }
        else {
            bodyString.append("<a href=\"login\">Login</a>");
        }
        return bodyString.toString();
    }
}
