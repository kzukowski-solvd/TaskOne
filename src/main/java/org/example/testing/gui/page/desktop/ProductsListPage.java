package org.example.testing.gui.page.desktop;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.testing.gui.page.base.ProductsListPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ProductsListPageBase.class)
public class ProductsListPage extends ProductsListPageBase {

    @FindBy(css = ".s-result-item")
    private List<ExtendedWebElement> products;
    @FindBy(css = "#s-result-sort-select option[value*='price-desc-rank']")
    private ExtendedWebElement priceDescendingOption;

    public ProductsListPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(priceDescendingOption);
    }

    @Override
    public boolean isProductListEmpty() {
        return products.isEmpty();
    }

    @Override
    public ArrayList<String> getProductTitles() {
        List<WebElement> productTitles = getDriver().findElements(By.cssSelector(".a-size-medium.a-color-base.a-text-normal"));
        ArrayList<String> titles = new ArrayList<>();
        for (WebElement title : productTitles) {
            titles.add(title.getText());
        }
        return titles;
    }

    @Override

    public String getProductTitle(int index) {
        return getProductTitles().get(index);
    }

    @Override
    public void filterByPriceDescending() {
        priceDescendingOption.click();
    }

    @Override
    public ArrayList<Double> getProductPrices() {
        List<WebElement> productPrices = getDriver().findElements(By.cssSelector(".a-price-whole"));
        ArrayList<Double> prices = new ArrayList<>();
        for (WebElement price : productPrices) {
            prices.add(Double.parseDouble(price.getText().replace(",", "")));
        }
        return prices;
    }

    @Override
    public Double getProductPrice(int index) {
        return getProductPrices().get(index);
    }

    @Override

    public ArrayList<String> getProductLinks() {

        List<WebElement> productLinks = getDriver().findElements(By.cssSelector("a.a-link-normal"));
        ArrayList<String> links = new ArrayList<>();
        for (WebElement link : productLinks) {
            links.add(link.getAttribute("href"));
        }
        // return a links without duplicates and filter out ones containing #customerReviews
        return (links).stream().filter(link -> !link.contains("#customerReviews")).distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    @Override

    public String getProductLink(int index) {
        return getProductLinks().get(index);
    }

    @Override

    public ProductPage openProductByLink(String link) {
        getDriver().get(link);
        return new ProductPage(getDriver());
    }


}
