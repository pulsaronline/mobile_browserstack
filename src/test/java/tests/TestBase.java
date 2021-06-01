package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackMobileDriver;
import helpers.AttachmentsHelper;
import helpers.BrowserstackApi;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestBase {

    @BeforeAll
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.browser = BrowserstackMobileDriver.class.getName();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    void startDriver(){
        open();
    }

    @AfterEach
    void afterEach(){
//        String sessionId = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        String sessionId = AttachmentsHelper.getSessionId();

        AttachmentsHelper.addScreenshotAs("Last screenshot");
        AttachmentsHelper.addPageSource();
        closeWebDriver();
        AttachmentsHelper.attachVideo(sessionId);
    }
}
