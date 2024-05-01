package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import resourcesCodes.baseTest;

public class LoginTestCases extends baseTest{
	
	
	@Test(dataProvider="details")
	public void loginIntoApp(String name,String gender,String country) {
		
		form.setNameField(name);
		form.setGender(gender);
		form.setCountry(country);
		form.submitForm();
	}
	
//	@Test
//	public void NegativeTestCases() {
//		
//		
//		form.setGender("Female");
//		form.setCountry("Argentina");
//		form.submitForm();
//		String toast=driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
//		Assert.assertEquals(toast, "Please enter your name");
//		
//	}
	
	
	 @DataProvider(name = "details")
	public Object[][] data() throws IOException, ParseException {
		Object[][] data= getLoginCredentials(System.getProperty("user.dir")+"\\src\\test\\java\\resourcesCodes\\details.json");
		return data;
	}

}
