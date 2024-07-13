import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // Lokatory
    public By signUpLinkLocator = By.cssSelector("span.hidden-sm-down");
    public By emailFieldLocator = By.id("field-email");
    public By passwordFieldLocator = By.id("field-password");
    public By loginButtonLocator = By.id("submit-login");
    public By logoImgLocator = By.id("_desktop_logo");
    public By sweterLogo = By.cssSelector("a[href*='rewrite=brown-bear-printed-sweater']");

    // Konstruktor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Metoda do kliknięcia na link do rejestracji
    public void clickOnSignUp() {
        WebElement signUpLink = driver.findElement(signUpLinkLocator);
        signUpLink.click();
    }

    // Metoda do wpisania emaila
    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(emailFieldLocator);
        emailInput.sendKeys(email);
    }

    // Metoda do wpisania hasła
    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordFieldLocator);
        passwordInput.sendKeys(password);
    }

    // Metoda do kliknięcia przycisku logowania
    public void clickOnLoginButton() {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
    }

    public void clickMystore() {
        WebElement element = driver.findElement(logoImgLocator);
        element.click();

    }
    public void clickSweter(){
        WebElement element = driver.findElement(sweterLogo);
        element.click();
    }

}
