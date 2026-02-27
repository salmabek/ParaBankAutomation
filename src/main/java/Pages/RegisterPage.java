package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    /**Locators ***/
    private final By firstName = By.id("customer.firstName");
    private final By lastName = By.id("customer.lastName");
    private final By address = By.id("customer.address.street");
    private final By city = By.id("customer.address.city");
    private final By state = By.id("customer.address.state");
    private final By zipcode = By.id("customer.address.zipCode");
    private final By phone = By.id("customer.phoneNumber");
    private final By ssn = By.id("customer.ssn");
    private final By username = By.id("customer.username");
    private final By password = By.id("customer.password");
    private final By confirmPassword = By.id("repeatedPassword");

    private final WebDriver driver;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private  void fillFirstName(String myFirstName)
    {
        driver.findElement(firstName).sendKeys(myFirstName);
    }

    private  void fillLastName(String myLastName)
    {
        driver.findElement(lastName).sendKeys(myLastName);
    }

    private  void fillAddress(String myAddress)
    {
        driver.findElement(address).sendKeys(myAddress);
    }

    private  void fillCity (String myCity)
    {
        driver.findElement(city).sendKeys(myCity);
    }

    private  void fillState(String myState)
    {
        driver.findElement(state).sendKeys(myState);
    }

    private  void fillZipCode(String myZipCode)
    {
        driver.findElement(zipcode).sendKeys(myZipCode);
    }

    private  void fillPhone(String myPhone)
    {
        driver.findElement(phone).sendKeys(myPhone);
    }

    private  void fillSsn(String mySsn)
    {
        driver.findElement(ssn).sendKeys(mySsn);
    }

    private  void fillUsername(String myUsername)
    {
        driver.findElement(username).sendKeys(myUsername);
    }

    private  void fillPassword(String myPassword)
    {
        driver.findElement(password).sendKeys(myPassword);
    }

    private  void fillConfirmPassword(String myConfirmPassword)
    {
        driver.findElement(confirmPassword).sendKeys(myConfirmPassword);
    }

    public void registerUser(WebDriver driver, String myFirstName, String myLastName, String myAddress,String myCity, String myState, String myZipCode,String myPhone, String mySsn, String myUsername, String myPassword, String myConfirmPassword)
    {
        fillFirstName(myFirstName);
        fillLastName(myLastName);
        fillAddress(myAddress);
        fillCity(myCity);
        fillState(myState);
        fillZipCode(myZipCode);
        fillPhone(myPhone);
        fillSsn(mySsn);
        fillUsername(myUsername);
        fillPassword(myPassword);
        fillConfirmPassword(myConfirmPassword);
        driver.findElement(By.xpath("//input[@value='Register']")).click();
    }














}
