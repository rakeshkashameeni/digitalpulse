package io.cucumber.skeleton.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.ConfigManager;
import io.cucumber.skeleton.pages.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;


public class StepDefinitions  {
    WebDriver driver;
    DigitalPulseHomePage digitalPulseHomePage;
    DigitalPulseSubscribePage digitalPulseSubscribePage;
    DigitalPulseSearchPage digitalPulseSearchPage;
    public StepDefinitions(TestContext context){

        this.driver = context.driver;
    }


    @Given("I navigate to the PwC Digital Pulse website")
    public void iNavigateToThePwCDigitalPulseWebsite() {
        driver.get(ConfigManager.getProp("url"));
        driver.manage().window().maximize();
        digitalPulseHomePage = new DigitalPulseHomePage(driver);
    }

    @When("I am viewing the Home page")
    public void iAmViewingTheHomePage() {
        Assert.assertTrue(digitalPulseHomePage.isDigitalPulseHomePageDisplayed());
    }

    @Then("I am presented with {int} columns of articles")
    public void iAmPresentedWithColumnsOfArticles(int numberOfColumns) {
        Assert.assertTrue(numberOfColumns==digitalPulseHomePage.getNumberOfColumns());
    }

    @Then("The left column is displaying {int} articles")
    public void theLeftColumnIsDisplayingArticles(int numberOfArticles) throws Exception {
       // Thread.sleep(10000);
        int actualNumberOfArticles = digitalPulseHomePage.getNumberOfArticles(1);
        Assert.assertTrue(actualNumberOfArticles==numberOfArticles);

    }

    @Then("The middle column is displaying {int} articles")
    public void theMiddleColumnIsDisplayingArticles(int numberOfArticles) {
        Assert.assertTrue(numberOfArticles==digitalPulseHomePage.getNumberOfArticles(2));
    }

    @Then("The right column is displaying {int} articles")
    public void theRightColumnIsDisplayingArticles(int numberOfArticles) {
        Assert.assertTrue(numberOfArticles==digitalPulseHomePage.getNumberOfArticles(3));

    }

    @When("I click on the Subscribe navigation link")
    public void iClickOnTheSubscribeNavigationLink() {
         digitalPulseSubscribePage = digitalPulseHomePage.clickOnSubscribeLink();
    }

    @Then("I am taken to the Subscribe page")
    public void iAmTakenToTheSubscribePage() throws  Exception{
        //Thread.sleep(10000);
        Assert.assertTrue(digitalPulseSubscribePage.isSubscribePageDisplayed());
    }

    @And("I am presented with the below fields")
    public void iAmPresentedWithTheBelowFields(DataTable table) {
        try {
            driver.switchTo().frame("sfmcform");
            List<List<String>> rows = table.asLists(String.class);
            for (List<String> columns : rows) {
                if (columns.get(0).equalsIgnoreCase("Field")) {
                    continue;
                }

                String fieldName = columns.get(0);
                String required = columns.get(1);
                String type = columns.get(2);

                WebElement fieldElement = driver.findElement(By.xpath("//*[text()='" + fieldName + "']"));

                Assert.assertTrue(fieldElement.isDisplayed());
                if (required.equalsIgnoreCase("true")) {
                    Assert.assertTrue(fieldElement.getText().contains("*"));
                }
                if (type.equalsIgnoreCase("dropdown")) {
                    type = "select";
                }
                if (type.equalsIgnoreCase("text")) {
                    type = "input";
                }
                String id = fieldElement.getAttribute("for");
                String tagName = driver.findElement(By.id(id)).getTagName();
                Assert.assertTrue(tagName.equalsIgnoreCase(type));

            }
        }catch (Exception ex){
        }finally {
            driver.switchTo().defaultContent();
        }
    }

    @And("I will need to complete Google reCAPTCHA before I can complete my request")
    public void iWillNeedToCompleteGoogleReCAPTCHABeforeICanCompleteMyRequest() {
        try {
            driver.switchTo().frame("sfmcform");
            Assert.assertTrue(driver.findElement(By.id("recaptcha-accessible-status")).isDisplayed());
        }catch (Exception ex) {
        }finally {
            driver.switchTo().defaultContent();
        }
    }

    @When("I click on the Magnifying glass icon to perform a search")
    public void iClickOnTheMagnifyingGlassIconToPerformASearch() {
        digitalPulseSearchPage = digitalPulseHomePage.clickOnMagifyingGlassIcon();
    }


    @And("I enter the text {string}")
    public void iEnterTheTextSinglePageApplications(String searchTerm) {
        digitalPulseSearchPage.search(searchTerm);
    }

    @And("I submit the search")
    public void iSubmitTheSearch() {
       digitalPulseSearchPage.submitSearch();
    }

    @Then("I am taken to the search results page")
    public void iAmTakenToTheSearchResultsPage() {
        Assert.assertTrue( digitalPulseSearchPage.isSearchResultsPageDisplayed());
    }

    @And("I am presented with at least 1 search result")
    public void iAmPresentedWithAtLeastSearchResult() {
        int results = digitalPulseSearchPage.getResultsSize();
        Assert.assertTrue(results > 0);

    }

}
