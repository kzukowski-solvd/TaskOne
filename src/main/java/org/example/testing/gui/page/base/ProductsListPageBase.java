package org.example.testing.gui.page.base;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public abstract class ProductsListPageBase extends AbstractPage {
    public ProductsListPageBase(WebDriver driver) {
        super(driver);
    }
    public abstract boolean isProductListEmpty();

    public abstract ArrayList<String> getProductTitles();

    public abstract String getProductTitle(int index);

    public abstract void filterByPriceDescending();

    public abstract ArrayList<Double> getProductPrices();

    public abstract Double getProductPrice(int index);

    public abstract ArrayList<String> getProductLinks();

    public abstract String getProductLink(int index);

    public abstract ProductPageBase openProductByLink(String link);
}
