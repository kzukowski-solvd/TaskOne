package org.example.testing.gui.page.base;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class SmartWagonPageBase extends AbstractPage {
    public SmartWagonPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract CartPageBase goToCart();
}
