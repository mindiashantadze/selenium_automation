package mshantadze.base;

import mshantadze.services.ConfigService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.LinkedList;
import java.util.Properties;

public abstract class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    private ConfigService configInstance;

    @BeforeSuite
    public void loadProps() {
        configInstance = ConfigService.init();
    }

    @BeforeTest
    public void beforeTests() {
        driver = ThreadGuard.protect(new ChromeDriver());
        this.driver.get(configInstance.get("url"));
    }

    @AfterTest
    public void afterTests() {
        driver.quit();
    }


}
