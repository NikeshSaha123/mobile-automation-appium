package automation;

import java.net.URL;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class SendingSMS {

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();

		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");

		dc.setCapability("appPackage", "com.android.mms");
		dc.setCapability("appActivity", "com.android.mms.ui.ComposeMessageActivity");

		// Start session with Appium server (running on localhost:4723)
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		AndroidDriver<WebElement> driver = new AndroidDriver<>(url, dc);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		try {
			// Enter recipient
			driver.findElement(By.id("com.android.mms:id/recipients_editor")).sendKeys("7717776035");

			// Enter message
			driver.findElement(By.id("com.android.mms:id/embedded_text_editor")).sendKeys("Hi Nikesh!");

			// Send message
			driver.findElement(By.id("com.android.mms:id/send_button_sms")).click();

			System.out.println("SMS sent successfully!");
		} catch (Exception e) {
			System.out.println("Failed to send SMS: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}
}