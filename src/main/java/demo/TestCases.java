package demo;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases extends Wappers {
    static ChromeDriver driver;

    public TestCases() {

        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    // TODO: Write Test Cases Here

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://stackoverflow.com/");
        boolean status = driver.getCurrentUrl().contains("stackoverflow");
        Assert.isTrue(status, "Current URL is worng", "stackoverflow");
        System.out.println("status:- " + status);
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        loginUser("designmate.hasmukh@gmail.com", "Hasy@1234");
        Thread.sleep(5000);
        WebElement searchElement = driver.findElement(By.xpath("//input[contains(@placeholder,'Search…')]"));

        SearchenterText(driver, searchElement, "Python list comprehension");
        WebElement reventDataElement = driver.findElement(By.xpath("//span[@data-text='Relevance']"));
        // (//span[@class='s-btn--text'])[1]
        clickOnElement(driver, reventDataElement);
        Thread.sleep(3000);
        List<WebElement> searchResultElements = driver
                .findElements(By.xpath("//*[contains(@id, 'question-summary') or contains(@id, 'answer-id')]"));

        printSearchResult(driver, searchResultElements, "python");
        Thread.sleep(3000);

        WebElement menuElement = driver
                .findElement(By.xpath("//a[contains(@class,'s-topbar--item js-site-switcher-button js-gps-track')]"));
        clickOnElement(driver, menuElement);
        WebElement logoutLinkElement = driver.findElement(By.xpath("(//a[contains(text(),'log out')])[2]"));
        clickOnElement(driver, logoutLinkElement);
        WebElement logoutButtonElement = driver.findElement(By.xpath("//button[contains(text(),'Log out')]"));
        clickOnElement(driver, logoutButtonElement);

        System.out.println("end Test case: testCase02");

    }

    public void testCase03() {

        System.out.println("Start Test case: testCase03");
        driver.get("https://stackoverflow.com/");
        WebElement tagElement = driver.findElement(By.xpath("//span[contains(text(),'Tags')]"));
        clickOnElement(driver, tagElement);

        WebElement javaScriptElement = driver.findElement(By.xpath("//a[contains(text(),'javascript')]"));
        clickOnElementUsingJS(driver, javaScriptElement);

        List<WebElement> javaScriptresultsList = driver
                .findElements(By.xpath("//*[contains(@id, 'question-summary')]"));
        printSearchResult(driver, javaScriptresultsList, "javascript");
        System.out.println("End Test case: testCase03");

    }

    public void testCase04() {
        System.out.println("Start Test case: testCase04");
        driver.get("https://stackoverflow.com/");
        WebElement moreElement = driver.findElement(By.xpath("//span[contains(@data-text,'More')]"));
        clickOnElement(driver, moreElement);

        WebElement scoreElement = driver.findElement(By.xpath("//a[contains(normalize-space(text()), 'Score')]"));
        clickOnElement(driver, scoreElement);
        List<WebElement> votesElements = driver.findElements(By.xpath("//div[contains(normalize-space(@title),'Score of')]"));

        sortedValue(driver, votesElements);
        System.out.println("Start Test case: testCase04");
             

    }

    public void loginUser(String usernameText, String passwordText) {
        WebElement loginButton = driver.findElement(By.xpath("(.//a[contains(text(),'Log in')])[2]"));
        clickOnElement(driver, loginButton);
        WebElement emailElement = driver.findElement(By.xpath("//input[contains(@type,'email')]"));
       
        enterText(driver, emailElement, usernameText);
       
        WebElement passwordElement = driver
                .findElement(By.xpath("//div[contains(@class,'d-flex ps-relative js-passwor')]//input"));

        
        enterText(driver, passwordElement, passwordText);
        WebElement signInButton = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
        clickOnElement(driver, signInButton);

    }

}
