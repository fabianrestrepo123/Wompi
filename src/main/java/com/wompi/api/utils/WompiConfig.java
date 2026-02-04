package com.wompi.api.utils;


import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;

public class WompiConfig {

    private final EnvironmentVariables environmentVariables;

    public WompiConfig(EnvironmentVariables environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    public String publicKey() {
        return environmentVariables.getProperty("wompi.publicKey");
    }

    public String privateKey() {
        return environmentVariables.getProperty("wompi.privateKey");
    }

    public String integrityKey() {
        return environmentVariables.getProperty("wompi.integrityKey");
    }

    private String get(String key) {
        return EnvironmentSpecificConfiguration
                .from(environmentVariables)
                .getProperty(key);
    }
}
