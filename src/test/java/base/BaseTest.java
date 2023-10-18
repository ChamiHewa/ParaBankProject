package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public static WebDriver driver; //creating an instance to the WebDriver clas
    public static Properties prop = new Properties(); //creating an instance to the Properties clas
    public static Properties loc = new Properties(); //instance for locator file
    public static FileReader frP; //creating an instance to the FileReader clas
    public static FileReader frL;

    @BeforeSuite
    public void browserSetup() throws IOException {
        if (driver == null) {
            //user.dir -> get project path. Remove file path hard coding
            frP = new FileReader(System.getProperty("user.dir") + "/src/test/resources/configfiles/config.properties");
            prop.load(frP);
            frL = new FileReader(System.getProperty("user.dir") + "/src/test/resources/configfiles/locators.properties");
            loc.load(frL);
        }
        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:/Chami/Projects/ParaBankProject/chromedriver.exe");

            //Creating an object of ChromeDriver
            //WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(prop.getProperty("testUrl"));
        }
        else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            //Creating an object of FirefoxDriver
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(prop.getProperty("testUrl"));
        }
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
