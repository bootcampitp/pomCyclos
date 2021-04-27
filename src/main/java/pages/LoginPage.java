package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath= "//*[@placeholder='User']")
    private WebElement username;

    @FindBy(xpath= "//*[@placeholder='Password']")
    private WebElement password;

    @FindBy(css = ".btn.d-flex")
    private WebElement submitButton;

    public void enterUsername(String username){
        this.username.sendKeys(username);
    }
    public void enterPassword(String password){
        this.password.sendKeys(password);
    }
    public DashboardPage clickSubmit(){
        submitButton.click();
        return new DashboardPage(driver);
    }

    public DashboardPage login(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        submitButton.click();
        return new DashboardPage(driver);
    }
}
