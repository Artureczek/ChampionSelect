package com.solodive.championselect.domain.enumeration;

import com.solodive.championselect.service.exception.ChampionTagUnknownValueException;

import java.util.HashMap;
import java.util.Map;

/**
 * The ChampionTagValue enumeration.
 */
public enum ChampionTagValue {

    FIGHTER("Fighter"),
    TANK("Tank"),
    MAGE("Mage"),
    ASSASSIN("Assassin");

    private static class ChampionTagValueHolder {
        static Map<String, ChampionTagValue> MAP = new HashMap<>();
    }

    private String value;

    ChampionTagValue(String value) {
        ChampionTagValue.ChampionTagValueHolder.MAP.put(value, this);
        this.value = value;
    }

    public static ChampionTagValue getType(String value) {
        ChampionTagValue type = ChampionTagValue.ChampionTagValueHolder.MAP.get(value);
        if (type == null)
            throw new ChampionTagUnknownValueException("Could not map: " + value + " to any known ChampionTagValue type!");
        return type;
    }

    public static ChampionTagValue getTypeIgnoreException(String value) {
        return ChampionTagValue.ChampionTagValueHolder.MAP.get(value);
    }

    public String getValue() {
        return value;
    }

}
