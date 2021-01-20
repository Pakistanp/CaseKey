package com.project.CaseKey.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private double balance;
    @Getter @Setter
    private String avatarUrl;
    @Getter @Setter
    private boolean canOpenCase;
    @OneToMany(mappedBy = "user")
    @Getter @Setter
    private Set<InventoryItem> inventoryItems;
}
