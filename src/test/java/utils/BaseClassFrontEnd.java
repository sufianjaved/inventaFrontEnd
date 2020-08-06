package utils;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import pages.inventa.common.Header;
import pages.inventa.common.LoginPage;
import utils.inventa.common.ConnectDB;
import utils.inventa.common.ScreenShotOnFailure;
import utils.selenium.SeleniumFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public abstract class BaseClassFrontEnd extends SeleniumFactory {

    public static final String USERNAME = ApplicationConfiguration.getMongoDB_UserName();
    public static final String PASSWORD = ApplicationConfiguration.getMongoDB_Password();
    public static final String HOST = ApplicationConfiguration.getMongoDB_Host();
    public static final String PORT = ApplicationConfiguration.getMongoDB_Port();
    public static final String AUTH_DB = ApplicationConfiguration.getMongoDB_AuthDB();
    public static final String DB_NAME = ApplicationConfiguration.getMongoDB_DBName();

    protected static final String DB_URL = "mongodb://"+USERNAME+":"+PASSWORD+"@"+HOST+":"+PORT+"/?authSource="+AUTH_DB+"&readPreference=primary&appname=MongoDB%20Compass&ssl=false";
    MongoClient mongoClient = new MongoClient(new MongoClientURI(DB_URL));
    protected DB db = mongoClient.getDB(DB_NAME);

    protected String packageName = this.getClass().getPackage().getName();
    protected abstract void initializePageElements();
    protected ConnectDB connectDB = new ConnectDB();

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
