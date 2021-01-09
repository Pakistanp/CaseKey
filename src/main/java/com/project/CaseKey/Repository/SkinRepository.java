package com.project.CaseKey.Repository;

import com.project.CaseKey.Model.Skin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkinRepository extends JpaRepository<Skin, String> {
    Skin findSkinByHashName(String name);
    List<Skin> findSkinBySkinName(String name);
}
