package pages.inventa.dashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage {

    @FindBy(xpath="*//div[contains(text(), 'Device Disclosure')]") public static WebElement dashboard_caption;
}
