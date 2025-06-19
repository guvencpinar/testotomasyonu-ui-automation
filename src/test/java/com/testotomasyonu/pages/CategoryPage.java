package com.testotomasyonu.pages;

import com.testotomasyonu.base.BasePage;
import com.testotomasyonu.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryPage extends BasePage {

    /*
    private final By DropdownElectronicsLink = By.xpath("(//a[text()='Electronics'])[1]");
    private final By DropdownMenFashionLink = By.xpath("(//a[text()='Men Fashion'])[1]");
    private final By DropdownWomenFashionLink = By.xpath("(//a[text()='Women Fashion'])[1]");
    private final By DropdownShoesLink = By.xpath("(//a[text()='Shoes'])[1]");
    private final By DropdownFurnitureLink = By.xpath("(//a[text()='Furniture'])[1]");
    private final By DropdownTravelLink = By.xpath("(//a[text()='Travel'])[1]");
    private final By DropdownKidsWearLink = By.xpath("(//a[text()='Kids Wear'])[1]");
    private final By DropdownGroceryLink = By.xpath("(//a[text()='Grocery'])[1]");
    */

    //Locators
    private final By selectCategoryDropdownButton = By.xpath(("(//a[@id='dropdownMenuLink'])[1]"));
    private final By homePageCategories = By.xpath("//div[@class='menu_link']");
    private final By dropdownSelectCategoryItems = By.xpath("//ul[@class='dropdown-menu ']");

    private final By breadcrumbCurrent = By.xpath("//li[@class='current']");
    private final By ElectronicsLink = By.xpath("(//a[text()='Electronics'])[3]");
    private final By MenFashionLink = By.xpath("(//a[text()='Men Fashion'])[3]");
    private final By WomenFashionLink = By.xpath("(//a[text()='Women Fashion'])[3]");
    private final By ShoesLink = By.xpath("(//a[text()='Shoes'])[3]");
    private final By FurnitureLink = By.xpath("(//a[text()='Furniture'])[3]");
    private final By TravelLink = By.xpath("(//a[text()='Travel'])[3]");
    private final By KidsWearLink = By.xpath("(//a[text()='Kids Wear'])[3]");
    private final By GroceryLink = By.xpath("(//a[text()='Grocery'])[3]");

    private final By topSellingProductsHeading = By.xpath("//*[.=' Top Selling Products  ']");

    private final By mostPopularSectionTitle = By.xpath("//div[@class='heading-lg ']");
    private final By mostPopularSectionCategoryItems = By.xpath("//ul[@class='nav nav-tabs ']");


    private WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    //Genel Method: Category linkine tiklamak icin
    public void clickCategoryLink (By categoryLinkLocator, String categoryName) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(categoryLinkLocator));
        Assert.assertTrue(element.isDisplayed(), categoryName+ " linki gorunur degil!");
        element.click();
        System.out.println("[INFO] " +categoryName+ " kategorisine tiklandi. ");
    }

    //Genel method: kategori URL dogrulamasi icin
    public void verifyCategoryURL(String expectedUrl, String categoryName){
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl, categoryName+" URL dogrulamasi basarisiz! ");
        System.out.println("[PASS] "+categoryName+" kategorisi URL dogrulandi: "+actualUrl);
    }

    //Genel Method: kategori sayfasi dogrulamasi icin  (breadcrumb + title)
    public void verifyCategoryPage(String expectedCategoryName) {
        WebElement breadcrumb = wait.until(ExpectedConditions.visibilityOfElementLocated(breadcrumbCurrent));
        String actualBreadcrumbText = breadcrumb.getText();
        Assert.assertEquals(actualBreadcrumbText, expectedCategoryName, "Breadcrumb '"+expectedCategoryName+"'degil!");
        System.out.println("[PASS] Breadcrumb dogrulandi: "+expectedCategoryName);

        String actualTitle= Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.toLowerCase().contains(expectedCategoryName.toLowerCase()), " Sayfa basliginda ' " +expectedCategoryName+ " ' gecmiyor!");
        System.out.println("[PASS] Sayfa basligi dogrulandi: "+actualTitle);
    }

    //1. Anasayfa kategorilerini doner.
    public List<String> getHomePageCategoryNames() {
        WebElement container = Driver.getDriver().findElement(homePageCategories);
        String rawText = container.getText();
        // \n ile ayir ve bos olmayanlari listele
        return Arrays.stream(rawText.split("\n")).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }

    //2. Select Category button gorunurlugunu test et ve tikla
    public void verifySelectCategoryDropdownVisible() {
        WebElement dropdown = Driver.getDriver().findElement(selectCategoryDropdownButton);
        Assert.assertTrue(dropdown.isDisplayed(), "[FAIL] Select Category dropdown gorunur degil! ");
        System.out.println("[PASS] Select Category dropdown gorunur durumda. ");
    }

    public  void  clickSelectCategoryDropdown(){
        WebElement dropdown = Driver.getDriver().findElement(selectCategoryDropdownButton);
        dropdown.click();
        System.out.println("[INFO] Select Category dropdown'a tiklandi. ");
    }

    //4. Dropdown'daki kategori isimlerini liste olarak dondur.
    public List<String> getSelectCategoryDropdownMenuNames() {
        WebElement container = Driver.getDriver().findElement(dropdownSelectCategoryItems);
        String rawText = container.getText();
        // \n ile ayir ve bos olmayanlari listele
        return Arrays.stream(rawText.split("\n")).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }

    //Sayfada Most Popular bolumune scroll
    public void scrollToMostPopularProducts() {
        WebElement section = Driver.getDriver().findElement(mostPopularSectionTitle);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",section);
        System.out.println("[INFO] Most Popular Products bolumune scroll yapildi. ");
    }

    //Most Popular Products bolum kategorileri gorunurluk testi
    public void verifyMostPopularCategoriesVisible() {
        List<WebElement> elements = Driver.getDriver().findElements(mostPopularSectionCategoryItems);
        Assert.assertFalse(elements.isEmpty(), "[FAIL] Most Popular Products bolumunde hic kategori bulunamadi! ");

        for (WebElement element : elements) {
            Assert.assertTrue(element.isDisplayed(), "[FAIL] Kategori elementi gorunur degil! ");
        }
        System.out.println("[PASS] Most Popular Products bolumundeki tum kategoriler gorunur. ");
    }

    //Most Popular Products bolumundeki kategori isimlerini dondur
    public List<String> getMostPopularProductCategoryNames() {
        WebElement container = Driver.getDriver().findElement(mostPopularSectionCategoryItems);
        String rawText = container.getText();
        // \n ile ayir ve bos olmayanlari listele
        return Arrays.stream(rawText.split("\n")).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }








}
