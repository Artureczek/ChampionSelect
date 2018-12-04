package com.solodive.championselect.service.exception;

public class ChampionTagUnknownValueException extends RuntimeException {

    public ChampionTagUnknownValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChampionTagUnknownValueException(String message) {
        super(message);
    }

    public ChampionTagUnknownValueException(Throwable cause) {
        super(cause);
    }
}
