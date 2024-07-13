import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    private WebDriver driver;

    private By sizeDropdown = By.id("group_1");
    private By quantityInput = By.cssSelector("input[name='qty']");
    private By addToCartButton = By.cssSelector("button.btn.btn-primary.add-to-cart");

    public ProductPage(WebDriver driver) {
        this.driver = driver;

    }

    public void selectSize(String size) {
        Select dropdown = new Select(driver.findElement(sizeDropdown));
        dropdown.selectByVisibleText(size);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void setQuantity(int quantity) {

        WebElement quantityElement = driver.findElement(quantityInput);


        quantityElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);


        quantityElement.sendKeys(String.valueOf(quantity));


        new Actions(driver).sendKeys(quantityElement, Keys.ENTER).perform();


    }

    public void addToCart() {
        WebElement addToCartButtonElement = driver.findElement(addToCartButton);
        addToCartButtonElement.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
