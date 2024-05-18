import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    By usernameLocator = By.id("login-email");
    By passwordLocator = By.id("login-password");
    By loginButtonLocator = By.id("my-account-action-login");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login() {
        this.waitAndReturnElement(usernameLocator).sendKeys("testseleniumcu6jw6@gmaill.com");
        this.waitAndReturnElement(passwordLocator).sendKeys("cu6jw6cu6jw6");
        this.waitAndReturnElement(loginButtonLocator).click();
    }
}
