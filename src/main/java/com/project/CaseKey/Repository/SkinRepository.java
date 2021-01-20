package com.project.CaseKey.Repository;

import com.project.CaseKey.Model.Skin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SkinRepository extends JpaRepository<Skin, String> {
    Skin findSkinByHashName(String name);
    List<Skin> findSkinBySkinName(String name);
}
