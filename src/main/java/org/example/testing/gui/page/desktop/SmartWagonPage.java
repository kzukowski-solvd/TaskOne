package org.example.testing.gui.page.desktop;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.testing.gui.page.base.SmartWagonPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = SmartWagonPageBase.class)
public class SmartWagonPage extends SmartWagonPageBase {

    @FindBy(xpath = "//*[@id=\"attach-sidesheet-view-cart-button\"]/span/input")
    private ExtendedWebElement goToCartButton;


    public SmartWagonPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(goToCartButton);
    }
@Override
    public CartPage goToCart() {
        try {
            goToCartButton.clickByJs(10, ExpectedConditions.elementToBeClickable(goToCartButton.getElement()));
        } catch ( NoSuchElementException e ) {
            getDriver().findElement(By.cssSelector(".a-button-text[href='/cart?ref_=sw_gtc']")).click();
        }
        return new CartPage(getDriver());
    }
}
