package testcases.inventa.discover;

import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Test;
import pages.inventa.common.Header;
import pages.inventa.discover.DevicePage;
import utils.BaseTest;

public class DeviceSearchTest extends BaseTest {

    DevicePage devicePage;

    @Override
    protected void initializePageElements() {

        devicePage = new DevicePage();
    }

    @Story("We can specify respective story from JIRA here")
    @Description("Search device functionality right now is not working")
    @Feature("Search Device from Search bar")
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void searchDevice() throws Exception{

        click(Header.devices_icon);
        devicePage.searchDevice();

        Assert.assertTrue("Explicitly failing test", false);
    }
    //@Test
    //Export CSV Test
}
