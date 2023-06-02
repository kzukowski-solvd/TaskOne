package org.example.testing;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;
import org.example.testing.gui.page.base.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AmazonListProductsTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testSearchForProduct() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        ProductsListPageBase productsListPage = homePage.searchForProduct("keyboard");
        Assert.assertFalse(productsListPage.isProductListEmpty());
        AtomicInteger i = new AtomicInteger(1);
        productsListPage.getProductTitles().forEach(x -> System.out.println(i.getAndIncrement() + ". " + x));
    }


    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testFilterByPrice() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        ProductsListPageBase productsListPage = homePage.searchForProduct("keyboard");
        productsListPage.filterByPriceDescending();
        ArrayList<Double> productPrices = productsListPage.getProductPrices();
        productPrices.forEach(System.out::println);
        Assert.assertTrue(isListDescending(productPrices));
    }


    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testShoppingCart() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        ProductsListPageBase productsListPage = homePage.searchForProduct("keyboard");
        String productTitle = productsListPage.getProductTitle(1);
        String productLink = productsListPage.getProductLink(1);
        ProductPageBase productPage = productsListPage.openProductByLink(productLink);
        SmartWagonPageBase smartWagonPage = productPage.addToCart();
        CartPageBase cartPage = smartWagonPage.goToCart();
        Assert.assertTrue(productTitle.contains(cartPage.getItemsTitles().get(0)));
    }


    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testShoppingCart2() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        ProductsListPageBase productsListPage = homePage.searchForProduct("keyboard");

        String productTitle = productsListPage.getProductTitle(1);
        String productLink = productsListPage.getProductLink(1);

        ProductPageBase productPage = productsListPage.openProductByLink(productLink);
        SmartWagonPageBase smartWagonPage = productPage.addToCart();
        CartPageBase cartPage = smartWagonPage.goToCart();
        Assert.assertTrue(productTitle.contains(cartPage.getItemsTitles().get(0)));

        Assert.assertEquals(cartPage.getItemsQuantities().get(0), "1");
        if (!productLink.startsWith("https://www.amazon.com"))
            productLink = "https://www.amazon.com" + productLink;

        getDriver().get(productLink);
        productPage.selectQuantity(3);
        SmartWagonPageBase smartWagonPage1 = productPage.addToCart();
        CartPageBase cartPage1 = smartWagonPage1.goToCart();
        Assert.assertEquals(cartPage1.getItemsQuantities().get(0),"4");
    }


    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testComparisonBoxNrOfProducts() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        ProductsListPageBase productsListPage = homePage.searchForProduct("keyboard");
        String productLink = productsListPage.getProductLink(0);
        ProductPageBase productPage = productsListPage.openProductByLink(productLink);
        Assert.assertTrue(productPage.getNrComparisonProducts() >= 2);
    }


    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testTitleCoherence() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        ProductsListPageBase productsListPage = homePage.searchForProduct("keyboard");
        String productLink = productsListPage.getProductLink(0);
        String productTitle2 = productsListPage.getProductTitle(1);
        ProductPageBase productPage = productsListPage.openProductByLink(productLink);
        ProductsListPageBase productsListPage2 = productPage.goToProductsListPage();
        String productLink2 = productsListPage2.getProductLink(2);
        ProductPageBase productPage2 = productsListPage2.openProductByLink(productLink2);
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
