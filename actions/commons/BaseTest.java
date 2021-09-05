package commons;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest {

	protected AndroidDriver<MobileElement> driver;
	protected WebDriverWait wait;
	protected static String environment;
	protected static String envFolderPath;
	protected static String configsFolderPath;
	protected static String testDataFolderPath;
	protected static String projectPath = Constants.PROJECT_PATH;
	protected static String fileSeperator = Constants.FILE_SEPERATOR;

	@BeforeTest
	@Parameters({"env", "deviceName", "udid"})
	public void setups(@Optional String env, String deviceName, String udid) throws Exception {
		setupDriver(deviceName, udid);
		environment = env;
		if (environment == null | environment.equalsIgnoreCase("${env}")) {
			envFolderPath = projectPath + fileSeperator + "environments" + fileSeperator + "testing";
			configsFolderPath = envFolderPath + fileSeperator + "configs";
			testDataFolderPath = envFolderPath + fileSeperator + "testData";
		} else if (environment.equalsIgnoreCase("testing") | environment.equalsIgnoreCase("demo")) {
			envFolderPath = projectPath + fileSeperator + "environments" + fileSeperator + environment;
			configsFolderPath = envFolderPath + fileSeperator + "configs";
			testDataFolderPath = envFolderPath + fileSeperator + "testData";
		} else {
			System.out.println("Invalid environment. Please check again!");
		}
	}

	public void setupDriver(String deviceName, String udid) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", deviceName);
		caps.setCapability("udid", udid);
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "10.0");
		caps.setCapability("skipUnlock", "false");
		caps.setCapability("appPackage", "com.karrostech.parentportal.android.prod");
		caps.setCapability("appActivity", "com.edulog.parentportal.busfollowed.core.navigation.RouteActivity");
		caps.setCapability("noReset", "false");
		driver = new AndroidDriver<MobileElement>(new URL("http://192.168.1.6:4444/wd/hub"), caps);
	}

	@AfterTest
	public void quitDriver() {
		driver.quit();
	}

}
