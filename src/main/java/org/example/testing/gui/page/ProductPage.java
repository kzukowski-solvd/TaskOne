package org.example.testing.gui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends AbstractPage {

    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    private WebElement addToCartButton;

    private WebElement carouselGotoStart;

    @FindBy(className = "comparison_table_image_row")
    private WebElement comparisonFirstRow;

    @FindBy(id = "productTitle")
    private WebElement titleElement;

    private String title;

    private List<WebElement> comparisonFirstRowList;

    private int maxQuantity;

    public ProductPage(WebDriver driver) {
        super(driver);
        title = titleElement.getText().trim();
    }

    public ProductsListPage goToProductsListPage() {
        driver.navigate().back();
        return new ProductsListPage(driver);
    }

    public CartPopup addToCart() {
        click(addToCartButton);
        return new CartPopup(driver);
    }

    public String getTitle() {
        return title;
    }

    private int determineMaxQuantity() {
        int count = 1;
        String selector = "#quantity option[value*='%d']";
        while (true) {
            try {
                driver.findElement(By.cssSelector(String.format(selector, count)));
            } catch (Exception e) {
                break;
            }
            count += 1;
        }
        return count;
    }
    public void selectQuantity(int quantity) {
        maxQuantity = determineMaxQuantity();
        System.out.println(maxQuantity);
        if (quantity < 1 || quantity >= maxQuantity) {
            throw new IllegalArgumentException("Quantity must be between 1 and 3");
        }
        WebElement quantityOption = driver.findElement
                (By.cssSelector(String.format("#quantity option[value*='%d']", quantity)));
        click(quantityOption);
    }

    public int getNrComparisonProducts() {
        this.comparisonFirstRowList = comparisonFirstRow.findElements(By.cssSelector("th[role='columnheader']"));
        return this.comparisonFirstRowList.size();
    }

}