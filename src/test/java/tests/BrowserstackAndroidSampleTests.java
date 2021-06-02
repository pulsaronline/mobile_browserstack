package tests;

import java.net.URL;
import java.util.List;
import java.net.MalformedURLException;

import config.Project;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserstackAndroidSampleTests {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserstack.user", Project.browserstackConfig.bsUsername());
        caps.setCapability("browserstack.key", Project.browserstackConfig.bsPassword());
        caps.setCapability("app", Project.browserstackConfig.bsApp());
        caps.setCapability("device", Project.deviceConfig.device());
        caps.setCapability("os_version", Project.deviceConfig.osVersion());
        caps.setCapability("project", Project.browserstackConfig.bsProject());
        caps.setCapability("build", Project.browserstackConfig.bsBuild());
        caps.setCapability("name", Project.browserstackConfig.bsName());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
                new URL("http://hub.browserstack.com/wd/hub"), caps);

        // Test case for the BrowserStack sample Android app.
        // If you have uploaded your app, update the test case here.
        AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(
                        MobileBy.AccessibilityId("Search Wikipedia")));
        searchElement.click();
        AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(
                        MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("BrowserStack");
        Thread.sleep(5000);
        List<AndroidElement> allProductsName = driver.findElementsByClassName(
                "android.widget.TextView");
        assert(allProductsName.size() > 0);

        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();
    }
}
