package treelogyAndroid;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class TreelogyAppQC {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		AndroidDriver driver = null;
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:deviceName", "Android Device");
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:dontStopAppOnReset", true);

	    caps.setCapability("appium:appPackage", "in.saaritresources.treecensus");
	    caps.setCapability("appium:appActivity", "in.saaritresources.treecensus.MainActivity");

	    URL url = new URL("http://127.0.0.1:4723");                                                                              
	    driver = new AndroidDriver(url , caps);
	    System.out.println("Session created and app launched....");

        Thread.sleep(3000);
        
        //Login
        driver.findElement(By.xpath("//android.widget.EditText[1]"))
        .sendKeys("bmcqc@gmail.com");
        driver.findElement(By.xpath("//android.widget.EditText[2]"))
        .sendKeys("123");
        driver.findElement(By.xpath("//android.widget.TextView[@text='Sign In']")).click();
        
        //view map
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.elementToBeClickable(
        		AppiumBy.androidUIAutomator("new UiSelector().text(\"View Map\")")))
               .click();
        //driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"View Map\")")).click();
        // ✅ WAIT
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
//        wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//android.widget.TextView[@text='Start New Survey']")))
//                .click();
	}

}
