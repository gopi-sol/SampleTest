package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import testUtils.TestUtil;

public class BaseClass {
	
    public static  WebDriver driver;
	public static  Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebDriverEventListener eventListener;
	public static ExtentTest extenttest;
	static TestUtil tu =new TestUtil();
			
	

	
	public static void initialization() throws IOException{
		
		String browserName =tu.getData("Logindata", 1, 3);

		if(browserName.equals("chrome")){
			
			WebDriverManager.chromedriver().setup();
			 Map<String, Object> prefs = new HashMap<String, Object>();

			 prefs.put("download.prompt_for_download", true);
			 prefs.put("download.extensions_to_open", "application/xml");
			 prefs.put("safebrowsing.enabled", true);
			 ChromeOptions options = new ChromeOptions();
			 options.setExperimentalOption("prefs", prefs);

			 options.addArguments("start-maximized");
			 options.addArguments("--safebrowsing-disable-download-protection");
			 options.addArguments("safebrowsing-disable-extension-blacklist");
			 driver =  new ChromeDriver(options); 

		}
		else if(browserName.equals("FF")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(); 
		}
		else if(browserName.equals("Edge")){
			WebDriverManager.edgedriver().setup();	
			driver = new EdgeDriver(); 
		}
	
		driver.manage().window().maximize();
		
	}
		
	public static void urlLaunch() throws IOException {
		
		try {
			driver.get(tu.getData("Logindata", 1, 0));
		} catch (Exception e) {
			
			System.out.println("No Valid Input");
			
		}

	}
	public static void urlLaunchT() {
		try {
			driver.get(tu.getData("Logindata", 2, 0));
		} catch (Exception e) {
			
			System.out.println("No Valid Input");
			
		}

	}
	
	
	public static String Currentdate() {
		
		// Create object of SimpleDateFormat class and decide the format
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
	    
	    //get current date time with Date()
	    Date date = new Date();
	    
	    // Now format the date
	    String date1= dateFormat.format(date);
	  
	    return date1;
		
	}
		
	public static String getScreenshot() {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE); 
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}
	
public static String entireScreenshot() throws IOException  {
    	
    	
		Screenshot src=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(10000)).takeScreenshot(driver);
		
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File f = new File(path);
		ImageIO.write(src.getImage(), "PNG", f);
		
		String absolutePath = f.getAbsolutePath();
		
		return absolutePath;
	}

public void passDates() {
	LocalDate today = LocalDate.now();
    System.out.println("Current date: " + today);
    
    LocalDate next2Week = today.minus(2, ChronoUnit.WEEKS);
    System.out.println("Next week: " + next2Week);

}

	
}
