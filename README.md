# BelajarAuto - Day 28 Assignment TestRunner DataDriven

## 📌 Deskripsi
Project ini adalah tugas **Day 28 Bootcamp QA Automation**.  
Framework ini dibangun untuk menguji aplikasi web **SauceDemo** dengan pendekatan:

- **Page Object Model (POM)** → struktur kode rapi dan reusable.
- **TestNG** → sebagai test runner, mendukung grouping, parallel test, dan data provider.
- **Data Driven Testing** → input data login diambil dari file Excel.
- **WebDriverManager** → otomatis setup driver (Chrome, Firefox, Edge).
- **Log4j** → logging profesional.

Tujuan: membangun framework automation yang modular, maintainable, dan bisa dijadikan portfolio QA Engineer.

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

## 🚀 Macam -macam Cara Menjalankan

1. Clone repo:
   ```bash
   git clone https://github.com/heru-cmu/Day28-Assignment-TestRunner-DataDriven.git
   cd Day28-Assignment-TestRunner-DataDriven
2. Menjalankan dengan Gradle:
    ```bash
   ./gradlew test
- Report default Gradle ada di: `build/reports/tests/test/index.html`
- Bisa override environment & browser:
    ```bash
  ./gradlew test -Penv=staging -Pbrowser=chrome
3. Menjalankan dengan IntelliJ IDEA
   Ada beberapa opsi:
    - Klik kanan `smoke.xml` → Run
    - Run/Debug Configuration  
      Buat konfigurasi baru:
      - Test kind = Suite 
      - Suite file = `src/test/resources/suites/smoke.xml`
---
## 📊 Hasil Test
- Semua test PASS ✅ (8/8)
---
## 🎯 Kesimpulan
Framework ini membuktikan:
- Integrasi penuh antara POM, TestNG, Data Driven Testing, dan WebDriverManager.
- Struktur modular, reusable, dan maintainable.
- Cocok dijadikan portfolio assignment QA Automation.
---
## 👨‍💻 Author
Heru Chairuddin Mukti Utama
