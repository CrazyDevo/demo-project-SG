package demo_sg.utils;

import demo_sg.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CookieHandler {

    public static void acceptCookies() {
        try {
            // Adjust the locator to match the "Accept Cookies" button on your site
            WebElement acceptButton = Driver.getDriver().findElement(By.xpath("//button[contains(text(),'Accept')]"));
            if (acceptButton.isDisplayed()) {
                acceptButton.click();
                System.out.println("Cookies accepted.");
            }
        } catch (NoSuchElementException e) {
            // If the "Accept Cookies" button is not found, it might already be dismissed
            System.out.println("No cookies prompt found.");
        }
    }
}
