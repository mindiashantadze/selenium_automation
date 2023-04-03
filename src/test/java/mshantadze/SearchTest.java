package mshantadze;

import mshantadze.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTest.class);
    private WebDriver driver;

    @BeforeTest
    public void beforeTests() {
        this.driver = new ChromeDriver();
        driver.get("https://www.ebay.com");
    }

    @AfterTest
    public void afterTests() {
        driver.quit();
    }

    @Test
    public void searchTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.getSearchSection().typeInSearchField("Ball");
        homePage.getSearchSection().clickSearchButton();
        List<WebElement> productNameLbls = homePage.getProductListingPage().getProductNames();
        for (WebElement productNameLbl : productNameLbls) {
            String productName = productNameLbl.getText().toLowerCase();
            LOGGER.info(productName);

            Assert.assertTrue(
                    productName.contains("ball"),
                    "Expected element to contain word 'ball' but result was" + productName
            );
        }
    }
}
