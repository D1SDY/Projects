import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class EmptyWordTest {
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
    public void emptyWordTest() {
        driver.get("file:///Volumes/MacDisk/RSP/flexentai/RSP%20PWA%20flashcards/public/index.html");
        driver.manage().window().setSize(new Dimension(1411, 800));
        driver.findElement(By.cssSelector("button:nth-child(5)")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[2]/p[1]")).click();
        driver.findElement(By.id("newWordTranslation")).click();
        driver.findElement(By.id("newWordTranslation")).sendKeys("banan");
        driver.findElement(By.id("addWord")).click();
        WebElement ele=driver.findElement(By.xpath("//*[@id=\"add_new_word_form\"]/div[3]/p[1]"));
        assertTrue(ele.isDisplayed());
    }
}
