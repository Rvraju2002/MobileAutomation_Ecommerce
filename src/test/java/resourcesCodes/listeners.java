package resourcesCodes;

import java.io.IOException;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ExcessCodes.reportGenerator;
import io.appium.java_client.android.AndroidDriver;


public class listeners extends baseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent =  reportGenerator.getReport();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//thread safe
      @Override		
      public void onTestStart(ITestResult result) {					
        // TODO Auto-generated method stub		
    	test=  extent.createTest(result.getMethod().getMethodName());
    	extentTest.set(test);//unique thread id
    	
    	
        		
        }		
      
      @Override		
	    public void onTestSuccess(ITestResult result) {					
	        // TODO Auto-generated method stub	
    	  extentTest.get().log(Status.PASS, "Test passed");
	    	
	    }
	
	    @Override		
	    public void onTestFailure(ITestResult result) {					
	        // TODO Auto-generated method stub				
	    	extentTest.get().fail(result.getThrowable());//console output
	    	try {
				driver = (AndroidDriver)  result.getTestClass().getRealClass().getField("driver")
						.get(result.getInstance());
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
	    	String filePath = null;
			try {
				filePath = getScreenShot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	    	//ScreenShot,Attach to report
	    }		

	    @Override		
	    public void onTestSkipped(ITestResult result) {					
	        // TODO Auto-generated method stub				
	        		
	    }
	    
	    @Override		
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {					
	        // TODO Auto-generated method stub				
	        		
	    }		
	    

	    @Override		
	    public void onStart(ITestContext context) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	   
	    @Override		
	    public void onFinish(ITestContext context) {					
	        // TODO Auto-generated method stub				
	        		extent.flush();
	    }		
}
