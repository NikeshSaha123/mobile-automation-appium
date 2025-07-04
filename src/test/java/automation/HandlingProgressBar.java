package automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class HandlingProgressBar {

    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        dc.setCapability(MobileCapabilityType.APP, "C:\\apkfiles\\AndroidUI.apk");

        // Start session with Appium server
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver<WebElement> driver = new AndroidDriver<>(url, dc);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            WebElement seekBar = driver.findElement(By.id("com.android.androidui:id/seekBar1"));

            String valueBefore = driver.findElement(By.id("com.android.androidui:id/TextProgress")).getText();
            System.out.println("Before progress: " + valueBefore);

            // Get seekBar location and size
            int startX = seekBar.getLocation().getX();
            int startY = seekBar.getLocation().getY();
            int width = seekBar.getSize().getWidth();

            // Move slider to 70% (example)
            int moveTo = (int) (startX + (width * 0.7));

            TouchAction<?> action = new TouchAction<>(driver);
            action.press(PointOption.point(startX, startY))
                  .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                  .moveTo(PointOption.point(moveTo, startY))
                  .release()
                  .perform();

            String valueAfter = driver.findElement(By.id("com.android.androidui:id/TextProgress")).getText();
            System.out.println("After progress: " + valueAfter);

        } catch (Exception e) {
            System.out.println("Failed to move progress bar: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
