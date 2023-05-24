package org.example.testing.gui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AbstractPage {
    @FindBy(css = ".sc-list-item-content")
    List<WebElement> cartItems;

    @FindBy(css = "input[value='Proceed to checkout']")
    private WebElement proceedToCheckoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getItemsTitles() {
        List<WebElement> productTitles = driver.findElements(By.cssSelector(".sc-product-title"));
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
        click(proceedToCheckoutButton);
    }

}