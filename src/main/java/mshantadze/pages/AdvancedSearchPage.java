package mshantadze.pages;

import mshantadze.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvancedSearchPage extends BasePage {
    @FindBy(id = "_nkw")
    WebElement inptIncludedWords;

    @FindBy(id = "_ex_kw")
    WebElement inptExcludedWords;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitBtn;

    @FindBy(xpath = "//select[@name = '_in_kw']")
    WebElement selectKeywordOptions;

    @FindBy(xpath = "//select[@name = '_in_kw']/option[text() = 'Exact words, exact order']")
    WebElement optionExactWordAndOrder;

    @FindBy(xpath = "//input[@name='_udlo']")
    WebElement inptMinPrice;

    @FindBy(xpath = "//input[@name='_udhi']")
    WebElement inptMaxPrice;

    public AdvancedSearchPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void typeIncludedWords(String words) {
        type(this.inptIncludedWords, words);
    }

    public void typeExcludedWords(String words) {
        type(this.inptExcludedWords, words);
    }

    public void submitFilter() {
        click(this.submitBtn);
    }

    public void selectExactWordAndOrder() {
        click(this.selectKeywordOptions);
        click(this.optionExactWordAndOrder);
    }

    public void enterMinPrice(String price) {
        type(this.inptMinPrice, price);
    }

    public void enterMaxPrice(String price) {
        type(this.inptMaxPrice, price);
    }
}
