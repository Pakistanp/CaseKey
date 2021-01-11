package com.project.CaseKey.JsonModel;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

public class SkinDetails {

    @Getter @Setter
    @SerializedName("appid")
    private String appId;
    @Getter @Setter
    @SerializedName("classid")
    private String classId;
    @Getter @Setter
    @SerializedName("instanceid")
    private String instanceId;
    @Getter @Setter
    @SerializedName("background_color")
    private String backgroundColor;
    @Getter @Setter
    @SerializedName("icon_url")
    private String iconUrl;
    @Getter @Setter
    private String tradable;
    @Getter @Setter
    private String name;
    @Getter @Setter
    @SerializedName("nameColor")
    private String appid;
    @Getter @Setter
    private String type;
    @Getter @Setter
    @SerializedName("market_name")
    private String marketName;
    @Getter @Setter
    @SerializedName("market_hash_name")
    private String marketHashName;
    @Getter @Setter
    private String commodity;

}