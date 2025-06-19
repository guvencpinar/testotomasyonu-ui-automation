package com.testotomasyonu.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import com.testotomasyonu.utilities.ConfigReader;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class Driver {
    private static WebDriver driver;

    private Driver(){
        //Private constructor
    }

    public static  WebDriver getDriver() {
       if (driver==null){
           String browser = ConfigReader.getProperty("browser");

           switch (browser.toLowerCase()) {
               case "chrome":
                   WebDriverManager.chromedriver().setup();
                   driver = new ChromeDriver();
                   break;
               case "firefox":
                   driver = new FirefoxDriver();
                   break;
               default:
                   driver = new ChromeDriver();
           }

           driver.manage().window().maximize();
       }

       return driver;
    }

    public  static void setDriverNull(){
        if (driver !=null) {
            driver.quit();
            driver = null;
        }
    }
}
