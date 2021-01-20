package com.project.CaseKey.Service;

import com.project.CaseKey.Model.Case;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.SkinInCase;
import com.project.CaseKey.Model.User;
import com.project.CaseKey.Repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class CaseService {

    private final int ALL_TICKETS = 10000;

    @Autowired
    CaseRepository caseRepository;

    public Case getCase(int caseId) {
        return caseRepository.findCaseById(caseId);
    }

    public void openCase(Case openCase, User user) {
        HashMap<Skin, int[]> skinWithTickets = prepareTicketsEachSkin(openCase);
        drawSkin(skinWithTickets);
    }

    private HashMap<Skin,int[]> prepareTicketsEachSkin(Case openCase) {
        HashMap<Skin, int[]> skinWithTickets = new HashMap<>();
        Set<SkinInCase> skinsInCase = openCase.getCaseSkins();
        int sumOfTickets = 0;
        for (SkinInCase skin : skinsInCase) {
            int[] section = new int[2];
            section[0] = sumOfTickets;
            int ticket = skin.getDropChance() * ALL_TICKETS / 100;
            sumOfTickets += ticket;
            section[1] = sumOfTickets;
            skinWithTickets.put(skin.getSkinCase(), section);
        }
        return skinWithTickets;
    }

    private Skin drawSkin(HashMap<Skin, int[]> skinWithTickets) {
        int winningTicket = drawTicket();
        return getWinningSkin(skinWithTickets, winningTicket);
    }

    private int drawTicket() {
        Random random = new Random();
        return random.nextInt(ALL_TICKETS) + 1;
    }

    private Skin getWinningSkin(HashMap<Skin, int[]> skinsWithTickets, int winningTicket) {
        Skin winningSkin = new Skin();
        for (Map.Entry<Skin, int[]> skinWithTicket : skinsWithTickets.entrySet()) {
            if(skinWithTicket.getValue()[0] < winningTicket && skinWithTicket.getValue()[1] >= winningTicket) {
                winningSkin = skinWithTicket.getKey();
                break;
            }
        }
        return winningSkin;
    }
}
