package org.example.testing.gui.page;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPopup extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"attach-sidesheet-view-cart-button\"]/span/input")
    private ExtendedWebElement goToCartButton;


    public CartPopup(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(goToCartButton);
    }

    public CartPage clickOnGoToCartButton() {
        try {
            goToCartButton.clickByJs(10, ExpectedConditions.elementToBeClickable(goToCartButton.getElement()));
        } catch ( NoSuchElementException e ) {
            getDriver().findElement(By.cssSelector(".a-button-text[href='/cart?ref_=sw_gtc']")).click();
        }
        return new CartPage(getDriver());
    }
}
