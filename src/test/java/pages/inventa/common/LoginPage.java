package pages.inventa.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.inventa.dashboard.DashboardPage;
import utils.ApplicationConfiguration;
import utils.selenium.SeleniumFactory;

public class LoginPage extends SeleniumFactory {


    @FindBy(id="input") private WebElement email_field; //should be user name on UI
    @FindBy(id="password") private WebElement password_field;
    @FindBy(id="submitButton") private WebElement login_btn;


    public LoginPage(){
        super();
    }

    public void loginInventa() throws Exception {

        driver.get(ApplicationConfiguration.getApplicationURL());
        waitForElement(email_field);
        clearField(email_field);
        sendKeys(email_field, ApplicationConfiguration.getUsername());
        clearField(password_field);
        sendKeys(password_field, ApplicationConfiguration.getUserPassword());
        click(login_btn);

        PageFactory.initElements(driver,DashboardPage.class);
        waitForElement(DashboardPage.dashboard_caption);
    }
}
