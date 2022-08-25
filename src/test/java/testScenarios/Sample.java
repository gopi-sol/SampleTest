package testScenarios;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import autoitx4java.AutoItX;
import base.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import testUtils.ElementUtil;
import testUtils.TestUtil;

public class Sample extends BaseClass{
	
	
	TestUtil tu;
	
	ElementUtil utils;
	
	 @BeforeTest
	public void setup() throws IOException {
			initialization();
			tu =new TestUtil();
			utils = new ElementUtil(driver);
	 }
	 
	 @Test
	public void xmlDownload() throws IOException {


		driver.get("http://www.landxmlproject.org/file-cabinet");
		 
		 LocalDate today = LocalDate.now();
		 
	        System.out.println("Current date: " + today);

	        //add 2 week to the current date
	        LocalDate next2Week = today.minus(2, ChronoUnit.WEEKS);
	        System.out.println("Next week: " + next2Week);
	        
	        String text = driver.findElement(By.xpath("//*[@id=\"JOT_FILECAB_container_wuid:gx:27e097a33d866e64\"]/td[3]/span[2]/a[1]")).getText();
	        String text1 = driver.findElement(By.xpath("//*[@id=\"JOT_FILECAB_container_wuid:gx:27e097a33d866e64\"]/td[3]/span[2]/a[2]")).getText();
	        String text2 = driver.findElement(By.xpath("//*[@id=\"JOT_FILECAB_label_wuid:gx:5bb69dd1ec12ffc\"]")).getText();
	        
	       for (int i = 0; i < 2; i++) {
				if ("View"!=driver.findElement(By.xpath("//*[@id=\"JOT_FILECAB_container_wuid:gx:27e097a33d866e64\"]/td[3]/span[2]/a[1]")).getText().intern()) {
				
		 System.out.println("not same");
				} else {
					System.out.println("same");
				}
			}
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='MntnRoad.xml']//following::span[1]//a[text()='Download']"))).click();
		 File f1 = new File(System.getProperty("user.dir")+File.separator+"inputfiles");
		 f1.delete();

//		 String parent = driver.getWindowHandle();
//		 Set<String> windowHandles = driver.getWindowHandles();
//		 
//		 for (String s : windowHandles) {
//			System.out.println(s);
//			if (s.equals(parent)) {
//				
//			}else {
//				driver.switchTo().window(s);
//			}
//				
//		}
//		 
//		 new WebDriverWait(driver, 20).until(ExpectedConditions.titleContains("Untitled"));
	
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		 File f = new File(System.getProperty("user.dir")+File.separator+"inputfiles");
		 f.mkdir();
		 System.out.println(f.mkdir());
		 
		 
		 String absolutePath = f.getAbsolutePath();
		 System.out.println(absolutePath);
		 
		 
		 StringSelection file = new StringSelection(absolutePath+File.separator+text2);
		 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);

		 Robot rb = null;
		
		 try {
			
			 rb = new Robot();
		} 
		 catch (AWTException e) {
			
		}

		 rb.setAutoDelay(2000); // Similar to thread.sleep

		 rb.keyPress(KeyEvent.VK_CONTROL);
		 rb.keyPress(KeyEvent.VK_V);
		 rb.keyRelease(KeyEvent.VK_V);
		 rb.keyRelease(KeyEvent.VK_CONTROL);
		 

		 rb.setAutoDelay(2000);

		 rb.keyPress(KeyEvent.VK_ENTER);
		 rb.keyRelease(KeyEvent.VK_ENTER);
		 
		 

	}
}
 /* Tracking Screen -remembrance
  * Switching Tabs And clearing browser cache as it need to be done
//String parent = driver.getWindowHandle();
//Set<String> windowHandles = driver.getWindowHandles();
//for (String s : windowHandles) {
//	System.out.println(s);
//	if (s.equals(parent)) {
//		
//	}else {
//		driver.switchTo().window(s);
//	}
//		
//}
//driver.get("chrome://settings/clearBrowserData");
//Thread.sleep(5000);
//Robot rb3 = null;
//	
//	rb3 = new Robot();
//
//rb3.setAutoDelay(2000); // Similar to thread.sleep
//
//rb3.keyPress(KeyEvent.VK_TAB);
//rb3.setAutoDelay(1000);
//rb3.keyPress(KeyEvent.VK_TAB);
//rb3.setAutoDelay(1000);
//rb3.keyPress(KeyEvent.VK_TAB);
//rb3.setAutoDelay(1000);
//rb3.keyPress(KeyEvent.VK_TAB);
//rb3.setAutoDelay(1000);
//rb3.keyPress(KeyEvent.VK_TAB);
//rb3.setAutoDelay(1000);
//rb3.keyPress(KeyEvent.VK_TAB);
//rb3.setAutoDelay(1000);
//rb3.keyPress(KeyEvent.VK_TAB);
//rb3.setAutoDelay(1000);
//rb3.keyPress(KeyEvent.VK_TAB);
//
//Thread.sleep(3000);
//rb3.keyPress(KeyEvent.VK_ENTER);
//Thread.sleep(2000);
//	 driver.manage().deleteAllCookies();
//		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
//driver.close();
	
//driver.get(tu.getData("Logindata", 2, 0));

//driver.manage().window().maximize();
 *
 */

/*creating a new tab
 * 
  			Robot rb2 = null;
//			
//			rb2 = new Robot();
//		
//		 rb2.setAutoDelay(2000); // Similar to thread.sleep
//
//		 rb2.keyPress(KeyEvent.VK_CONTROL);
//		 rb2.keyPress(KeyEvent.VK_T);
//		 rb2.keyRelease(KeyEvent.VK_T);
//		 rb2.keyRelease(KeyEvent.VK_CONTROL);
 * 
 */
	