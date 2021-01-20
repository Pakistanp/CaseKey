package com.project.CaseKey.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "skins")
public class Skin implements Serializable {

    @Id
    @Column(unique = true)
    @Getter @Setter
    private String hashName;
    @Getter @Setter
    private String skinName;
    @Getter @Setter
    private int quality;
    @Getter @Setter
    private String price;
    @Getter @Setter
    private String iconUrl;
    @Getter @Setter
    private LocalDateTime lastUpdate;
    @OneToMany(mappedBy="inventorySkin")
    @Getter @Setter
    private Set<InventoryItem> userItems;
    @OneToMany(mappedBy="skinCase")
    @Getter @Setter
    private Set<SkinInCase> skinCases;

    public Skin() {}

    public Skin(String hashName, String skinName, int quality, String price, String iconUrl, LocalDateTime lastUpdate, Set<InventoryItem> userItems, Set<SkinInCase> skinCases) {
        this.hashName = hashName;
        this.skinName = skinName;
        this.quality = quality;
        this.price = price;
        this.iconUrl = iconUrl;
        this.lastUpdate = lastUpdate;
        this.userItems = userItems;
        this.skinCases = skinCases;
    }
    public Skin(String hashName, String skinName, int quality, String price, String iconUrl, LocalDateTime lastUpdate) {
        this.hashName = hashName;
        this.skinName = skinName;
        this.quality = quality;
        this.price = price;
        this.iconUrl = iconUrl;
        this.lastUpdate = lastUpdate;
    }

}
