package io.cucumber.skeleton.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DigitalPulseSearchPage extends BasePage {

    public DigitalPulseSearchPage(WebDriver driver){
        super(driver);
    }
    By slimSearch = By.id("slimSearch");
    By submitButton = By.xpath("//input[@class='submit-search']");
    By results = By.xpath("//div[contains(@class,'srp-list')]//div");

    public void search(String searchTerm) {
        driver.findElement(slimSearch).sendKeys(searchTerm);
    }

    public void submitSearch() {
        driver.findElement(submitButton).click();
    }

    public boolean isSearchResultsPageDisplayed() {
        return driver.findElement(By.id("srp")).isDisplayed();
    }

    public int getResultsSize() {
        return driver.findElements(results).size();
    }
}
