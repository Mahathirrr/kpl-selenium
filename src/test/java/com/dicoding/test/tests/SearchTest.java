package com.dicoding.test.tests;

import com.aventstack.extentreports.ExtentTest;
import com.dicoding.test.base.BaseTest;
import com.dicoding.test.pages.HomePage;
import com.dicoding.test.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * SearchTest berisi test case untuk fungsi pencarian di website Dicoding
 */
public class SearchTest extends BaseTest {

    /**
     * Test untuk memverifikasi pencarian dengan kata kunci valid
     */
    @Test(description = "Verifikasi pencarian dengan kata kunci valid")
    public void testSearchWithValidKeyword() {
        // Inisialisasi test untuk reporting
        test = extent.createTest("Pencarian dengan Kata Kunci Valid", "Memverifikasi pencarian dengan kata kunci yang valid");
        
        // Inisialisasi HomePage
        HomePage homePage = new HomePage(driver, wait);
        
        // Verifikasi halaman utama dimuat
        Assert.assertTrue(homePage.isPageLoaded(), "Halaman utama tidak dimuat dengan benar");
        test.pass("Halaman utama berhasil dimuat");
        
        // Kata kunci pencarian
        String keyword = "Android";
        
        // Lakukan pencarian
        SearchResultPage searchResultPage = homePage.search(keyword);
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
        
        logger.info("Test pencarian dengan kata kunci valid berhasil");
    }
    
    /**
     * Test untuk memverifikasi pencarian dengan kata kunci tidak valid
     */
    @Test(description = "Verifikasi pencarian dengan kata kunci tidak valid")
    public void testSearchWithInvalidKeyword() {
        // Inisialisasi test untuk reporting
        test = extent.createTest("Pencarian dengan Kata Kunci Tidak Valid", "Memverifikasi pencarian dengan kata kunci yang tidak valid");
        
        // Inisialisasi HomePage
        HomePage homePage = new HomePage(driver, wait);
        
        // Verifikasi halaman utama dimuat
        Assert.assertTrue(homePage.isPageLoaded(), "Halaman utama tidak dimuat dengan benar");
        test.pass("Halaman utama berhasil dimuat");
        
        // Kata kunci pencarian yang tidak valid
        String invalidKeyword = "xyzabcdefghijklmnopqrstuvwxyz123456789";
        
        // Lakukan pencarian
        SearchResultPage searchResultPage = homePage.search(invalidKeyword);
        test.pass("Pencarian berhasil dilakukan dengan kata kunci tidak valid: " + invalidKeyword);
        
        // Verifikasi halaman hasil pencarian dimuat
        Assert.assertTrue(searchResultPage.isPageLoaded(), "Halaman hasil pencarian tidak dimuat dengan benar");
        test.pass("Halaman hasil pencarian berhasil dimuat");
        
        // Verifikasi halaman 404 ditampilkan
        Assert.assertTrue(searchResultPage.isErrorPageDisplayed(), "Halaman 404 tidak ditampilkan");
        test.pass("Halaman 404 ditampilkan sesuai ekspektasi");
        
        // Verifikasi pesan error
        String errorMessage = searchResultPage.getErrorMessage();
        Assert.assertFalse(errorMessage.isEmpty(), "Pesan error kosong");
        test.pass("Pesan error ditampilkan: " + errorMessage);
        
        logger.info("Test pencarian dengan kata kunci tidak valid berhasil");
    }
    
    /**
     * Test untuk memverifikasi navigasi kembali ke halaman utama dari halaman hasil pencarian
     */
    @Test(description = "Verifikasi navigasi kembali ke halaman utama dari halaman hasil pencarian")
    public void testNavigateBackToHomePage() {
        // Inisialisasi test untuk reporting
        test = extent.createTest("Navigasi Kembali ke Halaman Utama", "Memverifikasi navigasi kembali ke halaman utama dari halaman hasil pencarian");
        
        // Inisialisasi HomePage
        HomePage homePage = new HomePage(driver, wait);
        
        // Verifikasi halaman utama dimuat
        Assert.assertTrue(homePage.isPageLoaded(), "Halaman utama tidak dimuat dengan benar");
        test.pass("Halaman utama berhasil dimuat");
        
        // Lakukan pencarian
        SearchResultPage searchResultPage = homePage.search("Android");
        test.pass("Pencarian berhasil dilakukan");
        
        // Verifikasi halaman hasil pencarian dimuat
        Assert.assertTrue(searchResultPage.isPageLoaded(), "Halaman hasil pencarian tidak dimuat dengan benar");
        test.pass("Halaman hasil pencarian berhasil dimuat");
        
        // Kembali ke halaman utama
        homePage = searchResultPage.goToHomePage();
        test.pass("Kembali ke halaman utama");
        
        // Verifikasi halaman utama dimuat
        Assert.assertTrue(homePage.isPageLoaded(), "Halaman utama tidak dimuat dengan benar");
        test.pass("Halaman utama berhasil dimuat");
        
        logger.info("Test navigasi kembali ke halaman utama berhasil");
    }
}
