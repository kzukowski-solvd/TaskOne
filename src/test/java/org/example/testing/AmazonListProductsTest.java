package org.example.testing;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;
import org.example.testing.gui.page.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AmazonListProductsTest implements IAbstractTest {


    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testSearchForProduct() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ProductsListPage productsListPage = homePage.searchForProduct("keyboard");

        Assert.assertFalse(productsListPage.isProductListEmpty());
        AtomicInteger i = new AtomicInteger(1);
        productsListPage.getProductTitles().forEach(x -> System.out.println(i.getAndIncrement() + ". " + x));
    }

    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testFilterByPrice() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ProductsListPage productsListPage = homePage.searchForProduct("keyboard");
        productsListPage.filterByPriceDescending();
        ArrayList<Double> productPrices = productsListPage.getProductPrices();
        productPrices.forEach(System.out::println);
        Assert.assertTrue(isListDescending(productPrices));
    }

    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testShoppingCart() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ProductsListPage productsListPage = homePage.searchForProduct("keyboard");
        String productTitle = productsListPage.getProductTitle(0);
        String productLink = productsListPage.getProductLink(0);
        ProductPage productPage = productsListPage.openProductByLink(productLink);
        CartPopup cartPopup = productPage.addToCart();
        CartPage cartPage = cartPopup.clickOnGoToCartButton();
        Assert.assertTrue(productTitle.contains(cartPage.getItemsTitles().get(0)));
    }

    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testShoppingCart2() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ProductsListPage productsListPage = homePage.searchForProduct("keyboard");
        String productTitle = productsListPage.getProductTitle(0);

        String productLink = productsListPage.getProductLink(0);
        ProductPage productPage = productsListPage.openProductByLink(productLink);
        CartPopup cartPopup = productPage.addToCart();
        CartPage cartPage = cartPopup.clickOnGoToCartButton();
        Assert.assertTrue(cartPage.getItemsTitles().contains(productTitle));
        Assert.assertEquals(cartPage.getItemsQuantities().get(0), "1");
        getDriver().get(productLink);
        productPage.selectQuantity(3);
        CartPopup cartPopup2 = productPage.addToCart();
        CartPage cartPage2 = cartPopup2.clickOnGoToCartButton();
        Assert.assertEquals(cartPage2.getItemsQuantities().get(0), "4");
    }

    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testComparisonBoxNrOfProducts() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ProductsListPage productsListPage = homePage.searchForProduct("keyboard");
        String productLink = productsListPage.getProductLink(0);
        ProductPage productPage = productsListPage.openProductByLink(productLink);
        Assert.assertTrue(productPage.getNrComparisonProducts() >= 2);
    }

    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testTitleCoherence() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ProductsListPage productsListPage = homePage.searchForProduct("keyboard");
        String productLink = productsListPage.getProductLink(0);
        String productTitle2 = productsListPage.getProductTitle(1);
        ProductPage productPage = productsListPage.openProductByLink(productLink);
        ProductsListPage productsListPage2 = productPage.goToProductsListPage();
        String productLink2 = productsListPage2.getProductLink(2);
        ProductPage productPage2 = productsListPage2.openProductByLink(productLink2);
        String productPage2Title = productPage2.getTitle();
        Assert.assertEquals(productTitle2, productPage2Title);
    }

    private static boolean isListDescending(ArrayList<Double> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

}
