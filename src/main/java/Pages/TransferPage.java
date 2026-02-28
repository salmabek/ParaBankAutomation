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

    WebDriver driver;
    WebDriverWait wait;

    By linkTransfer = By.linkText("Transfer Funds");

    public TransferPage(WebDriver driver) {
        this.driver = driver;
    }

    // Méthode principale pour transférer
    public void transferAccount(String amount) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // 1️⃣ Aller sur Transfer Funds
        driver.findElement(linkTransfer).click();


        // 2️⃣ Sélectionner From Account
        WebElement fromAccountElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("fromAccountId"))
        );
        Select fromAccount = new Select(fromAccountElement);
        wait.until(d -> fromAccount.getOptions().size() > 1);
        fromAccount.selectByIndex(0);

        // 3️⃣ Sélectionner To Account
        WebElement toAccountElement = driver.findElement(By.id("toAccountId"));
        Select toAccount = new Select(toAccountElement);
        wait.until(d -> toAccount.getOptions().size() > 1);
        toAccount.selectByIndex(1);

        String fromAccNumber = fromAccount.getFirstSelectedOption().getText();
        String toAccNumber = toAccount.getFirstSelectedOption().getText();

        System.out.println("From Account: " + fromAccNumber);
        System.out.println("To Account: " + toAccNumber);

        // 4️⃣ Lire solde avant transfert
        double fromBalanceBefore = getAccountBalance(fromAccNumber);
        double toBalanceBefore = getAccountBalance(toAccNumber);

        System.out.println("From Account Balance BEFORE: $" + fromBalanceBefore);
        System.out.println("To Account Balance BEFORE: $" + toBalanceBefore);


        driver.findElement(linkTransfer).click();

        // 5️⃣ Remplir le montant
        WebElement amountField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("amount"))
        );
        amountField.clear();
        amountField.sendKeys(amount);
        System.out.println("Amount to transfer: $" + amountField.getAttribute("value"));

        // 6️⃣ Cliquer sur Transfer
        WebElement transferButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Transfer']"))
        );
        transferButton.click();

        // 7️⃣ Attendre le message de confirmation
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("showResult"))
        );
        System.out.println("Transfer Result: " + successMessage.getText());

        // 8️⃣ Lire les soldes après transfert
        double fromBalanceAfter = getAccountBalance(fromAccNumber);
        double toBalanceAfter = getAccountBalance(toAccNumber);

        System.out.println("From Account Balance AFTER: $" + fromBalanceAfter);
        System.out.println("To Account Balance AFTER: $" + toBalanceAfter);
    }

    private double getAccountBalance(String accountNumber) {

        driver.findElement(By.linkText("Accounts Overview")).click();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountTable")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='accountTable']//a[contains(text(),'"
                + accountNumber.trim() + "')]/ancestor::tr/td[2]")));
        WebElement balanceElement = driver.findElement(
                By.xpath("//table[@id='accountTable']//a[contains(text(),'"
                        + accountNumber.trim() + "')]/ancestor::tr/td[2]")
        );

        String balanceText = balanceElement.getText().trim();

        return Double.parseDouble(balanceText.replace("$", "").replace(",", ""));
    }
}