import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MyStepdefs {
    private WebDriver driver;

    @Given("I go to the login page")
    public void iGoToTheLoginPage() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @And("I click on sign up")
    public void iClickOnSignUp() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signUpLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.hidden-sm-down")));
        signUpLink.click();
    }

    @When("I enter {string} as email")
    public void iEnterAsEmail(String email) {
        WebElement emailInput = driver.findElement(By.id("field-email"));
        emailInput.sendKeys(email);
    }

    @And("I enter {string} as password")
    public void iEnterAsPassword(String password) {
        WebElement passwordInput = driver.findElement(By.id("field-password"));
        passwordInput.sendKeys(password);
    }

    @And("I click on Login button")
    public void iClickOnLoginButton() {
        WebElement loginButton = driver.findElement(By.id("submit-login"));
        loginButton.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("I navigate to the Addresses page")
    public void iNavigateToTheAddressesPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement spanElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".material-icons")));
        spanElement.click();
    }

    @And("I click on {string}")
    public void iClickOn(String button) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iconElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class, 'material-icons') and text()='']")));
        iconElement.click();
    }

    @And("I fill in the new address form with the following details:")
    public void iFillInTheNewAddressFormWithTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> addressDetails = data.get(0);

        WebElement aliasInput = driver.findElement(By.id("field-alias"));
        aliasInput.sendKeys(addressDetails.get("alias"));

        WebElement addressInput = driver.findElement(By.id("field-address1"));
        addressInput.sendKeys(addressDetails.get("address"));

        WebElement cityInput = driver.findElement(By.id("field-city"));
        cityInput.sendKeys(addressDetails.get("city"));

        WebElement postcodeInput = driver.findElement(By.id("field-postcode"));
        postcodeInput.sendKeys(addressDetails.get("zip/postal code"));

        WebElement phoneInput = driver.findElement(By.id("field-phone"));
        phoneInput.sendKeys(addressDetails.get("phone"));
    }

    @And("I submit the new address form")
    public void iSubmitTheNewAddressForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        saveButton.click();
    }

    @Then("the new address should be listed with the correct details:")
    public void theNewAddressShouldBeListedWithTheCorrectDetails(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> expectedDetails = data.get(0);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".address")));

        List<WebElement> addressElements = driver.findElements(By.cssSelector(".address"));

        boolean addressFound = false;
        for (WebElement addressElement : addressElements) {
            String actualAlias = addressElement.findElement(By.cssSelector("h4")).getText();
            if (actualAlias.equals(expectedDetails.get("alias"))) {
                addressFound = true;
                String actualAddress = addressElement.findElement(By.cssSelector(".address_address1")).getText();
                String actualCity = addressElement.findElement(By.cssSelector(".address_city")).getText();
                String actualZip = addressElement.findElement(By.cssSelector(".address_postcode")).getText();
                String actualCountry = addressElement.findElement(By.cssSelector(".address_country_name")).getText();
                String actualPhone = addressElement.findElement(By.cssSelector(".address_phone")).getText();

                Assertions.assertEquals(expectedDetails.get("address"), actualAddress);
                Assertions.assertEquals(expectedDetails.get("city"), actualCity);
                Assertions.assertEquals(expectedDetails.get("zip/postal code"), actualZip);
                Assertions.assertEquals(expectedDetails.get("country"), actualCountry);
                Assertions.assertEquals(expectedDetails.get("phone"), actualPhone);
                break;
            }
        }

        Assertions.assertTrue(addressFound, "Address with alias " + expectedDetails.get("alias") + " not found");
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
