package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FindTransactionsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By fromDateInput = By.id("fromDate");
    private By toDateInput = By.id("toDate");
    private By dateRangeError = By.id("dateRangeError");
    private By findTransactionButton = By.id("findByDateRange");

    public FindTransactionsPage(WebDriver driver) {
        this.driver= driver;
    }



    // Method to set the date range
    public List<WebElement> getTransactions(String fromDate, String toDate) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement from = wait.until(ExpectedConditions.visibilityOfElementLocated(fromDateInput));
        WebElement to = wait.until(ExpectedConditions.visibilityOfElementLocated(toDateInput));

        from.clear();
        from.sendKeys(fromDate);

        to.clear();
        to.sendKeys(toDate);
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(findTransactionButton));
        search.click();

        // Display transactions
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//table[@id='transactionTable']/tbody/tr")));

    }



}



