package mshantadze.components.base;

import mshantadze.utils.ui.UIElementWrapper;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseComponent extends UIElementWrapper {
    private WebElement componentLoadedMarker;

    protected WebDriver driver;

    protected BaseComponent(WebDriver driver) {
        super(driver);
    }

    public void waitUntilComponentIsVisible() {
        if (componentLoadedMarker == null) {
            throw new NotFoundException("Please specify componentLoaderMarker");
        }
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.visibilityOf(componentLoadedMarker));
    }

    public void setComponentLoadedMarker(WebElement componentLoadedMarker) {
        this.componentLoadedMarker = componentLoadedMarker;
    }
}
