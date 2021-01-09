package com.project.CaseKey.JsonModel;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
public class SkinInfo {

    @Getter @Setter
    private String name;
    @Getter @Setter
    @SerializedName("hash_name")
    private String hashName;
    @Getter @Setter
    @SerializedName("sell_listings")
    private String sellListings;
    @Getter @Setter
    @SerializedName("sell_price")
    private String sellPrice;
    @Getter @Setter
    @SerializedName("sell_price_text")
    private String sellPriceText;
    @Getter @Setter
    @SerializedName("app_icon")
    private String appIcon;
    @Getter @Setter
    @SerializedName("app_name")
    private String appName;
    @Getter @Setter
    @SerializedName("asset_description")
    private SkinDetails assetDescription;
    @Getter @Setter
    @SerializedName("sale_price_text")
    private String salePriceText;

}
