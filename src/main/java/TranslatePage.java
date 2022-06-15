import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TranslatePage {

    private final WebDriver driver;
    private final String url = "https://dictzone.com/angol-magyar-szotar/";
    private final By acceptButton = By.xpath("//*[@id=\"qc-cmp2-ui\"]/div[2]/div/button[1]");
    private final By inputField = By.xpath("//*[@type=\"text\" and @name=\"w\"]");
    private final By searchIcon = By.xpath("//*[@id=\"kw\"]/input[2]");
    private final By resultText = By.xpath("//*[@id=\"r\"]/tbody/tr[2]/td[2]/p[1]/a[2]");

    public TranslatePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get(url);
    }
    public void acceptPrivacy() {
        driver.findElement(acceptButton).click();
    }
    public void fillField(String text) {
        driver.findElement(inputField).sendKeys(text);
    }
    public void clickOnSearch() {
        driver.findElement(searchIcon).click();
    }
    public String getResult() {
        String resulText = driver.findElement(resultText).getText();
        return resulText;
    }
}
