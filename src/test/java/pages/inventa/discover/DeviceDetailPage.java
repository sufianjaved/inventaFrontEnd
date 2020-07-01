package pages.inventa.discover;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.selenium.SeleniumFactory;

public class DeviceDetailPage  extends SeleniumFactory {

    //tags tab
    @FindBy(id="nav-tags-tab") private WebElement tags_tab;
    @FindBy(id="tagName") private WebElement tag_field;
    @FindBy(xpath="//button[contains(text(), 'Add')]") private WebElement add_btn;

    //note tab
    @FindBy(id="nav-contact-tab") private WebElement note_tab;
    @FindBy(id="editNote") private WebElement note_textArea;
    @FindBy(xpath="//button[contains(text(),'Save')]") private WebElement save_btn;

    @Step("Add Tag to Device")
    public boolean addTagToDevice() throws Exception {

        String tagAddedByAutomation = "Test tag by automation";
        String tagShownOnUI = null;
        click(tags_tab);
        sendKeys(tag_field,tagAddedByAutomation);
        click(add_btn);

        WebElement tagName = getAttributeNameByString(tagAddedByAutomation);
        if(elementExists(tagName)) {
            tagShownOnUI = tagName.getText();
        }

        if(tagAddedByAutomation.contains(tagShownOnUI))
            return true;
        else
            return false;
    }

    public WebElement getAttributeNameByString(String stringAddedByAutomation){
        waitForElement(By.xpath("//div[contains(text(), '"+stringAddedByAutomation+"')]"));
        return getElement(By.xpath("//div[contains(text(), '"+stringAddedByAutomation+"')]"));
    }

    @Step("Add Note to Device")
    public boolean addNoteToDevice() throws Exception {

        String noteAddedByAutomation = "Test note by automation";
        String noteShownOnUI = null;
        click(note_tab);
        clearField(note_textArea);
        sendKeys(note_textArea,noteAddedByAutomation);
        waitForElement(save_btn);
        click(save_btn);
//sleep add
        WebElement deviceNote = getAttributeNameByString(noteAddedByAutomation);
        if(elementExists(deviceNote)) {
            noteShownOnUI = deviceNote.getText();
        }

        if(noteAddedByAutomation.contains(noteShownOnUI))
            return true;
        else
            return false;
    }
}
