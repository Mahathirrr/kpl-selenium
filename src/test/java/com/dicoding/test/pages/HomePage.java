package com.dicoding.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * HomePage merepresentasikan halaman utama website Dicoding
 * Kelas ini berisi semua elemen dan aksi yang dapat dilakukan di halaman utama
 */
public class HomePage extends BasePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);
    
    // Locator untuk elemen-elemen di halaman utama
    private final By loginButtonLocator = By.xpath("//a[text()='Masuk']");
    private final By registerButtonLocator = By.xpath("//a[text()='Daftar']");
    private final By searchInputLocator = By.cssSelector("input[placeholder='Apa yang ingin Anda pelajari?']");
    private final By learningPathMenuLocator = By.xpath("//a[text()='Learning Path']");
    private final By langgananMenuLocator = By.xpath("//a[text()='Langganan']");
    private final By programMenuLocator = By.xpath("//a[text()='Program']");
    private final By capaianDampakMenuLocator = By.xpath("//a[text()='Capaian & Dampak']");
    private final By lainnyaMenuLocator = By.xpath("//a[text()='Lainnya']");
    private final By heroTitleLocator = By.xpath("//h1[contains(text(), 'Bangun Karirmu')]");
    
    /**
     * Konstruktor untuk HomePage
     * @param driver instance WebDriver
     * @param wait instance WebDriverWait
     */
    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        logger.info("Halaman utama Dicoding diinisialisasi");
    }
    
    /**
     * Metode untuk memverifikasi apakah halaman utama sudah dimuat
     * @return true jika halaman utama sudah dimuat, false jika tidak
     */
    public boolean isPageLoaded() {
        boolean isLoaded = isElementDisplayed(heroTitleLocator);
        logger.info("Verifikasi halaman utama dimuat: " + isLoaded);
        return isLoaded;
    }
    
    /**
     * Metode untuk mendapatkan judul hero section
     * @return teks judul hero section
     */
    public String getHeroTitle() {
        return getText(heroTitleLocator);
    }
    
    /**
     * Metode untuk mengklik tombol login
     * @return instance LoginPage
     */
    public LoginPage clickLoginButton() {
        logger.info("Mengklik tombol login");
        click(loginButtonLocator);
        return new LoginPage(driver, wait);
    }
    
    /**
     * Metode untuk mengklik tombol register
     * @return instance RegisterPage (belum diimplementasikan)
     */
    public void clickRegisterButton() {
        logger.info("Mengklik tombol register");
        click(registerButtonLocator);
        // Return RegisterPage jika sudah diimplementasikan
    }
    
    /**
     * Metode untuk melakukan pencarian
     * @param keyword kata kunci pencarian
     * @return instance SearchResultPage
     */
    public SearchResultPage search(String keyword) {
        logger.info("Melakukan pencarian dengan kata kunci: " + keyword);
        type(searchInputLocator, keyword);
        // Tekan Enter untuk melakukan pencarian
        driver.findElement(searchInputLocator).submit();
        return new SearchResultPage(driver, wait);
    }
    
    /**
     * Metode untuk mengklik menu Learning Path
     */
    public void clickLearningPathMenu() {
        logger.info("Mengklik menu Learning Path");
        click(learningPathMenuLocator);
    }
    
    /**
     * Metode untuk mengklik menu Langganan
     */
    public void clickLanggananMenu() {
        logger.info("Mengklik menu Langganan");
        click(langgananMenuLocator);
    }
    
    /**
     * Metode untuk mengklik menu Program
     */
    public void clickProgramMenu() {
        logger.info("Mengklik menu Program");
        click(programMenuLocator);
    }
    
    /**
     * Metode untuk mengklik menu Capaian & Dampak
     */
    public void clickCapaianDampakMenu() {
        logger.info("Mengklik menu Capaian & Dampak");
        click(capaianDampakMenuLocator);
    }
    
    /**
     * Metode untuk mengklik menu Lainnya
     */
    public void clickLainnyaMenu() {
        logger.info("Mengklik menu Lainnya");
        click(lainnyaMenuLocator);
    }
}
