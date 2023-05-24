package org.example.testing.gui.page;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        waitForElementToBeClickable(element);
        log("Clicking on element: " + element.toString());
        element.click();
    }

    public void sendKeys(WebElement element, String keys) {
        waitForElementToBeVisible(element);
        log("Typing '" + keys + "' into element: " + element.toString());
        element.sendKeys(keys);
    }

    protected void waitForElementToBeClickable(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToBeVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void log(String message) {
        Logger logger = LogManager.getLogger(this.getClass());
        logger.info(message);
    }
}