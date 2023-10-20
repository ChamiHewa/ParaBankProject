package testcase;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class Login extends BaseTest {

    @Test(priority=1)
    public static void ValidatePageLoading() {
        //Testcase 1: validate the loaded page is correct by checking the web page title
        String actual = driver.getTitle();
        String expected = "ParaBank | Welcome | Online Banking";

        assertEquals(expected, actual);

        if (actual.equals(expected)) {
            System.out.println("Title is similar. Test case is Pass");
        }
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }
    @Test(priority=2)
    public static void LoginEmptyCred() {
        //locators
        WebElement username = driver.findElement(By.xpath(loc.getProperty("username")));
        WebElement password = driver.findElement(By.xpath(loc.getProperty("password")));
        WebElement login = driver.findElement(By.xpath(loc.getProperty("login_btn")));

        //Testcase 2: Validate login with empty credentials
        //Click login button
        login.click();
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        WebElement error = driver.findElement(By.xpath(loc.getProperty("error_msg")));

        if (error.getText().contains("Please enter a username and password.")) {
            System.out.println("Login failed with empty credentials. Test case is Pass");
        } else if (driver.findElement(By.xpath(loc.getProperty("error_msg"))).getText().contains("An internal error has occurred and has been logged.")) {
            System.out.println("An internal error has occured. Test case is Fail");
        } else {
            System.out.println("Test case is Fail");
        }
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority=3)
    public static void LoginInvalidCred() {
        //locators
        WebElement username = driver.findElement(By.xpath(loc.getProperty("username")));
        WebElement password = driver.findElement(By.xpath(loc.getProperty("password")));
        WebElement login = driver.findElement(By.xpath(loc.getProperty("login_btn")));

        //Testcase 3: Validate with invalid credentials
        enterUsernameAndPassword("aaaa", "bbb", username, password);

        driver.findElement(By.xpath(loc.getProperty("login_btn"))).click();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        if (driver.findElement(By.xpath(loc.getProperty("error_msg"))).getText().contains("The username and password could not be verified.")) {
            System.out.println("Login failed with invalid credentials. Test case is Pass");
        } else if (driver.findElement(By.xpath(loc.getProperty("error_msg"))).getText().contains("An internal error has occurred and has been logged.")) {
            System.out.println("An internal error has occured. Test case is Fail");
        } else {
            System.out.println("Test case is Fail");
        }
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
    }
    @Test(priority=4)
    @Parameters({"usernameP","passwordP"})
    public static void LoginValidCred() {
        WebElement username = driver.findElement(By.xpath(loc.getProperty("username")));
        WebElement password = driver.findElement(By.xpath(loc.getProperty("password")));
        WebElement login = driver.findElement(By.xpath(loc.getProperty("login_btn")));

        //Testcase 4: Validate login with valid credentials
        enterUsernameAndPassword("john","demo",username,password);

        driver.findElement(By.xpath(loc.getProperty("login_btn"))).click();

        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);

        if (driver.findElement(By.xpath(loc.getProperty("welcome_msg"))).getText().contains("Welcome")) {
            System.out.println("Logged in successfully with valid credentials. Test case Pass");
        } else if (driver.findElement(By.xpath(loc.getProperty("error_msg"))).getText().contains("An internal error has occurred and has been logged.")) {
            System.out.println("An internal error has occured. Test case is Fail");
        } else {
            System.out.println("Login fail. Testcase is Pass");
        }
    }

    private static void enterUsernameAndPassword(String usernameP, String passwordP, WebElement username, WebElement password) {
        username.clear();
        username.click();
        username.sendKeys(usernameP);
        password.clear();
        password.click();
        password.sendKeys(passwordP);
    }
}