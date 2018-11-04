package com.solodive.championselect.service.ftp.exception;

public class FTPConnectionFailureException extends RuntimeException {

    public FTPConnectionFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
