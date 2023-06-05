package org.example.testing.gui.page.chrome;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {
    @FindBy(id = "com.android.chrome:id/tab_switcher_button")
    private ExtendedWebElement switchTabButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public TabsPageBase addTab() {
        switchTabButton.clickByJs();
        return new TabsPageBase(driver);
    }

    public int getNrOfTabs() {
        return Integer.parseInt(
                String.valueOf(
                        switchTabButton.getAttribute("content-desc").charAt(0)
                )
        );
    }

}
