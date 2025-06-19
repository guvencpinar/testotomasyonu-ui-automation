package com.testotomasyonu.tests;

import com.testotomasyonu.base.BaseTest;
import com.testotomasyonu.pages.CategoryPage;
import com.testotomasyonu.utilities.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class US003CategoryTests extends BaseTest {

    CategoryPage categoryPage = new CategoryPage();

    @Test(description =" Kategori linkleri tiklandiktan sonra dogrulama testleri ")
    public void testCategoryNavigation() {


        //1- URL' ye git
        driver.get("https://testotomasyonu.com/");
        System.out.println("[INFO] Anasayfaya gidildi: "+driver.getCurrentUrl());

        //2- Sayfaya gittigini dogrula
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://testotomasyonu.com/", "Anasayfaya gidilemedi");
        System.out.println("[PASS] Anasayfa URL dogrulandi. ");


        //3- Kategori bilgileri (kategori adi, xpath index'i, URL'deki kategori ID'si)
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

        //4- Donguyle her kategori icin test islmeleri
        for (String data : categories){
            String[] parts = data.split("\\|");
            String name = parts[0];
            String id = parts[1];

            //Kategori linkine tikla
            By categoryLink = By.xpath("(//a[text()='"+name+"'])[3]");
            categoryPage.clickCategoryLink(categoryLink, name);

            //URL dogrula
            String expectedUrl = "https://testotomasyonu.com/category/"+id+"/products";
            categoryPage.verifyCategoryURL(expectedUrl, name);

            //Breadcrumb ve Title dogrulama
            categoryPage.verifyCategoryPage(name);

            //Geri don
            driver.navigate().back();
            System.out.println("[INFO] Anasayfaya geri donuldu. \n");
        }

        /*
        // =========================
        // ELECTRONICS CATEGORY TESTI
        // =========================

        categoryPage.clickCategoryLink(By.xpath("(//a[text()='Electronics'])[3]"), "Electronics");
        categoryPage.verifyCategoryURL("https://testotomasyonu.com/category/7/products", "Electronics");
        categoryPage.verifyCategoryPage("Electronics");

        // Navigated back to the homepage
        driver.navigate().back();
        System.out.println("[INFO] Anasayfaya geri dönüldü.");

        //Normalde her bir category icin bu sekilde testler olusturmustuk
        */
    }


    @Test (description = "Anasayfa ve Arama kutusu kategori listesi karsilastrima testi")
    public void testCategoryListComparison() {

        //1-2  URL' ye git ve Sayfaya gittigini dogrula
        Driver.getDriver().get("https://testotomasyonu.com/");
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://testotomasyonu.com/", "[FAIL] Anasayfa yüklenemedi.");
        System.out.println("[PASS] Anasayfa URL doğrulandı.");

        //3-4 Anasayfa kategorileri gorunurlugunu test et ve liste olarak kaydet
        List<String> homeCategories = categoryPage.getHomePageCategoryNames();
        System.out.println("[INFO] Anasayfa kategorileri: "+String.join(" , ", homeCategories));

        //5-6 Arama kutusu dropdown'u gorunur ve tiklanabilir olmali
        categoryPage.verifySelectCategoryDropdownVisible();
        categoryPage.clickSelectCategoryDropdown();

        //7-8  Dropdown kategori listesini al
        List<String> dropdownCategories = categoryPage.getSelectCategoryDropdownMenuNames();
        System.out.println("[INFO] Dropdown kategorileri: " +String.join(" , ", dropdownCategories));

        //9- Liste uzunluklarini karsilastir
        System.out.println("[INFO] Anasayfa kategori sayisi: " +homeCategories.size());
        System.out.println("[INFO] Dropdown kategori sayisi: " +dropdownCategories.size());
        Assert.assertEquals(dropdownCategories.size(), homeCategories.size(), "Kategori listesi uzunluklari esit degil");
        System.out.println("[PASS] Anasayfa ve dropdown kategori sayilari esittir: " +homeCategories.size());

        //10-17 Belirli kategorilerin dropdown'da yer aldigini dogrula
        String[] expectedCategories = {
                "Electronics", "Men Fashion", "Women Fashion", "Shoes", "Furniture", "Travel", "Kids Wear", "Grocery"
        };
        for (String expected : expectedCategories){
            Assert.assertTrue(dropdownCategories.contains(expected), "[FAIL]" + expected + " dropdown listesinde bulunamadi! ");
            System.out.println("[PASS]" + expected+ " dropdown listesinde bulundu. ");
        }


    }

    @Test (description = "Anasayfa ve Most Popular Products kategori listesi karsilastirma testi ")
    public void testMostPopularProductsCategories(){

        // 1-2 Url' ye git ve Sayfaya gittigini dogrula
        Driver.getDriver().get("https://testotomasyonu.com/");
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://testotomasyonu.com/", "[FAIL] Anasayfa yüklenemedi.");
        System.out.println("[PASS] Anasayfa URL doğrulandı.");

        // 3-4 Anasayfa kategori isimlerini al
        List<String> homeCategories = categoryPage.getHomePageCategoryNames();
        System.out.println("INFO] Anasayfa kategorileri: " + String.join(" , ", homeCategories));

        // 5 Most Popular Products bolumune git (scroll)
        categoryPage.scrollToMostPopularProducts();

        //6  Most Popular Bolumu kategorilerin gorunurluk testi.
        categoryPage.verifyMostPopularCategoriesVisible();

        // 7 Bolumdeki kategori isimlerini al
        List<String> mostPopularCategories = categoryPage.getMostPopularProductCategoryNames();
        System.out.println("[INFO] Most Popular Products kategorileri: " +String.join(" , ", mostPopularCategories));

        //8 Uuzunluklari karsilastir
        Assert.assertEquals(mostPopularCategories.size(), homeCategories.size(),"[FAIL] Kategori listesi uzunluklari esit degil! ");
        System.out.println("[PASS] Kategori listesi uzunluklari esittir: " +homeCategories.size());

        //9-16 Her kategori icin eslesme dogrulamasi
        for (String category : homeCategories) {
            Assert.assertTrue(mostPopularCategories.contains(category),
                    "[FAIL] '"+category+"'Most Popular Products'da bulunamadi.");
            System.out.println("[PASS] '"+category+ "'Most Popular Products bolumunde bulundu. ");
        }

    }



}
