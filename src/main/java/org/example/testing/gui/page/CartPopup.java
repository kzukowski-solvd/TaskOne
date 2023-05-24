package org.example.testing.gui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPopup extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"attach-sidesheet-view-cart-button\"]/span/input")
    private WebElement goToCartButton;


    public CartPopup(WebDriver driver) {
        super(driver);
    }

    public CartPage clickOnGoToCartButton() {
        click(goToCartButton);
        return new CartPage(driver);
    }
}