package org.example.testing.utils;

import java.util.ResourceBundle;

public class ConfigReader {
    private static final String CONFIG_FILE = "config";

    private final ResourceBundle resourceBundle;

    public ConfigReader() {
        resourceBundle = ResourceBundle.getBundle(CONFIG_FILE);
    }

    public String getBaseUrl() {
        return resourceBundle.getString("baseUrl");
    }

    public String getDriverDirectory() {
        return resourceBundle.getString("driverDirectory");
    }
}