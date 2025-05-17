# Automation Testing untuk Website Dicoding dengan Selenium Java dan Firefox

Proyek ini berisi kode automation testing untuk website Dicoding menggunakan Selenium WebDriver dengan Java dan browser Firefox. Proyek ini mengimplementasikan best practice dalam automation testing seperti Page Object Model (POM), reporting dengan ExtentReports, dan logging dengan Log4j.

## Struktur Proyek

```
DicodingAutomationTest/
├── src/
│   └── test/
│       └── java/
│           └── com/
│               └── dicoding/
│                   └── test/
│                       ├── base/
│                       │   └── BaseTest.java
│                       ├── pages/
│                       │   ├── BasePage.java
│                       │   ├── HomePage.java
│                       │   ├── LoginPage.java
│                       │   └── SearchResultPage.java
│                       └── tests/
│                           ├── HomePageTest.java
│                           ├── LoginTest.java
│                           └── SearchTest.java
├── pom.xml
├── testng.xml
└── README.md
```

## Prasyarat

Sebelum menjalankan automation test ini, pastikan Anda telah menginstal:

1. **Java Development Kit (JDK)** - versi 11 atau lebih tinggi
2. **Maven** - untuk manajemen dependensi dan menjalankan test
3. **Firefox Browser** - browser yang digunakan untuk testing

## Cara Menjalankan Test

### 1. Clone atau Download Proyek

```bash
git clone https://github.com/username/DicodingAutomationTest.git
cd DicodingAutomationTest
```

Atau download dan ekstrak file ZIP proyek.

### 2. Siapkan File TestNG XML

Buat file `testng.xml` di root proyek dengan konten berikut:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Dicoding Automation Test Suite">
    <test name="Dicoding Website Tests">
        <classes>
            <class name="com.dicoding.test.tests.HomePageTest" />
            <class name="com.dicoding.test.tests.LoginTest" />
            <class name="com.dicoding.test.tests.SearchTest" />
        </classes>
    </test>
</suite>
```

### 3. Menjalankan Test dengan Maven

Untuk menjalankan semua test:

```bash
mvn clean test
```

Untuk menjalankan test tertentu:

```bash
mvn clean test -Dtest=HomePageTest
```

### 4. Melihat Hasil Test

Setelah test selesai dijalankan, Anda dapat melihat hasil test di:

- **Extent Report**: `test-output/extent-report.html`
- **Screenshots**: `test-output/screenshots/` (jika ada test yang gagal)
- **TestNG Report**: `target/surefire-reports/index.html`

## Fitur

1. **Page Object Model (POM)**: Memisahkan logika halaman dari test case
2. **Reporting**: Menggunakan ExtentReports untuk laporan yang informatif dan visual
3. **Logging**: Menggunakan Log4j untuk logging aktivitas test
4. **Screenshot**: Mengambil screenshot otomatis saat test gagal
5. **WebDriverManager**: Mengelola driver browser secara otomatis

## Test Case yang Diimplementasikan

1. **HomePageTest**:
   - Verifikasi halaman utama dapat dimuat dengan benar
   - Verifikasi navigasi ke halaman login
   - Verifikasi fungsi pencarian

2. **LoginTest**:
   - Verifikasi tampilan halaman login
   - Verifikasi login dengan kredensial tidak valid
   - Verifikasi validasi form login

3. **SearchTest**:
   - Verifikasi pencarian dengan kata kunci valid
   - Verifikasi pencarian dengan kata kunci tidak valid
   - Verifikasi pencarian dengan kata kunci kosong

## Troubleshooting

### Browser Tidak Terbuka

Pastikan Firefox terinstal di sistem Anda. Jika Anda ingin menjalankan test dalam mode headless (tanpa UI browser), buka file `BaseTest.java` dan uncomment baris berikut:

```java
// options.addArguments("--headless");
```

### Timeout Error

Jika test gagal karena timeout, Anda dapat meningkatkan nilai timeout di `BaseTest.java`:

```java
private static final int TIMEOUT = 10; // Ubah nilai ini (dalam detik)
```

### Element Not Found Error

Jika test gagal karena elemen tidak ditemukan, kemungkinan locator yang digunakan tidak sesuai dengan struktur halaman saat ini. Periksa dan perbarui locator di file Page Object yang sesuai.

## Pengembangan Lebih Lanjut

Untuk menambahkan test case baru:

1. Buat Page Object baru di package `com.dicoding.test.pages` jika diperlukan
2. Buat class test baru di package `com.dicoding.test.tests`
3. Tambahkan class test baru ke file `testng.xml`

## Catatan

- Test ini dirancang untuk website Dicoding per tanggal pembuatan. Jika struktur website berubah, locator mungkin perlu diperbarui.
- Untuk menjalankan test di browser lain, Anda perlu memodifikasi `BaseTest.java` dan mengganti FirefoxDriver dengan driver browser yang sesuai.
