package org.example.testing;
import org.example.testing.gui.page.HomePage;
import org.example.testing.gui.page.ProductsListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonListProductsTest {

    private WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/kacperzukowski/Downloads/chromedriver_mac64/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void testSearchForProduct() {
        HomePage homePage = new HomePage(webDriver);
        ProductsListPage productsListPage = homePage.searchForProduct("keyboard");

        Assert.assertFalse(productsListPage.isProductListEmpty());
        productsListPage.printProductTitles();
    }

}