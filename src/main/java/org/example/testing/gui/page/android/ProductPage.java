package org.example.testing.gui.page.android;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.testing.gui.page.base.ProductPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase{

    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = "//span[@id='a-autoid-0-announce']")
    private ExtendedWebElement quantityRibbon;

    @FindBy(className = "comparison_table_image_row")
    private ExtendedWebElement comparisonFirstRow;

    private List<ExtendedWebElement> comparisonFirstRowList;

    @FindBy(xpath = "//h3[normalize-space()='From the brand']")
    private ExtendedWebElement comparisonSection;

    private int maxQuantity;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SmartWagonPage addToCart() {
        addToCartButton.scrollTo();
        addToCartButton.click();
        return new SmartWagonPage(getDriver());
    }

    @Override
    protected int determineMaxQuantity() {
        quantityRibbon.click();
        List<WebElement> elements = getDriver().findElements(By.className("a-dropdown-link"));
        maxQuantity = elements.size();
        return maxQuantity;
    }

    @Override
    public void selectQuantity(int quantity) {
        maxQuantity = determineMaxQuantity();
        System.out.println(maxQuantity);
        if (quantity < 1 || quantity >= maxQuantity) {
            throw new IllegalArgumentException(String.format("Quantity must be between 1 and %d", maxQuantity));
        }
        String selector = String.format("//a[@id='mobileQuantityDropDown_%d']", quantity - 1);
        System.out.println(selector);
        WebElement quantityOption = driver.findElement(By.xpath(selector));
        quantityOption.click();}

    @Override
    public ProductsListPage goToProductsListPage() {
        getDriver().navigate().back();
        return new ProductsListPage(getDriver());
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public int getNrComparisonProducts() {
        comparisonSection.scrollTo();
        List<WebElement> elements = getDriver().findElements(By.cssSelector("li[class^='a-carousel-card _product-comparison-mobile_carouselContentComponent_psem-carousel-element__']"));
        return elements.size();
    }
}
