package com.testotomasyonu.tests;

import com.github.javafaker.Faker;
import com.testotomasyonu.base.BaseTest;
import com.testotomasyonu.pages.RegistrationPage;
import com.testotomasyonu.utilities.ScreenshotUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class US001RegistrationTests extends BaseTest {

    @Test
    public void testSuccessfulRegistration() throws InterruptedException {


        RegistrationPage registrationPage = new RegistrationPage();

        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress().replace("@", System.currentTimeMillis() + "@");
        String password = "12345" + firstName + lastName;

        String expectedTitle = "Test Otomasyonu - Test Otomasyonu";
        //Anasayfanin acildigini dogrula
        Assert.assertTrue(registrationPage.isHomePageDisplayed(expectedTitle), "Anasayfa Goruntulenemedi");

        //Account Linkine tikla
        registrationPage.clickAccount();

        //Sign Up butonuna tikla
        try {
            registrationPage.clickSignUp();
        } catch(ElementClickInterceptedException e){
            registrationPage.waitAndOpenSignup();
        }

        //Register Now sayfasinin acildigini dogrula
        Assert.assertTrue(registrationPage.isRegisterNowPageDisplayed(), "Register Now sayfasi goruntulenemedi");

        //Kayit formundaki alanlarin gorunur oldugunu dogrula
        registrationPage.verifyRegistrationPageElements();

        //Tek bir yontemle tum kayit islemini yapabiliriz.
        registrationPage.registerUser(firstName, lastName, email, password, password);

        //Basarili kayit yapildigi 'Register Success' toast mesaji ile dogrulandi

        String actualMessage = registrationPage.retroNotifyHeader();
        String expectedMessage = "Register Success";

        Assert.assertEquals(actualMessage, expectedMessage, " 'Register Success' yazisi gorunmedi.");

        System.out.println("[PASS] Beklenen bildirim göründü: " + actualMessage);


        //URL degisikligi ile basarili kayit kontrolu
        boolean isSuccessful = registrationPage.isRegistrationSuccessful();

        //PageSource kontrolu ile basarili kayit kontrolu (alternatif)
        boolean isSuccessfullByPageSource = registrationPage.isRegistrationSuccessfulByPageSource();


        // Basarili kayit yapildigini test et
        Assert.assertTrue(isSuccessful || isSuccessfullByPageSource, "Kayit islemi basarisiz oldu veya dogrulanamadi");


    }

    @Test

    public void testRegistrationWithEmptyFields(){
        RegistrationPage registrationPage = new RegistrationPage();

        //URL'ye git
        registrationPage.goToRegistrationPage();
        System.out.println("Test baslangicinda URL: " + driver.getCurrentUrl());

        //Sayfaya gittigini dogrula
        Assert.assertTrue(registrationPage.isHomePageDisplayed(""), "Anasayfa goruntulenemedi");

        //Gorunur durumdaysa Accounta tikla
        try {
            registrationPage.clickAccount();
        } catch (Exception e) {
            System.out.println("AccountLinkine tiklanamadi: "+e.getMessage());
        }

        //Gorunur durumdaysa sign up'a tikla
        registrationPage.clickSignUp();

        //Register Now sayfasina gelindigini dogrula
        Assert.assertTrue(registrationPage.isRegisterNowPageDisplayed(), "Register Now sayfasi goruntulenemedi");

        //Bilgileri bos birak (hicbir alan doldurulmayacak)


        //Gorunur durumdaysa Sign Up' a tikla
        registrationPage.clickRegisterButton();

        //Kisa bir bekleme ekle
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Tum bilgiler icin required uyarisi verildigini dogrula
        boolean requiredWarningsDisplayed = registrationPage.areRequiredWarningsDisplayed();
        Assert.assertTrue(requiredWarningsDisplayed,"Required uyarilari goruntulenmiyor");

        //Kayit yapilamadigi ve Register Now sayfasinda kalindigini test et
        Assert.assertTrue(registrationPage.isRegisterNowPageDisplayed(),
                "Bos form gonderilmesine ragmen kayit sayfasindan ayrilindi");

        System.out.println("Test sonunda URL: " + driver.getCurrentUrl());
        System.out.println("==================TEST SONU=======================");
    }

    @Test
    public void testRegistrationWithInvalidEmail() throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage();
        Faker faker = new Faker();

        //URL ye git
        registrationPage.goToRegistrationPage();
        System.out.println("Test baslangicinda URL: " +driver.getCurrentUrl());

        //Sayfaya gittigini dogrula
        Assert.assertTrue(registrationPage.isHomePageDisplayed(""), "Anasayfa goruntulenemedi");

        //Kayit sayfasina git ve elemanlari dogrula
        registrationPage.clickAccount();
        registrationPage.clickSignUp();
        registrationPage.verifyRegistrationPageElements();

        //Bilgileri doldur, email adresini @ isareti olmadan gir
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String invalidEmail = "invalidemail"; //@ isareti yok
        String password = "12345" + System.currentTimeMillis();

        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.enterEmail(invalidEmail);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);

        System.out.println("Form doldurulduktan sonra, kayıt butonuna tıklamadan önce URL: " + driver.getCurrentUrl());

        //Gorunur durumdaysa Sign Up'a tikla
        registrationPage.clickRegisterButton();

        //Kisa bir bekleme ekle
        try {
            Thread.sleep(3000);
            // Kayıt işleminden hemen sonra ilk ekran görüntüsü
            ScreenshotUtils.takeScreenshot(driver, "after_registration_page");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Sayfanın durumunu kontrol et
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Kayıt butonuna tıkladıktan sonra URL: " + currentUrl);
        System.out.println("Sayfa başlığı: " + driver.getTitle());

        // Sayfa yapısını kontrol et
        try {
            List<WebElement> inputs = driver.findElements(By.tagName("input"));
            System.out.println("Sayfada bulunan input alanları: " + inputs.size());

            if (inputs.isEmpty()) {
                System.out.println("Input alanlarının özellikleri:");
                for (WebElement input : inputs) {
                    String inputType = input.getAttribute("type");
                    String inputId = input.getAttribute("id");
                    String inputName = input.getAttribute("name");
                    String inputValue = input.getAttribute("value");

                    System.out.println("Input: type=" + inputType + ", id=" + inputId +
                            ", name=" + inputName + ", value=" + inputValue);
                }
            }
        } catch (Exception e) {
            System.out.println("Input alanları kontrol edilirken hata: " + e.getMessage());
        }


        //Beklenen davranis: Kayit basarisiz olmali, ama sitede bug oldugu icin basarili oluyor
        boolean isStillOnRegisterPage = currentUrl.contains("register");
        boolean registrationSuccessful = !isStillOnRegisterPage;

        System.out.println("Hala kayit sayfasinda mi: " + isStillOnRegisterPage);
        System.out.println("Kayit basarili mi: " + registrationSuccessful);

        //BUG : Gecersiz email ile kayit yapilamamali
        if (registrationSuccessful) {

            System.out.println("=======BUG TESPIT EDILDI======");
            System.out.println("@ Isareti olmayan gecersiz email ile kayit yapilabiliyor: " + invalidEmail);

            //Ekran goruntusu al
            ScreenshotUtils.takeScreenshot(driver, "invalid_email_registration_bug");

            //Bu bir bug oldugu icin, testi geciyoruz ama raporlarda da belirtiyoruz testi bilincli bir sekilde fail ediyoruz.
            Assert.fail("BUG: @ icermeyen email ile kayıt yapılabildi. Bu bir hata ve düzeltilmesi gerekiyor.");
        }else {

            System.out.println("========TEST BASARILI=========");
            System.out.println("Gecersiz email ile kayit basariyla engellendi.");
            System.out.println("===========================");

            //Bu beklenen davranis, test basarili
            Assert.assertTrue(true, "Gecersiz email ile kayit basariyla engellendi. ");
        }

        System.out.println("Test sonunda URL: " +driver.getCurrentUrl());
        System.out.println("==================TEST SONU===================");
    }

    @Test
    public void testRegistrationWithPasswordMismatch() {
        RegistrationPage registrationPage = new RegistrationPage();
        Faker faker = new Faker();

        //URL'ye git
        registrationPage.goToRegistrationPage();
        System.out.println("Test baslangicinda URL: " + driver.getCurrentUrl());

        //Sayfaya gittigini dogrula
        Assert.assertTrue(registrationPage.isHomePageDisplayed(""), "Anasayfa goruntulenemedi");

        //Gorunur durumdaysa Accounta tikla
        registrationPage.clickAccount();

        //Gorunur durumdaysa sign up'a tikla
        registrationPage.clickSignUp();

        //Register Now sayfasina gelindigini dogrula
        Assert.assertTrue(registrationPage.isRegisterNowPageDisplayed(), "Register Now sayfasi goruntulenemedi");
        System.out.println("Su anki URL : " + driver.getCurrentUrl());
        System.out.println("Register sayfasi kontrolu sonucu: " + registrationPage.isRegisterNowPageDisplayed());

        //Gerekli form alanlarini doldur
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 12,true, true,true);//Min 8, Max 12 karakter, ozel karakter ve sayi iceren

        //Farkli bir sifre olustur = orjinal sifreden farkli olsun
        String confirmPassword = password + "X5!"; // Orjinal sifreye ek yaparak farklilastir

        System.out.println("Test verileri :  ");
        System.out.println("Ad : " + firstName);
        System.out.println("Soyad : " + lastName);
        System.out.println("E-mail : " + email );
        System.out.println("Sifre : " + password);
        System.out.println("Onay Sifresi " + confirmPassword);

        //Form alanlarini doldur
        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.enterEmail(email);

        //Confirm password'u,  passworddan farkli gir
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword((confirmPassword));

        //Gorunur durumdaysa sign up'a (register button) a tikla
        registrationPage.clickRegisterButton();

        //Kisa bir sure bekleme ekle
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Password hatasi verildigi dogrulanir
        Assert.assertTrue(registrationPage.isPasswordMismatchErrorDisplayed(), "Sifre uyusmazlik hatasi goruntulenmiyor");


        //Kayit yapilamadigini test et
        Assert.assertTrue(registrationPage.isRegisterNowPageDisplayed(),
                "Sifre uyusmazligi olmasina ragmen kayit sayfasindan ayrilindi ");


        System.out.println("Test sonunda URL: " + driver.getCurrentUrl());
        System.out.println("===============TEST SONU===============");



    }



}



