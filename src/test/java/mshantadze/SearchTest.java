package mshantadze;

import mshantadze.base.BaseTest;
import mshantadze.pages.AdvancedSearchPage;
import mshantadze.pages.CategoriesPage;
import mshantadze.pages.HomePage;
import mshantadze.pages.ProductListingPage;
import mshantadze.utils.ui.enums.KeyWordOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

public class SearchTest extends BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTest.class);
    private final static String CATEGORIES_TITLE = "All Categories";
    private final static String PRODUCTS_NOT_FOUND_MSG = "No exact matches found";

    @Test
    public void searchTest() {
        WebDriver driver = createRemoteDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded(driver);
        homePage.getSearchSection().typeInSearchField("Ball");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPage plp = new ProductListingPage(driver);
        Assert.assertTrue(plp.doesProductNameContain("Ball"));
    }

    @Test
    public void emptySearch() {
        WebDriver driver = createRemoteDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded(driver);
        homePage.getSearchSection().clickSearchButton();
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        Assert.assertEquals(categoriesPage.getCategoriesTitle(), CATEGORIES_TITLE, "Element should have text \"All Categories\"");
        Assert.assertTrue(driver.getCurrentUrl().contains("all-categories"), "url should contain all-categories");
    }

    @Test
    public void noProductsFound() {
        WebDriver driver = createRemoteDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded(driver);
        homePage.getSearchSection().typeInSearchField("somenonexistingproduct");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPage plp = new ProductListingPage(driver);
        Assert.assertEquals(plp.getNoProductFoundLbl(), PRODUCTS_NOT_FOUND_MSG, "Message should say that no matches were found");
    }

    @Test
    public void searchWithCategories() {
        WebDriver driver = createRemoteDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded(driver);
        homePage.getSearchSection().typeInSearchField("Ball");
        final String category = "Music";
        homePage.getSearchSection().selectCategory(category);
        homePage.getSearchSection().clickSearchButton();
        ProductListingPage plp = new ProductListingPage(driver);
        Assert.assertTrue(plp.isCategoryActive(category));
    }

    @Test
    public void excludeProducts() {
        WebDriver driver = createRemoteDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded(driver);
        AdvancedSearchPage advancedSearchPage = homePage.getSearchSection().goToAdvancedSearchPage();
        advancedSearchPage.typeIncludedWords("Ball");
        advancedSearchPage.typeExcludedWords("Disco");
        advancedSearchPage.submitFilter();
        ProductListingPage plp = new ProductListingPage(driver);
        Assert.assertTrue(plp.doesProductNameContain("Ball", "Disco"));
    }

    @Test
    public void includeExactWords() {
        WebDriver driver = createRemoteDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        AdvancedSearchPage advancedSearchPage = homePage.getSearchSection().goToAdvancedSearchPage();
        advancedSearchPage.typeIncludedWords("Disco Ball");
        advancedSearchPage.selectKeyWordOptions(KeyWordOptions.ExactWordsExactOrder);
        advancedSearchPage.submitFilter();
        ProductListingPage plp = new ProductListingPage(driver);
        Assert.assertTrue(plp.doesProductNameContain("disco ball"));
    }

    @Test
    public void searchByProductRange() {
        WebDriver driver = createRemoteDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        AdvancedSearchPage advancedSearchPage = homePage.getSearchSection().goToAdvancedSearchPage();
        advancedSearchPage.typeIncludedWords("Ball");
        advancedSearchPage.enterMinPrice(new BigDecimal(20));
        advancedSearchPage.enterMaxPrice(new BigDecimal(50));
        advancedSearchPage.submitFilter();
        ProductListingPage plp = new ProductListingPage(driver);
        List<Double> prices = plp.getProductPrices();
        for (Double price : prices) {
            LOGGER.info(price.toString());
            Assert.assertTrue(price >= 20 && price <= 50, "Price should be more than 20 and less than 50. actual price: " + price);
        }
    }
}
