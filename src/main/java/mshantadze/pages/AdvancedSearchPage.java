package mshantadze.pages;

import mshantadze.pages.base.BasePage;
import mshantadze.utils.ui.enums.KeyWordOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;

public class AdvancedSearchPage extends BasePage {
    @FindBy(id = "_nkw")
    WebElement inptIncludedWords;

    @FindBy(id = "_ex_kw")
    WebElement inptExcludedWords;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitBtn;

    @FindBy(xpath = "//select[@name = '_in_kw']")
    WebElement selectKeywordOptions;

    @FindBy(xpath = "//select[@name = '_in_kw']/option[text() = '%s']")
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

    public void selectKeyWordOptions(KeyWordOptions keyWordOptions) {
        click(this.selectKeywordOptions);
        WebElement keyWordOption = driver.findElement(By.xpath(String.format("//select[@name = '_in_kw']/option[text() = '%s']", keyWordOptions.getKeyWordOptions())));
        click(keyWordOption);
    }

    public void enterMinPrice(BigDecimal price) {
        type(this.inptMinPrice, price.toString());
    }

    public void enterMaxPrice(BigDecimal price) {
        type(this.inptMaxPrice, price.toString());
    }
}
