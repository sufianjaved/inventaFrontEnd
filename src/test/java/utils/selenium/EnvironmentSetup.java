package utils.selenium;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import static utils.AllureEnvironmentWriter.allureEnvironmentWriter;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ApplicationConfiguration;
import utils.DefaultConfiguration;

import java.time.Duration;

public class EnvironmentSetup {

    protected static WebDriver driver;
    //protected static FrameUtility frame = new FrameUtility();
    protected static WebDriverWait wait;
    protected static boolean isAllureEnvSet;


    public EnvironmentSetup() {
        PageFactory.initElements(driver, this);
    }

    public static void initializeLocalBrowser(){
        initializeLocalBrowser(DefaultConfiguration.getDefaultBrowserName());
    }

    public static void initializeLocalBrowser(String defaultBrowserValue){

        switch(defaultBrowserValue){
            case "Chrome":
                System.setProperty("webdriver.chrome.silentOutput", "true");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "Edge":
                System.setProperty("webdriver.edge.verboseLogging", "false");
                //java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"null");
                driver = new FirefoxDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        Long backendTime = (Long) ((JavascriptExecutor) driver)
                .executeScript("return performance.timing.responseStart - performance.timing.navigationStart;");

        Long frontendTime = (Long) ((JavascriptExecutor) driver)
                .executeScript("return performance.timing.domComplete - performance.timing.responseStart;");

        System.out.println("Back End load time is: " + backendTime);
        System.out.println("Front End load time is: " + frontendTime);

    }

    public void setAllureEnvironment() {
        isAllureEnvSet = true;
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("BROWSER", cap.getBrowserName()+" "+cap.getVersion().toString())
                        //.put("DB", Connector.getHost())
                        .put("URL", ApplicationConfiguration.getApplicationURL())
                        .put("OS", SystemUtils.OS_NAME)
                        .build(), System.getProperty("user.dir")
                        + "/build/allure-results/");
    }
}
