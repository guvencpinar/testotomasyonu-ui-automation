package com.testotomasyonu.tests;

import com.testotomasyonu.base.BaseTest;
import com.testotomasyonu.pages.CartPage;
import com.testotomasyonu.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class US004CartTests extends BaseTest {

    CartPage cartPage = new CartPage();

    @Test(description = "Her bir kategori icin rastgele 3 urun sepete eklenebilmeli ")

    public void testAdd3RandomProductsEachCategory() {


        //1- Anasayfaya git
        Driver.getDriver().get("https://testotomasyonu.com/");

        // 2 - Sayfaya başarıyla gidildiğini doğrula
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://testotomasyonu.com/", "[FAIL] Sayfa yüklenemedi!");
        System.out.println("[PASS] Anasayfa yüklendi.");

        // 3 - Her kategorinin tiklanabilirligini test et. (Kategorileri isim ve ID bilgileri ile oluştur)
        List<String> categories = Arrays.asList(
                "Electronics|7",
                "Men Fashion|1",
                "Women Fashion|2",
                "Shoes|3",
                "Furniture|8",
                "Travel|5",
                "Kids Wear|6",
                "Grocery|4"
        );

        // 4 - Tüm kategoriler için işlemleri döngüyle yap
        for (String data : categories) {
            String[] parts = data.split("\\|");
            String name = parts[0];
            String id = parts[1];

            // 4.1 - Kategori link locator’ı oluştur
            By categoryLink = By.xpath("(//a[text()='" + name + "'])[3]");

            // 4.2 - Kategoriye tıkla
            cartPage.clickCategory(categoryLink, name);

            // 4.3 - Rastgele 3 ürünü sepete ekle
            cartPage.addRandom3ProductsToCart(name);
        }

        System.out.println("[INFO] Tüm kategoriler için işlemler tamamlandı.");
        Driver.getDriver().quit(); // Tarayıcıyı kapat
    }



    @Test (description = "Sepete eklenen ürünler ile sayfadaki ürünlerin ayni oldugu test edilmeli")

    public void testVerifyProductMatchesListedProduct() throws InterruptedException {
        // 1 - URL’ye git
        Driver.getDriver().get("https://testotomasyonu.com/");

        // 2 - Sayfaya gittigini dogrula
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://testotomasyonu.com/", "[FAIL] Anasayfa yüklenemedi!");
        System.out.println("[PASS] Anasayfa başarıyla yüklendi.");

        // 3 - Top Selling Products bolumune git (başlık görünür mü?)
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Top Selling Products"), "[FAIL] Top Selling Products bölümü bulunamadı!");
        System.out.println("[PASS] Top Selling Products bölümü bulundu.");

        // 4 - View All Products butonunun tıklanabilirliğini test et
        cartPage.verifyViewAllButtonClickable();

        // 5 - View All Products butonuna tıkla
        cartPage.clickViewAllProductsButton();

        // 6 - Sayfa URL’sinin doğru olduğunu doğrula
        cartPage.verifyCurrentUrlIs("https://testotomasyonu.com/trending/all-products");

        // 7 - Kategoride 0’dan fazla ürün olduğunu doğrula
        cartPage.verifyProductCountGreaterThanZero();

        // 8 - İlk ürünün üzerine git
        cartPage.hoverFirstProduct();
        String expectedProductName = cartPage.getListedFirstProductName();
        System.out.println("[INFO] listede eklenen urun ismi: " + expectedProductName);

        // 9 - Urun kutusunun uzerinde dururken gorunur olan Add to Cart butonunun tiklanabilirligini test et ve bu butona tikla
        cartPage.clickAddToCartAfterHover();

        // 10 - Product Added To Cart yazisinin ciktigini dogrula
        String actualMessage = cartPage.retroNotifyContentAddedToCart();
        String expectedMessage = "Product Added To Cart!";

        Assert.assertEquals(actualMessage, expectedMessage, "Product Added To Cart! yazisi gorunmedi.");

        System.out.println("[PASS] Beklenen bildirim göründü: " + actualMessage);

        // 11 - 'Your Cart' butonunun gorunur oldugunu test et
        WebElement yourCartBtn = cartPage.getYourCartButton();
        Assert.assertTrue(yourCartBtn.isDisplayed(), "[FAIL] 'Your Cart' butonu gorunmuyor! ");
        System.out.println("[PASS] 'Your Cart' butonu gorunur durumda. ");

        // 12 - Sepetteki urun sayisinin 1 oldugunu dogrula
        String countText = cartPage.getYourCartItemCount();
        Assert.assertEquals(countText, "1", "[FAIL] Sepet urun sayisi 1 degil! ");
        System.out.println("[PASS] Sepet urun sayisi 1 olarak dogrulandi. ");

        // 13 - 'Your Cart' butonuna tikla
        cartPage.clickYourCartButton();

        // 14 - URL' nin https://testotomasyonu.com/shoppingCart oldugunu dogrula
        String actualUrl = cartPage.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://testotomasyonu.com/shoppingCart", "[FAIL] Your Cart Url eslesmedi.  ");
        System.out.println("[PASS] Your Cart Url' si dogru: "+actualUrl);

        // 15 - Sepette urun bulundugunu test et
        List<String> cartProductNames = cartPage.getAllYourCartProductNames();
        Assert.assertTrue(cartProductNames.size() > 0, "[FAIL] Sepette ürün bulunamadı!");
        System.out.println("[PASS] Sepette " + cartProductNames.size() + " adet ürün bulundu.");


        // 16 - Sepetteki urunun isminin listede eklenen urunun ismi ile ayni oldugunu test et
        String expectedProductNameInYourCart = cartPage.getListedFirstProductNameInYourCart();
        System.out.println("[INFO] Listeden eklenen ürün ismi: " + expectedProductNameInYourCart);

        Assert.assertEquals(cartProductNames.get(0), expectedProductName,
                "[FAIL] Sepetteki ürün ismi, listede eklenen ile aynı değil! Beklenen: "
                        + expectedProductName + " - Actual: " + cartProductNames.get(0));

        System.out.println("[PASS] Sepetteki ürün ismi doğru: " + cartProductNames.get(0));
    }


    @Test (description = "Sepet bosaltma ve dogrulama islemi")
    public void testEmptyCart() throws InterruptedException {
        // 1 - URL’ye git
        Driver.getDriver().get("https://testotomasyonu.com/");

        // 2 - Sayfaya gittigini dogrula
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://testotomasyonu.com/", "[FAIL] Anasayfa yüklenemedi!");
        System.out.println("[PASS] Anasayfa başarıyla yüklendi.");

        // 3 - Top Selling Products bolumune git (başlık görünür mü?)
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Top Selling Products"), "[FAIL] Top Selling Products bölümü bulunamadı!");
        System.out.println("[PASS] Top Selling Products bölümü bulundu.");

        // 4 - View All Products butonunun tıklanabilirliğini test et
        cartPage.verifyViewAllButtonClickable();

        // 5 - View All Products butonuna tıkla
        cartPage.clickViewAllProductsButton();

        // 6 - Sayfa URL’sinin doğru olduğunu doğrula
        cartPage.verifyCurrentUrlIs("https://testotomasyonu.com/trending/all-products");

        // 7 - Kategoride 0’dan fazla ürün olduğunu doğrula
        cartPage.verifyProductCountGreaterThanZero();

        // 8 - İlk ürünün üzerine git
        cartPage.hoverFirstProduct();
        String expectedProductName = cartPage.getListedFirstProductName();
        System.out.println("[INFO] listede eklenen urun ismi: " + expectedProductName);

        // 9 - Urun kutusunun uzerinde dururken gorunur olan Add to Cart butonunun tiklanabilirligini test et ve bu Add To Cart butonuna tikla
        cartPage.clickAddToCartAfterHover();

        // 10 - Product Added To Cart yazisinin ciktigini dogrula
        String actualMessage;
        actualMessage = cartPage.retroNotifyContentAddedToCart();
        String expectedMessage = "Product Added To Cart!";

        Assert.assertEquals(actualMessage, expectedMessage, "Product Added To Cart! yazisi gorunmedi.");

        System.out.println("[PASS] Beklenen bildirim göründü: " + actualMessage);

        // 11 - 'Your Cart' butonunun gorunur oldugunu test et
        WebElement yourCartBtn = cartPage.getYourCartButton();
        Assert.assertTrue(yourCartBtn.isDisplayed(), "[FAIL] 'Your Cart' butonu gorunmuyor! ");
        System.out.println("[PASS] 'Your Cart' butonu gorunur durumda. ");

        // 12 - Sepetteki urun sayisinin 1 oldugunu dogrula
        String countText = cartPage.getYourCartItemCount();
        Assert.assertEquals(countText, "1", "[FAIL] Sepet urun sayisi 1 degil! ");
        System.out.println("[PASS] Sepet urun sayisi 1 olarak dogrulandi. ");

        // 13 - 'Your Cart' butonuna tikla
        cartPage.clickYourCartButton();

        // 14 - URL' nin https://testotomasyonu.com/shoppingCart oldugunu dogrula
        String actualUrl = cartPage.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://testotomasyonu.com/shoppingCart", "[FAIL] Your Cart Url eslesmedi.  ");
        System.out.println("[PASS] Your Cart Url' si dogru: "+actualUrl);

        // 15 - Sepette urun bulundugunu test et
        List<String> cartProductNames = cartPage.getAllYourCartProductNames();
        Assert.assertTrue(cartProductNames.size() > 0, "[FAIL] Sepette ürün bulunamadı!");
        System.out.println("[PASS] Sepette " + cartProductNames.size() + " adet ürün bulundu.");

        // 16 - Remove X butonunun tiklanabilir olup olmadigini kontrol et
        WebElement removeButton = cartPage.getRemoveButton();
        Assert.assertNotNull(removeButton, "[FAIL] Remove butonu bulunamadi! ");
        Assert.assertTrue(removeButton.isDisplayed(), "[FAIL] Remove butonu gorunmuyor! ");
        System.out.println("[PASS] Remove butonu gorunur durumda. ");

        // 17 - X butonuna tikla
        removeButton.click();
        System.out.println("[INFO] X butonuna tiklandi. ");

        // 18 - Are you sure? penceresinin acildigini kontrol et
        WebElement removeItemConfirmationMessage = cartPage.getRemoveItemConfirmationMessage();
        Assert.assertNotNull(removeItemConfirmationMessage, "[FAIL] 'Are you sure?' penceresi bulunamadi!" );
        Assert.assertTrue(removeItemConfirmationMessage.isDisplayed(), "[FAIL] 'Are you sure?' penceresi gorunmedi! ");
        System.out.println("[PASS] 'Are you sure?' penceresi acildi. ");

        // 19 - Yes, remove it! butonuna tikla
        WebElement confirmRemoveButton = cartPage.getConfirmRemoveButton();
        Assert.assertNotNull(confirmRemoveButton, "[FAIL] 'Yes, remove it!' butonu bulunamadi! ");
        Assert.assertTrue(confirmRemoveButton.isDisplayed(), "[FAIL] 'Yes, remove it!' butonu gorunmedi! ");
        confirmRemoveButton.click();
        System.out.println("[INFO] 'Yes, romeve it!' butonuna tiklandi. ");

        // 20 - "Wait for it..." yazisinin kaybolmasini bekle
        cartPage.waitForWaitForMessageToDisappear();


        /*
        // calistiramadim bunu- "Cart successfully deleted" toast message gorundu
        String actualToastMessage = cartPage.getCartDeletedToastMessage();
        String expectedToastMessage = "Cart successfully deleted!";

        Assert.assertEquals(actualToastMessage, expectedToastMessage, "Cart successfully deleted! yazisi gorunmedi.");

        System.out.println("[PASS] Beklenen bildirim göründü: " + actualToastMessage);

        */

        // 21 - "Shopping cart is empty" mesajının görünürlüğünü kontrol et
        WebElement cartIsEmptyMessage = cartPage.getCartEmptyMessage();
        Assert.assertTrue(cartIsEmptyMessage.isDisplayed(), "[FAIL] 'Shopping cart is empty' mesaji gorunmedi! ");

        String actualEmptyMessage = cartIsEmptyMessage.getText().trim();
        String expectedEmptyMessage = "Shopping cart is empty";
        Assert.assertEquals(actualEmptyMessage, expectedEmptyMessage, "[FAIL] Mesaj içeriği beklenen ile eşleşmiyor!");
        System.out.println("[PASS] Shopping cart is empty mesaji gorundu: "+actualEmptyMessage);

    }

    @Test(description = "Ürün miktari arttirilarak sepete eklenmeli ve test edilmeli")
    public void testIncreaseProductQuantityAndAddToCart () throws InterruptedException {

        // 1 - URL’ye git
        Driver.getDriver().get("https://testotomasyonu.com/");

        // 2 - Sayfaya gittigini dogrula
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://testotomasyonu.com/", "[FAIL] Anasayfa yüklenemedi!");
        System.out.println("[PASS] Anasayfa başarıyla yüklendi.");

        // 3 - Arama kutusunun gorunur oldugunu test et
        cartPage.verifySearchBoxVisible();

        // 4 - Arama kutusuna 'phone' yaz ve Enter'a bas
        cartPage.enterSearchKeyword("phone");

        // 5 - Sayfa URL'sinin doğru olduğunu doğrula
        cartPage.verifyCurrentUrlIs("https://testotomasyonu.com/search-product?search=phone&search_category=0");

        // 6 - 0'dan fazla ürün bulunduğunu doğrula
        cartPage.verifyProductCountGreaterThanZeroInPhoneSection();

        // 7 - İlk ürüne tıkla
        cartPage.clickProductByIndex(0);

        // 8 - Sayfa URL'sinin tam olarak ürün sayfasına ait olduğunu doğrula
        cartPage.verifyCurrentUrlContains("https://testotomasyonu.com/product/");

        // 9 - Miktar kutusunun görünürlüğünü test et
        cartPage.verifyQuantityFieldVisible();

        // 10 - Miktar kutusunda + butonuna 2 kere bas
        cartPage.clickQuantityIncreaseButton();
        cartPage.clickQuantityIncreaseButton();
        System.out.println("[INFO] Miktar artırma işlemi gerçekleştirildi.");

        // 11 - Add to Cart butonuna tıkla
        cartPage.clickAddToCartButton();

        // 12 - 'Product added to cart!' yazisinin ciktigini dogrula.

        String actualMessage = cartPage.retroNotifyContentAddedToCart();
        String expectedMessage = "Product Added To Cart!";

        Assert.assertEquals(actualMessage, expectedMessage, "Product Added To Cart! yazisi gorunmedi.");

        System.out.println("[PASS] Beklenen bildirim göründü: " + actualMessage);

        // 13 - 'Your Cart' butonunun gorunur oldugunu test et
        WebElement yourCartBtn = cartPage.getYourCartButton();
        Assert.assertTrue(yourCartBtn.isDisplayed(), "[FAIL] 'Your Cart' butonu gorunmuyor! ");
        System.out.println("[PASS] 'Your Cart' butonu gorunur durumda. ");

        // 14 - Sepetteki urun sayisinin 1 oldugunu dogrula
        String countText = cartPage.getYourCartItemCount();
        Assert.assertEquals(countText, "3", "[FAIL] Sepet urun sayisi 3 degil! ");
        System.out.println("[PASS] Sepet urun sayisi 3 olarak dogrulandi. ");

        // 15 - 'Your Cart' butonuna tikla
        cartPage.clickYourCartButton();

        // 16 - URL' nin https://testotomasyonu.com/shoppingCart oldugunu dogrula
        String actualUrl = cartPage.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://testotomasyonu.com/shoppingCart", "[FAIL] Your Cart Url eslesmedi.  ");
        System.out.println("[PASS] Your Cart Url' si dogru: "+actualUrl);

        // 17 - Sepette urun bulundugunu test et
        List<String> cartProductNames = cartPage.getAllYourCartProductNames();
        Assert.assertTrue(cartProductNames.size() > 0, "[FAIL] Sepette ürün bulunamadı!");
        System.out.println("[PASS] Sepette " + cartProductNames.size() + " adet ürün bulundu.");

        // 18 - Urun miktarinin 3 oldugunu test et
        String actualItemCount = cartPage.getYourCartItemCount();
        String expectedItemCount = "3";
        Assert.assertEquals(actualItemCount, expectedItemCount,
                "[FAIL] Sepet ürün sayısı beklenenden farklı! Beklenen: " + expectedItemCount + " - Actual: " + actualItemCount);
        System.out.println("[PASS] Sepet ürün sayısı 3 olarak doğrulandı.");
    }

    @Test (description = "Sepete eklenen ürün sipariş verilmeli ve sipariş verildiği doğrulanmalı")
    public void testCompleteOrderProcess() throws InterruptedException {

        // 1 - URL’ye git
        Driver.getDriver().get("https://testotomasyonu.com/");

        // 2 - Sayfaya gittigini dogrula
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://testotomasyonu.com/", "[FAIL] Anasayfa yüklenemedi!");
        System.out.println("[PASS] Anasayfa başarıyla yüklendi.");

        // 3 - Top Selling Products bolumune git (başlık görünür mü?)
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Top Selling Products"), "[FAIL] Top Selling Products bölümü bulunamadı!");
        System.out.println("[PASS] Top Selling Products bölümü bulundu.");

        // 4 - View All Products butonunun tıklanabilirliğini test et
        cartPage.verifyViewAllButtonClickable();

        // 5 - View All Products butonuna tıkla
        cartPage.clickViewAllProductsButton();

        // 6 - Sayfa URL’sinin doğru olduğunu doğrula
        cartPage.verifyCurrentUrlIs("https://testotomasyonu.com/trending/all-products");

        // 7 - Kategoride 0’dan fazla ürün olduğunu doğrula
        cartPage.verifyProductCountGreaterThanZero();

        // 8 - İlk ürünün üzerine git
        cartPage.hoverFirstProduct();
        String expectedProductName = cartPage.getListedFirstProductName();
        System.out.println("[INFO] listede eklenen urun ismi: " + expectedProductName);

        // 9 - Urun kutusunun uzerinde dururken gorunur olan Add to Cart butonunun tiklanabilirligini test et ve bu butona tikla
        cartPage.clickAddToCartAfterHover();

        // 10 - Product Added To Cart yazisinin ciktigini dogrula
        String actualMessage = cartPage.retroNotifyContentAddedToCart();
        String expectedMessage = "Product Added To Cart!";

        Assert.assertEquals(actualMessage, expectedMessage, "Product Added To Cart! yazisi gorunmedi.");

        System.out.println("[PASS] Beklenen bildirim göründü: " + actualMessage);

        // 11 - 'Your Cart' butonunun gorunur oldugunu test et
        WebElement yourCartBtn = cartPage.getYourCartButton();
        Assert.assertTrue(yourCartBtn.isDisplayed(), "[FAIL] 'Your Cart' butonu gorunmuyor! ");
        System.out.println("[PASS] 'Your Cart' butonu gorunur durumda. ");

        // 12 - Sepetteki urun sayisinin 1 oldugunu dogrula
        String countText = cartPage.getYourCartItemCount();
        Assert.assertEquals(countText, "1", "[FAIL] Sepet urun sayisi 1 degil! ");
        System.out.println("[PASS] Sepet urun sayisi 1 olarak dogrulandi. ");

        // 13 - 'Your Cart' butonuna tikla
        cartPage.clickYourCartButton();

        // 14 - URL' nin https://testotomasyonu.com/shoppingCart oldugunu dogrula
        String actualUrl = cartPage.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://testotomasyonu.com/shoppingCart", "[FAIL] Your Cart Url eslesmedi.  ");
        System.out.println("[PASS] Your Cart Url' si dogru: " + actualUrl);

        // 15 - Sepette urun bulundugunu test et
        List<String> cartProductNames = cartPage.getAllYourCartProductNames();
        Assert.assertTrue(cartProductNames.size() > 0, "[FAIL] Sepette ürün bulunamadı!");
        System.out.println("[PASS] Sepette " + cartProductNames.size() + " adet ürün bulundu.");

        // 16 - Checkout butonuna tıkla
        cartPage.clickCheckoutButton();

        // 17 - Billing Address görünürlüğünü test et
        WebElement billingAddressText = cartPage.getBillingAddressTextElement();
        Assert.assertTrue(billingAddressText.isDisplayed(), "[FAIL] Billing Address metni görünmüyor!");
        System.out.println("[PASS] Billing Address metni görünür durumda.");

        // 18-19 Add Address butonuna tıkla ve formu doldur
        cartPage.clickAddAddressButtonInBillingSection();

        cartPage.fillAddressFormWithFaker();

        // 20 - Add Address butonuna tikla.
        cartPage.clickAddAddressBtnInAddAddressSection();

        // 21- Billing Address listesinde adres bulundugunu test et
        cartPage.isBillingAddressVisible();
        System.out.println("[TEST] 'Billing Address' görünürlüğü testi başlatıldı...");

        boolean isVisible = cartPage.isBillingAddressVisible();
        Assert.assertTrue(isVisible, "[FAIL] 'Billing Address' bölümü görünür hale gelmedi!");

        if (isVisible) {
            System.out.println("[PASS] 'Billing Address' bölümü görünür durumda.");
        }

        // 22 - Billing Address listesinde adres sec
        cartPage.selectBillingAddressWithJS();

        // 23 -  Delivery address same as billing address kutucuğuna tıklanır
        System.out.println("[INFO] 'Delivery address same as billing address' kutucuğuna tıklanıyor...");
        cartPage.clickDeliveryAddressSameAsBillingAddress();

        // 24 - Delivery Address bolumunun gorunur olmadigini test et
        cartPage.verifyDeliveryAddressNotVisible();

        // 25 - Checkbox'ı unchecked yap
        cartPage.uncheckSameAsBillingAddressCheckbox();

        // 26 - Delivery Address bölümünün görünür olduğunu test et
        cartPage.verifyDeliveryAddressVisibility();

        // 27 - Delivery  Address bolumunde Add Address butonuna tikla
        cartPage.clickAddAddressWithJavaScript();


        // 28 - Name, address, address 2, city, postcode, ülke ve sehir bilgilerini doldur
        cartPage.fillAddressFormWithFaker();


        // 29 - Add Address butonuna tikla
        cartPage.clickAddAddressBtnInAddAddressSection();
        // Başarı mesajı var mı kontrol et
        try {
            WebDriverWait successWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement successMessage = successWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(), 'Address added') or contains(text(), 'Success') or contains(text(), 'added successfully')]")
            ));
            System.out.println("SUCCESS MESAJI: " + successMessage.getText());
        } catch (Exception e) {
            System.out.println("SUCCESS MESAJI BULUNAMADI!");
        }

        System.out.println("=== DEBUG: Add Address butonuna tıkladıktan sonra ===");
        System.out.println("Add Address sonrası: " + cartPage.getRealDeliveryAddress());

        // DEBUG: Add Address sonrası durumu kontrol et
        System.out.println("=== DEBUG: Add Address SONRASI ===");
        String afterAddress = cartPage.getRealDeliveryAddress();
        System.out.println("Add Address sonrası adres box içeriği: " + afterAddress);

        // 30 - Delivery address listesinde adres bulundugunu test et
        String address = cartPage.getRealDeliveryAddress();
        System.out.println("=== FINAL SONUÇ ===");
        System.out.println("Adres kutusundaki gerçek içerik: " + address);

        Assert.assertFalse(address.isEmpty(), "Gerçek adres metni boş geldi!");

        // 31 - Delivery Address listesinde adres sec
        cartPage.selectDeliveryAddressWithJS();

        // 32 - Shipping Methods listesinden kargo sec
        cartPage.selectShippingMethodWithJS();

        // 33 - Term And Conditions checkBoxu tikliyoruz.
        cartPage.selectTermAndConditionsCheckBoxWithJS();

        // 34 - Place Order Now butonunu tikliyoruz
        cartPage.clickPlaceOrderNowBtnWithJS();

        // 35 - Your order is successfully done! Yazisinin gorunur oldugunu test et
        boolean messageVisible = cartPage.isOrderSuccessMessageVisible();
        System.out.println("[INFO] 'Your order is successfully done!' mesajının görünürlüğü: " + messageVisible);

        Assert.assertTrue(messageVisible, "Başarı mesajı görünmedi, sipariş sonrası akış hatalı!");




    }

}



















