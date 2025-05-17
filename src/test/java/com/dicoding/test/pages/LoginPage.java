package com.dicoding.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * LoginPage merepresentasikan halaman login website Dicoding
 * Kelas ini berisi semua elemen dan aksi yang dapat dilakukan di halaman login
 */
public class LoginPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    
    // Locator untuk elemen-elemen di modal login
    private final By modalTitleLocator = By.xpath("//div[contains(@class, 'modal')]//*[text()='Masuk']");
    private final By emailInputLocator = By.cssSelector("input[placeholder='Email']");
    private final By passwordInputLocator = By.cssSelector("input[placeholder='Password']");
    private final By loginButtonLocator = By.xpath("//button[text()='Masuk']");
    private final By forgotPasswordLinkLocator = By.xpath("//a[text()='Lupa Password?']");
    private final By errorMessageLocator = By.cssSelector(".alert-danger");
    private final By registerLinkLocator = By.xpath("//a[text()='daftar']");
    private final By rememberMeCheckboxLocator = By.xpath("//label[text()='Remember me']");
    private final By googleLoginButtonLocator = By.xpath("//a[text()='Masuk dengan Google']");
    
    /**
     * Konstruktor untuk LoginPage
     * @param driver instance WebDriver
     * @param wait instance WebDriverWait
     */
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        logger.info("Modal login Dicoding diinisialisasi");
    }
    
    /**
     * Metode untuk memverifikasi apakah modal login sudah dimuat
     * @return true jika modal login sudah dimuat, false jika tidak
     */
    public boolean isPageLoaded() {
        boolean isLoaded = isElementDisplayed(modalTitleLocator) && 
                          isElementDisplayed(emailInputLocator) && 
                          isElementDisplayed(passwordInputLocator);
        logger.info("Verifikasi modal login dimuat: " + isLoaded);
        return isLoaded;
    }
    
    /**
     * Metode untuk mengisi email
     * @param email alamat email
     * @return instance LoginPage (this)
     */
    public LoginPage enterEmail(String email) {
        logger.info("Mengisi email: " + email);
        type(emailInputLocator, email);
        return this;
    }
    
    /**
     * Metode untuk mengisi password
     * @param password password
     * @return instance LoginPage (this)
     */
    public LoginPage enterPassword(String password) {
        logger.info("Mengisi password");
        type(passwordInputLocator, password);
        return this;
    }
    
    /**
     * Metode untuk mengklik tombol login
     * @return instance DashboardPage (belum diimplementasikan)
     */
    public void clickLoginButton() {
        logger.info("Mengklik tombol login");
        click(loginButtonLocator);
        // Return DashboardPage jika sudah diimplementasikan
    }
    
    /**
     * Metode untuk melakukan login
     * @param email alamat email
     * @param password password
     * @return instance DashboardPage (belum diimplementasikan)
     */
    public void login(String email, String password) {
        logger.info("Melakukan login dengan email: " + email);
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        // Return DashboardPage jika sudah diimplementasikan
    }
    
    /**
     * Metode untuk mengklik link lupa password
     */
    public void clickForgotPasswordLink() {
        logger.info("Mengklik link lupa password");
        click(forgotPasswordLinkLocator);
    }
    
    /**
     * Metode untuk mengklik link register
     */
    public void clickRegisterLink() {
        logger.info("Mengklik link register");
        click(registerLinkLocator);
    }
    
    /**
     * Metode untuk mengklik checkbox Remember me
     */
    public void clickRememberMeCheckbox() {
        logger.info("Mengklik checkbox Remember me");
        click(rememberMeCheckboxLocator);
    }
    
    /**
     * Metode untuk mengklik tombol login dengan Google
     */
    public void clickGoogleLoginButton() {
        logger.info("Mengklik tombol login dengan Google");
        click(googleLoginButtonLocator);
    }
    
    /**
     * Metode untuk mendapatkan pesan error
     * @return pesan error
     */
    public String getErrorMessage() {
        if (isElementDisplayed(errorMessageLocator)) {
            return getText(errorMessageLocator);
        }
        return "";
    }
    
    /**
     * Metode untuk memeriksa apakah pesan error ditampilkan
     * @return true jika pesan error ditampilkan, false jika tidak
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessageLocator);
    }
}
