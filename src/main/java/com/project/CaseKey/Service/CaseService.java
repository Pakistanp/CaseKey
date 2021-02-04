package com.project.CaseKey.Service;

import com.google.inject.internal.util.$ImmutableSet;
import com.project.CaseKey.Model.Case;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Model.SkinInCase;
import com.project.CaseKey.Model.User;
import com.project.CaseKey.Repository.CaseRepository;
import com.project.CaseKey.Repository.InventoryRepository;
import com.project.CaseKey.Repository.SkinInCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class CaseService {

    private final int ALL_TICKETS = 10000;

    @Autowired
    CaseRepository caseRepository;
    @Autowired
    SkinInCaseRepository skinInCaseRepository;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    UserService userService;
    @Autowired
    SkinService skinService;

    public Case getCase(int caseId) {
        return caseRepository.findCaseById(caseId);
    }

    public Skin openCase(Case openCase, User user) {
        if (checkUserBalance(openCase, user)) {
            userService.updateUserBalance(user, user.getBalance() - openCase.getCost());
            HashMap<Skin, int[]> skinWithTickets = prepareTicketsEachSkin(openCase);
            Skin skin = drawSkin(skinWithTickets);
            giveSkin(user, skin);
            return skin;
        }
        else {
            return new Skin();
        }
    }

    private boolean checkUserBalance(Case openCase, User user) {
        return openCase.getCost() <= user.getBalance();
    }

    private void giveSkin(User user, Skin skin) {
        inventoryService.giveVirtualSkin(user, skin);
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

    public void createCase(List<Skin> skins, List<String> chances, String name) {
        Case newCase = new Case();
        Set<SkinInCase> skinsInCase = prepareSkinsInCase(skins, chances, newCase);
        newCase = prepareCase(skinsInCase, name);

        Case savedCase = caseRepository.save(newCase);
        skinsInCase = prepareSkinsInCase(skins, chances, savedCase);
        skinInCaseRepository.saveAll(skinsInCase);
    }

    public List<Skin> getAllSkins() {
        List<Skin> skins = skinService.getAllSkins();
        return skinService.sortByPrice(skins);
    }

    public List<Skin> getSkinsFromList(List<Skin> skins, List<String> skinsHashCode) {
        return skinService.getSkinsFromList(skins, skinsHashCode);
    }

    private Double priceToDouble(String price) {
        String withoutDolarSign = price.replace("$","");
        return Double.parseDouble(withoutDolarSign.replace(",",""));
    }

    public boolean verifySkinsChances(List<String> chances) {
        double sum = 0;
        for (String chance : chances) {
            sum += Double.parseDouble(chance);
        }
        return sum == 100.0;
    }

    private Case prepareCase(Set<SkinInCase> skinsInCase, String name) {
        Case newCase = new Case();
        newCase.setName(name);
        newCase.setCost(calculateCasePrice(skinsInCase));
        newCase.setCaseSkins(skinsInCase);
        return newCase;
    }

    private Set<SkinInCase> prepareSkinsInCase(List<Skin> skins, List<String> chances, Case newCase){
        List<SkinInCase> skinsInCase = new ArrayList<>();
        for (Skin skin : skins) {
            SkinInCase skinInCase = new SkinInCase();
            skinInCase.setDropChance(Integer.parseInt(chances.get(skins.indexOf(skin))));
            skinInCase.setSkinCase(skin);
            skinInCase.setCaseSkin(newCase);
            skinsInCase.add(skinInCase);
        }
        return Set.copyOf(skinsInCase);
    }

    private Double calculateCasePrice(Set<SkinInCase> skinsInCase) {
        double price = 0;
        for (SkinInCase skin : skinsInCase) {
            price += priceToDouble(skin.getSkinCase().getPrice()) * skin.getDropChance() / 100.0;
        }
        return Double.parseDouble(String.format("%,.2f", price));
    }
}
