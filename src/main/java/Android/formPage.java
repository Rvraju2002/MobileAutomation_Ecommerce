package Android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import ExcessCodes.reusableCode;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class formPage extends reusableCode {

	AndroidDriver driver;
	public formPage(AndroidDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement name;
//	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Asha");
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement female;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement male;
	
//	driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	WebElement spinnerCountry;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement sumbit;
	
	
	
	public void setNameField(String nameField) {
		name.sendKeys(nameField);
		driver.hideKeyboard();
	}
	
	public void setGender(String Gender) {
		if(Gender.contains("Female")) {
			female.click();
	}else if(Gender.contains("Male")) {
		male.click();
	}
	
}
	
public void setCountry(String CountryName) {
	spinnerCountry.click();
	scrollToText(CountryName);
	driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='"+CountryName+"']")).click();
	}


public productPage submitForm() {
	sumbit.click();
	return new productPage(driver);
}
}
