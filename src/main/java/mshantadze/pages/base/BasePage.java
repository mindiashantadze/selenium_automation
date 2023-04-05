package mshantadze.pages.base;

import mshantadze.services.ConfigService;
import mshantadze.utils.ui.UIElementWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public abstract class BasePage extends UIElementWrapper {
    private static final String url = ConfigService.init().get("url");
    protected WebDriver driver;

    protected BasePage (WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void verifyThatPageIsLoaded() {
        boolean isPageLoaded = new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.urlToBe(url));
        Assert.assertTrue(isPageLoaded, "Url should equal: " + url);
    }

    public void verifyThatPageIsLoaded(WebElement element) {
        verifyThatPageIsLoaded();
        super.waitUntilElementIsVisible(element);
        Assert.assertTrue(element.isDisplayed(), "Element should be visible");
    }
}
