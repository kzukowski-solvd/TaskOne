package org.example.testing.gui.page;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AbstractPage {
    @FindBy(css = ".sc-list-item-content")
    List<ExtendedWebElement> cartItems;

    @FindBy(css = "input[value='Proceed to checkout']")
    private ExtendedWebElement proceedToCheckoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(proceedToCheckoutButton);
    }

    public ArrayList<String> getItemsTitles() {
        List<WebElement> productTitles = getDriver().findElements(By.cssSelector(".sc-product-title"));

        ArrayList<String> titles = new ArrayList<>();
        for (WebElement title : productTitles) {
            titles.add(title.getText());
        }
        return titles;
    }

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
