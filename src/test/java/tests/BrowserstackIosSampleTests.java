package tests;

import java.net.URL;
import java.net.MalformedURLException;

import config.Project;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BrowserstackIosSampleTests {

//    @Test
    void iosSearchTest() throws MalformedURLException, InterruptedException{
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
        IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(
                new URL("http://hub-cloud.browserstack.com/wd/hub"), caps);

        // Test case for the BrowserStack sample iOS app.
        // If you have uploaded your app, update the test case here.
        IOSElement textButton = (IOSElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Button")));
        textButton.click();
        IOSElement textInput = (IOSElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Input")));
        textInput.sendKeys("hello@browserstack.com");
        Thread.sleep(5000);
        IOSElement textOutput = (IOSElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Output")));
        if(textOutput != null && textOutput.getText().equals("hello@browserstack.com"))
            assert(true);
        else
            assert(false);

        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();
    }
}