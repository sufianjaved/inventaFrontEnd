package testcases.inventa.discover;

import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Test;
import pages.inventa.common.Header;
import pages.inventa.discover.DeviceDetailPage;
import pages.inventa.discover.DevicePage;
import utils.BaseClassFrontEnd;

public class DeviceAddTagTest extends BaseClassFrontEnd {

    DevicePage devicePage;
    DeviceDetailPage deviceDetailPage;

    @Override
    protected void initializePageElements() {
        devicePage = new DevicePage();
        deviceDetailPage = new DeviceDetailPage();
    }
    @Story("We can specify respective story from JIRA here")
    @Description("This test verifies adding a new tag to a device")
    @Feature("Add Tag to Device")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void addTagToDevice() throws Exception{

        click(Header.devices_icon);
        devicePage.selectDevice();
        Assert.assertTrue("Tag could not be added to the device",deviceDetailPage.addTagToDevice());
    }
}
