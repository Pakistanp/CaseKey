package com.project.CaseKey.Service;

import com.project.CaseKey.Model.Case;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.User;
import com.project.CaseKey.Repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseService {

    @Autowired
    CaseRepository caseRepository;

    public Case getCase(int caseId) {
        return caseRepository.findCaseById(caseId);
    }

    public void openCase(int caseId, User user) {

    }

}
