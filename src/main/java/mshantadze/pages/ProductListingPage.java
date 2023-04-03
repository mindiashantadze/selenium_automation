package mshantadze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductListingPage {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductListingPage.class);
    WebDriver driver;

    List<WebElement> productNameLbls;

    public ProductListingPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getProductNames() {
        productNameLbls = driver.findElements(By.xpath("//div[@id = 'srp-river-results']//span[@role = 'heading']"));

        return productNameLbls;
    }
}
