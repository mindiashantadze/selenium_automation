package mshantadze.utils.ui;

import mshantadze.utils.logging.LoggingUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class UIElementWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(UIElementWrapper.class);

    protected WebDriver driver;

    public UIElementWrapper(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(WebElement element) {
        waitUntilElementIsVisible(element);
        LOGGER.info("- clicking on element - ");
        LoggingUtils.logHTMLElementAttributes(element);
        element.click();
    }

    protected void type(WebElement element, String text) {
        waitUntilElementIsVisible(element);
        LOGGER.info("- typing text " + text + " on element -");
        LoggingUtils.logHTMLElementAttributes(element);
        element.sendKeys(text);
    }

    protected void waitUntilElementIsVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.visibilityOf(element));
    }
}
