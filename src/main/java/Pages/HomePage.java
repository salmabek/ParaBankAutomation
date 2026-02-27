package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;
    private final By Register = By.linkText("Register");
    private final By LogOut = By.linkText("Log Out");
    private final By openNewAccount = By.linkText("Open New Account");

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
    }

    public RegisterPage goToRegisterPage() {

        driver.findElement(Register).click();
        return new RegisterPage(driver);
    }

    public LoginPage goToLogOut()
    {
        driver.findElement(LogOut).click();
       return  new LoginPage(driver);
    }

    public AccountsPage goToOpenNewAccount ()
    {
        driver.findElement(openNewAccount).click();
        return  new AccountsPage(driver);
    }
}
