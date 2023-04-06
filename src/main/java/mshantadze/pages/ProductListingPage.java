package mshantadze.pages;

import mshantadze.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class ProductListingPage extends BasePage {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductListingPage.class);

    public ProductListingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id = 'srp-river-results']//span[@role = 'heading']")
    private List<WebElement> productNameLbls;

    @FindBy(className = "srp-save-null-search__heading")
    private WebElement noProductFoundLbl;

    @FindBy(xpath = "//span[text() = 'Music']/span")
    private WebElement activeCategory;

    @FindBy(xpath = "//div[@id='srp-river-results']//span[@class='s-item__price']")
    private List<WebElement> priceLbls;

    public List<WebElement> getProductNames() {
        return this.productNameLbls;
    }

    public WebElement getNoProductFoundLbl() {
        return this.noProductFoundLbl;
    }

    public WebElement getActiveCategory() {
        return this.activeCategory;
    }

    public List<Double> getProductPrices() {
        List<Double> prices = new LinkedList<>();
        for (WebElement priceLbl : priceLbls) {
            LOGGER.info("Price:" + priceLbl.getText());
            String price = priceLbl.getText().trim();
            if (price.toLowerCase().contains("to")) {
                price = price.split(" to ")[0];
            }

            price = price.replace("$", "");
            prices.add(Double.parseDouble(price));
        }

        return prices;
    }
}
