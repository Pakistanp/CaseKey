package com.project.CaseKey.Service;

import com.google.gson.Gson;
import com.project.CaseKey.JsonModel.SkinInfo;
import com.project.CaseKey.JsonReader;
import com.project.CaseKey.Model.Skin;
import com.project.CaseKey.Repository.SkinRepository;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@PropertySource("classpath:config.properties")
@Service
public class SkinService {

    @Autowired
    private SkinRepository skinRepository;

    private List<Skin> skins = new ArrayList<>();

    private final String GET_ALL_SKINS_INFO = "https://steamcommunity.com/market/search/render/?&sort_dir=desc&appid=730&norender=1&count=100&start=%s#p1_name_asc";
    private final int COUNTS_ITEMS_FOR_CSGO = 15199;//15226;
    private final int PAGE_SIZE = 100;
    private final int COUNT_ONE_UPDATE_ITEMS = 500;

    //QUALITIES
    private final int FACTORY_NEW = 1;
    private final int MINIMAL_WEAR = 2;
    private final int FIELD_TESTED = 3;
    private final int WELL_WORN = 4;
    private final int BATTLE_SCARED = 5;

    private String[] QUALITIES = new String[]{
            "(Factory New)",
            "(Minimal Wear)",
            "(Field-Tested)",
            "(Well-Worn)",
            "(Battle-Scarred)"
        };

    public Skin getSkinByHashName(String skinName) {
        return skinRepository.findSkinByHashName(skinName);
        //List<Skin> skins = skinRepository.findSkinBySkinName(skinName);
    }

    public void updateAllSkinsWithDelay() {
        for (int start = 0; start < COUNTS_ITEMS_FOR_CSGO; start += COUNT_ONE_UPDATE_ITEMS) {
            getAllSkinsInfoFromApi(start, start + COUNT_ONE_UPDATE_ITEMS);
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getAllSkinsInfoFromApi(int start, int limit) {
        for (int i = start; i < limit; i += PAGE_SIZE){
            System.out.println(i);
            String urlToApi = String.format(GET_ALL_SKINS_INFO, String.valueOf(i));
            getSkinsInfoPackFromJson(urlToApi);
        }
    }

    private void getSkinsInfoPackFromJson(String urlToApi) {
        JsonReader reader = new JsonReader();
        JSONObject itemInfoAsJson = reader.readJsonFromUrl(urlToApi);
        List<Skin> skins = mapSkinsInfoFromJson(itemInfoAsJson);
        updateSkinsToDatabase(skins);
    }

    private List<Skin> mapSkinsInfoFromJson (JSONObject skinInfoAsJson) {
        JSONArray skinInfosAsJson = skinInfoAsJson.getJSONArray("results");
        LocalDateTime lastUpdate = LocalDateTime.now();
        for (Object skinInfoJ: skinInfosAsJson) {
            SkinInfo skinInfo = new Gson().fromJson(skinInfoJ.toString(), SkinInfo.class);
            if (skinInfo.getAssetDescription().getTradable().equals("1")) {
                String skinName = getSkinNameFromHashName(skinInfo.getHashName());
                int quality = getSkinQualityFromHashName(skinInfo.getHashName());
                skins.add(new Skin(skinInfo.getHashName(), skinName, quality, skinInfo.getSalePriceText(),
                        skinInfo.getAssetDescription().getIconUrl(), lastUpdate));
            }
        }
        return skins;
    }

    private void updateSkinsToDatabase(List<Skin> skins) {
        List<Skin> skinsWithoutDuplicates = removeDuplicateSkins(skins);
        skinRepository.saveAll(skinsWithoutDuplicates);
    }

    private List<Skin> removeDuplicateSkins(List<Skin> skins) {
        Set<String> uniques = new HashSet<>();
        return skins.stream().filter(skin -> uniques.add(skin.getHashName())).collect(Collectors.toList());
    }

    private String getSkinNameFromHashName(String hashName) {
        for (int quality = 1; quality <= QUALITIES.length; quality++) {
            if (hashName.contains(QUALITIES[quality - 1]))
                return hashName.replace(QUALITIES[quality - 1],"");
        }
        return hashName;
    }
    private int getSkinQualityFromHashName(String hashName) {
        for (int quality = 1; quality <= QUALITIES.length; quality++) {
            if (hashName.contains(QUALITIES[quality - 1]))
                return quality;
        }
        return 0;
    }

    public List<Skin> getAllSkins() {
        return skinRepository.findAll();
    }

    public Skin getSkinByHashNameFromList(List<Skin> allSkins, String skinHashName) {
        Skin skin = new Skin();
        for (Skin searchSkin : allSkins) {
            if(searchSkin.getHashName().equals(skinHashName)) {
                skin = searchSkin;
                break;
            }
        }
        return skin;
    }

    private Double priceToDouble(String price) {
        String withoutDolarSign = price.replace("$","");
        return Double.parseDouble(withoutDolarSign.replace(",",""));
    }

    public List<Skin> sortByPrice(List<Skin> skins) {
        skins.sort(new Comparator<Skin>() {
            @Override
            public int compare(Skin s1, Skin s2) {
                if (s1.getPrice().equals(s2.getPrice())) {
                    return 0;
                }
                else if (priceToDouble(s1.getPrice()) > priceToDouble(s2.getPrice())) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });
        return skins;
    }

    public List<Skin> getSkinsFromList(List<Skin> skins, List<String> skinsHashCode) {
        List<Skin> result = new ArrayList<>();
        for (String skinHashCode : skinsHashCode) {
            for (Skin skin : skins) {
                if (skin.getHashName().equals(skinHashCode)) {
                    result.add(skin);
                    break;
                }
            }
        }
        return result;
    }
}
