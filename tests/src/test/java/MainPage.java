import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class MainPage extends PageBase {

    private By footerBy = By.xpath("//footer[@id=\"page-footer\"]");
    private By searchBarTogglerBy = By.xpath("//input[@class=\"site-header__search-input\"]");
    private By searchBarBy = By.name("query");
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.mediamarkt.hu/");
        //this.handleCookies();
        //acceptCookies();
    }    
    
    public String getFooterText() {
        return this.waitAndReturnElement(footerBy).getText();
    }

    public LoginPage getLoginPage() {
        By locator = By.xpath("//a[text()='Bejelentkez']");
        this.waitAndReturnElement(locator).click();
        return new LoginPage(this.driver);
    }

    public String testAfterLogin() {
        By locator = By.xpath("//a[text()='Kijelentkez']");
        return waitAndReturnElement(locator).getText();
    }
    
    public SearchResultPage search(String searchQuery) {
        this.waitAndReturnElement(searchBarTogglerBy).click();
        
        this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + "\n");
        return new SearchResultPage(this.driver);
    }
}
