# BelajarAuto - Day 28 Assignment TestRunner DataDriven

## 📌 Deskripsi
Project ini adalah tugas **Day 28 Bootcamp QA Automation**.  
Framework ini dibangun untuk menguji aplikasi web **SauceDemo** dengan pendekatan:

- **Page Object Model (POM)** → struktur kode rapi dan reusable.
- **TestNG** → sebagai test runner, mendukung grouping, parallel test, dan data provider.
- **Data Driven Testing** → input data login diambil dari file Excel.
- **WebDriverManager** → otomatis setup driver (Chrome, Firefox, Edge).
- **Log4j** → logging profesional.

Tujuan: membangun framework automation yang modular, dan maintainable.

---

## 📁 Struktur Project

| Folder/File                  | Deskripsi                                |
|------------------------------|------------------------------------------|
| `build.gradle.kts`           | Gradle build file (Kotlin DSL)           |
| `src/main/java/Core/BasePage.java` | Abstraksi dasar untuk semua page object |
| `src/main/java/saucedemo/LoginPage.java` | Page Object untuk login SauceDemo   |
| `src/test/java/Core/BaseTest.java` | Setup/teardown test lifecycle          |
| `src/test/java/Core/DriverManager.java` | Setup WebDriver (Chrome, Firefox, Edge) |
| `src/test/java/Core/ConfigReader.java` | Load konfigurasi environment         |
| `src/test/java/Core/TestUtils.java` | Helper baca data Excel                 |
| `src/test/java/saucedemo/LoginTest.java` | Test case login (positive, negative, data driven) |
| `src/test/resources/config/staging.properties` | Konfigurasi environment (baseUrl, user, password) |
| `src/test/resources/data/login-data-test.xlsx` | Data driven untuk login test        |
| `src/test/resources/suites/smoke.xml` | TestNG suite file (group smoke)       |

---

## ⚙️ Penjelasan Tiap File

- **BasePage.java** → induk semua page object, menyediakan driver & wait.
- **LoginPage.java** → page object login SauceDemo, punya action & helper untuk validasi login.
- **BaseTest.java** → lifecycle TestNG, setup driver sebelum test, quit setelah test.
- **DriverManager.java** → setup WebDriver (Chrome, Firefox, Edge), pakai ThreadLocal & Log4j.
- **ConfigReader.java** → load file `.properties` dari `src/test/resources/config`.
- **TestUtils.java** → utility baca Excel dengan Apache POI.
- **LoginTest.java** → test case login sukses, login gagal, data driven (Excel).
- **smoke.xml** → suite file TestNG untuk group `smoke`.
- **staging.properties** → konfigurasi environment (baseUrl, user, password).
- **login-data-test.xlsx** → data driven untuk login test.

---
## 🎯 Kesimpulan
Framework ini membuktikan:
- Integrasi penuh antara POM, TestNG, Data Driven Testing, dan WebDriverManager.
- Struktur modular, reusable, dan maintainable.
- Cocok dijadikan portfolio assignment QA Automation.

## 👨‍💻 Author
Heru Chairuddin Mukti Utama
