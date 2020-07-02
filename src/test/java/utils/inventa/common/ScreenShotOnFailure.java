package utils.inventa.common;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.selenium.EnvironmentSetup;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenShotOnFailure extends EnvironmentSetup implements MethodRule {
    String packageName = "";

    public ScreenShotOnFailure(String packageName){
        this.packageName = packageName;
    }

    @Override
    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            private static final String DATE_FORMATTER= "HH:mm";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
            String formatDateTime = now.format(formatter);
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {

                    if( formatDateTime.indexOf(":") != -1 )
                    {
                        formatDateTime = formatDateTime.replaceAll(":"," ");
                    }
                    captureScreenshot(o.getClass().getSimpleName(), frameworkMethod.getName(), formatDateTime);
                    throw t; // rethrow to allow the failure to be reported to JUnit
                }
            }

            @Attachment(value = "{className} - {testName} - {formatDateTime}", type = "image/png")
            public byte[] captureScreenshot(String className, String testName, String now) throws IOException {

                Allure.addAttachment("{className} - {testName} - {formatDateTime}", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
                TakesScreenshot screenshot = ((TakesScreenshot) driver);

/*
            	if(driver!=null){
            		File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
                    File targetFile = new File("build/screenshots/"+packageName+"/"+className+" - " + testName +" - " + formatDateTime+".png");
                    if(targetFile.exists())
                    	targetFile.delete();
                    FileUtils.copyFile(scrFile, targetFile);
            	}

*/
                if(driver!=null) {
                    Allure.addAttachment("{className} - {testName} - {formatDateTime}", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
                    return screenshot.getScreenshotAs(OutputType.BYTES);
                }
                else
                    return null;
            }
        };
    }
}
