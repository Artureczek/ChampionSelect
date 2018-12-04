package com.solodive.championselect.domain.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * The ChampionResourceType enumeration.
 */
public enum ChampionResourceType {

    HEALTH("HP"),
    MANA("MP"),
    ENERGY("Energy"),
    BATTLEFURY("Battlefury");

    private static class ChampionResourceTypeHolder {
        static Map<String, ChampionResourceType> MAP = new HashMap<>();
    }

    private String value;

    ChampionResourceType(String value) {
        ChampionResourceTypeHolder.MAP.put(value, this);
        this.value = value;
    }

    public static ChampionResourceType getType(String value) {
        return ChampionResourceTypeHolder.MAP.get(value);
    }

    public String getValue() {
        return value;
    }

}
