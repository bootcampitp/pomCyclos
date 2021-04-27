package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    private WebDriver driver;

    @FindBy(id = "menu_banking")
    private WebElement bankingLink;

    @FindBy(xpath = "//*[@href='/users/contacts']")
    private WebElement contactsLink;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BankingPage clickBanking(){
        bankingLink.click();
        return new BankingPage(driver);
    }


}
