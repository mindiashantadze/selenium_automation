package mshantadze.services;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigService.class);
    private static Properties props = new Properties();
    private static ConfigService configInstance = null;

    public ConfigService() {
        try (InputStream inputStream = new FileInputStream("src/test/resources/config.properties")){
            props = new Properties();
            props.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static ConfigService init() {
        if (configInstance == null) {
            return new ConfigService();
        }

        return configInstance;
    }

    public static String get(String property) {
        return props.getProperty(property);
    }
}
