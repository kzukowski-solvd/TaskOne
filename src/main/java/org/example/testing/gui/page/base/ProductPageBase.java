package org.example.testing.gui.page.base;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ProductPageBase extends AbstractPage {
    public ProductPageBase(WebDriver driver) {
        super(driver);
    }
    public abstract SmartWagonPageBase addToCart();

    public abstract ProductsListPageBase goToProductsListPage();

    public abstract String getTitle();

    protected abstract int determineMaxQuantity();

    public abstract void selectQuantity(int quantity);

    public abstract int getNrComparisonProducts();
}
