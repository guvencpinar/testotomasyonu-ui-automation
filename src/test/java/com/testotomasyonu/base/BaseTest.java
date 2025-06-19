package com.testotomasyonu.base;

import com.testotomasyonu.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public  void setUp(){
        /*
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
        Eger ChromeDriver' i PATH'e eklediysen her seferinde System.setProperty(..) kullanmana gerek kalmaz.

        */
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://testotomasyonu.com/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        if (Driver.getDriver() != null){
            Driver.getDriver().quit();
            Driver.setDriverNull();
        }

    }







}
