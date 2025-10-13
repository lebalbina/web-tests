package com.balbina.automationworkshop.utils;

public class EnvironmentConfig {
    private static final String BASE_URL;

    static {
        String env = System.getProperty("env", "prod");
        switch (env) {
            case "prod":
                BASE_URL = "https://www.saucedemo.com/";
                break;
            case "uat":
                BASE_URL = "some//uat//address";
                break;
            case "dev":
                BASE_URL = "some//dev//address";
                break;
            default:
                throw new RuntimeException("Unexpected environment: " + env);
        }
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getPage(String path) {
        return BASE_URL + path;
    }
}
