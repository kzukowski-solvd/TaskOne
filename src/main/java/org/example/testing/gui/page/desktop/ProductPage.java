package org.example.testing.gui.page.desktop;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.testing.gui.page.base.ProductPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {

    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    private ExtendedWebElement addToCartButton;

    @FindBy(className = "comparison_table_image_row")
    private ExtendedWebElement comparisonFirstRow;

    @FindBy(id = "productTitle")
    private ExtendedWebElement titleElement;

    private final String title;

    private List<ExtendedWebElement> comparisonFirstRowList;

    private int maxQuantity;

    public ProductPage(WebDriver driver) {
        super(driver);
        title = titleElement.getText().trim();
        setUiLoadedMarker(comparisonFirstRow);
    }

    @Override
    public ProductsListPage goToProductsListPage() {
        getDriver().navigate().back();
        return new ProductsListPage(getDriver());
    }

    @Override
    public SmartWagonPage addToCart() {
        addToCartButton.click();
        return new SmartWagonPage(getDriver());
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    protected int determineMaxQuantity() {
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

    @Override
    public void selectQuantity(int quantity) {
        maxQuantity = determineMaxQuantity();
        System.out.println(maxQuantity);
        if (quantity < 1 || quantity >= maxQuantity) {
            throw new IllegalArgumentException("Quantity must be between 1 and 3");
        }
        WebElement quantityOption = driver.findElement(By.cssSelector(String.format("#quantity option[value*='%d']", quantity)));
        quantityOption.click();
    }

    @Override
    public int getNrComparisonProducts() {
        comparisonFirstRowList = comparisonFirstRow.findExtendedWebElements(By.cssSelector("th[role='columnheader']"));
        return this.comparisonFirstRowList.size();
    }

}
