package mshantadze;

import mshantadze.base.BaseTest;
import mshantadze.pages.AdvancedSearchPage;
import mshantadze.pages.CategoriesPage;
import mshantadze.pages.HomePage;
import mshantadze.pages.ProductListingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTest.class);

    @Test
    public void searchTest() {
        WebDriver driver = new ChromeDriver();
        driver.get(configInstance.get("url"));
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
        driver.quit();
    }

    @Test
    public void emptySearch() {
        WebDriver driver = new ChromeDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded();
        homePage.getSearchSection().clickSearchButton();
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        String categoryTitle = categoriesPage.getCategoriesTitle().getText().trim();
        Assert.assertEquals(categoryTitle, "All Categories", "Element should have text \"All Categories\"");
        Assert.assertTrue(driver.getCurrentUrl().contains("all-categories"), "url should contain all-categories");
        driver.quit();
    }

    @Test
    public void noProductsFound() {
        WebDriver driver = new ChromeDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded();
        homePage.getSearchSection().typeInSearchField("somenonexistingproduct");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPage plp = new ProductListingPage(driver);
        String productNotFoundMessage = plp.getNoProductFoundLbl().getText().trim();
        Assert.assertEquals(productNotFoundMessage, "No exact matches found", "Message should say that no matches were found");
        driver.quit();
    }

    @Test
    public void searchWithCategories() {
        WebDriver driver = new ChromeDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded();
        homePage.getSearchSection().typeInSearchField("Ball");
        homePage.getSearchSection().selectCategory();
        homePage.getSearchSection().clickSearchButton();
        ProductListingPage plp = new ProductListingPage(driver);
        String activeCategoryText = plp.getActiveCategory().getText();
        Assert.assertEquals(activeCategoryText, "Selected category", "Category should be selected");
        driver.quit();
    }

    @Test
    public void excludeProducts() {
        WebDriver driver = new ChromeDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        homePage.verifyThatPageIsLoaded();
        AdvancedSearchPage advancedSearchPage = homePage.getSearchSection().goToAdvancedSearchPage();
        advancedSearchPage.typeIncludedWords("Ball");
        advancedSearchPage.typeExcludedWords("Disco");
        advancedSearchPage.submitFilter();
        ProductListingPage plp = new ProductListingPage(driver);
        List<WebElement> productNameLbls = plp.getProductNames();
        for (WebElement productNameLbl : productNameLbls) {
            String productName = productNameLbl.getText().toLowerCase();
            LOGGER.info(productName);

            Assert.assertTrue(
                    productName.contains("ball") && !productName.contains("disco"),
                    "Expected element to contain word 'ball' and not 'disco' but result was" + productName
            );
        }

        driver.quit();
    }

    @Test
    public void includeExactWords() {
        WebDriver driver = new ChromeDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        AdvancedSearchPage advancedSearchPage = homePage.getSearchSection().goToAdvancedSearchPage();
        advancedSearchPage.typeIncludedWords("Disco Ball");
        advancedSearchPage.selectExactWordAndOrder();
        advancedSearchPage.submitFilter();
        ProductListingPage plp = new ProductListingPage(driver);
        List<WebElement> productNameLbls = plp.getProductNames();
        for (WebElement productNameLbl : productNameLbls) {
            String productName = productNameLbl.getText().toLowerCase();
            LOGGER.info(productName);

            Assert.assertTrue(
                    productName.contains("disco ball"),
                    "Expected element to contain word 'disco ball' but result was" + productName
            );
        }
        driver.quit();
    }

    @Test
    public void searchByProductRange() {
        WebDriver driver = new ChromeDriver();
        driver.get(configInstance.get("url"));
        HomePage homePage = new HomePage(driver);
        AdvancedSearchPage advancedSearchPage = homePage.getSearchSection().goToAdvancedSearchPage();
        advancedSearchPage.typeIncludedWords("Ball");
        advancedSearchPage.enterMinPrice("20");
        advancedSearchPage.enterMaxPrice("50");
        advancedSearchPage.submitFilter();
        ProductListingPage plp = new ProductListingPage(driver);
        List<Double> prices = plp.getProductPrices();
        for (Double price : prices) {
            LOGGER.info(price.toString());
            Assert.assertTrue(price > 20 && price < 50, "Price should be more than 20 and less than 50. actual price: " + price);
        }

        driver.quit();
    }
}
