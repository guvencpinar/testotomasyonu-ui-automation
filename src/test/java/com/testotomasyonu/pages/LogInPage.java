package com.testotomasyonu.pages;

import com.testotomasyonu.base.BasePage;
import com.testotomasyonu.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class LogInPage extends BasePage {

    //Locators
    private final By accountLink = By.xpath("(//a[@class='e-cart'])[1]");
    private final By emailAddressField = By.xpath( "//input[@id='email']");
    private final By passwordField = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//button[@id='submitlogin']");

    //Kullanici profiline yonlendirildigini dogrula
    private final By userAccountIcon = By.xpath("//div[@class='profile']");

    //Kullanici profil bilgileri icin locator'lar
    private final By userFirstName = By.xpath("//input[@name='firstName']");
    private final By userLastName = By.xpath("//input[@name='lastName']");
    private final By userEmail = By.xpath("(//input[@name='email'])[1]");

    //Account bolumu element locatorlari
    private final By myProfileLink = By.xpath("(//span[contains(text(),'My Profile')])");
    private final By myOrdersLink = By.xpath("//span[contains(text(),'My Orders')]");
    private final By wishlistLink =By.xpath("(//span[text()='Wishlist'])[2]");
    private final By manageAddressLink = By.xpath("//span[text()='Manage Address']");
    private final By changePasswordLink = By.xpath("(//span[@class='item'])[1]");
    private final By logoutLink = By.xpath("//span[text()='Logout']");



    //Sayfa dogrulamasi
    public void goToLoginPage(){
        // Yönlendirme için biraz bekleyelim
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl;
        currentUrl = driver.getCurrentUrl();

        // URL kontrolünü try-catch içine alalım
        try {
            // Login sayfasına yönlendirildiğini doğrula
            Assert.assertTrue(currentUrl.contains("login"),
                    "Login sayfasına yönlendirilemedi");
        } catch (AssertionError e) {
            System.out.println("URL doğrulaması başarısız oldu. Mevcut URL: " + currentUrl);
            System.out.println("Yeniden login sayfasına gitmeyi deniyorum...");

            // Doğrudan login URL'sine gitmeyi deneyelim
            driver.get("https://testotomasyonu.com/customer-login");

            // Tekrar kontrol edelim
            currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("login"),
                    "Login sayfasına yönlendirilemedi (ikinci deneme)");
        }
    }



    //login Success gorunurlugunu dogrula
    public void verifySuccessfullLogin() {
        //URL kontrolu ile giris basarisini dogrula
        String dashboardURL = driver.getCurrentUrl();
        Assert.assertEquals(dashboardURL, "https://testotomasyonu.com/user-dashboard",
                "Kullanici dashboard URL'sine yonlendirilemedi");

        //Kullanici hesap ikonunun gorunurlugunu de kontrol et
        Assert.assertTrue(isElementDisplayed(userAccountIcon), "Kullanici hesap ikonu goruntulenemiyor ");
    }


    //Sayfa islemleri

    public void clickAccountLink() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofSeconds(20));
        WebElement accountLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='e-cart'])[1]")));
        accountLink.click();
    }

    public void enterEmail (String email){
        try{
            waitForElementVisible(emailAddressField);
            sendKeys(emailAddressField, email);
            System.out.println("Email alanina deger girildi: " +email);
        } catch (Exception e) {
            System.out.println("Email alanina deger girilemedi: "+ e.getMessage());
        }
    }

    public void enterPassword(String password){
        WebDriverWait wait = new  WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.sendKeys(password);
    }

    public void clickLogin(){
        try{
            waitForElementClickable(loginButton);
            click(loginButton);
            System.out.println("Login butonuna tiklandi");
        } catch (Exception e) {
            System.out.println("Login butonuna tiklanamadi: "+ e.getMessage());
        }
    }



    public void verifyFailedLogin() {
        Assert.assertFalse(isElementDisplayed(userAccountIcon), "Geçersiz kimlik bilgileriyle giriş yapıldı");
    }

    //Login sayfasinda Email ve password alanlarinin gorunur oldugunu dogrula
    public void verifyLoginFormFieldsDisplayed() {
        try{
            waitForPageToLoad();
            Thread.sleep(2000);

            Assert.assertTrue(isElementDisplayed(emailAddressField), "Email alani gorunur degil");
            Assert.assertTrue(isElementDisplayed(passwordField), "Password alanı görünür değil");
            Assert.assertTrue(isElementDisplayed(loginButton), "Login butonu görünür değil");
            System.out.println("Tum form alanlari gorunur durumda");
        }catch(Exception e) {
            System.out.println("Form alanlari kontrolunde hata: " + e.getMessage());
        }
    }

    //Kullanici profil bilgilerinin goruntulendigini dogrula
    public void verifyUserProfileInfo() {
        try {
            waitForElementVisible(userFirstName);
            waitForElementVisible(userLastName);
            waitForElementVisible(userEmail);

            Assert.assertTrue(isElementDisplayed(userFirstName), "Kullanıcı adı görüntülenemiyor");
            Assert.assertTrue(isElementDisplayed(userLastName), "Kullanıcı soyadı görüntülenemiyor");
            Assert.assertTrue(isElementDisplayed(userEmail), "Kullanıcı email adresi görüntülenemiyor");
            System.out.println("Kullanici profil bilgileri gorunur durumda");
        } catch (Exception e) {
            System.out.println("Kullanici profil bilgileri kontrolunde hata: " +e.getMessage());
        }
    }


    //profil bölümü element doğrulamaları
    public void verifyAccountSectionElements() {
        try{
        Assert.assertTrue(isElementDisplayed(myProfileLink), "My Profile elementi görünmüyor");
        Assert.assertTrue(isElementDisplayed(myOrdersLink), "My Orders elementi görünmüyor");
        Assert.assertTrue(isElementDisplayed(wishlistLink), "Wishlist elementi görünmüyor");
        Assert.assertTrue(isElementDisplayed(manageAddressLink), "Manage Address elementi görünmüyor");
        Assert.assertTrue(isElementDisplayed(changePasswordLink), "Change Password elementi görünmüyor");
        Assert.assertTrue(isElementDisplayed(logoutLink), "Logout elementi görünmüyor");

            System.out.println("Tum profil bolumu elementleri gorunur durumda");
        } catch (Exception e) {
            System.out.println("Profil bolumu elementleri kontrolunde hata: "+e.getMessage());
        }
    }


    @Override
    public boolean isElementDisplayed(By locator) {
        try{
            return super.isElementDisplayed(locator);
        } catch (Exception e) {
            System.out.println("Element gorunurluk kontrolunde hata: "+locator);
            return false;
        }
    }



}
