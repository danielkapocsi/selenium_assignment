import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class SeleniumAssignementTest {
    public WebDriver driver;
    
    @Before
    public void setup()  throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        this.driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testSeleniumAssignment() {
        MainPage mainPage = new MainPage(this.driver);

        // Cookie handling
        mainPage.handleCookies();

        // Test title and footer
        Assert.assertTrue(mainPage.getPageTitle().contains("MediaMarkt Magyarország"));
        Assert.assertTrue(mainPage.getFooterText().contains("Impresszum"));
        mainPage.waitFor(1000);

        // Test login
        LoginPage loginPage = mainPage.getLoginPage();
        loginPage.login();
        mainPage.waitFor(1000);
        assertTrue(mainPage.testAfterLogin().contains("Kijelentkezés"));
        mainPage.waitFor(1000);

        // Test dropdown
        assertTrue(mainPage.testDropdown().contains("Film"));
        mainPage.waitFor(1000);

        // Test Services page
        ServicesPage servicesPage = mainPage.getServicesPage();
        Assert.assertTrue(servicesPage.getBodyText().contains("Szolgáltatásaink"));
        mainPage.waitFor(1000);
        mainPage.navigateBack();
        mainPage.waitFor(1000);

        // Test Support page
        SupportPage supportPage = mainPage.getSupportPage();
        assertTrue(supportPage.getBodyText().contains("Miben tudunk segíteni?"));
        mainPage.waitFor(1000);
        mainPage.navigateBack();
        mainPage.waitFor(1000);

        // Test Newsletter page
        NewsletterPage newsletterPage = mainPage.getNewsletterPage();
        assertTrue(newsletterPage.getBodyText().contains("Hírlevél feliratkozás"));
        mainPage.waitFor(1000);
        mainPage.navigateBack();
        mainPage.waitFor(1000);

        // Test search from array
        String[] searchQueries={"monitor", "asztal", "laptop"};  
        for(String searchQuery : searchQueries) {  
            SearchResultPage searchResultPage = mainPage.search(searchQuery);
            String bodyText = searchResultPage.getBodyText();
            Assert.assertTrue(bodyText.contains("Keresés eredménye"));
            mainPage.waitFor(1000);
        }


        mainPage.navigateToHomePage();
        mainPage.waitFor(1000);


        mainPage.logout();
        mainPage.waitFor(1000);
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
