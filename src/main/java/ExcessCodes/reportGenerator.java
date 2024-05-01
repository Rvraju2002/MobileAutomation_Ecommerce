package ExcessCodes;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class reportGenerator {
	
public static  ExtentReports getReport() {
		
		String path =System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		spark.config().setReportName("OpenCart Test results");
		spark.config().setDocumentTitle("OpenCart 2024");
		
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Vignesh", "Tester");
		return extent;
	}

}
