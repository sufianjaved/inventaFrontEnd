package utils;

import io.qameta.allure.Attachment;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.support.PageFactory;
import pages.inventa.common.Header;
import pages.inventa.common.LoginPage;
import utils.inventa.common.ScreenShotOnFailure;
import utils.selenium.SeleniumFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class BaseTest extends SeleniumFactory {
    protected String packageName = this.getClass().getPackage().getName();
    protected abstract void initializePageElements();

    LoginPage login;

    @Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(packageName);

/*    @AfterEach
    public void afterEach() throws IOException {
        attachScreenshot();
        driver.quit();
    }*/


/*    @Attachment(value = "screenshot", type = "image/png")
    public byte[] attachScreenshot() throws IOException {
        BufferedImage image = Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).getImage();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        return outputStream.toByteArray();
    }*/

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
