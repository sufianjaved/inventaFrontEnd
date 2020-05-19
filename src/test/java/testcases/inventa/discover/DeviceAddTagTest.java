package testcases.inventa.discover;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.inventa.common.Header;
import pages.inventa.discover.DeviceDetailPage;
import pages.inventa.discover.DevicePage;
import utils.BaseTest;

public class DeviceAddTagTest extends BaseTest {

    DevicePage devicePage;
    DeviceDetailPage deviceDetailPage;

    @Override
    protected void initializePageElements() {
        devicePage = new DevicePage();
        deviceDetailPage = new DeviceDetailPage();
    }

    @Test
    public void addTagToDevice() throws Exception{

        PageFactory.initElements(driver, Header.class);
        click(Header.devices_icon);
        devicePage.selectDevice();
        Assert.assertTrue(deviceDetailPage.addTagToDevice(),"Tag could not be added to the device");
    }
}
