package com.project.CaseKey.Repository;

import com.project.CaseKey.Model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, Integer> {
    Case findCaseById(int caseId);
}
