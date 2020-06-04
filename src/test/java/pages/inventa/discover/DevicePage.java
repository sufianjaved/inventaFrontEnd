package pages.inventa.discover;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.selenium.SeleniumFactory;

import java.util.List;

public class DevicePage  extends SeleniumFactory {

    @FindBy(xpath="*//div[contains(text(), 'Devices')]") public static WebElement device_caption;
    @FindBy(xpath="//button[contains(text(), 'Query Wizard')]") private WebElement queryWizard_btn;
    @FindBy(xpath="//td[contains(string(), 'DEVICE')]") private WebElement randomDevice_gridResult;
    @FindBy(xpath="//input[@class='search-field']") private WebElement search_field;


    @Step("Select Asset Type: Device")
    public void selectDevice()
    {
        waitForElement(device_caption);

        List<WebElement> eleme = driver.findElements(By.tagName("iframe"));
        System.out.println("Number of frames in a page :" + eleme.size());
        for(WebElement el : eleme){
            //Returns the Id of a frame.
            System.out.println("Id :" + el.getAttribute("id"));
            //Returns the Name of a frame.
            System.out.println("class :" + el.getAttribute("class"));
        }

        click(randomDevice_gridResult);
    }

    @Step("Search Device")
    public void searchDevice() throws Exception {
        sendKeys(search_field, "test");
        sleep(5000);
    }
}
