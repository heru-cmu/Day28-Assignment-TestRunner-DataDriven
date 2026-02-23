package Core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static WebDriver driver;

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            try {
                switch (browser.toLowerCase()) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        logger.info("ChromeDriver initialized successfully.");
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        logger.info("FirefoxDriver initialized successfully.");
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        logger.info("EdgeDriver initialized successfully.");
                        break;
                    default:
                        logger.error("Unsupported browser: {}", browser);
                        throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
            } catch (Exception e) {
                logger.error("Error initializing WebDriver for browser: {}", browser, e);
                throw new RuntimeException("Failed to initialize WebDriver", e);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
                logger.info("WebDriver quit successfully.");
            } catch (Exception e) {
                logger.error("Error quitting WebDriver", e);
            } finally {
                driver = null;
            }
        }
    }
}