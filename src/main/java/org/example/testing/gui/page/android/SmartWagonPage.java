package org.example.testing.gui.page.android;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.testing.gui.page.base.SmartWagonPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = SmartWagonPageBase.class)
public class SmartWagonPage extends SmartWagonPageBase {

    @FindBy(xpath = "//a[normalize-space()='Go to Cart']")
    private ExtendedWebElement goToCartButton;

    public SmartWagonPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(goToCartButton);
    }

    @Override
    public CartPage goToCart(){
        goToCartButton.click();
        return new CartPage(getDriver());
    }

}
