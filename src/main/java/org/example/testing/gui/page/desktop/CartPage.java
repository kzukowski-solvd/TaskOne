package org.example.testing.gui.page.desktop;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.testing.gui.page.base.CartPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {
    @FindBy(css = ".sc-list-item-content")
    List<ExtendedWebElement> cartItems;

    @FindBy(css = "input[value='Proceed to checkout']")
    private ExtendedWebElement proceedToCheckoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(proceedToCheckoutButton);
    }
    @Override

    public ArrayList<String> getItemsTitles() {
        List<WebElement> productTitles = getDriver().findElements(By.cssSelector(".sc-product-title"));
        ArrayList<String> titles = new ArrayList<>();
        for (WebElement title : productTitles) {
            titles.add(title.getText());
        }
        return titles;
    }
    @Override

    public ArrayList<String> getItemsQuantities() {
        List<WebElement> quantities = driver.findElements(By.cssSelector(".a-dropdown-prompt"));
        ArrayList<String> quantitiesList = new ArrayList<>();
        for (WebElement quantity : quantities) {
            quantitiesList.add(quantity.getText());
        }
        return quantitiesList;
    }

    public void clickOnProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
    }

}
