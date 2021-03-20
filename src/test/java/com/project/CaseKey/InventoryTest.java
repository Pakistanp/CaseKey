package com.project.CaseKey;

import com.project.CaseKey.Service.InventoryService;
import com.project.CaseKey.Service.SkinService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;

@SpringBootTest(classes = {InventoryService.class, SkinService.class})
public class InventoryTest {
    @Autowired
    private InventoryService inventoryService;
    @Test
    void getUserInformation() {
        JSONObject jsonObjectMock = new JSONObject(
        "{\"assets\":[{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"20398843805\",\"classid\":\"3213411179\",\"instanceid\":\"0\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"20333575991\",\"classid\":\"3946324730\",\"instanceid\":\"0\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"20268689125\",\"classid\":\"3946324730\",\"instanceid\":\"0\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"20205538126\",\"classid\":\"3213411179\",\"instanceid\":\"0\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"20067980250\",\"classid\":\"3946324730\",\"instanceid\":\"0\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"20067719765\",\"classid\":\"1989300316\",\"instanceid\":\"302028390\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"19946934320\",\"classid\":\"3213411179\",\"instanceid\":\"0\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"19909223788\",\"classid\":\"3946324730\",\"instanceid\":\"0\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"19863059722\",\"classid\":\"2727227113\",\"instanceid\":\"0\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"19813520643\",\"classid\":\"3761545285\",\"instanceid\":\"0\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"19683561065\",\"classid\":\"3761545285\",\"instanceid\":\"0\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"19664511678\",\"classid\":\"1989287716\",\"instanceid\":\"302028390\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"19644973336\",\"classid\":\"3761545285\",\"instanceid\":\"0\",\"amount\":\"1\"},{\"appid\":730,\"contextid\":\"2\",\"assetid\":\"19608143511\",\"classid\":\"1989284449\",\"instanceid\":\"302028390\",\"amount\":\"1\"}],\"descriptions\":[{\"appid\":730,\"classid\":\"3213411179\",\"instanceid\":\"0\",\"currency\":0,\"background_color\":\"\",\"icon_url\":\"-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXU5A1PIYQNqhpOSV-fRPasw8rsUFJ5KBFZv668FFUynfWaI25G6Ijkl9iPw_SnNrjXw2oBu8cj3b2Qo4_33QbnrUdlYD37ddCLMlhpvs0XIz0\",\"descriptions\":[{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"Container Series #274\",\"color\":\"99ccff\"},{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"Contains one of the following:\"},{\"type\":\"html\",\"value\":\"FAMAS | Crypsis\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"AK-47 | Uncharted\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"MAC-10 | Whitefish\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"Galil AR | Akoben\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"MP7 | Mischief\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"P250 | Verdigris\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"P90 | Off World\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"AWP | Atheris\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"Tec-9 | Bamboozle\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"Desert Eagle | Light Rail\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"MP5-SD | Gauss\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"UMP-45 | Moonrise\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"R8 Revolver | Skull Crusher\",\"color\":\"d32ce6\"},{\"type\":\"html\",\"value\":\"AUG | Momentum\",\"color\":\"d32ce6\"},{\"type\":\"html\",\"value\":\"XM1014 | Incinegator\",\"color\":\"d32ce6\"},{\"type\":\"html\",\"value\":\"Five-SeveN | Angry Mob\",\"color\":\"eb4b4b\"},{\"type\":\"html\",\"value\":\"M4A4 | The Emperor\",\"color\":\"eb4b4b\"},{\"type\":\"html\",\"value\":\"or an Exceedingly Rare Special Item!\",\"color\":\"ffd700\"},{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"\",\"color\":\"00a000\"}],\"tradable\":1,\"name\":\"Prisma Case\",\"name_color\":\"D2D2D2\",\"type\":\"Base Grade Container\",\"market_name\":\"Prisma Case\",\"market_hash_name\":\"Prisma Case\",\"commodity\":1,\"market_tradable_restriction\":7,\"marketable\":1,\"tags\":[{\"category\":\"Type\",\"internal_name\":\"CSGO_Type_WeaponCase\",\"localized_category_name\":\"Type\",\"localized_tag_name\":\"Container\"},{\"category\":\"ItemSet\",\"internal_name\":\"set_community_22\",\"localized_category_name\":\"Collection\",\"localized_tag_name\":\"The Prisma Collection\"},{\"category\":\"Quality\",\"internal_name\":\"normal\",\"localized_category_name\":\"Category\",\"localized_tag_name\":\"Normal\"},{\"category\":\"Rarity\",\"internal_name\":\"Rarity_Common\",\"localized_category_name\":\"Quality\",\"localized_tag_name\":\"Base Grade\",\"color\":\"b0c3d9\"}],\"market_buy_country_restriction\":\"FR\"},{\"appid\":730,\"classid\":\"3946324730\",\"instanceid\":\"0\",\"currency\":0,\"background_color\":\"\",\"icon_url\":\"-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXU5A1PIYQNqhpOSV-fRPasw8rsUFJ5KBFZv668FFU2nfGaJG0btN2wwYHfxa-hY-uFxj4Dv50nj7uXpI7w3AewrhBpMWH6d9CLMlhpEbAe-Zk\",\"descriptions\":[{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"Container Series #307\",\"color\":\"99ccff\"},{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"Contains one of the following:\"},{\"type\":\"html\",\"value\":\"Negev | Ultralight\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"P2000 | Gnarled\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"SG 553 | Ol' Rusty\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"SSG 08 | Mainframe 001\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"P250 | Cassette\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"P90 | Freight\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"PP-Bizon | Runic\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"MAG-7 | Monster Call\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"Tec-9 | Brother\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"MAC-10 | Allure\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"Galil AR | Connexion\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"MP5-SD | Kitbash\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"M4A4 | Tooth Fairy\",\"color\":\"d32ce6\"},{\"type\":\"html\",\"value\":\"Glock-18 | Vogue\",\"color\":\"d32ce6\"},{\"type\":\"html\",\"value\":\"XM1014 | Entombed\",\"color\":\"d32ce6\"},{\"type\":\"html\",\"value\":\"Desert Eagle | Printstream\",\"color\":\"eb4b4b\"},{\"type\":\"html\",\"value\":\"AK-47 | Legion of Anubis\",\"color\":\"eb4b4b\"},{\"type\":\"html\",\"value\":\"or an Exceedingly Rare Special Item!\",\"color\":\"ffd700\"},{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"\",\"color\":\"00a000\"}],\"tradable\":1,\"name\":\"Fracture Case\",\"name_color\":\"D2D2D2\",\"type\":\"Base Grade Container\",\"market_name\":\"Fracture Case\",\"market_hash_name\":\"Fracture Case\",\"commodity\":1,\"market_tradable_restriction\":7,\"marketable\":1,\"tags\":[{\"category\":\"Type\",\"internal_name\":\"CSGO_Type_WeaponCase\",\"localized_category_name\":\"Type\",\"localized_tag_name\":\"Container\"},{\"category\":\"ItemSet\",\"internal_name\":\"set_community_26\",\"localized_category_name\":\"Collection\",\"localized_tag_name\":\"The Fracture Collection\"},{\"category\":\"Quality\",\"internal_name\":\"normal\",\"localized_category_name\":\"Category\",\"localized_tag_name\":\"Normal\"},{\"category\":\"Rarity\",\"internal_name\":\"Rarity_Common\",\"localized_category_name\":\"Quality\",\"localized_tag_name\":\"Base Grade\",\"color\":\"b0c3d9\"}],\"market_buy_country_restriction\":\"FR\"},{\"appid\":730,\"classid\":\"1989300316\",\"instanceid\":\"302028390\",\"currency\":0,\"background_color\":\"\",\"icon_url\":\"IzMF03bi9WpSBq-S-ekoE33L-iLqGFHVaU25ZzQNQcXdB2ozio1RrlIWFK3UfvMYB8UsvjiMXojflsZalyxSh31CIyHz2GZ-KuFpPsrTzBG0pveDD3n4YzKKdieBTAhpH7ZbMD3R_Gfx5L6cFDrNQu8uEgFRe_NXoDAfPcuAPRM80ZlLpWL-lEtxEQQlZ8lSeR-30ylFM7xwynNj_-DmVA\",\"icon_url_large\":\"IzMF03bi9WpSBq-S-ekoE33L-iLqGFHVaU25ZzQNQcXdB2ozio1RrlIWFK3UfvMYB8UsvjiMXojflsZalyxSh31CIyHz2GZ-KuFpPsrTzBG0pveDD3n4YzL7IyDLG1smGedeNWGP-Gfx5bjCQ2rMQrksEQgMf_AAp2dBbJuPahFp1oJe_z25lAptEBFuccpKfx2233gHOK0p0XxEJ8pXyyaAltL43A\",\"descriptions\":[{\"type\":\"html\",\"value\":\"This is a sealed container of a graffiti pattern. Once this graffiti pattern is unsealed, it will provide you with enough charges to apply the graffiti pattern <b>50</b> times to the in-game world.\"},{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"\",\"color\":\"00a000\"}],\"tradable\":1,\"actions\":[{\"link\":\"steam://rungame/730/76561202255233023/+csgo_econ_action_preview%20S%owner_steamid%A%assetid%D3318134269750431072\",\"name\":\"Inspect in Game...\"}],\"name\":\"Sealed Graffiti | Quickdraw (Wire Blue)\",\"name_color\":\"D2D2D2\",\"type\":\"Base Grade Graffiti\",\"market_name\":\"Sealed Graffiti | Quickdraw (Wire Blue)\",\"market_hash_name\":\"Sealed Graffiti | Quickdraw (Wire Blue)\",\"market_actions\":[{\"link\":\"steam://rungame/730/76561202255233023/+csgo_econ_action_preview%20M%listingid%A%assetid%D3318134269750431072\",\"name\":\"Inspect in Game...\"}],\"commodity\":1,\"market_tradable_restriction\":7,\"marketable\":1,\"tags\":[{\"category\":\"Type\",\"internal_name\":\"CSGO_Type_Spray\",\"localized_category_name\":\"Type\",\"localized_tag_name\":\"Graffiti\"},{\"category\":\"Quality\",\"internal_name\":\"normal\",\"localized_category_name\":\"Category\",\"localized_tag_name\":\"Normal\"},{\"category\":\"Rarity\",\"internal_name\":\"Rarity_Common\",\"localized_category_name\":\"Quality\",\"localized_tag_name\":\"Base Grade\",\"color\":\"b0c3d9\"},{\"category\":\"SprayColorCategory\",\"internal_name\":\"Tint11\",\"localized_category_name\":\"Graffiti Color\",\"localized_tag_name\":\"Wire Blue\"}]},{\"appid\":730,\"classid\":\"2727227113\",\"instanceid\":\"0\",\"currency\":0,\"background_color\":\"\",\"icon_url\":\"-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXU5A1PIYQNqhpOSV-fRPasw8rsUFJ5KBFZv668FFY5naqQIz4R7Yjix9bZkvKiZrmAzzlTu5AoibiT8d_x21Wy8hY_MWz1doSLMlhpM3FKbNs\",\"descriptions\":[{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"Container Series #238\",\"color\":\"99ccff\"},{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"Contains one of the following:\"},{\"type\":\"html\",\"value\":\"PP-Bizon | Night Riot\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"Five-SeveN | Flame Test\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"MP9 | Black Sand\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"P2000 | Urban Hazard\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"R8 Revolver | Grip\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"SG 553 | Aloha\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"XM1014 | Oxide Blaze\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"Glock-18 | Moonrise\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"Negev | Lionfish\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"Nova | Wild Six\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"MAG-7 | SWAG-7\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"UMP-45 | Arctic Wolf\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"AUG | Stymphalian\",\"color\":\"d32ce6\"},{\"type\":\"html\",\"value\":\"AWP | Mortis\",\"color\":\"d32ce6\"},{\"type\":\"html\",\"value\":\"USP-S | Cortex\",\"color\":\"d32ce6\"},{\"type\":\"html\",\"value\":\"M4A4 | Neo-Noir\",\"color\":\"eb4b4b\"},{\"type\":\"html\",\"value\":\"MP7 | Bloodsport\",\"color\":\"eb4b4b\"},{\"type\":\"html\",\"value\":\"or the Exceedingly Rare Gloves!\",\"color\":\"ffd700\"},{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"\",\"color\":\"00a000\"}],\"tradable\":1,\"name\":\"Clutch Case\",\"name_color\":\"D2D2D2\",\"type\":\"Base Grade Container\",\"market_name\":\"Clutch Case\",\"market_hash_name\":\"Clutch Case\",\"commodity\":1,\"market_tradable_restriction\":7,\"marketable\":1,\"tags\":[{\"category\":\"Type\",\"internal_name\":\"CSGO_Type_WeaponCase\",\"localized_category_name\":\"Type\",\"localized_tag_name\":\"Container\"},{\"category\":\"ItemSet\",\"internal_name\":\"set_community_19\",\"localized_category_name\":\"Collection\",\"localized_tag_name\":\"The Clutch Collection\"},{\"category\":\"Quality\",\"internal_name\":\"normal\",\"localized_category_name\":\"Category\",\"localized_tag_name\":\"Normal\"},{\"category\":\"Rarity\",\"internal_name\":\"Rarity_Common\",\"localized_category_name\":\"Quality\",\"localized_tag_name\":\"Base Grade\",\"color\":\"b0c3d9\"}],\"market_buy_country_restriction\":\"FR\"},{\"appid\":730,\"classid\":\"3761545285\",\"instanceid\":\"0\",\"currency\":0,\"background_color\":\"\",\"icon_url\":\"-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXU5A1PIYQNqhpOSV-fRPasw8rsUFJ5KBFZv668FFU1nfbOIj8W7oWzkYLdlPOsMOmIk2kGscAj2erE99Sn2AGw_0M4NW2hIYOLMlhpcmY0CRM\",\"descriptions\":[{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"Container Series #303\",\"color\":\"99ccff\"},{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"Contains one of the following:\"},{\"type\":\"html\",\"value\":\"AUG | Tom Cat\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"AWP | Capillary\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"CZ75-Auto | Distressed\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"Desert Eagle | Blue Ply\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"MP5-SD | Desert Strike\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"Negev | Prototype\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"R8 Revolver | Bone Forged\",\"color\":\"4b69ff\"},{\"type\":\"html\",\"value\":\"P2000 | Acid Etched\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"Sawed-Off | Apocalypto\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"SCAR-20 | Enforcer\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"SG 553 | Darkwing\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"SSG 08 | Fever Dream\",\"color\":\"8847ff\"},{\"type\":\"html\",\"value\":\"AK-47 | Phantom Disruptor\",\"color\":\"d32ce6\"},{\"type\":\"html\",\"value\":\"MAC-10 | Disco Tech\",\"color\":\"d32ce6\"},{\"type\":\"html\",\"value\":\"MAG-7 | Justice\",\"color\":\"d32ce6\"},{\"type\":\"html\",\"value\":\"M4A1-S | Player Two\",\"color\":\"eb4b4b\"},{\"type\":\"html\",\"value\":\"Glock-18 | Bullet Queen\",\"color\":\"eb4b4b\"},{\"type\":\"html\",\"value\":\"or an Exceedingly Rare Special Item!\",\"color\":\"ffd700\"},{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"\",\"color\":\"00a000\"}],\"tradable\":1,\"name\":\"Prisma 2 Case\",\"name_color\":\"D2D2D2\",\"type\":\"Base Grade Container\",\"market_name\":\"Prisma 2 Case\",\"market_hash_name\":\"Prisma 2 Case\",\"commodity\":1,\"market_tradable_restriction\":7,\"marketable\":1,\"tags\":[{\"category\":\"Type\",\"internal_name\":\"CSGO_Type_WeaponCase\",\"localized_category_name\":\"Type\",\"localized_tag_name\":\"Container\"},{\"category\":\"ItemSet\",\"internal_name\":\"set_community_25\",\"localized_category_name\":\"Collection\",\"localized_tag_name\":\"The Prisma 2 Collection\"},{\"category\":\"Quality\",\"internal_name\":\"normal\",\"localized_category_name\":\"Category\",\"localized_tag_name\":\"Normal\"},{\"category\":\"Rarity\",\"internal_name\":\"Rarity_Common\",\"localized_category_name\":\"Quality\",\"localized_tag_name\":\"Base Grade\",\"color\":\"b0c3d9\"}],\"market_buy_country_restriction\":\"FR\"},{\"appid\":730,\"classid\":\"1989287716\",\"instanceid\":\"302028390\",\"currency\":0,\"background_color\":\"\",\"icon_url\":\"IzMF03bi9WpSBq-S-ekoE33L-iLqGFHVaU25ZzQNQcXdB2ozio1RrlIWFK3UfvMYB8UsvjiMXojflsZalyxSh31CIyHz2GZ-KuFpPsrTzBG0puWyTCW5aWOSKXaATwlpTeBcN2iI9zWl4-TCETrBEuAvRFtSKKYGo2dMa8uAbQx9itAdqGq0mFZwCxo8e9VKaVK6zC1BNbtiLNsyJA\",\"icon_url_large\":\"IzMF03bi9WpSBq-S-ekoE33L-iLqGFHVaU25ZzQNQcXdB2ozio1RrlIWFK3UfvMYB8UsvjiMXojflsZalyxSh31CIyHz2GZ-KuFpPsrTzBG0puWyTCXIZDbWKCSXT1oxSLteYTrbqzvx4-mUSzzIEugvEQgDL6MFoGVIO8iMbBNsgIIC8iuomUM7HRkkfddLZQOvw2QfKOAkznhAIc2xskh5Yg\",\"descriptions\":[{\"type\":\"html\",\"value\":\"This is a sealed container of a graffiti pattern. Once this graffiti pattern is unsealed, it will provide you with enough charges to apply the graffiti pattern <b>50</b> times to the in-game world.\"},{\"type\":\"html\",\"value\":\" \"},{\"type\":\"html\",\"value\":\"\",\"color\":\"00a000\"}],\"tradable\":1,\"actions\":[{\"link\":\"steam://rungame/730/76561202255233023/+csgo_econ_action_preview%20S%owner_steamid%A%assetid%D4938376413689851694\",\"name\":\"Inspect in Game...\"}],\"name\":\"Sealed Graffiti | GGEZ (Violent Violet)\",\"name_color\":\"D2D2D2\",\"type\":\"Base Grade Graffiti\",\"market_name\":\"Sealed Graffiti | GGEZ (Violent Violet)\",\"market_hash_name\":\"Sealed Graffiti | GGEZ (Violent Violet)\",\"market_actions\":[{\"link\":\"steam://rungame/730/76561202255233023/+csgo_econ_action_preview%20M%listingid%A%assetid%D4938376413689851694\",\"name\":\"Inspect in Game...\"}],\"commodity\":1,\"market_tradable_restriction\":7,\"marketable\":1,\"tags\":[{\"category\":\"Type\",\"internal_name\":\"CSGO_Type_Spray\",\"localized_category_name\":\"Type\",\"localized_tag_name\":\"Graffiti\"},{\"category\":\"Quality\",\"internal_name\":\"normal\",\"localized_category_name\":\"Category\",\"localized_tag_name\":\"Normal\"},{\"category\":\"Rarity\",\"internal_name\":\"Rarity_Common\",\"localized_category_name\":\"Quality\",\"localized_tag_name\":\"Base Grade\",\"color\":\"b0c3d9\"},{\"category\":\"SprayColorCategory\",\"internal_name\":\"Tint14\",\"localized_category_name\":\"Graffiti Color\",\"localized_tag_name\":\"Violent Violet\"}]},]}],\"total_inventory_count\":498,\"success\":1,\"rwgrsn\":-2}"
        );
        HashMap<String, Integer> hashNamesWithCount = inventoryService.getHashNamesWithCount(jsonObjectMock);
        //Assertions.assertEquals("Z-Boku", userInfo.getPersonaName());
    }
}