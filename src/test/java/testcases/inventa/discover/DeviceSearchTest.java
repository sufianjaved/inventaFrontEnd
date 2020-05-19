package testcases.inventa.discover;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.inventa.common.Header;
import pages.inventa.discover.DevicePage;
import utils.BaseTest;

public class DeviceSearchTest extends BaseTest {

    DevicePage devicePage;

    @Override
    protected void initializePageElements() {
        devicePage = new DevicePage();
    }

    //@DisplayName("Some text")
    //@Description("Some text")
    @Test
    public void searchDevice() throws Exception{

        PageFactory.initElements(driver,Header.class);
        click(Header.devices_icon);
        devicePage.searchDevice();
    }
}
