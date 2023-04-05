package mshantadze;

import mshantadze.base.BaseTest;
import mshantadze.pages.HomePage;
import mshantadze.pages.ProductListingPage;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTest.class);

    @Test
    public synchronized void searchTest() {
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded();
        homePage.getSearchSection().typeInSearchField("Ball");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPage plp = new ProductListingPage(driver);
        List<WebElement> productNameLbls = plp.getProductNames();
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
