package com.dicoding.test.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * BaseTest adalah kelas dasar untuk semua test case
 * Kelas ini menangani setup dan teardown WebDriver serta konfigurasi reporting
 */
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected static ExtentReports extent;
    protected ExtentTest test;
    
    private static final String BASE_URL = "https://www.dicoding.com";
    private static final int TIMEOUT = 10; // dalam detik
    private static final String SCREENSHOT_DIR = "test-output/screenshots";
    
    /**
     * Metode ini dijalankan sebelum semua test suite dimulai
     * Mengatur konfigurasi ExtentReports untuk reporting hasil test
     */
    @BeforeSuite
    public void setupSuite() {
        // Inisialisasi ExtentReports
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        // Tambahkan informasi sistem
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Browser", "Firefox");
        
        // Buat direktori untuk screenshot jika belum ada
        createScreenshotDirectory();
        
        logger.info("Test suite setup selesai");
    }
    
    /**
     * Metode ini dijalankan sebelum setiap test method
     * Mengatur WebDriver dan membuka browser Firefox
     */
    @BeforeMethod
    public void setupTest() {
        logger.info("Memulai setup WebDriver");
        
        // Setup WebDriverManager untuk Firefox
        WebDriverManager.firefoxdriver().setup();
        
        // Konfigurasi Firefox options
        FirefoxOptions options = new FirefoxOptions();
        // Uncomment baris berikut jika ingin menjalankan browser dalam mode headless
        options.addArguments("--headless");
        
        // Inisialisasi WebDriver
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        
        // Buka URL dasar
        driver.get(BASE_URL);
        logger.info("Browser Firefox dibuka dengan URL: " + BASE_URL);
    }
    
    /**
     * Metode ini dijalankan setelah setiap test method
     * Menangani hasil test dan mengambil screenshot jika test gagal
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test gagal: " + result.getName());
            
            // Ambil screenshot jika test gagal
            String screenshotPath = captureScreenshot(result.getName());
            
            // Tambahkan screenshot ke report
            if (test != null && screenshotPath != null) {
                try {
                    test.fail("Screenshot: " + test.addScreenCaptureFromPath(screenshotPath));
                } catch (Exception e) {
                    logger.error("Gagal menambahkan screenshot ke report", e);
                }
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("Test berhasil: " + result.getName());
        }
        
        // Tutup browser
        if (driver != null) {
            driver.quit();
            logger.info("Browser ditutup");
        }
    }
    
    /**
     * Metode ini dijalankan setelah semua test suite selesai
     * Menutup ExtentReports
     */
    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
            logger.info("Extent report disimpan");
        }
    }
    
    /**
     * Metode untuk mengambil screenshot
     * @param testName nama test untuk penamaan file screenshot
     * @return path ke file screenshot
     */
    private String captureScreenshot(String testName) {
        try {
            // Format nama file dengan timestamp
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = now.format(formatter);
            
            String fileName = SCREENSHOT_DIR + "/" + testName + "_" + timestamp + ".png";
            
            // Ambil screenshot
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshotFile.toPath(), Paths.get(fileName));
            
            logger.info("Screenshot disimpan: " + fileName);
            return fileName;
        } catch (Exception e) {
            logger.error("Gagal mengambil screenshot", e);
            return null;
        }
    }
    
    /**
     * Metode untuk membuat direktori screenshot
     */
    private void createScreenshotDirectory() {
        Path path = Paths.get(SCREENSHOT_DIR);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                logger.info("Direktori screenshot dibuat: " + SCREENSHOT_DIR);
            } catch (IOException e) {
                logger.error("Gagal membuat direktori screenshot", e);
            }
        }
    }
}
