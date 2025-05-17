package com.dicoding.test.tests;

import com.aventstack.extentreports.ExtentTest;
import com.dicoding.test.base.BaseTest;
import com.dicoding.test.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * HomePageTest berisi test case untuk halaman utama Dicoding
 */
public class HomePageTest extends BaseTest {

    /**
     * Test untuk memverifikasi bahwa halaman utama dapat dimuat dengan benar
     */
    @Test(description = "Verifikasi halaman utama Dicoding dapat dimuat dengan benar")
    public void testHomePageLoaded() {
        // Inisialisasi test untuk reporting
        test = extent.createTest("Verifikasi Halaman Utama", "Memverifikasi bahwa halaman utama Dicoding dapat dimuat dengan benar");
        
        // Inisialisasi HomePage
        HomePage homePage = new HomePage(driver, wait);
        
        // Verifikasi halaman utama dimuat
        Assert.assertTrue(homePage.isPageLoaded(), "Halaman utama tidak dimuat dengan benar");
        test.pass("Halaman utama berhasil dimuat");
        
        // Verifikasi judul halaman
        String pageTitle = homePage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Dicoding"), "Judul halaman tidak sesuai");
        test.pass("Judul halaman sesuai: " + pageTitle);
        
        // Verifikasi URL halaman
        String currentUrl = homePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dicoding.com"), "URL halaman tidak sesuai");
        test.pass("URL halaman sesuai: " + currentUrl);
        
        // Verifikasi hero title ditampilkan
        String heroTitle = homePage.getHeroTitle();
        Assert.assertFalse(heroTitle.isEmpty(), "Hero title tidak ditampilkan");
        test.pass("Hero title ditampilkan: " + heroTitle);
        
        logger.info("Test halaman utama berhasil");
    }
    
    /**
     * Test untuk memverifikasi navigasi ke halaman login
     */
    @Test(description = "Verifikasi navigasi ke modal login")
    public void testNavigateToLogin() {
        // Inisialisasi test untuk reporting
        test = extent.createTest("Navigasi ke Modal Login", "Memverifikasi navigasi dari halaman utama ke modal login");
        
        // Inisialisasi HomePage
        HomePage homePage = new HomePage(driver, wait);
        
        // Verifikasi halaman utama dimuat
        Assert.assertTrue(homePage.isPageLoaded(), "Halaman utama tidak dimuat dengan benar");
        test.pass("Halaman utama berhasil dimuat");
        
        // Klik tombol login
        var loginPage = homePage.clickLoginButton();
        test.pass("Tombol login berhasil diklik");
        
        // Verifikasi modal login dimuat
        Assert.assertTrue(loginPage.isPageLoaded(), "Modal login tidak dimuat dengan benar");
        test.pass("Modal login berhasil dimuat");
        
        logger.info("Test navigasi ke modal login berhasil");
    }
    
    /**
     * Test untuk memverifikasi fungsi pencarian
     */
    @Test(description = "Verifikasi fungsi pencarian")
    public void testSearch() {
        // Inisialisasi test untuk reporting
        test = extent.createTest("Fungsi Pencarian", "Memverifikasi fungsi pencarian di halaman utama");
        
        // Inisialisasi HomePage
        HomePage homePage = new HomePage(driver, wait);
        
        // Verifikasi halaman utama dimuat
        Assert.assertTrue(homePage.isPageLoaded(), "Halaman utama tidak dimuat dengan benar");
        test.pass("Halaman utama berhasil dimuat");
        
        // Kata kunci pencarian
        String keyword = "Android";
        
        // Lakukan pencarian
        var searchResultPage = homePage.search(keyword);
        test.pass("Pencarian berhasil dilakukan dengan kata kunci: " + keyword);
        
        // Verifikasi halaman hasil pencarian dimuat
        Assert.assertTrue(searchResultPage.isPageLoaded(), "Halaman hasil pencarian tidak dimuat dengan benar");
        test.pass("Halaman hasil pencarian berhasil dimuat");
        
        // Verifikasi halaman 404 ditampilkan (karena pencarian mengarah ke 404)
        Assert.assertTrue(searchResultPage.isErrorPageDisplayed(), "Halaman 404 tidak ditampilkan");
        test.pass("Halaman 404 ditampilkan sesuai ekspektasi");
        
        // Verifikasi pesan error
        String errorTitle = searchResultPage.getErrorTitle();
        Assert.assertTrue(errorTitle.contains("Oppps"), "Judul error tidak sesuai");
        test.pass("Judul error sesuai: " + errorTitle);
        
        logger.info("Test fungsi pencarian berhasil");
    }
}
