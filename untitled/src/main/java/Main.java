import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Main {
    public static void main(String[] args) {
        // Ustawienie ścieżki do chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");

        // Inicjalizacja WebDrivera
        WebDriver driver = new ChromeDriver();

        // Przechodzenie na stronę logowania
        driver.get("https://mystore-testlab.coderslab.pl/index.php");

        // Inicjalizacja strony logowania
        LoginPage loginPage = new LoginPage(driver);

        // Wykonanie akcji na stronie logowania
        loginPage.clickOnSignUp();
        loginPage.enterEmail("kacper-prusak370@wp.pl");
        loginPage.enterPassword("Adam123");
        loginPage.clickOnLoginButton();
        loginPage.clickMystore();
        loginPage.clickSweter();

        //sleep

        // Przejście do strony produktu
        ProductPage productPage = new ProductPage(driver);
        productPage.selectSize("M");
        productPage.setQuantity(5);
        productPage.addToCart();


        CheckoutPage checkoutPage = new CheckoutPage(driver);


        checkoutPage.clickProceedToCheckout();
        checkoutPage.clickNexxtProcccedToCheckout();
        checkoutPage.clickContinueButton();
        checkoutPage.clickConfirmButton();
        checkoutPage.clickByCheck();
        checkoutPage.clickTermsofservice();
        checkoutPage.clickPaceOrder();
        checkoutPage.screenshoot();

        // Zamykanie przeglądarki


        driver.quit();
    }
}
