package com.testotomasyonu.tests;

import com.testotomasyonu.base.BaseTest;
import com.testotomasyonu.pages.LogInPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class US002LoginTests extends BaseTest {


    @Test
    public void validLoginTest() throws InterruptedException {

        LogInPage logInPage = new LogInPage();

        //1. Url'ye git(https://testotomasyonu.com/)
        driver.get("https://testotomasyonu.com/");
        System.out.println("Anasayfaya gidildi: " + driver.getCurrentUrl());


        //2. Sayfaya gittigini dogrula
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://testotomasyonu.com/", "Anasayfaya gidilemedi");

        //3. Account butonu gorunur durumdaysa tikla
        try {
            logInPage.clickAccountLink();
            System.out.println("Account linkine tiklandi");
        } catch (Exception e) {
            System.out.println("Account linkine tiklanamadi: " + e.getMessage());
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //4. Login Now sayfasina gelindigini test et
        logInPage.goToLoginPage();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //5. Email ve password alanlarinin gorunur oldugunu dogrula
        logInPage.verifyLoginFormFieldsDisplayed();

        //6. Gecerli email ve password gir
        logInPage.enterEmail("gvncpnrpnr@gmail.com");
        logInPage.enterPassword("12345PnrGvnc");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //7. Sign in butonu gorunur durumdaysa tikla
        logInPage.clickLogin();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //8. "Login Success" mesajinin gorunurlugunu dogrula (Url'yi kontrol ederek)
        String dashboardUrl = driver.getCurrentUrl();
        Assert.assertEquals(dashboardUrl, "https://testotomasyonu.com/user-dashboard",
                "Kullanici dashboard URL' sine yonlendrilemedi");

        //9.  Kullanici profiline yonlendirildigini dogrula
        logInPage.verifySuccessfullLogin();


        //10-11-12. Kullanici bilgilerinin gorunurlugunu test et
        logInPage.verifyUserProfileInfo();

    }

    @Test

    public void ValidLoginAndVerifyProfileElements() throws InterruptedException {

        LogInPage logInPage = new LogInPage();

        //1. Url'ye git(https://testotomasyonu.com/)
        driver.get("https://testotomasyonu.com/");
        System.out.println("Anasayfaya gidildi: " + driver.getCurrentUrl());


        //2. Sayfaya gittigini dogrula
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://testotomasyonu.com/", "Anasayfaya gidilemedi");

        //3. Account butonu gorunur durumdaysa tikla
        try {
            logInPage.clickAccountLink();
            System.out.println("Account linkine tiklandi");
        } catch (Exception e) {
            System.out.println("Account linkine tiklanamadi: " + e.getMessage());
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //4. Login Now sayfasina gelindigini test et
        logInPage.goToLoginPage();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //5. Email ve password alanlarinin gorunur oldugunu dogrula
        logInPage.verifyLoginFormFieldsDisplayed();

        //6. Gecerli email ve password gir
        logInPage.enterEmail("gvncpnrpnr@gmail.com");
        logInPage.enterPassword("12345PnrGvnc");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //7. Sign in butonu gorunur durumdaysa tikla
        logInPage.clickLogin();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //8. "Login Success" mesajinin gorunurlugunu dogrula (Url'yi kontrol ederek)
        String dashboardUrl = driver.getCurrentUrl();
        Assert.assertEquals(dashboardUrl, "https://testotomasyonu.com/user-dashboard",
                "Kullanici dashboard URL' sine yonlendrilemedi");

        //9.  Kullanici profiline yonlendirildigini dogrula
        logInPage.verifySuccessfullLogin();

        //10-15 Kullanici profiline yonlendirildigini dogrula
        logInPage.verifyAccountSectionElements();

        System.out.println("Test basariyla tamamlandi");
    }
}
