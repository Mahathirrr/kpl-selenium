package com.dicoding.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * BasePage adalah kelas dasar untuk semua halaman
 * Kelas ini menyediakan metode-metode umum yang digunakan di semua halaman
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    
    /**
     * Konstruktor untuk BasePage
     * @param driver instance WebDriver
     * @param wait instance WebDriverWait
     */
    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    
    /**
     * Metode untuk menunggu elemen menjadi visible
     * @param locator locator elemen
     * @return WebElement yang sudah visible
     */
    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Metode untuk menunggu elemen menjadi clickable
     * @param locator locator elemen
     * @return WebElement yang sudah clickable
     */
    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Metode untuk mengklik elemen
     * @param locator locator elemen
     */
    protected void click(By locator) {
        waitForElementClickable(locator).click();
        logger.info("Mengklik elemen: " + locator);
    }
    
    /**
     * Metode untuk mengisi teks pada elemen
     * @param locator locator elemen
     * @param text teks yang akan diisi
     */
    protected void type(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
        logger.info("Mengisi teks pada elemen: " + locator + " dengan nilai: " + text);
    }
    
    /**
     * Metode untuk mendapatkan teks dari elemen
     * @param locator locator elemen
     * @return teks dari elemen
     */
    protected String getText(By locator) {
        String text = waitForElementVisible(locator).getText();
        logger.info("Mendapatkan teks dari elemen: " + locator + " dengan nilai: " + text);
        return text;
    }
    
    /**
     * Metode untuk memeriksa apakah elemen ditampilkan
     * @param locator locator elemen
     * @return true jika elemen ditampilkan, false jika tidak
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Metode untuk mendapatkan judul halaman
     * @return judul halaman
     */
    public String getPageTitle() {
        String title = driver.getTitle();
        logger.info("Mendapatkan judul halaman: " + title);
        return title;
    }
    
    /**
     * Metode untuk mendapatkan URL saat ini
     * @return URL saat ini
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.info("Mendapatkan URL saat ini: " + url);
        return url;
    }
}
