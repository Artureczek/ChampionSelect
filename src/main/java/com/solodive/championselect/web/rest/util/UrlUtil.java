package com.solodive.championselect.web.rest.util;

public class UrlUtil {

    public static final String SLASH_REPLACEMENT = "_";
    public static final String SLASH = "/";

    private UrlUtil() {
        //util class
    }

    public static String changeUnderscoresToSlashes(String urlParameter){
        return urlParameter.replace(SLASH_REPLACEMENT, SLASH);
    }

    public static String changeSlashesToUnderscores(String urlParameter){
        return urlParameter.replace(SLASH, SLASH_REPLACEMENT);
    }
}
