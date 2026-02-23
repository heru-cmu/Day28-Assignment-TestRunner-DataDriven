package saucedemo;

import Core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorAlert;

    @FindBy(css = ".title")
    private WebElement pageTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Action
    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    // Getter
    public String getErrorMessage() {
        return errorAlert.getText();
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Helper untuk test
    public boolean isUserLoggedInSuccessfully() {
        try {
            waitForElementToBeVisible(pageTitle); // tunggu sampai .title muncul
            return pageTitle.isDisplayed() && pageTitle.getText().equalsIgnoreCase("Products");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            waitForElementToBeVisible(errorAlert); // tunggu sampai error muncul
            return errorAlert.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
