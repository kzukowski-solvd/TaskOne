package org.example.testing.gui.page.desktop;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.testing.gui.page.base.HomePageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {
    @FindBy(css = "#twotabsearchtextbox")
    private ExtendedWebElement searchField;

    @FindBy(css = "#nav-search-submit-button")
    private ExtendedWebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(searchField);

    }

    @Override
    public ProductsListPage searchForProduct(String product) {
        searchField.type(product);
        searchButton.click();
        return new ProductsListPage(driver);
    }
}
