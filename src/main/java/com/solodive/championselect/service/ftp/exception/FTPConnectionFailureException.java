package com.solodive.championselect.service.ftp.exception;

public class FTPConnectionFailureException extends RuntimeException {

    public FTPConnectionFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public FTPConnectionFailureException(String message) {
        super(message);
    }

    public FTPConnectionFailureException(Throwable cause) {
        super(cause);
    }
}
