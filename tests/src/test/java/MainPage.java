import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;


class MainPage extends PageBase {
    private By footerBy = By.xpath("//footer[@id='page-footer' and @class='footer']");
    private By searchBarBy = By.xpath("/html/body/div[2]/header/div[1]/div/form/div/div[2]/input");
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.mediamarkt.hu/");
    }    
    
    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public String getFooterText() {
        return this.waitAndReturnElement(footerBy).getText();
    }

    public LoginPage getLoginPage() {
        By hoverLocator = By.xpath("//*[@id=\"header\"]/header/div[1]/div/div[4]");
        By loginLocator = By.xpath("//*[@id=\"header\"]/header/div[1]/div/div[4]/nav/div/div/ul/li[1]/a");

        this.hoverElement(hoverLocator);

        this.waitAndReturnElement(loginLocator).click();
        return new LoginPage(this.driver);
    }

    public String testAfterLogin() {
        By hoverLocator = By.xpath("//*[@id=\"header\"]/header/div[1]/div/div[4]");
        By logoutLocator = By.xpath("/html/body/div[2]/header/div[1]/div/div[4]/nav/div/div/ul/li[1]/a");

        this.hoverElement(hoverLocator);
     
        return waitAndReturnElement(logoutLocator).getText();
    }
    
    public void logout() {
        By hoverLocator = By.xpath("//*[@id=\"header\"]/header/div[1]/div/div[4]");
        By logoutLocator = By.xpath("/html/body/div[2]/header/div[1]/div/div[4]/nav/div/div/ul/li[1]/a");

        this.hoverElement(hoverLocator);

        this.waitAndReturnElement(logoutLocator).click();
    }

    public String testDropdown() {
        By hoverLocator = By.xpath("/html/body/div[2]/header/div[3]/div[1]");
        By dropdownItemsLocator = By.xpath("/html/body/div[2]/header/div[3]/div[1]/ul");

        this.hoverElement(hoverLocator);
        
        return waitAndReturnElement(dropdownItemsLocator).getText();
    }

    public ServicesPage getServicesPage() {
        By servicesLocator = By.xpath("/html/body/div[2]/header/div[3]/div[3]/nav/a[1]");

        this.waitAndReturnElement(servicesLocator).click();

        return new ServicesPage(this.driver);
    }

    public SupportPage getSupportPage() {
        By servicesLocator = By.xpath("/html/body/div[2]/header/div[3]/div[3]/nav/a[6]");

        this.waitAndReturnElement(servicesLocator).click();

        return new SupportPage(this.driver);
    }

    public NewsletterPage getNewsletterPage() {
        By servicesLocator = By.xpath("/html/body/div[2]/header/div[3]/div[3]/nav/a[4]");

        this.waitAndReturnElement(servicesLocator).click();

        return new NewsletterPage(this.driver);
    }

    public SearchResultPage search(String searchQuery) {
        this.waitAndReturnElement(searchBarBy).clear();

        this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + "\n");
        
        return new SearchResultPage(this.driver);
    }

    public void acceptCookies() {
        By locator = By.xpath("//button[contains(text(), 'Összes elfogadása')]");
        this.waitAndReturnElement(locator).click();
    }

    public void handleCookies() {
        this.acceptCookies();
        
        Cookie cookie = new Cookie("seleniumTest", "tested");
        this.driver.manage().addCookie(cookie);

        this.driver.navigate().refresh();
    }
}
