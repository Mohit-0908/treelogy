package treelogyAndroid;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class TreelogyApp {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        AndroidDriver driver = null;

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "Android Device");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:noReset", false);
        caps.setCapability("appium:dontStopAppOnReset", true);

        caps.setCapability("appium:appPackage", "in.saaritresources.treecensus");
        caps.setCapability("appium:appActivity", "in.saaritresources.treecensus.MainActivity");

        caps.setCapability("appium:udid", "NFAEQKUWDQTKVGKR");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);

        System.out.println("Session created and app launched....");

        Thread.sleep(5000);

        //  LOGIN
        driver.findElement(By.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[1]"))
                .sendKeys("bmcS@gmail.com");

        driver.findElement(By.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[2]"))
                .sendKeys("123");

        driver.findElement(By.xpath("//android.widget.TextView[@text='Sign In']")).click();

        // ✅ WAIT
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='Start New Survey']")))
                .click();

        // 🌳 SELECT TREE
        driver.findElement(By.xpath("//android.widget.TextView[@text='Select Local Name']")).click();
        Thread.sleep(2000);
    driver.findElement(By.xpath("//android.widget.TextView[@text='African Wattle']")).click();
//        driver.findElement(By.xpath("//android.widget.TextView[@text='dummy']")).click();

        // 🌿 TREE MEASUREMENTS (SCROLL + ENTER)
        enterValueWithScroll(driver, "Girth", "26");
        
        enterValueWithScroll(driver, "Height", "15");
       
        enterValueWithScroll(driver, "Canopy", "5");
        
        enterValueWithScroll(driver, "Dist", "10");

        // driver.quit();
        
        //Tree Assestment 
        scrollToTreeAssessment(driver);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
        	    By.xpath("//android.widget.TextView[@text='Select Condition']")
        	)).click();
        //  driver.findElement(By.xpath("//android.widget.TextView[@text='Select Condition']")).click();
        driver.findElement(By.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]")).click();
        driver.findElement(By.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='NA']")).click();
        driver.findElement(By.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Not Required']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='On Compound Wall']")).click();
     
        // Ownership Details
//        scrollToOwnershipDetails(driver);
        driver.findElement(By.xpath("//android.widget.TextView[@text='BMC']")).click();
        driver.findElement(By.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]")).click();
//        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Condition')]")).click();
        
        // Location and Details
        scrollToLocationDetails(driver);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.View[2]/android.view.View"))).click();
       // driver.findElement(By.xpath("//android.widget.ScrollView/android.view.View[2]/android.view.View")).click();
        Thread.sleep(5000);
        
        //driver.findElement( AppiumBy.androidUIAutomator("new UiSelector().className('android.widget.Button').instance(0)")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Select Society Name']")).click();
//        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Select Society Name\")")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='1 A House']")).click();
        
        driver.findElement(By.xpath("//android.widget.TextView[@text='Select Road Name']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='10th Road']")).click();
        
        
        // 👉 Step 2: Select ConditionW
//     selectFromOpenList(driver, "Healthy");
//
//     // 👉 Step 3: Tree Balance opens automatically
//     selectFromOpenList(driver, "Balanced");
//
//     // 👉 Step 4: Remark opens automatically
//     selectFromOpenList(driver, "NA");
//
//     // 👉 Step 5: Bird Nest opens automatically
//     selectFromOpenList(driver, "NO");
//
//     // 👉 Step 6: Tree Basin opens automatically
//     selectFromOpenList(driver, "Not Required");
//     
//     selectFromOpenList(driver, "On Compound Wall");
        
//        selectDropdownValueofAssestment(driver, "Condition", "Healthy");
//        selectDropdownValueofAssestment(driver, "Tree Balance", "Balance");
//        selectDropdownValueofAssestment(driver, "Remark", "NA");
//        selectDropdownValueofAssestment(driver, "Bird Nest", "NO");
//        selectDropdownValueofAssestment(driver, "Tree Basin", "Not Required");
//        selectDropdownValueofAssestment(driver, "Other Remarks", "On Compound Wall");
        
        
    }

    // 🔥 FIND FIELD + SCROLL + ENTER VALUE
    public static void enterValueWithScroll(AndroidDriver driver, String label, String value) {

        boolean found = false;

        for (int i = 0; i < 8; i++) {
            try {
                WebElement field = driver.findElement(
//                        By.xpath("//android.widget.TextView[contains(@text,'" + label + "')]/parent::*/android.widget.EditText")
                        By.xpath("//android.widget.TextView[contains(@text,'" + label + "')]/following-sibling::android.widget.EditText")

                       // By.xpath("//android.widget.TextView[contains(@text,'Height')]/following-sibling::android.widget.EditText")
//                        By.xpath("(//android.widget.TextView[@text,'Height']/following::android.widget.EditText)[1]")
                     
                );

                if (field.isDisplayed()) {
                    field.click();
                    field.clear();
                    field.sendKeys(value);
                    
                    driver.hideKeyboard();
                    System.out.println("✅ Entered: " + label);
                    found = true;
                    break;
                }

            } catch (Exception e) {
                smallScroll(driver);
            }
        }

        if (!found) {
            System.out.println("❌ Not found: " + label);
        }
    }
    
    //tree assestment 
