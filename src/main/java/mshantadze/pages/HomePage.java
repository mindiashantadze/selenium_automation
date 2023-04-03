package mshantadze.pages;

import mshantadze.components.Search;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = "gh-f")
    private Search searchSection;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Search getSearchSection() {
        return this.searchSection == null ? new Search(driver) : this.searchSection;
    }

    public ProductListingPage getProductListingPage() {
        return new ProductListingPage(driver);
    }
}
