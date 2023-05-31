package org.example.testing.gui.page.base;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public abstract class CartPageBase extends AbstractPage {
    public CartPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract ArrayList<String> getItemsTitles();

    public abstract ArrayList<String> getItemsQuantities();
}
