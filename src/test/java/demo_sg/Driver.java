package demo_sg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;

public class
Driver {
    static String browser;

    private Driver() {
    }

    //private static WebDriver driver;
    private  static InheritableThreadLocal<WebDriver> driverPool=new InheritableThreadLocal<>();   // parallel

    public static WebDriver getDriver() {
       // if (driver == null) {
            if (driverPool.get() == null) {
            browser= (System.getProperty("browser") == null)?ConfigurationReader.getProperty("browser"):System.getProperty("browser");
          System.out.println("Browser: " + browser);

            switch (browser) {
                case "remote-chrome":
                    try {
                        // assign your grid server address
                         String gridAddress = "54.163.210.152"; //My SG in Remote
                      // String gridAddress="192.168.1.30"; //if you want to run in your local SG
                        URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
                      //  URL url=new URL("http://192.168.1.30:4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                     //   driver = new RemoteWebDriver(url, desiredCapabilities);
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "chrome":

                   // driver = new ChromeDriver();
                    driverPool.set(new ChromeDriver());
                    break;

                case "firefox":
                   // WebDriverManager.firefoxdriver().setup();
                    //driver = new FirefoxDriver();
                    driverPool.set(new FirefoxDriver());
                    break;

                case "chrome-headless":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
                    chromeOptions.addArguments("--window-size=1920,1080"); // Set desired width and height
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;

                case "firefox-headless":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--width=1920");  // Set desired width
                    firefoxOptions.addArguments("--height=1080"); // Set desired height
                    driverPool.set(new FirefoxDriver(firefoxOptions));
                    break;


                case "ie":
                    if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your operating system does not support the requested browser");
                    }
                  //  WebDriverManager.iedriver().setup();
                    //driver = new InternetExplorerDriver();
                    driverPool.set(new InternetExplorerDriver());
                    break;

                case "edge":
                    if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your operating system does not support the requested browser");
                    }
                //    WebDriverManager.edgedriver().setup();
                    //driver = new EdgeDriver();
                    driverPool.set(new EdgeDriver());
                    break;

                case "safari":
                    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your operating system does not support the requested browser");
                    }
                    //driver = new SafariDriver();
                    driverPool.set(new SafariDriver());
                    break;
            }
        }

       // return driver;
        return driverPool.get();
    }

    public static void closeDriver() {
       // if (driver != null) {
        if (driverPool.get() != null) {
            //driver.quit();
            driverPool.get().quit();
          //  driver = null;
            driverPool.remove();
        }
    }
}
