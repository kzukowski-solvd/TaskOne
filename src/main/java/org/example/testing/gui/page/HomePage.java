package org.example.testing.gui.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchField;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsListPage searchForProduct(String product) {
        searchField.sendKeys(product);
        searchField.sendKeys(Keys.RETURN);
        return new ProductsListPage(driver);
    }
}
