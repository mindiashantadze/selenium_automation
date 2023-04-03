package mshantadze.services;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigService.class);
    private static Properties props = null;

    public static Properties getProps() {
        if (props == null) {
            try (InputStream inputStream = new FileInputStream("src/test/resources/config.properties")){
                props = new Properties();
                props.load(inputStream);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }

        return props;
    }
}
