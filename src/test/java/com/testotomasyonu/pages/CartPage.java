package com.testotomasyonu.pages;

import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;
import com.testotomasyonu.base.BasePage;
import com.testotomasyonu.utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    private final WebDriver driver = Driver.getDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    private final By productNameInViewAllProductsSectionLocator = By.xpath("//div[@class='product-box mb-2 pb-1']");
    private final By addToCartButtons = By.xpath("//button[@class='add-to-cart']");

    public final By viewAllTopSellingBtnLocator = By.xpath("(//a[@class='view_all'])[1]");


    private final By retroNotifyHeaderLocator = By.xpath("//div[@class='retro-notify-header']");
    private final By productAddedToCartToastMessageLocator = By.xpath("//div[@class='retro-notify-content']");

    private final By yourCartItemCountLocator = By.xpath("(//span[@class='cart-count basket-count'])[1]");
    private final By yourCartButtonLocator = By.xpath("//span[text()='Your Cart']");

    private final By inYourCartProductTitleContainersLocator = By.xpath("//a[@class='product-title text-center']");

    private final By removeButtonLocator = By.xpath("//button[@class='remove']");
    private final By removeItemConfirmationMessageLocator = By.xpath("//h2[@id='swal2-title' and text()='Are you sure?']");
    private final By confirmRemoveItButtonLocator = By.xpath("//button[contains(@class, 'swal2-confirm') and text()='Yes, remove it!']");
    private final By waitForItMessageLocator = By.xpath("//div[@id='loading' and normalize-space(text())='Wait for it...']");
    private final By cartDeletedToastMessageLocator = By.xpath("//div[@class='retro-notify-content']");
    private final By shoppingCartIsEmptyLocator = By.xpath("//h3[contains(@class, 'notfoundtxt mt-5') and text()='Shopping cart is empty']");

    private final By searchBoxLocator = By.xpath("//input[@id='global-search']");
    private final By productNameInPhoneSectionLocator = By.xpath("//div[@class='product-box my-2  py-1']");
    private final By quantityBoxLocator = By.xpath("//div[@class='quantity ']");
    private final By btnIncreaseQtyProductLocator = By.xpath("//div[@onclick='increaseValue($(this))']");
    private final By productQtyInTextBoxLocator = By.xpath("//input[@class='input-text qty text']");
    private final By checkoutButtonLocator = By.xpath("//a[@class='button-block button-place']");
    private final By billingAddressHeaderLocator = By.xpath("//div[text()='  Billing Address ']");


    private final By billingSectionAddAddressBtnLocator = By.xpath("(//button[@class='btn-add-address'])[1]");
    private final By billingSectionAddAddressBtnLocator1 = By.xpath("(//button[@class='btn-add-address'])[1]");



    private final By addAddressSectionSelectFields = By.xpath("//input[contains(@placeholder, 'Enter')] | //select");

    private final By enterNameInAddAddressSectionLocator = By.xpath("//input[@id='name']");
    private final By enterEmailAddressInAddAddressSectionLocator = By.xpath("//input[@id='useremail']");
    private final By enterMobileNumberInAddAddressSectionLocator = By.xpath("//input[@class='my-3  form-control']");
    private final By enterAddress1InAddAddressSectionLocator = By.xpath("//textarea[@id='address_1']");
    private final By enterAddress2InAddAddressSectionLocator = By.xpath("//textarea[@id='address_2']");
    private final By selectCountryInAddAddressSectionLocator = By.xpath("//select[@id='country_id']");
    private final By selectStateInAddAddressSectionLocator = By.xpath("//select[@id='add_address_state']");
    private final By enterCityInAddAddressSectionLocator = By.xpath("//input[@id='city']");
    private final By enterPostcodeInAddAddressSectionLocator = By.xpath("//input[@id='postcode']");
    private final By btnAddAddressInAddAddressSectionLocator = By.xpath("//button[@class='swal2-confirm swal2-styled']");

    private final By deliveryAddressSameAsBillingAddressBtnLocator = By.xpath("//input[@id='sameasBillingAddress']");
    private final By deliveryAddressSameAsBillingAddressBtnLocator1 = By.xpath("//label[contains(text(), 'Delivery address same as billing address')]");
    private final By deliveryAddressSameAsBillingAddressBtnLocator2 = By.xpath("//label[@class='check-label' and contains(text(), 'Delivery address same as billing address')]");


    private final By deliveryAddressSectionLocator = By.xpath("//div[@class='checkout-box deliveryAdd']");
    private final By deliveryAddressSectionLocator1 = By.xpath("(//div[@class='col-md-8 col-xl-8 col-lg-8'])[2]");
    private final By deliveryAddressSectionLocator2 = By.xpath("//div[text()='  Delivery Address ']");
    private final By deliveryAddressSectionLocator3 = By.xpath("//*[contains(text(), 'Delivery Address')]");


    private final By billingAddressBoxLocator = By.xpath("//div[@class='address-box']//p[contains(@class, 'addressItem')]");

    private final By checkMarkBillingAddressLocator = By.xpath("//label[normalize-space()='Billing Address']//input[@name='billing_address_id']");
    private final By checkMarkBillingAddressLocator1 = By.xpath("(//input[@name='billing_address_id'])[2]");
    private final By checkMarkBillingAddressLocator2 = By.xpath("//span[@class='checkmark']");
    private final By checkMarkBillingAddressLocator3 = By.xpath("(//div[@class='address-box'])[1]//input[@name='billing_address_id']/following-sibling::span[@class='checkmark']");
    private final By checkMarkBillingAddressLocator4 = By.xpath("(//label[@class='check-label'])[1]");

    private final By checkMarkDeliveryAddressLocator = By.xpath("(//label[@class='check-label'])[3]");



    private final By selectShippingMethodLocator = By.xpath("//input[@name='selectedShippingMethod']");
    private final By termsAndConditionsCheckBoxLocator = By.xpath("//input[@name='accept_terms']");
    private final By placeOrderNowSubmitBtnLocator = By.xpath("//button[@class='button-block button-place mt-3']");
    private final By successMessageLocator = By.xpath("//h2[text()='Your order is successfully done!']");


    //1- Kategori linkinin tiklanabilir oldugunu dogrular
    public void verifyHomePageCategoriesClickable(By locator, String categoryName) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!element.isDisplayed()) {
            throw new AssertionError("[FAIL] " + categoryName + " kategorisi gorunur degil!");
        }
        System.out.println("[PASS] " + categoryName + "kategorisi tiklanabilir durumda. ");
    }

    //2- Kategori linkine tiklar
    public void clickCategory(By locator, String categoryName) {
        try {
            WebElement category = wait.until(ExpectedConditions.elementToBeClickable(locator));
            category.click();
            System.out.println("[INFO] " + categoryName + " kategorisine tiklandi. ");
        } catch (Exception e) {
            System.out.println("[FAIL] " + categoryName + " kategorisine tiklanamadi: " + e.getMessage());
        }
    }

    //3- Rastgele 3 urunu sepete ekler
    public void addRandom3ProductsToCart(String currentCategory) {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNameInViewAllProductsSectionLocator));
        List<WebElement> allProducts = driver.findElements(productNameInViewAllProductsSectionLocator);

        int productCount = allProducts.size();

        if (productCount == 0) {
            System.out.println("[WARN] '" + currentCategory + "' kategorisinde ürün bulunamadı. Bu kategori atlandı.");
            return;
        }
        int countToAdd = Math.min(3, productCount);
        Collections.shuffle(allProducts);
        List<WebElement> selectedProducts = allProducts.subList(0, countToAdd);

        for (int i = 0; i < countToAdd; i++) {
            WebElement product = selectedProducts.get(i);
            try {
                // Ürün detay linkini al
                WebElement link = product.findElement(By.xpath(".//a[@href]"));
                String productUrl = link.getAttribute("href");

                // Ürün detay sayfasına git
                driver.navigate().to(productUrl);

                // Add to Cart butonunu bekle ve JavaScript ile tıkla
                WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.add-to-cart")));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", addToCartBtn);

                System.out.println("[PASS] Ürün sepete eklendi: " + productUrl);
            } catch (Exception e) {
                System.out.println("[FAIL] Ürün sepete eklenemedi: " + e.getMessage());
            } finally {
                // Kategori sayfasına geri dön
                driver.navigate().back();
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNameInViewAllProductsSectionLocator));
            }
        }
    }
    //==============================================================================================
    //==============================================================================================


    //ViewAllproducts butonuna tikladiktan sonra sayfa URL' si >>> https://testotomasyonu.com/trending/all-products

    //4 - View All Products butonunun tiklanabilirligini test eder
    public void verifyViewAllButtonClickable() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(viewAllTopSellingBtnLocator));
        Assert.assertTrue(button.isDisplayed(), "[FAIL] View All Products butonu görünür değil!");
        System.out.println("[PASS] View All Products butonu tıklanabilir.");
    }

    // 5 - View All Products butonuna tıklar
    public void clickViewAllProductsButton() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(viewAllTopSellingBtnLocator));

            // Butonu görünür alana kaydır
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

            // Küçük bir bekleme: scroll sonrası sayfa hareketleri bitsin
            Thread.sleep(500);

            // JS ile tıklama
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            System.out.println("[INFO] View All Products butonuna tıklandı.");
        } catch (Exception e) {
            System.out.println("[FAIL] View All Products butonuna tıklanamadı: " + e.getMessage());
            Assert.fail("[FAIL] View All Products butonuna tıklanamadı: " + e.getMessage());
        }
    }

    // 6 - URL’nin doğruluğunu test eder
    public void verifyCurrentUrlContains(String expectedUrlSubstring) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            System.out.println("[INFO] URL'nin '" + expectedUrlSubstring + "' içermesi bekleniyor...");
            wait.until(driver -> driver.getCurrentUrl().contains(expectedUrlSubstring));
            Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlSubstring),
                    "[FAIL] URL beklenen metni içermiyor: " + expectedUrlSubstring);
            System.out.println("[PASS] URL '" + expectedUrlSubstring + "' içeriyor.");
        } catch (TimeoutException e) {
            System.out.println("[FAIL] URL belirtilen süre içerisinde beklenen metni içermedi: " + expectedUrlSubstring);
            throw new AssertionError("[FAIL] URL eşleşmedi: Timeout! Beklenen: " + expectedUrlSubstring
                    + " - Actual: " + driver.getCurrentUrl(), e);
        }
    }


    // 7 - Kategoride ürün olup olmadığını kontrol eder
    public void verifyProductCountGreaterThanZero() {
        List<WebElement> products = driver.findElements(productNameInViewAllProductsSectionLocator);
        Assert.assertTrue(products.size() > 0, "[FAIL] Ürün bulunamadı!");
        System.out.println("[PASS] Ürün sayısı: " + products.size());
    }

    // 8 - Listedeki ilk ürün kutusuna hover yapar
    public void hoverFirstProduct() {
        List<WebElement> products = driver.findElements(productNameInViewAllProductsSectionLocator);
        WebElement firstProduct = products.get(0);
        new Actions(driver).moveToElement(firstProduct).perform();
        System.out.println("[INFO] İlk ürün üzerine gidildi.");
    }


    //9-10 Add to cart butonunun olup olmadigini kontrol et ve gerekiyorsa detay sayfasina git
    public void clickAddToCartAfterHover() {
        List<WebElement> products = driver.findElements(productNameInViewAllProductsSectionLocator);
        WebElement firstProduct = products.get(0);

        try {
            // Hover sonrası "Add to Cart" butonu arıyoruz
            WebElement addToCartBtn = firstProduct.findElement(addToCartButtons);
            if (addToCartBtn.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
                System.out.println("[PASS] Hover alanındaki 'Add to Cart' butonuna tıklandı.");
                return;
            }
        } catch (Exception e) {
            System.out.println("[INFO] Hover alanında 'Add to Cart' butonu bulunamadı. Ürün detayına geçiliyor.");
        }
        try {
            // Detay sayfasına geç
            WebElement productLink = firstProduct.findElement(By.xpath(".//a[contains(@class,'prod-img')]"));
            String productUrl = productLink.getAttribute("href");
            driver.navigate().to(productUrl);
            System.out.println("[INFO] Ürün detay sayfasına geçildi: " + productUrl);

            // Butonu bul ve görünür olduğunu doğrula
            WebElement addToCartDetail = wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons));
            Assert.assertTrue(addToCartDetail.isDisplayed() && addToCartDetail.isEnabled(),
                    "[FAIL] Detay sayfasındaki 'Add to Cart' butonu tıklanabilir değil!");
            System.out.println("[PASS] Add to cart butonunun tiklanabilirligi test edildi. ");

            // Detay sayfasındaki "Add to Cart" butonunu tıkla

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartDetail);
            Thread.sleep(500); // scroll sonrası sayfa stabil hale gelsin
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartDetail);

            System.out.println("[PASS] Detay sayfasındaki 'Add to Cart' butonuna tıklandı.");
        } catch (Exception e) {
            System.out.println("[FAIL] Detay sayfasında 'Add to Cart' işlemi başarısız: " + e.getMessage());
            Assert.fail();
        }
    }

    // 'Product Added To Cart!'
    public String retroNotifyContentAddedToCart() {
        WebElement toastMessage = waitForVisible(productAddedToCartToastMessageLocator, 2);
        return toastMessage.getText();
    }

    public WebElement waitForVisible(By locator, int seconds) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // your cart butonu gorunur mu ?
    public WebElement getYourCartButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(yourCartButtonLocator));
    }

    // Sepet icindeki urun sayisini String olarak doner
    public String getYourCartItemCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(yourCartItemCountLocator)).getText().trim();
    }

    // Sepet butonuna tiklar
    public void clickYourCartButton() {
        try {
            WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(yourCartButtonLocator));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);
            System.out.println("[PASS] 'Your Cart' butonuna tiklandi");
        } catch (Exception e) {
            System.out.println("[FAIL] 'Your Cart' butonuna tiklanamadi: " + e.getMessage());
        }
    }

    //Gecerli URL' yi doner
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getListedFirstProductName() {
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNameInViewAllProductsSectionLocator));
        WebElement firstProduct = products.get(0);

        // Her bir kutuda ürün başlığı varsa onu al
        WebElement titleElement = firstProduct.findElement(By.xpath("//a[@class='prod-title mb-3 ']"));
        return titleElement.getText().trim();
    }

    // Sepetteki butun urun isimlerini doner
    public List<String> getAllYourCartProductNames() {
        List<WebElement> nameElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inYourCartProductTitleContainersLocator));
        return nameElements.stream().map(WebElement::getText).map(String::trim).collect(Collectors.toList());
    }


    public String getListedFirstProductNameInYourCart() {
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inYourCartProductTitleContainersLocator));
        WebElement firstProduct = products.get(0);

        // Her bir kutuda ürün başlığı varsa onu al
        WebElement titleElement = firstProduct.findElement(By.xpath("//a[@class='product-title text-center']"));
        return titleElement.getText().trim();
    }

    // X butonunu doner
    public WebElement getRemoveButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(removeButtonLocator));
    }

    // Are you sure? penceresini doner
    public WebElement getRemoveItemConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(removeItemConfirmationMessageLocator));
    }

    // Yes, remove it! butonunu doner
    public WebElement getConfirmRemoveButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(confirmRemoveItButtonLocator));
    }

    // "Wait for it..." mesajını döner
    public void waitForWaitForMessageToDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(waitForItMessageLocator));
            System.out.println("[PASS] 'Wait for it...' yazisi kayboldu. ");
        } catch (TimeoutException e) {
            System.out.println("[FAIL] 'Wait for it...' yazisi belirtilen surede kaybolmadi. ");
            System.out.println("[DEBUG] Hata: " + e.getMessage());
            throw e;
        }
    }


    // "Shopping cart is empty" mesajını döner
    public WebElement getCartEmptyMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartIsEmptyLocator));
    }

    // "Cart successfully deleted" toast mesajini doner
    public String getCartDeletedToastMessage() {
        WebElement toastMessage = waitForVisible(cartDeletedToastMessageLocator, 4);
        return toastMessage.getText();
    }

    // Arama kutusunun gorunurlugunu kontrol et
    public void verifySearchBoxVisible() {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxLocator));
        Assert.assertTrue(searchBox.isDisplayed(), "[FAIL] Arama kutusu görünmüyor.");
        System.out.println("[PASS] Arama kutusu görünür durumda.");
    }

    // Arama kutusuna yazi gonder
    public void enterSearchKeyword(String phone) {
        WebElement searchBox = driver.findElement(searchBoxLocator);
        searchBox.clear();
        searchBox.sendKeys(phone);
        searchBox.sendKeys(Keys.ENTER);
        System.out.println("[INFO] Arama kutusuna '" + phone + "' yazıldı ve Enter'a basıldı.");

        // Arama işlemi sonrası belirli bir elementin görünmesini bekle
        wait.until(ExpectedConditions.urlContains("search-product"));
        System.out.println("[INFO] Arama sonuçları sayfası yüklendi.");
    }

    // URL'nin tam olarak beklenen "https://testotomasyonu.com/search-product?search=phone&search_category=0" URL ile eşleştiğini doğrular
    public void verifyCurrentUrlIs(String expectedUrl) {

        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "[FAIL] Sayfa URL'si beklenen ile eşleşmiyor!");
        System.out.println("[PASS] Sayfa URL'si doğru: " + actualUrl);
    }

    public void verifyProductCountGreaterThanZeroInPhoneSection() {
        List<WebElement> products = driver.findElements(productNameInPhoneSectionLocator);
        Assert.assertTrue(products.size() > 0, "[FAIL] Ürün bulunamadı!");
        System.out.println("[PASS] Ürün sayısı: " + products.size());
    }

    // Belirtilen sıradaki ürüne tıklar
    public void clickProductByIndex(int index) {
        try {
            List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNameInPhoneSectionLocator));

            if (index >= 0 && index < products.size()) {
                products.get(index).click();
                System.out.println("[INFO] " + (index + 1) + ". ürüne tıklandı.");
            } else {
                System.out.println("[FAIL] Geçersiz ürün indeksi: " + index);
            }

        } catch (TimeoutException e) {
            System.out.println("[FAIL] Ürün bulunamadı veya tıklanamadı!");
            throw e;
        }
    }

    public void verifyQuantityFieldVisible() {
        WebElement quantityField = wait.until(ExpectedConditions.visibilityOfElementLocated(productQtyInTextBoxLocator));
        Assert.assertTrue(quantityField.isDisplayed(), "[FAIL] Miktar kutusu görünmüyor.");
        System.out.println("[PASS] Miktar kutusu görünür durumda.");
    }

    // Miktar artırma butonuna tıklar (JavaScript ile)
    public void clickQuantityIncreaseButton() throws InterruptedException {
        try {
            WebElement increaseButton = wait.until(ExpectedConditions.elementToBeClickable(btnIncreaseQtyProductLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", increaseButton);
            Thread.sleep(500);  // Scroll sonrası sayfanın sabitlenmesi için kısa bir bekleme
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", increaseButton);
            System.out.println("[INFO] Miktar artırma butonuna JavaScript ile tıklandı.");
        } catch (Exception e) {
            System.out.println("[FAIL] Miktar artırma butonuna tıklanamadı: " + e.getMessage());
            throw e;
        }
    }

    public void clickAddToCartButton() throws InterruptedException {
        try {
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
            Thread.sleep(500);  // Scroll sonrası sayfanın sabitlenmesi için kısa bir bekleme
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
            System.out.println("[INFO] Add to cart butonuna JavaScript ile tıklandı.");
        } catch (Exception e) {
            System.out.println("[FAIL] Add to cart butonuna tıklanamadı: " + e.getMessage());
            throw e;
        }
    }

    public void clickCheckoutButton() throws InterruptedException {
        try {
            WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButtonLocator));

            // JavaScript ile tıklama
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);
            Thread.sleep(500); // Scroll sonrası stabilizasyon için bekleme
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);

            System.out.println("[INFO] Checkout butonuna JavaScript ile tıklandı.");
        } catch (Exception e) {
            System.out.println("[FAIL] Checkout butonuna tıklanamadı: " + e.getMessage());
            throw e;
        }
    }

    // "Billing Address" metninin görünürlüğünü kontrol eder
    public WebElement getBillingAddressTextElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(billingAddressHeaderLocator));
    }

    // Add Address butonuna tıkla
    public void clickAddAddressButtonInBillingSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            System.out.println("[INFO] 'Add Address' butonunun tıklanabilir olması bekleniyor...");
            WebElement addAddressBtn = wait.until(ExpectedConditions.elementToBeClickable(billingSectionAddAddressBtnLocator1));

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Butonu görünür alana getir
            js.executeScript("arguments[0].scrollIntoView(true);", addAddressBtn);
            Thread.sleep(2000); // Scroll sonrası kısa bekleme

            // JavaScript ile tıklama işlemi
            js.executeScript("arguments[0].click();", addAddressBtn);
            System.out.println("[PASS] 'Add Address' butonuna JavaScript ile tıklandı.");

            // ⬇️ Buradan sonra formun açılıp açılmadığını kontrol ediyoruz:
            WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(enterNameInAddAddressSectionLocator));
            System.out.println("[PASS] 'Name' input alanı başarıyla görünür hale geldi.");

        } catch (TimeoutException e) {
            System.out.println("[FAIL] 'Add Address' butonu belirtilen süre içinde tıklanabilir hale gelmedi.");
            throw e;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Adres formunu doldur (Faker ile)
    public void fillAddressFormWithFaker() throws InterruptedException {
        Faker faker = new Faker();
        Random random = new Random();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Faker Verileri
        String fullName = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String mobile = faker.numerify("###-###-####");  // Telefon numarasını sayısal olarak alalım
        String address1 = faker.address().streetAddress();
        String address2 = faker.address().secondaryAddress();
        String city = faker.address().city();
        String postcode = faker.address().zipCode();

        try {
            // Name
            WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(enterNameInAddAddressSectionLocator));
            nameField.sendKeys(fullName);

            System.out.println("[INFO] Yazılan isim: " + nameField.getAttribute("value"));

            System.out.println("[INFO] İsim girildi: " + fullName);
            Thread.sleep(1000);

            // Email
            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(enterEmailAddressInAddAddressSectionLocator));
            emailField.sendKeys(email);

            System.out.println("[INFO] Yazılan email: " + emailField.getAttribute("value"));
            
            System.out.println("[INFO] Email girildi: " + email);
            Thread.sleep(1000);

            // Mobile Number
            WebElement mobileField = wait.until(ExpectedConditions.elementToBeClickable(enterMobileNumberInAddAddressSectionLocator));
            mobileField.sendKeys(mobile);
            System.out.println("[INFO] Telefon girildi: " + mobile);
            Thread.sleep(1000);

            // Address 1
            WebElement address1Field = wait.until(ExpectedConditions.elementToBeClickable(enterAddress1InAddAddressSectionLocator));
            address1Field.sendKeys(address1);
            System.out.println("[INFO] Adres 1 girildi: " + address1);
            Thread.sleep(1000);

            // Address 2
            WebElement address2Field = wait.until(ExpectedConditions.elementToBeClickable(enterAddress2InAddAddressSectionLocator));
            address2Field.sendKeys(address2);
            System.out.println("[INFO] Adres 2 girildi: " + address2);
            Thread.sleep(1000);

            // City
            WebElement cityField = wait.until(ExpectedConditions.elementToBeClickable(enterCityInAddAddressSectionLocator));
            cityField.sendKeys(city);
            System.out.println("[INFO] Şehir girildi: " + city);
            Thread.sleep(1000);

            // Postcode
            WebElement postcodeField = wait.until(ExpectedConditions.elementToBeClickable(enterPostcodeInAddAddressSectionLocator));
            postcodeField.sendKeys(postcode);
            System.out.println("[INFO] Posta Kodu girildi: " + postcode);
            Thread.sleep(1000);

            // Country
            WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(selectCountryInAddAddressSectionLocator));
            Select selectCountry = new Select(countryDropdown);

            // Tüm ülke isimlerini al
            List<WebElement> allCountries = selectCountry.getOptions();

            List<String> validCountries = allCountries.stream()
                    .map(WebElement::getText)
                    .filter(text -> !text.toLowerCase().contains("select")) // "Select country" gibi seçenekleri atla
                    .collect(Collectors.toList());

            String selectedCountry = validCountries.get(random.nextInt(validCountries.size()));
            selectCountry.selectByVisibleText(selectedCountry);
            System.out.println("[INFO] Ülke seçildi: " + selectedCountry);
            Thread.sleep(3000);

            // State - Eğer eyalet seçenekleri varsa seç
            try {
                WebElement stateDropdown = wait.until(ExpectedConditions.elementToBeClickable(selectStateInAddAddressSectionLocator));
                Select selectState = new Select(stateDropdown);
                List<WebElement> stateOptions = selectState.getOptions();

                if (stateOptions.size() > 1) {
                    int randomIndex = random.nextInt(stateOptions.size() - 1) + 1;
                    String selectedState = stateOptions.get(randomIndex).getText();
                    selectState.selectByVisibleText(selectedState);
                    System.out.println("[INFO] Eyalet seçildi: " + selectedState);
                } else {
                    System.out.println("[WARN] Eyalet seçenekleri bulunamadı.");
                }
            } catch (TimeoutException e) {
                System.out.println("[FAIL] Eyalet seçenekleri yüklenmedi: " + e.getMessage());
            }

            System.out.println("[PASS] Adres formu başarıyla dolduruldu.");

        } catch (Exception e) {
            System.out.println("[ERROR] Adres formu doldurulurken hata oluştu: " + e.getMessage());
            throw new RuntimeException(e);
        }
        Thread.sleep(4000);

    }

    // Add Address bolumunde form doldurulduktan sonra add address butonuna tikla
    public void clickAddAddressBtnInAddAddressSection() {
        WebElement addAddressBtn = wait.until(ExpectedConditions.elementToBeClickable(btnAddAddressInAddAddressSectionLocator));
        addAddressBtn.click();
        System.out.println("[INFO] Add Address bolumunde form doldurulduktan sonra 'Add Address' butonuna tıklandı.");

        // ⬇️ Buradan sonra formun açılıp açılmadığını kontrol ediyoruz:
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(enterNameInAddAddressSectionLocator));
        System.out.println("[PASS] 'Name' input alanı başarıyla görünür hale geldi.");
    }


    // Billing Address listesindeki adres elementini döner
    public boolean isBillingAddressVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        int retryCount = 3;
        int retryInterval = 2000;

        for (int i = 0; i < retryCount; i++) {
            try {
                System.out.println("[INFO] 'Billing Address' bölümünün yüklenmesi bekleniyor... Deneme: " + (i + 1));

                WebElement billingBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='checkout-box mt-0']")));

                System.out.println("[PASS] 'Billing Address' bölümü görünür hale geldi.");

                // 📌 İçeriğini yazdıralım
                String billingText = billingBox.getText().trim();
                System.out.println("[INFO] 'Billing Address' kutusu içeriği:\n" + billingText);

                return true;

            } catch (TimeoutException e) {
                System.out.println("[WARN] 'Billing Address' bölümü henüz görünür değil. Bekleniyor...");
                try {
                    Thread.sleep(retryInterval);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }

        System.out.println("[FAIL] 'Billing Address' bölümü belirtilen süre içinde görünür olmadı.");
        return false;
    }


    // Billing Address checkbox'ına tıklar
    public void selectBillingAddressWithJS() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            System.out.println("[INFO] 'Billing Address' checkbox'ının tıklanabilir olması bekleniyor...");

            WebElement checkBox = wait.until(ExpectedConditions.presenceOfElementLocated(checkMarkBillingAddressLocator4));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", checkBox); // Görünür alana getir
            Thread.sleep(500); // Scroll sonrası kısa bekleme
            js.executeScript("arguments[0].click();", checkBox); // JavaScript ile tıklama

            System.out.println("[PASS] 'Billing Address' checkbox'ı JavaScript ile başarıyla seçildi.");

        } catch (TimeoutException e) {
            System.out.println("[FAIL] 'Billing Address' checkbox'ı belirtilen süre içinde tıklanabilir hale gelmedi.");
            throw e;
        } catch (Exception e) {
            System.out.println("[FAIL] Tıklama işlemi başarısız: " + e.getMessage());
            throw e;
        }
    }

    // "Delivery address same as billing address" kutucuğuna tıklama işlemi
    public void clickDeliveryAddressSameAsBillingAddress() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            System.out.println("[INFO] 'Delivery address same as billing address' kutucuğu tıklanabilir durumda mı kontrol ediliyor...");
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(deliveryAddressSameAsBillingAddressBtnLocator1));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", checkbox);
            Thread.sleep(2000); // Scroll sonrası kısa bekleme

            js.executeScript("arguments[0].click();", checkbox);
            System.out.println("[PASS] 'Delivery address same as billing address' kutucuğu başarıyla tıklandı.");

        } catch (TimeoutException e) {
            System.out.println("[FAIL] 'Delivery address same as billing address' kutucuğu belirtilen süre içinde tıklanabilir hale gelmedi.");
            throw e;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Delivery Address bolumunun gorunur olmadigini test eder
    public void verifyDeliveryAddressNotVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            System.out.println("[INFO] 'Delivery Address' bölümünün kaybolması bekleniyor...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(deliveryAddressSectionLocator));
            System.out.println("[PASS] 'Delivery Address' bölümü artık görünmüyor.");

        } catch (TimeoutException e) {
            System.out.println("[FAIL] 'Delivery Address' bölümü belirtilen süre içinde kaybolmadı.");
            throw e;
        }
    }

    // Delivery address same as billing address kutucugunu unchecked yapar
    public void uncheckSameAsBillingAddressCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            System.out.println("[INFO] 'Delivery address same as billing address' kutucuğu unchecked yapilabilir durumda mı kontrol ediliyor...");
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(deliveryAddressSameAsBillingAddressBtnLocator1));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", checkbox);
            Thread.sleep(2000); // Scroll sonrası kısa bekleme

            js.executeScript("arguments[0].click();", checkbox);
            System.out.println("[PASS] 'Delivery address same as billing address' kutucuğu başarıyla unchecked yapildi.");

        } catch (TimeoutException e) {
            System.out.println("[FAIL] 'Delivery address same as billing address' kutucuğu belirtilen süre içinde unchecked yapilabilir hale gelmedi.");
            throw e;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyDeliveryAddressVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        int retryCount = 5;
        int retryInterval = 2000; // 2 saniye

        for (int i = 0; i < retryCount; i++) {
            try {
                System.out.println("[INFO] 'Delivery Address' bölümünün DOM'a eklenmesi bekleniyor... Deneme: " + (i + 1));
                WebElement deliveryAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='address-check']")));

                if (deliveryAddress.isDisplayed()) {
                    System.out.println("[PASS] 'Delivery Address' bölümü görünür hale geldi.");
                    return;
                }
            } catch (TimeoutException e) {
                System.out.println("[WARN] 'Delivery Address' bölümü henüz DOM'a eklenmemiş.");
            }

            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[FAIL] 'Delivery Address' bölümü belirtilen süre içinde görünür olmadı.");
    }

    public void clickAddAddressWithJavaScript() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // En sağlam yöntem: doğru locatordan WebElement bulup JS ile tıkla
            WebElement addAddressBtn = driver.findElement(By.xpath("(//button[@class='btn-add-address'])[2]"));
            js.executeScript("arguments[0].click();", addAddressBtn);

            System.out.println("[PASS] JS ile 2. Add Address butonuna başarıyla tıklandı.");
        } catch (Exception e) {
            System.out.println("[FAIL] JS ile tıklama başarısız: " + e.getMessage());
        }
    }


    public String getRealDeliveryAddress() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement addressElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='checkout-box deliveryAdd'] ")
        ));
        return addressElement.getText();
    }

    // Delivery Address checkbox'ına tıklar
    public void selectDeliveryAddressWithJS() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            System.out.println("[INFO] 'Delivery Address' checkbox'ının tıklanabilir olması bekleniyor...");

            WebElement checkBox = wait.until(ExpectedConditions.presenceOfElementLocated(checkMarkDeliveryAddressLocator));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", checkBox); // Görünür alana getir
            Thread.sleep(1000); // Scroll sonrası kısa bekleme
            js.executeScript("arguments[0].click();", checkBox); // JavaScript ile tıklama

            System.out.println("[PASS] 'Delivery Address' checkbox'ı JavaScript ile başarıyla seçildi.");

        } catch (TimeoutException e) {
            System.out.println("[FAIL] 'Delivery Address' checkbox'ı belirtilen süre içinde tıklanabilir hale gelmedi.");
            throw e;
        } catch (Exception e) {
            System.out.println("[FAIL] Tıklama işlemi başarısız: " + e.getMessage());
            throw e;
        }
    }

    // Shipping Methods listesinden birini seciyoruz
    public void selectShippingMethodWithJS() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            System.out.println("[INFO] 'Select Shipping Method' checkbox'ının tıklanabilir olması bekleniyor...");

            WebElement checkBox = wait.until(ExpectedConditions.presenceOfElementLocated(selectShippingMethodLocator));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", checkBox); // Görünür alana getir
            Thread.sleep(1000); // Scroll sonrası kısa bekleme
            js.executeScript("arguments[0].click();", checkBox); // JavaScript ile tıklama

            System.out.println("[PASS] 'Select Shipping Method' checkbox'ı JavaScript ile başarıyla seçildi.");

        } catch (TimeoutException e) {
            System.out.println("[FAIL] 'Select Shipping Method' checkbox'ı belirtilen süre içinde tıklanabilir hale gelmedi.");
            throw e;
        } catch (Exception e) {
            System.out.println("[FAIL] Tıklama işlemi başarısız: " + e.getMessage());
            throw e;
        }
    }

    // Term And Conditions checkbox tiklama
    public void selectTermAndConditionsCheckBoxWithJS() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            System.out.println("[INFO] 'Select Shipping Method' checkbox'ının tıklanabilir olması bekleniyor...");

            WebElement checkBox = wait.until(ExpectedConditions.presenceOfElementLocated(termsAndConditionsCheckBoxLocator));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", checkBox); // Görünür alana getir
            Thread.sleep(1000); // Scroll sonrası kısa bekleme
            js.executeScript("arguments[0].click();", checkBox); // JavaScript ile tıklama

            System.out.println("[PASS] 'Term And Conditions' checkbox'ı JavaScript ile başarıyla seçildi.");

        } catch (TimeoutException e) {
            System.out.println("[FAIL] 'Term And Conditions' checkbox'ı belirtilen süre içinde tıklanabilir hale gelmedi.");
            throw e;
        } catch (Exception e) {
            System.out.println("[FAIL] Tıklama işlemi başarısız: " + e.getMessage());
            throw e;
        }
    }

    // Place Order Now butonuna tiklama
    public void clickPlaceOrderNowBtnWithJS() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            System.out.println("[INFO] 'Place Order Now' butonunun tıklanabilir olması bekleniyor...");

            WebElement checkBox = wait.until(ExpectedConditions.presenceOfElementLocated(placeOrderNowSubmitBtnLocator));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", checkBox); // Görünür alana getir
            Thread.sleep(1000); // Scroll sonrası kısa bekleme
            js.executeScript("arguments[0].click();", checkBox); // JavaScript ile tıklama

            System.out.println("[PASS] 'Place Order Now' butonuna JavaScript ile başarıyla tiklandi.");

        } catch (TimeoutException e) {
            System.out.println("[FAIL] 'Place Order Now' butonu belirtilen süre içinde tıklanabilir hale gelmedi.");
            throw e;
        } catch (Exception e) {
            System.out.println("[FAIL] Tıklama işlemi başarısız: " + e.getMessage());
            throw e;
        }
    }
    public boolean isOrderSuccessMessageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//section[@id='shop-listing']")));
            return successMessage.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("[FAIL] Başarı mesajı belirtilen sürede görünmedi.");
            return false;
        }
    }













}




