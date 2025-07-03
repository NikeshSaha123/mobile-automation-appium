package automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;

public class InstallAppAndroidEmulator {

	public static void main(String[] args) throws MalformedURLException {

		DesiredCapabilities dc = new DesiredCapabilities();

		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		dc.setCapability(MobileCapabilityType.APP, "C:\\apkfiles\\IndiaMart.apk");
		dc.setCapability(MobileCapabilityType.NO_RESET, true);

		// Start session with Appium server (running on localhost:4723)
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		AndroidDriver<WebElement> driver = new AndroidDriver<>(url, dc);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("App Launched Successfully!");

		driver.quit();
	}
}
