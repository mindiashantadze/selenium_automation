package mshantadze.utils.report;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshot {
    public static void takeScreenshot(String testCaseName, WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("target/reports/screenshots/" + testCaseName + ".png");
        try {
            FileUtils.copyFile(source, destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
