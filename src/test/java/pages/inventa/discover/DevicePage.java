package pages.inventa.discover;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.selenium.SeleniumFactory;

import java.util.List;

public class DevicePage  extends SeleniumFactory {

    @FindBy(xpath="*//h1[contains(text(), 'Devices')]") public static WebElement device_caption;
    @FindBy(xpath="//button[contains(text(), 'Query Wizard')]") private WebElement queryWizard_btn;
    @FindBy(xpath="//td[contains(text(), 'DEVICE')]") private WebElement randomDevice_gridResult;
    @FindBy(xpath="//input[@class='search-field']") private WebElement search_field;


    @Step("Select Asset Type: Device")
    public void selectDevice()
    {
        waitForElement(device_caption);

        //List<WebElement> List_randomDevice_gridResult =
        //        driver.findElements(By.xpath("//tr[@class='parent-row mat-row ng-tns-c14-10 ng-star-inserted']/td[]"));

        List<WebElement> randomDevice_gridResultLast = driver.findElements((By.xpath("//tr[@class='parent-row mat-row ng-tns-c14-6 ng-star-inserted']/td[3]")));
        String device = "";

        for (int i=0; i < randomDevice_gridResultLast.size(); i++) {
            device = device + randomDevice_gridResultLast.get(i).getText() + "\n";
            if (device.contains("DEVICE"))
            {
                randomDevice_gridResultLast.get(i).click();
                break;
            }
            else if(device.contains("SUBNET"))
            {
                randomDevice_gridResultLast.get(i).click();
                break;
            }
        }
    }

    @Step("Search Device")
    public void searchDevice() throws Exception {
        sendKeys(search_field, "test");
        sleep(5000);
    }
}
