package mshantadze.components;

import mshantadze.components.base.BaseComponent;
import mshantadze.pages.AdvancedSearchPage;
import mshantadze.utils.ui.UIElementWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search extends UIElementWrapper {
    WebDriver driver;

    @FindBy(id = "gh-ac")
    WebElement inptSearchField;

    @FindBy(id = "gh-btn")
    WebElement btnSearchProducts;

    @FindBy(id = "gh-cat-box")
    WebElement categoriesDropDown;

    @FindBy(id = "gh-as-a")
    WebElement advancedBtn;

    public Search(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void typeInSearchField(String productName) {
        type(inptSearchField, productName);
    }

    public void clickSearchButton() {
        click(btnSearchProducts);
    }

    public void selectCategory(String categoryName) {
        click(categoriesDropDown);
        WebElement categoryOption = driver.findElement(By.xpath(String.format("//select[@id = 'gh-cat']/option[text() = '%s']", categoryName)));
        click(categoryOption);
    }

    public AdvancedSearchPage goToAdvancedSearchPage() {
        click(this.advancedBtn);
        return new AdvancedSearchPage(driver);
    }
}
