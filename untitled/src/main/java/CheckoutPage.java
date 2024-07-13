import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;


    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void clickProceedToCheckout() {
        WebElement proceedToCheckoutButton = driver.findElement(By.cssSelector("a.btn.btn-primary[href*='controller=cart&action=show']"));

        try {

            wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
            proceedToCheckoutButton.click();
        } catch (Exception e) {
            System.out.println("Error clicking 'Proceed to checkout': " + e.getMessage());
        }


    }

    public void clickNexxtProcccedToCheckout() {
        WebElement nextproceedToCheckoutButton = driver.findElement(By.cssSelector("a.btn.btn-primary[href='https://mystore-testlab.coderslab.pl/index.php?controller=order']"));
        nextproceedToCheckoutButton.click();
    }

    public void clickContinueButton() {
        WebElement continueButton = driver.findElement(By.cssSelector("button.btn.btn-primary.continue.float-xs-right[type='submit'][name='confirm-addresses'][value='1']"));
        continueButton.click();
    }

    public void clickConfirmButton() {
        WebElement confirmButton = driver.findElement(By.cssSelector("button.continue.btn.btn-primary.float-xs-right[type='submit'][name='confirmDeliveryOption'][value='1']"));
        confirmButton.click();
    }

    public void clickByCheck() {
        WebElement selectByCheck = driver.findElement(By.cssSelector("input.ps-shown-by-js[type='radio'][id='payment-option-1'][name='payment-option'][data-module-name='ps_checkpayment'][required='']"));
        selectByCheck.click();
    }

    public void clickTermsofservice() {
        WebElement termsOfservice = driver.findElement(By.cssSelector("input#conditions_to_approve\\[terms-and-conditions\\].ps-shown-by-js[type='checkbox'][required='']"));
        termsOfservice.click();
    }

    public void clickPaceOrder() {
        WebElement placeorder = driver.findElement(By.cssSelector("button.btn.btn-primary.center-block"));
        placeorder.click();
    }

    public void screenshoot() {


        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Określ katalog docelowy i nazwę pliku
        File destinationFile = new File("C:\\Users\\TheHe\\OneDrive\\Pulpit\\zadanko\\untitled\\src\\main\\screenshots\\screenshot.png");

        try {
            // Skopiuj plik zrzutu ekranu do miejsca docelowego
            FileUtils.copyFile(screenshotFile, destinationFile);
            System.out.println("Zrzut ekranu został zapisany do: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania zrzutu ekranu: " + e.getMessage());
        }
    }


}

