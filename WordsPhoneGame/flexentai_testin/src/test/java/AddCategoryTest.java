import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class AddCategoryTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void addCategoryTest() {
        driver.get("file:///Volumes/MacDisk/RSP/flexentai/RSP%20PWA%20flashcards/public/index.html");
        driver.manage().window().setSize(new Dimension(1411, 800));
        driver.findElement(By.cssSelector("button:nth-child(5)")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[2]/p[2]")).click();
        driver.findElement(By.id("addCategory")).click();
        driver.findElement(By.id("addCategory")).sendKeys("hentai");
        driver.findElement(By.id("addCategoryToWords")).click();
    }
}
