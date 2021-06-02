package tests;

import com.codeborne.selenide.Condition;
import io.appium.java_client.MobileBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("selenide_android")
public class BrowserstackAndroidSelenideTests extends TestBase {

    @Test
    @DisplayName("Successful Wikipedia simple Selenide test in Android app")
    void androidSearchTest(){
        step("Type search", () -> {
        $(MobileBy.AccessibilityId("Search Wikipedia")).click();
        $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).val("BrowserStack");
            });
        step("Verify content found", () -> {
        $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_container")).shouldHave(sizeGreaterThan(0));
            });
    }

    @Test
    @DisplayName("Successful Wikipedia advanced Selenide test in Android app")
    void androidSearchTestWikipedia(){
        step("Type search", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).val("BrowserStack");
        });
        step("Verify content found", () -> {
            step("Verify search results page not empty", () ->$$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_image")).shouldHave(sizeGreaterThan(0)));
            step("Verify Wiki container is visible", () -> $(MobileBy.id("org.wikipedia.alpha:id/search_container")).shouldBe(Condition.visible));
            step("Verify Wiki container have header text", () -> $(MobileBy.id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(text("BrowserStack")));
            step("Verify Wiki container have footer text", () ->$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_description")).shouldHave(text("Software company based in India")));
        });
    }
}
