package com.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try {
            String userDir = System.getProperty("user.dir");
            String configPath;

            // Check if we're already in the automation directory
            if (userDir.endsWith("automation")) {
                configPath = userDir + "/src/test/resources/config/config.properties";
            } else {
                configPath = userDir + "/automation/src/test/resources/config/config.properties";
            }

            FileInputStream fis = new FileInputStream(configPath);
            properties.load(fis);
            fis.close();
            System.out.println("Config loaded from: " + configPath);
        } catch (IOException e) {
            System.err.println("Error loading config.properties: " + e.getMessage());
            System.err.println("Working directory: " + System.getProperty("user.dir"));
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getAppUrl() {
        return getProperty("app.url");
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }

    public static String getDbDriver() {
        return getProperty("db.driver");
    }

    public static String getDbUrl() {
        return getProperty("db.url");
    }

    public static String getDbUsername() {
        return getProperty("db.username");
    }

    public static String getDbPassword() {
        return getProperty("db.password");
    }

    public static String getTestUsername() {
        return getProperty("test.username");
    }

    public static String getTestPassword() {
        return getProperty("test.password");
    }
}
