package org.example.testing.gui.page.ios;

import com.zebrunner.carina.utils.factory.DeviceType;
import org.example.testing.gui.page.base.CartPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {


    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ArrayList<String> getItemsTitles() {
        List<WebElement> productTitles = getDriver().findElements(By.xpath("//span[@role='link']//span//span"));
        ArrayList<String> titles = new ArrayList<>();
        for (WebElement title : productTitles) {
            titles.add(title.getText());
        }
        return titles;
    }

    @Override
    public ArrayList<String> getItemsQuantities() {
        List<WebElement> quantities = driver.findElements(By.xpath("//a[@class='a-size-medium a-link-normal']"));
        ArrayList<String> quantitiesList = new ArrayList<>();
        for (WebElement quantity : quantities) {
            quantitiesList.add(quantity.getText());
        }
        return quantitiesList;
    }
}
