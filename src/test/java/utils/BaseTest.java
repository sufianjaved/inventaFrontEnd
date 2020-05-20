package utils;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import pages.inventa.common.Header;
import pages.inventa.common.LoginPage;
import utils.selenium.SeleniumFactory;

public abstract class BaseTest extends SeleniumFactory {
    protected String packageName = this.getClass().getPackage().getName();
    protected abstract void initializePageElements();

    LoginPage login;

    //@Rule
    //public ScreenShotOnFailure to do

    @BeforeClass
    public void setUp() throws Exception {
    if(driver!=null) {
        driver.close();
    }
    initializeLocalBrowser();
    if(!isAllureEnvSet)
        setAllureEnvironment();
    login = new LoginPage();
    initializePageElements();
    login.loginInventa();
    PageFactory.initElements(driver, Header.class);
    }

    @AfterTest
    public static void cleanUp(){
        if(driver!=null)
            driver.quit();
        driver = null;
    }

}
