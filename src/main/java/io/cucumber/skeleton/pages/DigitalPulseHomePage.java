package io.cucumber.skeleton.pages;

import io.cucumber.skeleton.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DigitalPulseHomePage extends BasePage {

    public DigitalPulseHomePage(WebDriver driver){
        super(driver);
    }

    By digitalPulseText = By.xpath("//p[text()='Digital Pulse']");
    By columns = By.xpath("//div[contains(@class,'headline_column')]");
    By leftColumn = By.xpath("//div[contains(@class,'headline_column1')]/article");
    By middleColumn = By.xpath("//div[contains(@class,'headline_column2')]/article");
    By rightColumn = By.xpath("//div[contains(@class,'headline_column3')]/article");

    By subscribeLink = By.linkText("Subscribe");



    public boolean isDigitalPulseHomePageDisplayed() {
        return driver.findElement(digitalPulseText).isDisplayed();
    }

    public int getNumberOfColumns() {
        return driver.findElements(columns).size();
    }

    public int getNumberOfArticles(int columnNumber) {
         if (columnNumber==1)
            return driver.findElements(middleColumn).size();
         else if (columnNumber==2)
             return driver.findElements(leftColumn).size();
         else if (columnNumber==3)
             return driver.findElements(rightColumn).size();
         return -1;
    }

    public DigitalPulseSubscribePage clickOnSubscribeLink() {
        driver.findElement(subscribeLink).click();
        return  new DigitalPulseSubscribePage(driver);
    }

    public DigitalPulseSearchPage clickOnMagifyingGlassIcon() {
        driver.findElement(By.xpath("//button[@class='search-hide levelOneLink']")).click();
        return new DigitalPulseSearchPage(driver);
    }
}
