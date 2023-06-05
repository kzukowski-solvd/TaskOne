package org.example.testing.gui.page.chrome;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TabsPageBase extends AbstractPage {

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"New tab\"]")
    private ExtendedWebElement addNewTabButton;

    public TabsPageBase(WebDriver driver) {
        super(driver);
    }

    public HomePage addNewTab() {
        addNewTabButton.click();
        return new HomePage(driver);
    }

}
