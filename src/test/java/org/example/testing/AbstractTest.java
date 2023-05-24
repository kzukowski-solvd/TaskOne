package org.example.testing;

import org.example.testing.utils.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public abstract class AbstractTest {

    protected ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) throws MalformedURLException {
        if (browser.isEmpty()) {
            browser = "chrome";
        }
        ConfigReader configReader = new ConfigReader();
        WebDriver driver;
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new RemoteWebDriver(new URL(configReader.getHubUrl()), new ChromeOptions());
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new RemoteWebDriver(new URL(configReader.getHubUrl()), new FirefoxOptions());
        } else {
            throw new RuntimeException("Unsupported browser");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
    }
    @AfterMethod
    public void tearDown(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            takeScreenshotIfFailed(driverThreadLocal.get(), testResult.getMethod().getMethodName());
        }
    }

    @AfterClass
    public void tearDown() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
        }
    }

    private void takeScreenshotIfFailed(WebDriver driver, String methodName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = String.format("screenshots/%s_%s.png", methodName, new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        saveScreenshot(screenshot, screenshotPath);
    }

    private void saveScreenshot(File screenshot, String screenshotPath) {
        try {
            Files.createDirectories(Path.of("screenshots")); // Create the directory if it doesn't exist
            Files.copy(screenshot.toPath(),  Path.of(screenshotPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}