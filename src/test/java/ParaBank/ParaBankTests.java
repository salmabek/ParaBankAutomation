package ParaBank;

import AddUser.PasswordGenerator;
import AddUser.UserGenerator;
import Base.BasesTest;
import Pages.AccountsPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.lang.model.util.Elements;
import java.time.Duration;
import java.util.List;

import static java.lang.IO.println;

public class ParaBankTests extends BasesTest {
    private HomePage homePage;
    private final By succeedOpenedAccount = By.xpath("//h1[text()='Account Opened!']");



    @Test
    public void testLogin() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = homePage.goToRegisterPage();
        String passwordUser = PasswordGenerator.generatePassword(12);
        String user = UserGenerator.generateUsername();
        println(user);
        println(passwordUser);
        /*Register User *****/
        registerPage.registerUser(driver,"salma","bekkali","11 rue mamsmouda Hay El Hana","Casablanca","Casablanca","25000","0667676767","67/KLKKJJHHD/90", user ,passwordUser, passwordUser);
        /*Log Out ************/
        LoginPage loginPage =homePage.goToLogOut();
        /*Login   ***********/
        loginPage.LoginUser(driver,user,passwordUser);

        // Wait until account table is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        /*Open a new account ***/
       AccountsPage accountsPage = homePage.goToOpenNewAccount();
       accountsPage.createNewAccount(driver);
        /* Wait until the <h1> element with text "Account Opened" is visible into the homepage */
        String openedAccountText = wait.until( ExpectedConditions.visibilityOfElementLocated(succeedOpenedAccount)).getText();
        Assert.assertEquals(openedAccountText, "Account Opened!");

        accountsPage.getAllAccounts(driver);


        



    }
}



