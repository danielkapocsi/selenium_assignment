import java.util.Date;

import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;


class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    
    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }
    
    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    } 
    
    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(By.tagName("body"));
        return bodyElement.getText();
    }

    public void acceptCookies() {
        By locator = By.xpath("//button[contains(text(), 'Összes elfogadása')]");
        this.waitAndReturnElement(locator).click();
    }

    public void handleCookies() {
        String URL="https://www.mediamarkt.hu/";
        this.driver.navigate().to(URL);
        // Cookie cookie = new Cookie("mycookie", "123456789123");
		// this.driver.manage().addCookie(cookie);

        Cookie cookie = new Cookie("MC_PS_USER_ID", "1715969391267-980339", "/");
		this.driver.manage().addCookie(cookie);

        cookie = new Cookie("BVBRANDID", "8ebeca0e-72da-4e74-9bd7-9d41ded34ef3", ".mediamarkt.hu", "/", new Date(1735689592000L), true);
        this.driver.manage().addCookie(cookie);

        cookie = new Cookie("MC_USERTYPE", "G", ".mediamarkt.hu", "/", new Date(1735689592000L));
        
        this.driver.manage().addCookie(cookie);

        cookie = new Cookie("BVBRANDSID", "f25dcebe-76cc-4e10-b8aa-21c51eb5f846", ".mediamarkt.hu", "/", new Date(1735689592000L), true);
        this.driver.manage().addCookie(cookie);

        cookie = new Cookie("__cf_bm", "dMyF9A.PBn8LjGU.wOWN3ZV.dCctIl2MiGzLIs5aWIE-1715969391-1.0.1.1-ZuQpe0JVv2.hZwcd37_i977YTRZyJRaxyVslIb_hoyhiR_qKw1dMDQkgJMhRZghGqZzEDri4BR1xRf7dpdew0g", ".mediamarkt.hu",
            "/", new Date(1735689592000L), true);
        this.driver.manage().addCookie(cookie);

        cookie = new Cookie("MC_DEVICE_ID_EXT", "-1", ".mediamarkt.hu", "/", new Date(1735689592000L), true);
        this.driver.manage().addCookie(cookie);

        cookie = new Cookie("MC_DEVICE_ID", "-1", "www.mediamarkt.hu", "/", new Date(1735689592000L), true);
        this.driver.manage().addCookie(cookie);

        cookie = new Cookie("_cfuvid", ".GyE63hC7TfoVIK6tN86XZJ1xxVDbNSfn9.eEMxxLcg-1715969391107-0.0.1.1-604800000", ".mediamarkt.hu", "/", new Date(1735689592000L), true);
        this.driver.manage().addCookie(cookie);

        cookie = new Cookie("MC_PS_SESSION_ID", "hVsA8I_nFdIAiLE1df4IGVS", "www.mediamarkt.hu", "/", new Date(1735689592000L), true);
        this.driver.manage().addCookie(cookie);

        cookie = new Cookie("MC_PS_CHANNEL_ID", "desktop", "www.mediamarkt.hu", "/", new Date(1735689592000L), true);
        this.driver.manage().addCookie(cookie);

        this.driver.navigate().refresh();
    }
}
