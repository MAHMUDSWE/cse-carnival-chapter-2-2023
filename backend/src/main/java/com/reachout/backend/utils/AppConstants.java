package com.reachout.backend.utils;

public class AppConstants {
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_PAGE_NO = "0";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    public static final String[] WHITE_LIST_URL = {
            "/api/auth/**", "/api/test/public/**", "/covid/get-all-country"};

    public static boolean isWhiteListedURL(String url) {
        for (String whiteListedUrl : WHITE_LIST_URL) {
            // Convert the white-list pattern to a regex pattern
            String regexPattern = whiteListedUrl.replace("/**", "/.*");

            // Check if the requested URL matches the regex pattern
            if (url.matches(regexPattern)) {
                return true;
            }
        }
        return false;
    }

}

