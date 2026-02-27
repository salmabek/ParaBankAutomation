package Base;

import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BasesTest {


    protected WebDriver driver;


    @BeforeClass
    /* Set Up the properties */
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","ressources/chromedriver/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        goPage();
    }

    /* Redirect to The page */
    @BeforeMethod
    public void goPage()
    {
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

    }

    /* Close the browser */
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }



}
