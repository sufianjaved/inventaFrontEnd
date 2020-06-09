package pages.inventa.discover;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.selenium.SeleniumFactory;

public class DevicePage  extends SeleniumFactory {

    @FindBy(xpath="*//div[contains(text(), 'Devices')]") public static WebElement device_caption;
    @FindBy(xpath="//button[contains(text(), 'Query Wizard')]") private WebElement queryWizard_btn;
    @FindBy(xpath="//td[contains(text(), 'DEVICE')]") private WebElement randomDevice_gridResult;
	@FindBy(xpath = "//td[@class='ng-tns-c14-10 ng-star-inserted']") public List<WebElement> List_randomDevice_gridResult;
    @FindBy(xpath="//input[@class='search-field']") private WebElement search_field;


    @Step("Select Asset Type: Device")
    public void selectDevice()
    {
        waitForElement(device_caption);
		for (int i = 0; i < List_randomDevice_gridResult.length; i++) {
	    	if(List_randomDevice_gridResult.get(i)).contains("DEVICE")
			{
				System.out.println("Working fine");
			}
	    }
        click(randomDevice_gridResult);
    }

    @Step("Search Device")
    public void searchDevice() throws Exception {
        sendKeys(search_field, "test");
        sleep(5000);
    }
}
