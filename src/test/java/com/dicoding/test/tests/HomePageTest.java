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
        test = extent.createTest("Verifikasi Halaman Utama",
                "Memverifikasi bahwa halaman utama Dicoding dapat dimuat dengan benar");

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
}
