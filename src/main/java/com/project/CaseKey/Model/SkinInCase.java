package com.project.CaseKey.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "skins_in_cases")
public class SkinInCase {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @ManyToOne
    @JoinColumn(name="case_id")
    @Getter@Setter
    private Case caseSkin;

    @ManyToOne
    @JoinColumn(name="skin_id")
    @Getter@Setter
    private Skin skinCase;

    @Getter@Setter
    private int dropChance;
}
