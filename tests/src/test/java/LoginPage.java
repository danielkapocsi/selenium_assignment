import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends PageBase {

    By usernameLocator = By.id("login-email");
    By passwordLocator = By.id("login-password");
    By loginButtonLocator = By.id("my-account-action-login");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login() {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("userdata.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        
            this.waitAndReturnElement(usernameLocator).sendKeys(properties.getProperty("username"));
            this.waitAndReturnElement(passwordLocator).sendKeys(properties.getProperty("password"));

            this.waitFor(1000);

            WebElement loginButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButtonLocator));
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", loginButtonElement);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
