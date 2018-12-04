package com.solodive.championselect.service.exception;

public class ChampionResourceUnknownTypeException extends RuntimeException {

    public ChampionResourceUnknownTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChampionResourceUnknownTypeException(String message) {
        super(message);
    }

    public ChampionResourceUnknownTypeException(Throwable cause) {
        super(cause);
    }
}
