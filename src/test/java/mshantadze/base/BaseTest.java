package mshantadze.base;

import mshantadze.services.ConfigService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    private static final ThreadLocal<WebDriver> driverInLocal = new ThreadLocal<>();

    protected ConfigService configInstance;

    @BeforeSuite
    public void loadProps() {
        configInstance = ConfigService.init();
    }

    @AfterMethod
    public void quitDriver() {
        WebDriver driver = driverInLocal.get();
        if (driver != null) {
            driver.quit();
            driverInLocal.remove();
        }
    }

    protected WebDriver createRemoteDriver() {
        try {
            if (driverInLocal.get() == null) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(configInstance.get("browser"));
                driverInLocal.set(new RemoteWebDriver(new URL(configInstance.get("selenium_hub")), capabilities));
            }

            return driverInLocal.get();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
