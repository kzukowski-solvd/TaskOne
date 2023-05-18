package org.example.testing;
import org.example.testing.gui.page.HomePage;
import org.example.testing.gui.page.ProductsListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonListProductsTest extends AbstractTest{
    @Test
    public void testSearchForProduct() {
        HomePage homePage = new HomePage(driver);
        ProductsListPage productsListPage = homePage.searchForProduct("keyboard");

        Assert.assertFalse(productsListPage.isProductListEmpty());
        productsListPage.printProductTitles();
    }

}