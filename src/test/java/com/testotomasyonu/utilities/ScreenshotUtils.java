package com.testotomasyonu.utilities;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class ScreenshotUtils {
    /*
     * Ekran görüntüsü alan ve belirtilen isimle kaydeden metot
     * @param driver WebDriver nesnesi
     * @param fileName Kaydedilecek dosya adı (uzantı otomatik eklenir)
     * @return Başarı durumu
     */
    public static void takeScreenshot(WebDriver driver, String fileName) {
        try {
            //Sayfanin tamamen yuklenmesini bekle (readyState 'complete' olana kadar)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(webDriver -> (JavascriptExecutor)webDriver);

           //Klasor kontrolu
            File targetDir = new File("target/screenshots");
            if (!targetDir.exists()) {
                targetDir.mkdirs(); // Klasor yoksa olustur
            }

            //Ekran goruntusu al
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            //Dosya al
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fullFileName = fileName + "_" +timestamp+ ".png";
            String filePath = "target/screenshots/" + fullFileName;

            //Dosyayi kopyala
            FileUtils.copyFile(screenshot, new File(filePath));


            System.out.println("Ekran görüntüsü kaydedildi: " + filePath);
        } catch (Exception e) {
            System.out.println("Ekran görüntüsü alınamadı: " + e.getMessage());
        }
    }

    
}
