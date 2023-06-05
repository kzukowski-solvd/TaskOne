package org.example.testing.gui.page.android;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.testing.gui.page.base.HomePageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(id = "nav-search-keywords")
    private ExtendedWebElement searchField;

    @FindBy(xpath = "//*[@id=\"nav-search-form\"]/div[2]/div")
    private ExtendedWebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(searchButton);
    }

    @Override
    public ProductsListPage searchForProduct(String product) {
        searchField.type(product);
        searchButton.click();
        return new ProductsListPage(getDriver());
    }

}
