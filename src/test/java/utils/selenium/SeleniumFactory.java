package utils.selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumFactory extends EnvironmentSetup {

    Logger logger = LogManager.getLogger("common");
    public JavascriptExecutor js = (JavascriptExecutor) driver;
    public Actions actions;

    public SeleniumFactory(){
        super();
    }

    public void closeDialogWindow(){
        driver.switchTo().parentFrame();
        scrollToThenClick(getElement(By.xpath("*//span[@class='ui-button-icon-primary ui-icon ui-icon-closethick']")));
    }

    public boolean elementExists(By locator){
        return elementExists(getElement(locator));
    }

    public boolean elementExists(WebElement element){
        boolean isElementExists = false;
        try {
            if(element.isEnabled() && ExpectedConditions.elementToBeClickable(element)!=null)
                isElementExists = true;
        } catch (Exception e) {
            //System.out.println(e);
        }
        return isElementExists;
    }

    public void acceptAlert(){
        try{
            if(isAlertPresent())
                driver.switchTo().alert().accept();
            else
                logger.error("Alert not present");
        }catch(UnhandledAlertException e){
            if(isAlertPresent())
                driver.switchTo().alert().accept();
            logger.error("Unexpected alert not present" + e);
        }
    }

    public void dismissAlert(){
        try{
            if(isAlertPresent())
                driver.switchTo().alert().dismiss();
            else
                logger.error("Alert not present");
        }catch(UnhandledAlertException e){
            if(isAlertPresent())
                driver.switchTo().alert().dismiss();
            logger.error("Unexpected alert not present" + e);
        }
    }

    public WebElement waitForElement(By selector){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public boolean waitUntilElementAttributeContains(By locator, String attribute, String value){
        WebElement element = getElement(locator);
        return waitUntilElementAttributeContains(element,attribute,value);
    }

    public boolean waitUntilElementAttributeContains(WebElement element, String attribute, String value){
        return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public WebElement waitForElement(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElement(By selector,int seconds){
        WebDriverWait elmWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return elmWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public WebElement waitForElement(WebElement element,int seconds){
        WebDriverWait elmWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return elmWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void navigateBack(){
        driver.navigate().back();
    }

    public void refreshBrowser(){
        driver.navigate().refresh();
        sleep(2000);
    }

    public void enterURL(String url){
        driver.get(url);
    }

    public void click(WebElement element){
        waitForElement(element);
        element.click();
    }

    public void click(By selector){
        waitForElement(selector);
        click(getElement(selector));
    }

    public void scrollToThenClick(By selector) {
        waitForElement(selector);
        WebElement element = getElement(selector);
        scrollToThenClick(element);
    }

    public void scrollToThenClick(WebElement element) {
        actions = new Actions(driver);
        //waitForElement(element);
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        js.executeScript(scrollElementIntoMiddle, element);
        actions.moveToElement(element);
        actions.click(element).perform();
    }

    public void moveToElement(WebElement element) {
        actions = new Actions(driver);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        actions.moveToElement(element).perform();
    }

    public void moveToElement2(WebElement element) {
        actions = new Actions(driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)", element);
        actions.moveToElement(element).perform();
    }

    public void moveToElementThenClick(WebElement element, int xOffset, int yOffset) {
        actions = new Actions(driver);
        waitForElement(element);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        actions.moveToElement(element, xOffset, yOffset).perform();
        actions.click().perform();
    }

    public void moveToElementThenClick(WebElement element, String position) {
        actions = new Actions(driver);
        waitForElement(element);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)", element);

        Point pt = element.getLocation();
        int NumberX=pt.getX();
        int NumberY=pt.getY();

        //  int offsetX = (element.getSize().getWidth() / 2 ) + 1;
        // int offsetY = (element.getSize().getHeight() / 2 ) + 1;

        // int Width = element.getSize().width;//For Wait List 58
        // int Height = element.getSize().height;//For Wait List 25

        // Width = -1;
        //  Height = -1;

        switch (position){
            case "left":
                //actions.moveToElement(element, 1, element.getSize().height/2).click().build();
                //actions.moveToElement(element, Width, Height).perform();
                actions.moveByOffset(NumberX, NumberY);
                break;
            case "right":
                actions.moveToElement(element, (element.getSize().width)-1, element.getSize().height/2).perform();
                break;
            case "top":
                actions.moveToElement(element, element.getSize().width/2, 1).perform();
                break;
            case "bottom":
                actions.moveToElement(element, element.getSize().width/2, (element.getSize().height)-1).perform();
                break;
        }
        actions.click().perform();
    }

    public void dragAndDrop(WebElement source,WebElement target) throws AWTException {
        actions = new Actions(driver);
        waitForElement(source);
        waitForElement(target);
        Point coordinates2 = target.getLocation();
        Robot robot = new Robot();

        actions.moveToElement(source);
        actions.clickAndHold(source).perform();
        robot.mouseMove(coordinates2.getX(), coordinates2.getY()); //moving mouse on screen to handle chromedriver issue
        sleep(2000);
        actions.release(target).perform();
    }

    public void dragAndDrop(By src,By tgt) throws AWTException {
        WebElement source = getElement(src);
        WebElement target = getElement(tgt);
        dragAndDrop(source,target);
    }

    public void rightClick(WebElement element){
        waitForElement(element);
        actions = new Actions(driver);
        actions.contextClick(element).build().perform();
    }

    public void rightClick(By selector){
        waitForElement(selector);
        actions = new Actions(driver);
        WebElement element = getElement(selector);
        actions.contextClick(element).build().perform();
    }

    public WebElement getElement(By selector) {
        try {
            return driver.findElement(selector);
        } catch (Exception e) {
            logger.error(String.format("Element %s does not exist - proceeding", selector));
            return null;
        }
    }

    public List<WebElement> getElements(By selector) {
        try {
            return driver.findElements(selector);
        } catch (Exception e) {
            logger.error(String.format("Element %s does not exist - proceeding", selector));
            return null;
        }
    }

    public void sendKeys(By selector, CharSequence... keysToSend) throws Exception {
        waitForElement(selector);
        WebElement element = getElement(selector);
        try {
            sendKeys(element,keysToSend);
        } catch (Exception e) {
            logger.error(String.format("Error in sending [%s] to the following element: [%s]", keysToSend, element.toString()));
        }
    }

    public void sendKeys(WebElement element, CharSequence... keysToSend) throws Exception {
        waitForElement(element);
        try {
            element.sendKeys(keysToSend);
        } catch (Exception e) {
            logger.error(String.format("Error in sending [%s] to the following element: [%s]", keysToSend, element.toString()));
        }
    }

    public void clearField(By selector) {
        waitForElement(selector);
        WebElement element = getElement(selector);
        try {
            clearField(element);
        } catch (Exception e) {
            logger.error(String.format("The following element could not be cleared: [%s]", element.getText()));
        }
    }

    public void clearField(WebElement element) {
        try {
            waitForElement(element);
            element.clear();
        } catch (Exception e) {
            logger.error(String.format("The following element could not be cleared: [%s]", element.getText()));
        }
    }

    public Select select(WebElement element){
        //waitForElement(element);
        Select sel = new Select(element);
        return sel;
    }

    public void selectByIndex_excludeFirstListItem(WebElement element){
        int index = (int)(Math.random()*select(element).getOptions().size());
        select(element).selectByIndex(index>0?index:1);
    }

    public void selectRandomValue(WebElement element, ArrayList<Integer> list){
        int value = list.get((int)(Math.random()*list.size()));
        select(element).selectByValue(Integer.toString(value));
    }

    public void selectAll(WebElement element){
        for(WebElement eachElem:select(element).getOptions())
            select(element).selectByVisibleText(eachElem.getText());
    }

    public Select select(By selector){
        WebElement element = getElement(selector);
        return select(element);
    }

    public void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public boolean selectFromAutoCompleteFieldSearch(String value){
        return selectFromAutoCompleteFieldSearch(value,"");
    }

    /**
     The method select option from autocomplete search result using value; criteria to use [starts-with,ends-with], default is contains
     @param
     */
    public boolean selectFromAutoCompleteFieldSearch(String value, String criteria){
        String xpath = "//li[@class='ui-menu-item']/a[contains(text(),'"+value+"')]";

        if(criteria!=null && criteria.equals("starts-with"))
            xpath = "//li[@class='ui-menu-item']/a[starts-with(text(),'"+value+"')]";

        if(criteria!=null && criteria.equals("ends-with"))
            xpath = "//li[@class='ui-menu-item']/a[substring(text(),string-length(text()) -string-length('"+value+"') +1) = '"+value+"']";

        try {
            waitForElement(By.xpath(xpath));
            click(By.xpath(xpath));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	/*public boolean isAlertPresent(){
		try {
	        WebDriverWait wait = new WebDriverWait(driver, 5);
	        wait.until(ExpectedConditions.alertIsPresent());
	        Alert alert = driver.switchTo().alert();
	        return alert != null;
		}catch(Exception e){
			return false;
		}
	}*/

    public boolean isAlertPresent(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); //added because it was failing because of expectedAlert
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = ExpectedConditions.alertIsPresent().apply(driver);
            return alert!=null?true:false;
        }catch(UnhandledAlertException e){
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Nullable
    public String alertGetMessage() {
        try {
            Alert alert = driver.switchTo().alert();
            String msg = alert.getText();
            return msg;
        } catch(Exception ex) {
            return null;
        }
    }


}
