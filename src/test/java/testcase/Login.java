package testcase;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class Login extends BaseTest {

    @Test
    public static void loginTest() {
        //validate the loaded page is correct by checking the web page title
        String actual = driver.getTitle();
        String expected = "ParaBank | Welcome | Online Banking";

        assertEquals(expected, actual);

        if (actual.equals(expected)) {
            System.out.println("Title is similar. Test case is Pass");
        }
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //login
        WebElement username = driver.findElement(By.xpath(loc.getProperty("username")));
        WebElement password = driver.findElement(By.xpath(loc.getProperty("password")));
        WebElement login = driver.findElement(By.xpath(loc.getProperty("login_btn")));

        username.clear();
        username.click();
        username.sendKeys("john");

        password.clear();
        password.click();
        password.sendKeys("demo");

        login.click();
    }
}