package org.example.testing.gui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductsListPage extends AbstractPage {

    @FindBy(css = ".s-result-item")
    private List<WebElement> products;
    @FindBy(css = "#s-result-sort-select option[value*='price-desc-rank']")
    private WebElement priceDescendingOption;

    public ProductsListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isProductListEmpty() {
        return products.isEmpty();
    }

    public ArrayList<String> getProductTitles() {
        List<WebElement> productTitles = driver.findElements(By.cssSelector(".a-size-medium.a-color-base.a-text-normal"));
        ArrayList<String> titles = new ArrayList<>();
        for (WebElement title : productTitles) {
            titles.add(title.getText());
        }
        return titles;
    }

    public String getProductTitle(int index) {
        return getProductTitles().get(index);
    }

    public void filterByPriceDescending() {
        click(priceDescendingOption);
    }

    public ArrayList<Double> getProductPrices() {
        List<WebElement> productPrices = driver.findElements(By.cssSelector(".a-price-whole"));
        ArrayList<Double> prices = new ArrayList<>();
        for (WebElement price : productPrices) {
            prices.add(Double.parseDouble(price.getText().replace(",", "")));
        }
        return prices;
    }
    public Double getProductPrice(int index) {
        return getProductPrices().get(index);
    }

    public ArrayList<String> getProductLinks() {

        List<WebElement> productLinks = driver.findElements(By.cssSelector("a.a-link-normal"));
        ArrayList<String> links = new ArrayList<>();
        for (WebElement link : productLinks) {
            links.add(link.getAttribute("href"));
        }
        // return a links without duplicates and filter out ones containing #customerReviews
        return new ArrayList<>(links).stream().filter(link -> !link.contains("#customerReviews")).distinct()
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    public String getProductLink(int index) {
        return getProductLinks().get(index);
    }

    public ProductPage openProductByLink(String link) {
        driver.get(link);
        return new ProductPage(driver);
    }


}