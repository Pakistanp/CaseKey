package com.project.CaseKey.Model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "inventoryitems")
public class InventoryItem implements Serializable {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    @ManyToOne
    @JoinColumn(name="userId")
    @Getter @Setter
    private User user;
    @ManyToOne
    @JoinColumn(name="skinHashName", nullable=false)
    @Getter @Setter
    private Skin inventorySkin;
    @Getter @Setter
    private int count;
    @NonNull
    @Getter @Setter
    private String type;

    public InventoryItem() {}

    public InventoryItem(User user, Skin inventorySkin, int count, String type) {
        this.user = user;
        this.inventorySkin = inventorySkin;
        this.count = count;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItem item = (InventoryItem) o;
        return count == item.count &&
                user.equals(item.user) &&
                inventorySkin.equals(item.inventorySkin) &&
                type.equals(item.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, inventorySkin, count, type);
    }
}
