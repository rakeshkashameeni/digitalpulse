package io.cucumber.skeleton.pages;

import io.cucumber.skeleton.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DigitalPulseSubscribePage extends BasePage {

    public DigitalPulseSubscribePage(WebDriver driver){
        super(driver);
    }
    By subscribeHeader = By.xpath("//h2[text()='Subscribe']");


    public boolean isSubscribePageDisplayed() {
        return  driver.findElement(subscribeHeader).isDisplayed();
    }

}
