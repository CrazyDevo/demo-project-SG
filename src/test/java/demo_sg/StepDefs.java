package demo_sg;


import demo_sg.utils.CookieHandler;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class StepDefs {

    String searched;

    @Given("^I am on the home page$")
    public void i_am_on_the_home_page() {
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Driver.getDriver().get("https://www.google.com");

        CookieHandler.acceptCookies();

    }

    @When("^I search for \"([^\"]*)\"$")
    public void i_search_for(String search) {


        searched = search;
        Driver.getDriver().findElement(By.name("q")).sendKeys(search + Keys.ENTER);
    }

    @Then("^I should see the results$")
    public void i_should_see_the_results() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }

        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(searched));
    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
       // if (scenario.isFailed()) {

            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

       // }

        System.out.println("---> @After: RUNNING AFTER EACH SCENARIO");

        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }

        Driver.closeDriver();
    }

}
