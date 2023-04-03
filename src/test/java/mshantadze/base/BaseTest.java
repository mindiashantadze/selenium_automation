package mshantadze.base;

import mshantadze.services.ConfigService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public abstract class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void loadProps() {
        ConfigService.init();
    }

    @BeforeTest
    public void beforeTests() {
        this.driver = new ChromeDriver();
        this.driver.get(ConfigService.get("url"));
    }

    @AfterTest
    public void afterTests() {
        driver.quit();
    }
}
