package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class TransferPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By linkTransfer = By.linkText("Transfer Funds");
    private By fromAccountSelect = By.id("fromAccountId");
    private By toAccountSelect = By.id("toAccountId");
    private By amountField = By.id("amount");
    private By transferButton = By.cssSelector("input[value='Transfer']");
    private By successTitle = By.xpath("//h1[contains(text(),'Transfer Complete')]");
    private By accountsOverviewLink = By.linkText("Accounts Overview");
    private By accountTable = By.id("accountTable");

    public TransferPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void transferAccount(String amount) {

        // 1️⃣ Go to Accounts Overview
        driver.findElement(accountsOverviewLink).click();

        By accountsLocator = By.xpath("//table[@id='accountTable']//td/a");

        // Wait until accounts are loaded
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(accountsLocator, 0));

        List<WebElement> accounts = driver.findElements(accountsLocator);

        System.out.println("Accounts found: " + accounts.size());

        if (accounts.size() < 2) {
            throw new RuntimeException("Not enough accounts to perform transfer. Found: " + accounts.size());
        }

        String fromAccNumber = accounts.get(0).getText().trim();
        String toAccNumber = accounts.get(1).getText().trim();

        double fromBalanceBefore = getAccountBalance(fromAccNumber);
        double toBalanceBefore = getAccountBalance(toAccNumber);

        System.out.println("From BEFORE: " + fromBalanceBefore);
        System.out.println("To BEFORE: " + toBalanceBefore);

        // 2️⃣ Go to Transfer Funds page
        driver.findElement(linkTransfer).click();

        // 3️⃣ Select correct accounts by visible text
        Select fromAccount = new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(fromAccountSelect)));
        fromAccount.selectByVisibleText(fromAccNumber);

        Select toAccount = new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(toAccountSelect)));
        toAccount.selectByVisibleText(toAccNumber);

        // 4️⃣ Enter amount
        WebElement amountInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(amountField));
        amountInput.clear();
        amountInput.sendKeys(amount);

        System.out.println("Entered amount: " + amountInput.getAttribute("value"));

        // 5️⃣ Click Transfer
        wait.until(ExpectedConditions.elementToBeClickable(transferButton)).click();

        // 6️⃣ Wait for Transfer Complete
        wait.until(ExpectedConditions.visibilityOfElementLocated(successTitle));

        System.out.println("Transfer completed.");

        // Small stabilization wait (ParaBank demo quirk)
        wait.until(driver -> driver.getPageSource().contains("Transfer Complete"));

        // 7️⃣ Go back to Accounts Overview
        driver.findElement(accountsOverviewLink).click();

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(accountsLocator, 0));

        double fromBalanceAfter = getAccountBalance(fromAccNumber);
        double toBalanceAfter = getAccountBalance(toAccNumber);

        System.out.println("From AFTER: " + fromBalanceAfter);
        System.out.println("To AFTER: " + toBalanceAfter);

        double amountValue = Double.parseDouble(amount);

        if (fromBalanceAfter == fromBalanceBefore &&
                toBalanceAfter == toBalanceBefore) {

            throw new RuntimeException("Balances did NOT change after transfer.");
        }

        System.out.println("Transfer verified successfully ✅");
    }
    private double getAccountBalance(String accountNumber) {

        driver.findElement(accountsOverviewLink).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(accountTable));

        By balanceLocator = By.xpath(
                "//table[@id='accountTable']//tr[td/a[normalize-space()='"
                        + accountNumber.trim() + "']]/td[2]"
        );

        // Wait until specific account row is present
        WebElement balanceElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(balanceLocator)
        );

        String balanceText = balanceElement.getText()
                .replace("$", "")
                .replace(",", "")
                .trim();

        return Double.parseDouble(balanceText);
    }
}