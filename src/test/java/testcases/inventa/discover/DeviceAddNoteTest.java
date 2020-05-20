package testcases.inventa.discover;

import io.qameta.allure.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.inventa.common.Header;
import pages.inventa.discover.DeviceDetailPage;
import pages.inventa.discover.DevicePage;
import utils.BaseTest;

public class DeviceAddNoteTest extends BaseTest {

    DevicePage devicePage;
    DeviceDetailPage deviceDetailPage;

    @Override
    protected void initializePageElements() {
        devicePage = new DevicePage();
        deviceDetailPage = new DeviceDetailPage();
    }

    @Story("We can specify respective story from JIRA here")
    @Description("This test verifies adding note to a device")
    @Feature("Add Note to Device")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void addNoteToDevice() throws Exception{


        click(Header.devices_icon);
        devicePage.selectDevice();
        Assert.assertTrue(deviceDetailPage.addNoteToDevice(),"Note could not be added to the device");
    }
}
