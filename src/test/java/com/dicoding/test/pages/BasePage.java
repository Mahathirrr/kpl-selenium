package com.dicoding.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
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
     * 
     * @param driver instance WebDriver
     * @param wait   instance WebDriverWait
     */
    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Metode untuk menunggu elemen menjadi visible
     * 
     * @param locator locator elemen
     * @return WebElement yang sudah visible
     */
    protected WebElement waitForElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.warn("Element not visible: " + locator);
            return null;
        }
    }

    /**
     * Metode untuk menunggu elemen menjadi clickable
     * 
     * @param locator locator elemen
     * @return WebElement yang sudah clickable
     */
    protected WebElement waitForElementClickable(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            logger.warn("Element not clickable: " + locator);
            return null;
        }
    }

    /**
     * Metode untuk mengklik elemen
     * 
     * @param locator locator elemen
     */
    protected void click(By locator) {
        WebElement element = waitForElementClickable(locator);
        if (element != null) {
            try {
                element.click();
                logger.info("Mengklik elemen: " + locator);
            } catch (Exception e) {
                logger.error("Gagal mengklik elemen: " + locator, e);
                // Coba klik dengan JavaScript jika click biasa gagal
                clickWithJS(element);
            }
        } else {
            logger.error("Tidak dapat mengklik elemen karena tidak ditemukan: " + locator);
        }
    }

    /**
     * Metode untuk mengklik elemen dengan JavaScript
     * 
     * @param element WebElement yang akan diklik
     */
    protected void clickWithJS(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            logger.info("Mengklik elemen dengan JavaScript");
        } catch (Exception e) {
            logger.error("Gagal mengklik elemen dengan JavaScript", e);
        }
    }

    /**
     * Metode untuk mengisi teks pada elemen
     * 
     * @param locator locator elemen
     * @param text    teks yang akan diisi
     */
    protected void type(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        if (element != null) {
            try {
                element.clear();
                element.sendKeys(text);
                logger.info("Mengisi teks pada elemen: " + locator + " dengan nilai: " + text);
            } catch (Exception e) {
                logger.error("Gagal mengisi teks pada elemen: " + locator, e);
            }
        } else {
            logger.error("Tidak dapat mengisi teks karena elemen tidak ditemukan: " + locator);
        }
    }

    /**
     * Metode untuk mendapatkan teks dari elemen
     * 
     * @param locator locator elemen
     * @return teks dari elemen
     */
    protected String getText(By locator) {
        WebElement element = waitForElementVisible(locator);
        if (element != null) {
            String text = element.getText();
            logger.info("Mendapatkan teks dari elemen: " + locator + " dengan nilai: " + text);
            return text;
        } else {
            logger.warn("Tidak dapat mendapatkan teks karena elemen tidak ditemukan: " + locator);
            return "";
        }
    }

    /**
     * Metode untuk memeriksa apakah elemen ditampilkan
     * 
     * @param locator locator elemen
     * @return true jika elemen ditampilkan, false jika tidak
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    /**
     * Metode untuk mendapatkan judul halaman
     * 
     * @return judul halaman
     */
    public String getPageTitle() {
        String title = driver.getTitle();
        logger.info("Mendapatkan judul halaman: " + title);
        return title;
    }

    /**
     * Metode untuk mendapatkan URL saat ini
     * 
     * @return URL saat ini
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.info("Mendapatkan URL saat ini: " + url);
        return url;
    }

    /**
     * Metode untuk scroll ke elemen
     * 
     * @param locator locator elemen
     */
    protected void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        if (element != null) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", element);
                logger.info("Scroll ke elemen: " + locator);
            } catch (Exception e) {
                logger.error("Gagal scroll ke elemen: " + locator, e);
            }
        }
    }

    /**
     * Metode untuk menunggu halaman selesai dimuat
     */
    protected void waitForPageLoad() {
        try {
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));
            logger.info("Halaman selesai dimuat");
        } catch (TimeoutException e) {
            logger.warn("Timeout menunggu halaman dimuat", e);
        }
    }
}