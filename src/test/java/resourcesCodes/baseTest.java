package resourcesCodes;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import Android.formPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class baseTest {
	
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public formPage form ;
	@BeforeMethod
	public void configureAppium() throws MalformedURLException, URISyntaxException {
		
		
				service=new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\admin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).withIPAddress("127.0.0.1").usingPort(4723).build();
				//service.start();
			
//				UiAutomator2Options options = new UiAutomator2Options();
//				options.setDeviceName("PIXEL4");
//				options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\resourcesCodes\\General-Store.apk");
//					
//				driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
				String appFilePath =System.getProperty("user.dir")+"\\src\\test\\java\\resourcesCodes\\General-Store.apk";
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("deviceName", "PIXEL4");
				caps.setCapability("platformName", "Android");
				caps.setCapability("automationName", "UiAutomator2");
				caps.setCapability("app", appFilePath);
				
				driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), caps);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				form = new formPage(driver);
	}
	
	public void longPress(WebElement ele) {
		
		((JavascriptExecutor)driver).executeScript("mobile:longClickGesture", ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),"duration",2000));
	}
	
	public void endScroll() {
		boolean canScrollMore;
		do {
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			));
		}while(canScrollMore);

	}
	public void swiped(WebElement ele,String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement)ele).getId(),
			    "direction", direction,
			    "percent", 0.10
			));
	}
	
	public void dragDrop(WebElement source,int x,int y) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) source).getId(),
			    "endX", x,
			    "endY", y
			));
	}
	
	public double StringtoDoubleConverter(String object) {
		double price=Double.parseDouble(object.substring(1));
		return price;
	}
	
	
	 public Object[][] getLoginCredentials(String path) throws IOException, ParseException {
//	         Read JSON file
	        JSONParser parser = new JSONParser();
	        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(path));

	        // Create a 2D array to store email and password combinations
	        Object[][] data = new Object[jsonArray.size()][3];

	        // Extract email and password from each JSON object
	        for (int i = 0; i < jsonArray.size(); i++) {
	            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
	            String name = (String) jsonObject.get("name");
	            String gender = (String) jsonObject.get("gender");
	            String country = (String) jsonObject.get("country");

	            data[i][0] = name;
	            data[i][1] = gender;
	            data[i][2] = country;
	        }

	        return data;
		 
	 }
	 
	 public String getScreenShot(String testCaseName,AndroidDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source =  ts.getScreenshotAs(OutputType.FILE);
			File file= new File(System.getProperty("user.dir")+ "//reports//"+testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir")+ "//reports//"+testCaseName + ".png";
		}
	 
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
        service.stop();
	}

}
