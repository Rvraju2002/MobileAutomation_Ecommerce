package Android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import ExcessCodes.reusableCode;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends reusableCode {

	
	
	AndroidDriver driver;
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	List<WebElement> getProductList;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	WebElement totalvalue;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	WebElement terms;
	
	@AndroidFindBy(className="android.widget.Button")
	WebElement close;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	WebElement check;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	WebElement waytoweb;
	

	
	public double getProductSum() {
		
		double totalsum = 0;
		for (int i = 0; i < getProductList.size(); i++) {
			String list = getProductList.get(i).getText();
			double price = StringtoDoubleConverter(list);
			totalsum = totalsum + price;
			
			
		}
		return totalsum;

	}
	
	public double getTotalAmountDisplayed() {
		String declared=totalvalue.getText();
		double values = StringtoDoubleConverter(declared);
		return values;
	}
	
	public void acceptTermsandConditions() {
		longPress(terms);
		close.click();
        check.click();
        
	}
	
	public void submitProduct() {
		waytoweb.click();
	}
}
