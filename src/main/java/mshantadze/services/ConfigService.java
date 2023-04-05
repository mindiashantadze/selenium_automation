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
    private static ConfigService configInstance = null;
    private Properties props = new Properties();

    private ConfigService() {
        try (InputStream inputStream = new FileInputStream("src/test/resources/config.properties")){
            this.props = new Properties();
            props.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static ConfigService init() {
        if (configInstance == null) {
            configInstance = new ConfigService();
        }

        return configInstance;
    }

    public String get(String property) {
        return this.props.getProperty(property);
    }
}
