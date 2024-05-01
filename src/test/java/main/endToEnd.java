package main;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Android.CartPage;
import Android.formPage;
import Android.productPage;
import io.appium.java_client.AppiumBy;
import resourcesCodes.baseTest;

public class endToEnd extends baseTest {

	@Test
	public void fillForm() throws InterruptedException {

		form.setNameField("Asha");
		form.setGender("Female");
		form.setCountry("Argentina");
		productPage product = form.submitForm();
		List<String> productNames = Arrays.asList("Air Jordan 4 Retro", "PG 3");
		for (int i = 0; i < productNames.size(); i++) {
			String productName = productNames.get(i);

			product.scrollIntoProducts(productName);

			List<WebElement> products = product.getProductfromProductPage();

			for (WebElement productList : products) {
				String extractedProductName = productList.getText();

				if (extractedProductName.contains(productName)) {

					product.addProductIntoCart(i);

					break;
				}
			}
		}

		CartPage cart = product.goToCartPage();
        		double totalsum = cart.getProductSum();
		System.out.println(totalsum);
       
		double values = cart.getTotalAmountDisplayed();
		Assert.assertEquals(values, totalsum);
		
		cart.acceptTermsandConditions();
		cart.submitProduct();

	}
}
