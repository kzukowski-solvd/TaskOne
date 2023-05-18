package org.example.testing.gui.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage{
    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchField;

    @FindBy(css = "#nav-search-submit-button")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.get("https://www.amazon.com/");
    }

    public ProductsListPage searchForProduct(String product) {
        sendKeys(searchField, product);
        click(searchButton);
        return new ProductsListPage(driver);
    }
}
