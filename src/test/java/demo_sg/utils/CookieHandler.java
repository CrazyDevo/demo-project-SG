package demo_sg.utils;

import demo_sg.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CookieHandler {

    public static void acceptCookies() {
        try {

            Thread.sleep(3000);
            // Adjust the locator to match the "Accept Cookies" button on your site
            List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//*[contains(text(),'Accept')]"));

            System.out.println(elements.size()+"=============================");


            WebElement acceptButton = Driver.getDriver().findElement(By.xpath("//*[contains(text(),'Accept')]"));
            if (acceptButton.isDisplayed()) {
                acceptButton.click();
                System.out.println("Cookies accepted.");
            }
        } catch (Exception e) {
            // If the "Accept Cookies" button is not found, it might already be dismissed
            System.out.println("No cookies prompt found.");
        }
    }
}
