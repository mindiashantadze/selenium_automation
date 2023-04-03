package mshantadze.pages;

import mshantadze.components.Search;
import mshantadze.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @FindBy(id = "gh-f")
    private Search searchSection;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Search getSearchSection() {
        return this.searchSection == null ? new Search(driver) : this.searchSection;
    }
}
