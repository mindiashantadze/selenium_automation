package mshantadze.pages;

import mshantadze.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductListingPage extends BasePage {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductListingPage.class);
    WebDriver driver;

    public ProductListingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id = 'srp-river-results']//span[@role = 'heading']")
    List<WebElement> productNameLbls;

    public List<WebElement> getProductNames() {
        return productNameLbls;
    }
}
