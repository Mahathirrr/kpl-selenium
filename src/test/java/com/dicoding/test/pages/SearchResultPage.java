package com.dicoding.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * SearchResultPage merepresentasikan halaman hasil pencarian website Dicoding
 * Kelas ini berisi semua elemen dan aksi yang dapat dilakukan di halaman hasil
 * pencarian
 */
public class SearchResultPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(SearchResultPage.class);

    // Locator untuk elemen-elemen di halaman hasil pencarian atau halaman 404
    private final By errorTitleLocator = By.xpath("//h1[contains(text(), 'Oppps')] | //h1[contains(text(), 'Oops')]");
    private final By errorMessageLocator = By
            .xpath("//*[contains(text(), 'Sudah aku cari')] | //*[contains(text(), 'tidak ditemukan')]");
    private final By searchInputLocator = By.cssSelector("input[placeholder='Apa yang ingin Anda pelajari?']");
    private final By logoLocator = By.xpath("//a/img[@alt='Dicoding Indonesia'] | //img[contains(@alt, 'Dicoding')]");

    /**
     * Konstruktor untuk SearchResultPage
     * 
     * @param driver instance WebDriver
     * @param wait   instance WebDriverWait
     */
    public SearchResultPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        logger.info("Halaman hasil pencarian Dicoding diinisialisasi");
    }

    /**
     * Metode untuk memverifikasi apakah halaman hasil pencarian sudah dimuat
     * 
     * @return true jika halaman hasil pencarian sudah dimuat, false jika tidak
     */
    public boolean isPageLoaded() {
        // Karena halaman pencarian bisa mengarah ke hasil atau 404, kita verifikasi
        // salah satu
        boolean logoVisible = isElementDisplayed(logoLocator);
        boolean correctUrl = getCurrentUrl().contains("dicoding.com");

        boolean isLoaded = logoVisible && correctUrl;
        logger.info("Verifikasi halaman hasil pencarian dimuat: " + isLoaded);
        return isLoaded;
    }

    /**
     * Metode untuk memeriksa apakah halaman 404 ditampilkan
     * 
     * @return true jika halaman 404 ditampilkan, false jika tidak
     */
    public boolean isErrorPageDisplayed() {
        boolean isErrorTitle = isElementDisplayed(errorTitleLocator);
        boolean isErrorMessage = isElementDisplayed(errorMessageLocator);
        boolean isErrorPage = isErrorTitle || isErrorMessage;
        logger.info("Verifikasi halaman 404 ditampilkan: " + isErrorPage);
        return isErrorPage;
    }

    /**
     * Metode untuk mendapatkan judul error
     * 
     * @return teks judul error
     */
    public String getErrorTitle() {
        if (isElementDisplayed(errorTitleLocator)) {
            return getText(errorTitleLocator);
        }
        return "";
    }

    /**
     * Metode untuk mendapatkan pesan error
     * 
     * @return pesan error
     */
    public String getErrorMessage() {
        if (isElementDisplayed(errorMessageLocator)) {
            return getText(errorMessageLocator);
        }
        return "";
    }

    /**
     * Metode untuk melakukan pencarian baru
     * 
     * @param keyword kata kunci pencarian
     * @return instance SearchResultPage (this)
     */
    public SearchResultPage searchAgain(String keyword) {
        logger.info("Melakukan pencarian baru dengan kata kunci: " + keyword);
        type(searchInputLocator, keyword);
        driver.findElement(searchInputLocator).submit();
        return this;
    }

    /**
     * Metode untuk kembali ke halaman utama
     * 
     * @return instance HomePage
     */
    public HomePage goToHomePage() {
        logger.info("Kembali ke halaman utama");
        click(logoLocator);
        return new HomePage(driver, wait);
    }
}