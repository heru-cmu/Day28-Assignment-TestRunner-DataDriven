package Core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        WebDriver webDriver = null;
        try {
            switch (browser.toLowerCase()) {
                case "chrome" -> {
                    String githubActions = System.getenv("GITHUB_ACTIONS");
                    boolean isCI = githubActions != null && githubActions.equals("true");

                    logger.info("=== Driver Initialization ===");
                    logger.info("Running in CI: {}", isCI);

                    if (!isCI) {
                        logger.info("Setting up ChromeDriver via WebDriverManager");
                        WebDriverManager.chromedriver().setup();
                    } else {
                        logger.info("Using pre-installed ChromeDriver from CI");
                        String chromeDriverPath = System.getenv("CHROMEDRIVER_PATH");
                        if (chromeDriverPath != null && !chromeDriverPath.isEmpty()) {
                            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                            logger.info("ChromeDriver path set to: {}", chromeDriverPath);
                        }
                    }

                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-plugins");
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

                    if (isCI) {
                        logger.info("Configuring Chrome for CI environment");
                        String chromeBin = System.getenv("CHROME_BIN");
                        if (chromeBin != null && !chromeBin.isEmpty()) {
                            options.setBinary(chromeBin);
                            logger.info("Chrome binary set to: {}", chromeBin);
                        }
                        options.addArguments("--headless=new");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--window-size=1920,1080");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--remote-debugging-port=9222");
                    } else {
                        options.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) " +
                                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
                    }

                    logger.info("Creating ChromeDriver instance...");
                    webDriver = new ChromeDriver(options);
                    logger.info("ChromeDriver created successfully!");
                }
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    logger.info("FirefoxDriver initialized successfully.");
                }
                case "edge" -> {
                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                    logger.info("EdgeDriver initialized successfully.");
                }
                default -> {
                    logger.error("Unsupported browser: {}", browser);
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
            }
        } catch (Exception e) {
            logger.error("Error initializing WebDriver for browser: {}", browser, e);
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
                logger.info("WebDriver quit successfully.");
            } catch (Exception e) {
                logger.error("Error quitting WebDriver", e);
            } finally {
                driver.remove();
            }
        }
    }
}
