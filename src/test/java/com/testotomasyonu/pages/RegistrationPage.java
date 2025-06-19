package com.testotomasyonu.pages;

import com.testotomasyonu.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static com.testotomasyonu.utilities.Driver.getDriver;

public class RegistrationPage extends BasePage {


    //Locators
    private final By accountLink = By.xpath("(//span[@class='menu-icon-text'])[1]");
    private final By signUpButton = By.xpath("//a[@class='sign-up ']");
    private final By firstNameField = By.xpath("//input[@id='firstName']");
    private final By lastNameField = By.xpath("//input[@id='lastName']");
    private final By emailAddressField = By.xpath("//input[@id='signupemail']");
    private final By passwordField = By.xpath("//input[@id='signuppassword']");
    private final By confirmPasswordField = By.xpath("//input[@id='comfirmPassword']");
    private final By registerButton = By.xpath("(//button[@type='button'])[3]");
    private final By errorMessage = By.xpath("//strong[@class='text-white']");
    private final By successMessage = By.xpath("//*[contains(text(), 'Register Success') or contains(text(), 'Success')]");
    private final By firstNameRequiredLink = By.xpath("//span[text()='First name is required']");
    private final By lastNameRequiredLink = By.xpath("//span[text()='Last name is required']");
    private final By emailAddressRequiredLink = By.xpath("//span[text()='Email address is required']");
    private final By passwordRequired = By.xpath("//span[text()='Password is required']");
    private final By confirmPasswordDoesNotMatch = By.xpath("//span[text()='Confirm password does not match']");
    private final By retroNotifyHeaderLocator = By.xpath("//div[@class='retro-notify-header']");

    // Sayfaya git ve baslik dogrulamasi yap
    public void goToRegistrationPage() {
        driver.get("https://testotomasyonu.com/");
        waitForPageToLoad();
        Assert.assertTrue(driver.getTitle().contains("Test Otomasyonu - Test Otomasyonu"));
    }

    public void clickAccount() {
       clickwithJS(accountLink);
    }

    public void clickSignUp() {
        click(signUpButton);
    }

    public void enterFirstName(String firstName) {
        sendKeys(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        sendKeys(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        sendKeys(emailAddressField, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void enterConfirmPassword(String ConfirmPassword) {
        sendKeys(confirmPasswordField, ConfirmPassword);
    }

    public void clickRegisterButton() {

        //click(registerButton);
        try {
            WebElement button = driver.findElement(registerButton);
            //Sayfayi butona kaydir
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            Thread.sleep(1000);

            //JavaScript ile tikla
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        } catch (Exception e) {
            System.out.println("JavaScript ile tiklama basarisiz: " + e.getMessage());

            try {
                //Alternatif olarak son care - ActionChains kullan
                WebElement button = driver.findElement(registerButton);
                new Actions(driver).moveToElement(button).pause(Duration.ofMillis(500)).click().perform();
            } catch (Exception ex) {
                System.out.println("Actions ile tiklama da basarisiz: " + ex.getMessage());
            }
        }
    }

    public boolean isRegistrationSuccessful() {
        // Başarılı kayıt sonrası ana sayfaya yönlendiriliyor olabilirsiniz
        try {
            Thread.sleep(2000); // URL değişikliğinin gerçekleşmesi için kısa bir bekleme
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);

            return !currentUrl.contains("customer-register");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRegistrationSuccessfulByPageSource() {
        try {
            String pageSource = driver.getPageSource();
            return pageSource.contains("Success") || pageSource.contains("Register") || pageSource.contains("Login") || pageSource.contains("Account");
        } catch (Exception e) {
            return false;
        }
    }


    // Sayfadaki input alanlarının görünür olduğunu doğrula
    public void verifyRegistrationPageElements() {
        Assert.assertTrue(isElementDisplayed(firstNameField), "First Name field is not displayed");
        Assert.assertTrue(isElementDisplayed(lastNameField), "Last Name field is not displayed");
        Assert.assertTrue(isElementDisplayed(emailAddressField), "Email field is not displayed");
        Assert.assertTrue(isElementDisplayed(passwordField), "Password field is not displayed");
        Assert.assertTrue(isElementDisplayed(confirmPasswordField), "Confirm Password field is not displayed");
    }


    // Daha kolya kullanim icin tum kodlari birlestiren method...
    public void registerUser(String firstName, String lastName, String email, String password, String ConfirmPassword) {

        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(ConfirmPassword);
        clickRegisterButton();
    }

    // Başarılı kayıt sonrası mesajı kontrol et
    public boolean isSuccessMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
        } catch (Exception e) {
            System.out.println("Basari mesaji bulunamadi: " + e.getMessage());
            return false;
        }

    }

    public boolean isHomePageDisplayed(String expectedTitle) {

        String actualTitle = driver.getTitle();
        return actualTitle.equals(expectedTitle);
    }

    public boolean isRegisterNowPageDisplayed() {
        try {
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Su anki URL: " + currentUrl);

            //Burada hem URL hem de sayfa elementlerini kontrol edin
            boolean urlCheck = currentUrl.contains("register");

            //Sonucu loglayin
            System.out.println("Register sayfasi kontrolu sonucu: " + urlCheck);
            return urlCheck;
        } catch (Exception e) {
            System.out.println("Register sayfasi kontrolu sirasinda hata: " + e.getMessage());
            return false;
        }
    }

    // Hatalı kayıt sonrası mesajı kontrol et
    public boolean isErrorMessageDisplayed() {

        return driver.getCurrentUrl().equals("https://testotomasyonu.com/customer-register");
    }

    public By getErrorMessage() {
        return errorMessage;
    }


    public boolean areRequiredWarningsDisplayed() {
        try{
            //Tum required uyarilarini XPath OR ile bulma
            List<WebElement> requiredFieldWarnings = driver.findElements(
                    By.xpath("//span[text()='First name is required' or "
                    + "text()='Last name is required' or "
                    + "text()='Email address is required'or "
                    + "text()='Password is required'or "
                    + "text()='Confirm password does not match']")
            );

            //Beklenen uyari sayisi (form alani sayisi)
            int expectedWarningsCount = 5; //  First name, Last name, Email, Password, Confirm password

            //Bulunan uyari sayisi beklenen sayiysa esit mi?
            if(requiredFieldWarnings.size() < expectedWarningsCount) {
                System.out.println("Beklenen uyari sayisi : " + expectedWarningsCount +
                        ", bulunan uyari sayisi" + requiredFieldWarnings.size());
                return false;
            }

            //Tum uyarilarin gorunur olup olmadigini kontrol et
            for (WebElement warning : requiredFieldWarnings) {
                if(!warning.isDisplayed()) {
                    System.out.println("Bir uyari gorunur degil : " +warning.getText());
                    return false;
                }
            }

            //Tum kosullar saglandiysa true dondur
            return true;

        } catch (Exception e) {
            System.out.println("Required uayrilari kontrol edilirken hata olustu: "+ e.getMessage());
            return false;
        }
    }

    public boolean  isPasswordMismatchErrorDisplayed (){
        try {
            return driver.findElement(confirmPasswordDoesNotMatch).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    public void waitAndOpenSignup () {
        WebElement element = driver.findElement(signUpButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public String retroNotifyHeader () {
        WebElement toast = waitForVisible(retroNotifyHeaderLocator, 2);
        return toast.getText();
    }

    public WebElement waitForVisible(By locator, int seconds) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}







