package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginButton = By.xpath("//input[@value='Log In']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

        public void LoginUser(WebDriver driver, String userNameValue, String passwordValue)
        {
            driver.findElement(username).sendKeys(userNameValue);
            driver.findElement(password).sendKeys(passwordValue);
            driver.findElement(loginButton).click();
        }
    }

