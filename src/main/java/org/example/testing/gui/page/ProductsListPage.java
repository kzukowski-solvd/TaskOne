package org.example.testing.gui.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsListPage extends AbstractPage{
    @FindBy(css = ".s-result-item")
    private List<WebElement> products;

    public ProductsListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isProductListEmpty() {
        return products.isEmpty();
    }

    public void printProductTitles() {
        List<WebElement> productTitles = driver.findElements(By.cssSelector(".a-size-medium.a-color-base.a-text-normal"));
        int i = 1;
        for (WebElement title : productTitles) {
            System.out.println(i++ + ". " + title.getText());
        }
    }

}
