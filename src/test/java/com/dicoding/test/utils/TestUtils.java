package com.dicoding.test.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestUtils berisi utilitas yang digunakan dalam test
 */
public class TestUtils {
    private static final Logger logger = LogManager.getLogger(TestUtils.class);
    
    /**
     * Metode untuk menunggu halaman dimuat sepenuhnya
     * @param driver instance WebDriver
     * @param timeoutInSeconds timeout dalam detik
     */
    public static void waitForPageLoad(WebDriver driver, int timeoutInSeconds) {
        logger.info("Menunggu halaman dimuat sepenuhnya");
        
        long endTime = System.currentTimeMillis() + (timeoutInSeconds * 1000);
        
        while (System.currentTimeMillis() < endTime) {
            String state = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState");
            if (state.equals("complete")) {
                logger.info("Halaman dimuat sepenuhnya");
                return;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.error("Terjadi kesalahan saat menunggu halaman dimuat", e);
            }
        }
        
        logger.warn("Timeout: Halaman tidak dimuat sepenuhnya dalam " + timeoutInSeconds + " detik");
    }
    
    /**
     * Metode untuk scroll ke elemen
     * @param driver instance WebDriver
     * @param element elemen yang akan di-scroll
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        logger.info("Scroll ke elemen");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error("Terjadi kesalahan saat scroll ke elemen", e);
        }
    }
    
    /**
     * Metode untuk mendapatkan timestamp saat ini
     * @return timestamp dalam format yyyyMMdd_HHmmss
     */
    public static String getCurrentTimestamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return formatter.format(new Date());
    }
    
    /**
     * Metode untuk membuat direktori jika belum ada
     * @param directoryPath path direktori
     * @return true jika direktori berhasil dibuat atau sudah ada, false jika gagal
     */
    public static boolean createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                logger.info("Direktori berhasil dibuat: " + directoryPath);
                return true;
            } else {
                logger.error("Gagal membuat direktori: " + directoryPath);
                return false;
            }
        }
        return true;
    }
    
    /**
     * Metode untuk highlight elemen
     * @param driver instance WebDriver
     * @param element elemen yang akan di-highlight
     */
    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; background: yellow;');", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error("Terjadi kesalahan saat highlight elemen", e);
        }
        js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
    }
}
