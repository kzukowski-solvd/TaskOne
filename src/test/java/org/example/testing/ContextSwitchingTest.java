package org.example.testing;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;
import org.example.testing.gui.page.base.HomePageBase;
import org.example.testing.gui.page.chrome.HomePage;
import org.example.testing.gui.page.chrome.TabsPageBase;
import org.example.testing.utils.MobileContextUtils;
import org.example.testing.utils.MobileContextUtils.View;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContextSwitchingTest implements IAbstractTest {
    @Test
    @MethodOwner(owner = "kzukowski")
    @TestPriority(Priority.P4)
    void testSwitchContext (){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();

        MobileContextUtils contextHelper  = new MobileContextUtils();
        contextHelper .switchMobileContext(View.NATIVE);

        HomePage homePageChrome = new HomePage(getDriver());
        TabsPageBase tabsPage = homePageChrome.addTab();
        HomePage homePageChrome1 = tabsPage.addNewTab();
        HomePage homePageChrome2 = tabsPage.addNewTab();

        Assert.assertEquals(homePageChrome2.getNrOfTabs(), 3);
    }
}
