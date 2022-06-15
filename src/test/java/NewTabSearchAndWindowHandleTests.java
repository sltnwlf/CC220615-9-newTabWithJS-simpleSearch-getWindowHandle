import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NewTabSearchAndWindowHandleTests {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void testNewTabSearchAndWindowHandle() throws InterruptedException {
        AutomationPracticePage automationPracticePage = new AutomationPracticePage(driver);
        automationPracticePage.navigate();
        String automationPracticeTab = driver.getWindowHandle();
        String buttonText = automationPracticePage.getButtonText();
        automationPracticePage.createNewTab();
        TranslatePage translatePage = new TranslatePage(driver);
        translatePage.navigate();
        String dictZoneTab = driver.getWindowHandle();
        translatePage.acceptPrivacy();
        translatePage.fillField(buttonText);
        translatePage.clickOnSearch();
        String actual = translatePage.getResult();
        String expected = "kocsi";

        Assertions.assertEquals(expected, actual);

        driver.switchTo().window(automationPracticeTab);
        Thread.sleep(1000);
        driver.switchTo().window(dictZoneTab);
    }

    @Test
    public void testNewTabSearchAndWindowHandles() {
        AutomationPracticePage automationPracticePage = new AutomationPracticePage(driver);
        automationPracticePage.navigate();
        String buttonText = automationPracticePage.getButtonText();
        automationPracticePage.createNewTab();
        TranslatePage translatePage = new TranslatePage(driver);
        translatePage.navigate();
        translatePage.acceptPrivacy();
        translatePage.fillField(buttonText);
        translatePage.clickOnSearch();
        String actual = translatePage.getResult();
        String expected = "kocsi";

        Assertions.assertEquals(expected, actual);

        Set<String> tabs = driver.getWindowHandles();
        String tabNext = tabs.iterator().next();
        driver.switchTo().window(tabNext);
    }

    @AfterEach
    public void dispose() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
