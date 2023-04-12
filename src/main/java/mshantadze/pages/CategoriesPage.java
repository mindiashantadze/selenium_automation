package mshantadze.pages;

import mshantadze.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoriesPage extends BasePage {
    @FindBy(tagName = "h1")
    private WebElement categoriesTitle;

    public CategoriesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getCategoriesTitle() {
        return this.categoriesTitle.getText().trim();
    }
}
