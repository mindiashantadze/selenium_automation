package mshantadze.base;

import mshantadze.services.ConfigService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    private ConcurrentHashMap<String, WebDriver> drivers = new ConcurrentHashMap<>();

    protected ConfigService configInstance;

    @BeforeSuite
    public void loadProps() {
        configInstance = ConfigService.init();
    }
}
