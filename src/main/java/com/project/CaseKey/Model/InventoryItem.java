package com.project.CaseKey.Model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inventoryitems")
public class InventoryItem implements Serializable {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    @NonNull
    @Getter @Setter
    private String userId;
    @ManyToOne
    @JoinColumn(name="skinHashName", nullable=false)
    private Skin skin;
    @Getter @Setter
    private int count;
    @NonNull
    @Getter @Setter
    private String type;

    public InventoryItem() {}

    public InventoryItem(String userId, Skin skin, int count, String type) {
        this.userId = userId;
        this.skin = skin;
        this.count = count;
        this.type = type;
    }
}
