package org.example.testing.gui.page;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.zebrunner.carina.utils.Configuration;
import com.zebrunner.carina.utils.R;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {
    @FindBy(css = "#twotabsearchtextbox")
    private ExtendedWebElement searchField;

    @FindBy(css = "#nav-search-submit-button")
    private ExtendedWebElement searchButton;


    public HomePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(searchField);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    public ProductsListPage searchForProduct(String product) {
        searchField.type(product);
        searchButton.click();
        return new ProductsListPage(driver);
    }
}
