package com.solodive.championselect.service.ftp.exception;

public class FTPRequestNotFoundException extends RuntimeException {

    public FTPRequestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FTPRequestNotFoundException(String message) {
        super(message);
    }

    public FTPRequestNotFoundException(Throwable cause) {
        super(cause);
    }
}
