package com.project.CaseKey.JsonModel;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
public class UserInfo {

    @Getter @Setter
    @SerializedName("steamid")
    private String steamId;
    @Getter @Setter
    @SerializedName("communityvisibilitystate")
    private String communityVisibilityState;
    @Getter @Setter
    @SerializedName("profilestate")
    private String profileState;
    @Getter @Setter
    @SerializedName("personaname")
    private String personaName;
    @Getter @Setter
    @SerializedName("commentpermission")
    private String commentPermission;
    @Getter @Setter
    @SerializedName("profileurl")
    private String profileUrl;
    @Getter @Setter
    private String avatar;
    @Getter @Setter
    @SerializedName("avatarmedium")
    private String avatarMedium;
    @Getter @Setter
    @SerializedName("avatarfull")
    private String avatarFull;
    @Getter @Setter
    @SerializedName("avatarhash")
    private String avatarHash;
    @Getter @Setter
    @SerializedName("lastlogoff")
    private String lastLogoff;
    @Getter @Setter
    @SerializedName("personastate")
    private String personaState;
    @Getter @Setter
    @SerializedName("primaryclanid")
    private String primaryClanId;
    @Getter @Setter
    @SerializedName("timecreated")
    private String timeCreated;
    @Getter @Setter
    @SerializedName("personastateflags")
    private String personaStateFlags;
    @Getter @Setter
    @SerializedName("loccountrycode")
    private String locCountryCode;
    @Getter @Setter
    @SerializedName("locstatecode")
    private String locStateCode;

}
