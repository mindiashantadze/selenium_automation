package mshantadze.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search {
    WebDriver driver;

    @FindBy(id = "gh-ac")
    WebElement inptSearchField;

    @FindBy(id = "gh-btn")
    WebElement btnSearchProducts;

    @FindBy(id = "gh-cat-box")
    WebElement categoriesDropDown;

    public Search(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void typeInSearchField(String productName) {
        this.inptSearchField.sendKeys(productName);
    }

    public void clickSearchButton() {
        this.btnSearchProducts.click();
    }

    public void selectCategory(String categoryName) {
        this.categoriesDropDown.click();
        driver.findElement(By.xpath(String.format("//select[@id = 'gh-cat']/option[text() = '%s']", categoryName))).click();
    }
}
