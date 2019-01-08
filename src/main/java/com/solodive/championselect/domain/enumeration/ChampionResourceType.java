package com.solodive.championselect.domain.enumeration;

import com.solodive.championselect.service.exception.ChampionResourceUnknownTypeException;

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
        ChampionResourceType type = ChampionResourceTypeHolder.MAP.get(value);
        if (type == null)
            throw new ChampionResourceUnknownTypeException("Could not map: " + value + " to any known ChampionResourceType type!");
        return type;
    }

    public static ChampionResourceType getTypeIgnoreException(String value) {
        return ChampionResourceTypeHolder.MAP.get(value);
    }

    public String getValue() {
        return value;
    }

}
