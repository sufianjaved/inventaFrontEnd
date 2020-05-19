package utils;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import pages.inventa.common.LoginPage;
import utils.selenium.SeleniumFactory;

public abstract class BaseTest extends SeleniumFactory {
    protected String packageName = this.getClass().getPackage().getName();
    protected abstract void initializePageElements();

    LoginPage login;

    //@Rule
    //public ScreenShotOnFailure to do

    @BeforeTest
    public void setUp() throws Exception {
    if(driver!=null) {
        driver.close();
    }
    initializeLocalBrowser();
    login = new LoginPage();
    initializePageElements();
    login.loginInventa();
    }

    @AfterClass
    public static void cleanUp(){
        if(driver!=null)
            driver.quit();
        driver = null;
    }

}