//    public static void selectFromOpenList(AndroidDriver driver, String option) {
//
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            WebElement value = wait.until(ExpectedConditions.elementToBeClickable(
////                By.xpath("//android.widget.TextView[@text='" + option + "']")
//            		By.xpath("//android.widget.TextView[contains(@text,'Condition')]/ancestor::android.view.View")
//            ));
//
//            value.click();
//
//            System.out.println("✅ Selected: " + option);
//
//        } catch (Exception e) {
//            System.out.println("❌ Failed selecting: " + option);
//        }
//    }
//    public static void selectDropdownValueofAssestment(AndroidDriver driver, String label, String option) {
//
//        try {
//            // 👉 Step 1: Click on the field using label
//            WebElement field = driver.findElement(
//                By.xpath("//android.widget.TextView[contains(@text,'" + label + "')]/following-sibling::android.widget.EditText")
//            );
// 
//            field.click();
//            Thread.sleep(1000);
//
//            // 👉 Step 2: Select option from list
//            WebElement value = driver.findElement(
//                By.xpath("//android.widget.TextView[@text='" + option + "']")
//            );
//
//            value.click();
//
//            System.out.println("✅ Selected " + label + " -> " + option);
//
//        } catch (Exception e) {
//            System.out.println("❌ Failed for " + label);
//        }
//    }
//    public static void enterValueWithScroll(AndroidDriver driver, String label, String value) {
//
//        boolean found = false;
//
//        for (int i = 0; i < 10; i++) {
//            try {
//
//                String xpath =
//                    "//android.widget.TextView[contains(@text,'" + label + "')]/following::android.widget.EditText[1]";
//
//                WebElement field = driver.findElement(By.xpath(xpath));
//
//                if (field.isDisplayed()) {
//
//                    // 👇 Step 1: Tap properly
//                    field.click();
//                    Thread.sleep(500);
//
//                    // 👇 Step 2: Ensure focus
//                    if (!field.equals(driver.switchTo().activeElement())) {
//                        field.click();
//                    }
//
//                    // 👇 Step 3: Clear + type
//                    field.clear();
//                    field.sendKeys(value);
//
//                    System.out.println("✅ Entered: " + label);
//
//                    // 👇 Step 4: Move to next field (IMPORTANT)
//                    try {
//                        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "next"));
//                    } catch (Exception e) {
//                        driver.hideKeyboard(); // fallback
//                    }
//
//                    found = true;
//                    break;
//                }
//
//            } catch (Exception e) {
//                smallScroll(driver);
//            }
//        }
//
//        if (!found) {
//            System.out.println("❌ Not found: " + label);
//        }
//    }

    // 🔥 SMALL SCROLL (NO FULL PAGE JUMP)
    public static void bigScroll(AndroidDriver driver) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger"); 
        Sequence swipe = new Sequence(finger, 1);

        int startX = 500;
        int startY = 1800;   // bottom
        int endY = 400;      // top (big movement)

        swipe.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), startX, startY));

        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(800),
                PointerInput.Origin.viewport(), startX, endY));

        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }
    
    public static void smallScroll(AndroidDriver driver) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        int startX = 500;
        int startY = 1600;
        int endY = 1300; // small scroll (~40%)

        swipe.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), startX, startY));

        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), startX, endY));

        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }
    public static void scrollToTreeAssessment(AndroidDriver driver) {

        boolean found = false;

        for (int i = 0; i < 10; i++) {
            try {
                WebElement section = driver.findElement(
                    By.xpath("//android.widget.TextView[contains(@text,'Tree Assessment')]")
                );

                if (section.isDisplayed()) {
                    System.out.println("✅ Tree Assessment section found");
                    found = true;
                    break;
                }

            } catch (Exception e) {
                bigScroll(driver); // 🔥 USE BIG SCROLL HERE
            }
        }

        if (!found) {
            System.out.println("❌ Tree Assessment NOT found");
        }
    }
    //Scroll to Location and Details
    public static void scrollToLocationDetails(AndroidDriver driver) {

        boolean found = false;

        for (int i = 0; i < 7; i++) {
            try {
                WebElement section = driver.findElement(
//                    By.xpath("//android.widget.TextView[@text='Location']")
                    By.xpath("//android.widget.TextView[@text='Road Name']")
                );

                if (section.isDisplayed()) {
                    System.out.println("✅ Scroll to Locations & Details section found");
                    found = true;
                    break;
                }

            } catch (Exception e) {
                bigScroll(driver); // 🔥 USE BIG SCROLL HERE
            }
        }

        if (!found) {
            System.out.println("❌ Scroll to Locations & Details NOT found");
        }
    }
    
//    public static void scrollToOwnershipDetails(AndroidDriver driver) {
//    	
//    	boolean found = false;
//
//        for (int i = 0; i < 10; i++) {
//            try {
//                WebElement section = driver.findElement(
//                    By.xpath("//android.widget.TextView[contains(@text,'Ownership Details')]")
//                );
//
//                if (section.isDisplayed()) {
//                    System.out.println("✅ Ownership Details section found");
//                    found = true;
//                    break;
//                }
//
//            } catch (Exception e) {
//                bigScroll(driver); // 🔥 USE BIG SCROLL HERE
//            }
//        }
//
//        if (!found) {
//            System.out.println("❌ Ownership Details NOT found");
//    }
//    }
}