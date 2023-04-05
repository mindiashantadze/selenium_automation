package mshantadze;

import mshantadze.base.BaseTest;
import mshantadze.pages.CategoriesPage;
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
    public void searchTest() {
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

    @Test
    public void emptySearch() {
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded();
        homePage.getSearchSection().clickSearchButton();
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        String categoryTitle = categoriesPage.getCategoriesTitle().getText().trim();
        Assert.assertEquals(categoryTitle, "All Categories", "Element should have text \"All Categories\"");
        Assert.assertTrue(driver.getCurrentUrl().contains("all-categories"), "url should contain all-categories");
    }

    @Test
    public void noProductsFound() {
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded();
        homePage.getSearchSection().typeInSearchField("somenonexistingproduct");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPage plp = new ProductListingPage(driver);
        String productNotFoundMessage = plp.getNoProductFoundLbl().getText().trim();
        Assert.assertEquals(productNotFoundMessage, "No exact matches found", "Message should say that no matches were found");
    }

    @Test
    public void searchWithCategories() {
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded();
        homePage.getSearchSection().typeInSearchField("Ball");
        homePage.getSearchSection().selectCategory();
        homePage.getSearchSection().clickSearchButton();
        ProductListingPage plp = new ProductListingPage(driver);
        String activeCategoryText = plp.getActiveCategory().getText();
        Assert.assertEquals(activeCategoryText, "Selected category", "Category should be selected");
    }


}
