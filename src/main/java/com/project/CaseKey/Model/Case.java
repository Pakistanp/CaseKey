package com.project.CaseKey.Model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cases")
public class Case {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    @NonNull
    @Getter@Setter
    private String name;
    @NonNull
    @Getter@Setter
    private Double cost;
    @Getter@Setter
    @OneToMany(mappedBy="caseSkin")
    private Set<SkinInCase> caseSkins;


    public Case () {}

    public Case(String name,Double cost, Set<SkinInCase> caseSkins) {
        this.name = name;
        this.cost = cost;
        this.caseSkins = caseSkins;
    }
}
