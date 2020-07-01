package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import pages.inventa.common.Header;
import pages.inventa.common.LoginPage;
import utils.inventa.common.ScreenShotOnFailure;
import utils.selenium.SeleniumFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public abstract class BaseClassFrontEnd extends SeleniumFactory {
    protected String packageName = this.getClass().getPackage().getName();
    protected abstract void initializePageElements();

    LoginPage login;

    //@Rule
    //public ScreenShotOnFailure failure = new ScreenShotOnFailure(packageName);

    @After
    public void afterEach() throws IOException {
        attachScreenshot();
        driver.quit();
    }


    //@Attachment(value = "screenshot", type = "image/png")
    public void attachScreenshot() throws IOException {
        //TakesScreenshot screenshot = ((TakesScreenshot) driver);
        Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        //return screenshot.getScreenshotAs(OutputType.BYTES);
    }

    @Before
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



    @AfterClass
    public static void cleanUp(){
        if(driver!=null)
            driver.quit();
        driver = null;
    }

}
