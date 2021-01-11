package com.project.CaseKey.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    public Skin() {}

    public Skin(String hashName, String skinName, int quality, String price, String iconUrl, LocalDateTime lastUpdate) {
        this.hashName = hashName;
        this.skinName = skinName;
        this.quality = quality;
        this.price = price;
        this.iconUrl = iconUrl;
        this.lastUpdate = lastUpdate;
    }

}
