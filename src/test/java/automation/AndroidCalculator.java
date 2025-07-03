package automation;

import java.net.URL;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class AndroidCalculator {

	public static void main(String[] args) {
		DesiredCapabilities dc = new DesiredCapabilities();

		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");

		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");

		// Start session with Appium server (running on localhost:4723)
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		AndroidDriver<WebElement> driver = new AndroidDriver<>(url, dc);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.id("com.android.calculator2:id/digit_2")).click();
		driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
		driver.findElement(By.id("com.android.calculator2:id/digit_3")).click();
		driver.findElement(By.id("com.android.calculator2:id/eq")).click();

		// Get result
		String result = driver.findElement(By.id("com.android.calculator2:id/result")).getText();
		System.out.println("Result is: " + result);

		if (result.equals("5"))
			System.out.println("Test Passed");
		else
			System.out.println("Test Failed");

		driver.quit();
	}

}
