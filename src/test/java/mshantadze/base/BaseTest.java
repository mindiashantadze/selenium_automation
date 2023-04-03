package mshantadze.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    protected Properties props = new Properties();
    protected WebDriver driver;

    protected BaseTest() {
        try (InputStream inputStream = new FileInputStream("src/test/resources/config.properties")){
            props.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @BeforeTest
    public void beforeTests() {
        this.driver = new ChromeDriver();
        driver.get(props.getProperty("url"));
    }

    @AfterTest
    public void afterTests() {
        driver.quit();
    }
}
