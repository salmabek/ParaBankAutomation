package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AccountsPage {

    private final WebDriver driver;
    private final By accountTypeDropdown = By.id("type");
    private final By openAccountButton = By.xpath("//input[@value='Open New Account']");
    private final By newAccount = By.id("newAccountId");

    public AccountsPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void createNewAccount(WebDriver driver)
    {
        Select select = new Select(driver.findElement(accountTypeDropdown));
        select.selectByVisibleText("CHECKING");
        driver.findElement(openAccountButton).click();
    }

    public void getAllAccounts(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        String newAccountNumber = driver.findElement(newAccount).getText();
        System.out.println("New Account Number: " + newAccountNumber);


        driver.findElement(By.linkText("Accounts Overview")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#accountTable tbody tr td a")));

        List<WebElement> accountElements = driver.findElements(By.cssSelector("#accountTable tbody tr td a"));
        List<String> accountNumbers = accountElements.stream()
                .map(WebElement::getText)
                .toList();

        System.out.println("Account Number : " + accountNumbers.size());

        if (accountNumbers.contains(newAccountNumber)) {
            System.out.println("✅ New account is listed in Accounts Overview");

            for (String accountNumber : accountNumbers) {
                System.out.println("Account found: " + accountNumber);

                driver.findElement(By.linkText(accountNumber)).click();


                wait.until(driver1 -> {
                    WebElement balanceElement = driver1.findElement(By.id("balance"));
                    return balanceElement.isDisplayed() && !balanceElement.getText().isEmpty();
                });


                String balance = driver.findElement(By.id("balance")).getText();
                System.out.println("Solde du compte " + accountNumber + " : " + balance);


                driver.findElement(By.linkText("Accounts Overview")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#accountTable tbody tr td a")));
            }
        } else {
            System.out.println("❌ New account is NOT listed in Accounts Overview");
        }
    }


}






