package ParaBank;

import AddUser.PasswordGenerator;
import AddUser.UserGenerator;
import Base.BasesTest;
import Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static java.lang.IO.println;

public class ParaBankTests extends BasesTest {
    private HomePage homePage;
    private final By succeedOpenedAccount = By.xpath("//h1[text()='Account Opened!']");
    private final By results = By.xpath("//div[@id='showResult']//h1");
    private final By openNewAccount = By.linkText("Open New Account");



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

        WebElement newAccElement = driver.findElement(By.id("newAccountId")); // ou via le message affiché
         String newAccountNumber = newAccElement.getText().trim();
        System.out.println("New Account Number: " + newAccountNumber);
       /* Get All Account ***/
        accountsPage.getAllAccounts(driver);
        /*Transfer ***/
        TransferPage transferPage = new TransferPage(driver);
        transferPage.transferAccount("50");
        FindTransactionsPage findTransactionsPage = homePage.goToFindTransactionPage();
        List<WebElement> listTransactions= findTransactionsPage.getTransactions("01-01-2020","02-29-2026");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("transactionTable")));
        List<WebElement> headers = driver.findElements(
                By.xpath("//table[@id='transactionTable']/thead/tr/th"));

        for (WebElement header : headers) {
            System.out.print(header.getText() + " | ");
        }
        System.out.println();

        for (WebElement row : listTransactions) {
            System.out.println(
                    row.findElement(By.xpath("./td[1]")).getText() + " | " +
                            row.findElement(By.xpath("./td[2]")).getText() + " | " +
                            row.findElement(By.xpath("./td[3]")).getText() + " | " +
                            row.findElement(By.xpath("./td[4]")).getText()
            );
        }
    }
}



