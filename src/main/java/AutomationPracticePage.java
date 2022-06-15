import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class AutomationPracticePage {

    private final WebDriver driver;
    private final By cartButton = By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/b");
    private final String url = "http://automationpractice.com/index.php";

    public AutomationPracticePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get(url);
    }
    public void createNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }
    public String getButtonText() {
        return driver.findElement(cartButton).getText();
    }
}
