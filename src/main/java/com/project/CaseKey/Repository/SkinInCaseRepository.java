package com.project.CaseKey.Repository;

import com.project.CaseKey.Model.SkinInCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinInCaseRepository extends JpaRepository<SkinInCase, String> {
}
