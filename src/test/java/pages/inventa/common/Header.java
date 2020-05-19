package pages.inventa.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.selenium.SeleniumFactory;

public class Header {

    //@FindBy(xpath="//div[contains(text(), 'Admin')]") public WebElement user_caption;
    @FindBy(id="deviceicon") public static WebElement devices_icon;
}
