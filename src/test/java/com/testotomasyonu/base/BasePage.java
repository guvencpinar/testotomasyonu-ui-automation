package com.testotomasyonu.base;

import com.testotomasyonu.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private static  final int TIMEOUT_IN_SECONDS= 20;

    public  BasePage(){
        this.driver= Driver.getDriver();
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS));
    }

    protected void clickwithJS(By locator){
        //clickAccount locate calismayinca bu methodu ekledik.
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    //Temel sayfa islemleri icin yardimci methodlar...
    protected WebElement waitForElementVisible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        waitForElementClickable(locator).click();
    }

    protected void sendKeys(By locator, String text){
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
    protected String getText(By locator){
        return waitForElementVisible(locator).getText();
    }


    public boolean isElementDisplayed(By locator) {
        try{
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            System.out.println("Element bulunamadi: "+ locator);
            return false;
        }
    }

    public boolean isElementEnabled(By locator){
       try{
           return driver.findElement(locator).isEnabled();
       } catch (Exception e) {
           return false;
       }
    }

    protected void waitForPageToLoad(){

        wait.until(driver -> Objects.equals(((JavascriptExecutor) driver)
                .executeScript("return document.readyState"), "complete"));

        //wait.until(webDriver-> ((org.openqa.selenium.JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
