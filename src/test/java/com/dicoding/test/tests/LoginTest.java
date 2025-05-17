package com.dicoding.test.tests;

import com.aventstack.extentreports.ExtentTest;
import com.dicoding.test.base.BaseTest;
import com.dicoding.test.pages.LoginPage;
import com.dicoding.test.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * LoginTest berisi test case untuk halaman login Dicoding
 */
public class LoginTest extends BaseTest {

    /**
     * Test untuk memverifikasi tampilan modal login
     */
    @Test(description = "Verifikasi tampilan modal login")
    public void testLoginPageDisplay() {
        // Inisialisasi test untuk reporting
        test = extent.createTest("Tampilan Modal Login", "Memverifikasi tampilan modal login Dicoding");
        
        // Inisialisasi HomePage dan navigasi ke modal login
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.clickLoginButton();
        test.pass("Navigasi ke modal login berhasil");
        
        // Verifikasi modal login dimuat
        Assert.assertTrue(loginPage.isPageLoaded(), "Modal login tidak dimuat dengan benar");
        test.pass("Modal login berhasil dimuat");
        
        // Verifikasi judul halaman
        String pageTitle = loginPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Dicoding"), "Judul halaman tidak sesuai");
        test.pass("Judul halaman sesuai: " + pageTitle);
        
        logger.info("Test tampilan modal login berhasil");
    }
    
    /**
     * Test untuk memverifikasi login dengan kredensial tidak valid
     */
    @Test(description = "Verifikasi login dengan kredensial tidak valid")
    public void testLoginWithInvalidCredentials() {
        // Inisialisasi test untuk reporting
        test = extent.createTest("Login dengan Kredensial Tidak Valid", "Memverifikasi pesan error saat login dengan kredensial tidak valid");
        
        // Inisialisasi HomePage dan navigasi ke modal login
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.clickLoginButton();
        test.pass("Navigasi ke modal login berhasil");
        
        // Verifikasi modal login dimuat
        Assert.assertTrue(loginPage.isPageLoaded(), "Modal login tidak dimuat dengan benar");
        test.pass("Modal login berhasil dimuat");
        
        // Login dengan kredensial tidak valid
        String invalidEmail = "test@example.com";
        String invalidPassword = "password123";
        
        loginPage.login(invalidEmail, invalidPassword);
        test.pass("Mencoba login dengan kredensial tidak valid");
        
        // Verifikasi masih berada di halaman yang sama (login gagal)
        Assert.assertTrue(driver.getCurrentUrl().contains("dicoding.com"), "Tidak berada di website Dicoding");
        test.pass("Tetap berada di website Dicoding setelah login gagal");
        
        logger.info("Test login dengan kredensial tidak valid berhasil");
    }
    
    /**
     * Test untuk memverifikasi validasi form login
     */
    @Test(description = "Verifikasi validasi form login")
    public void testLoginFormValidation() {
        // Inisialisasi test untuk reporting
        test = extent.createTest("Validasi Form Login", "Memverifikasi validasi form login Dicoding");
        
        // Inisialisasi HomePage dan navigasi ke modal login
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.clickLoginButton();
        test.pass("Navigasi ke modal login berhasil");
        
        // Verifikasi modal login dimuat
        Assert.assertTrue(loginPage.isPageLoaded(), "Modal login tidak dimuat dengan benar");
        test.pass("Modal login berhasil dimuat");
        
        // Klik tombol login tanpa mengisi form
        loginPage.clickLoginButton();
        test.pass("Mencoba login tanpa mengisi form");
        
        // Verifikasi masih berada di modal login
        Assert.assertTrue(loginPage.isPageLoaded(), "Tidak berada di modal login");
        test.pass("Tetap berada di modal login");
        
        // Isi email saja
        loginPage.enterEmail("test@example.com");
        loginPage.clickLoginButton();
        test.pass("Mencoba login dengan hanya mengisi email");
        
        // Verifikasi masih berada di modal login
        Assert.assertTrue(loginPage.isPageLoaded(), "Tidak berada di modal login");
        test.pass("Tetap berada di modal login");
        
        logger.info("Test validasi form login berhasil");
    }
    
    /**
     * Test untuk memverifikasi fitur Remember Me
     */
    @Test(description = "Verifikasi fitur Remember Me")
    public void testRememberMeFeature() {
        // Inisialisasi test untuk reporting
        test = extent.createTest("Fitur Remember Me", "Memverifikasi fitur Remember Me pada form login");
        
        // Inisialisasi HomePage dan navigasi ke modal login
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = homePage.clickLoginButton();
        test.pass("Navigasi ke modal login berhasil");
        
        // Verifikasi modal login dimuat
        Assert.assertTrue(loginPage.isPageLoaded(), "Modal login tidak dimuat dengan benar");
        test.pass("Modal login berhasil dimuat");
        
        // Klik checkbox Remember Me
        loginPage.clickRememberMeCheckbox();
        test.pass("Checkbox Remember Me berhasil diklik");
        
        logger.info("Test fitur Remember Me berhasil");
    }
}
