package ExcessCodes;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class reusableCode {
	
	AndroidDriver driver;
	public reusableCode(AndroidDriver driver) {
		
		this.driver=driver;
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
	public void scrollToText(String textName) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ textName +"\"));"));
	}
	
	public void waitUnitilTextShow(String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains(
				driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", text));
	}
	
	public double StringtoDoubleConverter(String object) {
		double price=Double.parseDouble(object.substring(1));
		return price;

}
	
}
