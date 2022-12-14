package testUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ElementUtil {

	private static WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	

	/**
	 * this is used to create the webelement on the basis of by locator
	 * @param locator
	 * @return webelement
	 */
	public WebElement getElement(By locator) {
		
		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("Could not Process...");
		}

		return element;
	}
	
	

	
	
	public void doClick(By locator){
		getElement(locator).click();
	}
	
	public void doSendKeys(By locator, String value){
		getElement(locator).sendKeys(value);
	}
	
	public String doGetText(By locator){
		return getElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator){
		return getElement(locator).isDisplayed();
	}
	
	
	//**********************Actions Methods ********************
	public void doActionsClick(By locator){
		Actions ac = new Actions(driver);
		ac.click(getElement(locator)).perform();
	}
	public void doMoveToElement(By locator) {
		Actions ac = new Actions(driver);
		ac.moveToElement(getElement(locator)).perform();
	}
	
	public static void doMoveToElement(WebElement webElement) {
		Actions ac = new Actions(driver);
		ac.moveToElement(webElement).perform();

	}
	public void doMoveToElementAndClick(By locator) {
		Actions ac = new Actions(driver);
		ac.moveToElement(getElement(locator)).click().perform();

	}
	
	public void doActionsSendKeys(By locator, String value){
		Actions ac = new Actions(driver);
		ac.sendKeys(getElement(locator), value).perform();
	}
	public static void doJsClick(WebElement webElement) {
    	JavascriptExecutor js =(JavascriptExecutor) driver;
    	js.executeScript("arguments[0].click()", webElement);

	}
	public void doJsClick(By locator) {
    	JavascriptExecutor js =(JavascriptExecutor) driver;
    	js.executeScript("arguments[0].click()", getElement(locator));

	}
	
	//********************drop down utils **************************
	
	public void doSelectValuesByVisibleText(By locator, String value){
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}
	
	public void doSelectValuesByIndex(By locator, int index){
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doSelectValuesByValue(By locator, String value){
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public List<String> getDropDownOptionsValues(By locator) {
		List<String> optionsList = new ArrayList<String>();

		Select select = new Select(getElement(locator));

		List<WebElement> dropList = select.getOptions();
		System.out.println(dropList.size());

		for (int i = 0; i < dropList.size(); i++) {
			String text = dropList.get(i).getText();
			optionsList.add(text);
		}

		return optionsList;
	}
	
	/**
	 * 
	 * @param locator
	 * @param value
	 * 
	 */
	
	public void BrowserSearch(String value) 
	{
	
		driver.findElement(By.id("browSearch")).sendKeys(value);
		
		driver.findElement(By.id("Search")).click();
		
	}
	
	
	
	public void selectValuesFromDropDown(By locator, String value){
		
		
		List<WebElement> daysList = driver.findElements(locator);
		
		for(int i=0; i<daysList.size(); i++){
			String text  = daysList.get(i).getText();
			System.out.println(text);
			if(text.equals(value)){
				daysList.get(i).click();
				break;
			}
		}
	}
	
	
	//***************************wait utils ******************************
	public String doGetPageTitleWithContains(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public String doGetPageTitleWithIsTitle(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	public String doGetPageCurrentUrl(int timeOut, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.urlContains(urlValue));
		return  driver.getCurrentUrl();
	}
	
	public WebElement waitForElementPresent(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement waitForElementToBeClickable(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public WebElement waitForElementToBeClickable(WebElement webElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(webElement));

	}
	public WebElement waitForElementToBeVisible(By locator, int timeOut){
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public List<WebElement> visibilityOfAllElements(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void clickWhenReady(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	public void clickWhenReady(WebElement webElement, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
		element.click();
	}
	
	public Alert waitForAlertPresent(int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	
	public boolean isElementDisplayed(By locator, int timeout) {
		WebElement element = null;
		boolean flag = false;
		for (int i = 0; i < timeout; i++) {

			try {
				element = driver.findElement(locator);
				flag = element.isDisplayed();
				break;
			} catch (Exception e) {
				System.out.println("waiting for element to be present on the page -->" + i + "secs");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
			}

		}
		return flag;

	}
	
	//******************* FluentWait Utils ***********************
	public WebElement waitForElementWithFluentWaitConcept(By locator, int timeOut){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement waitForElementWithFluentWaitmin(By locator, int timeOut){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMinutes(timeOut))
				.pollingEvery(Duration.ofSeconds(30))
				.ignoring(NoSuchElementException.class);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement waitForElementWithFluentWait(final By locator, int timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		
		return element;
	}
	
	public void BrowserSwitch() throws InterruptedException
	{
		
		Thread.sleep(3000);
		
		driver.switchTo().defaultContent();
			
		driver.findElement(By.id("txtSearch")).click();
		    
		Thread.sleep(3000);
		    
		driver.switchTo().frame("mainwindow");
		
		
	}
	public boolean retryingFindClick(By by) {
	    boolean result = false;
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	            driver.findElement(by).click();
	            result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        }
	        attempts++;
	    }
	    return result;
	}
	
	
	
}

