package com.ahmed.newsfeed.network;

public class APIUtils {
    private static final String BASE_URL = "https://newsapi.org/";

    public static APIService getAPIService() {
        return RetrofitClient.getClientInstance(BASE_URL).create(APIService.class);
    }
}
