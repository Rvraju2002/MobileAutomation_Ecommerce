package Android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import ExcessCodes.reusableCode;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class productPage extends reusableCode{

	AndroidDriver driver;
	public productPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	private List<WebElement> productsList;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> clickAddtoCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement addtoCart;
	
	public List<WebElement> getProductfromProductPage() {
		
		 
		 return productsList;
		
	}
	
	public void scrollIntoProducts(String productName) {
		scrollToText(productName);
	}
	
	public void addProductIntoCart(int i) {
		clickAddtoCart.get(i).click();
	}
	
	public CartPage goToCartPage() {
		addtoCart.click();
		return new CartPage(driver);
	}
	
	
	
	
}
