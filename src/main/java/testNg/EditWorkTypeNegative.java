package testNg;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import baseClassTestNg.ProjectSpecification;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EditWorkTypeNegative extends ProjectSpecification {

	@Test
	public  void runEditWorkTypeGroup() throws InterruptedException {

		Actions action = new Actions(driver);
		JavascriptExecutor executor =(JavascriptExecutor)driver;

		driver.findElementByClassName("slds-icon-waffle").click();
		driver.findElementByXPath("//button[text()='View All']").click();

		action.moveToElement(driver.findElementByXPath("//p[text()='Work Types']")).perform();
		driver.findElementByXPath("//p[text()='Work Types']").click();
		Thread.sleep(2000);

		List<WebElement> findWorkType= driver.findElementsByXPath("(//span[text()='Work Types'])[1]");
		WebElement findWork= driver.findElementByXPath("(//span[text()='Work Types'])[1]");
		//executor.executeScript("arguements[0].click", findWork);


		WebElement dropdownBtn = driver.findElementByXPath("//a[contains(@title,'Work Types')]//following-sibling::one-app-nav-bar-item-dropdown//lightning-icon");
		executor.executeScript("arguments[0].click()", dropdownBtn);
		Thread.sleep(3000);


		WebElement allWorkTypes = driver.findElementByXPath("//span[text()='All Work Types']");
		executor.executeScript("arguments[0].click()", allWorkTypes);
		Thread.sleep(3000);
		// List<WebElement> firstDropdown = driver.findElementsByXPath("(//a[contains(@class,'rowActionsPlaceHolder')] )[1]/span/span[1]");
		WebElement clickDropDown = driver.findElementByXPath("(//a[contains(@class,'rowActionsPlaceHolder')] )[1]/span/span[1]");
		executor.executeScript("arguments[0].click()", clickDropDown);


		WebElement edit = driver.findElementByXPath("//a[@title='Edit']");
		executor.executeScript("arguments[0].click()", edit);

		driver.findElementByXPath("(//input[@class='input uiInputSmartNumber'])[4]").clear();
		driver.findElementByXPath("(//input[@class='input uiInputSmartNumber'])[4]").sendKeys("9");
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='Timeframe End']/parent::label/following-sibling::input").clear();
		driver.findElementByXPath("//span[text()='Timeframe End']/parent::label/following-sibling::input").sendKeys("6");
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		driver.findElementByXPath("//span[text()='Review the errors on this page.']").getText();
		driver.findElementByXPath("//li[text()='Enter a Timeframe End number that is greater than the Timeframe Start number.: Timeframe End']").isDisplayed();

		String errorMsg= driver.findElementByXPath("//li[text()='Enter a Timeframe End number that is greater than the Timeframe Start number.: Timeframe End']").getText();
		if(errorMsg.matches(errorMsg))
		{
			WebElement changeTimeFrame = driver.findElementByXPath("//span[text()='Timeframe End']/parent::label/following-sibling::input");
			changeTimeFrame .clear();
			changeTimeFrame.sendKeys("18");
			System.out.println("ReWrited the End TimeFreme");
		}
		else
		{
			driver.findElementByXPath("(//span[text()='Save'])[2]").click();

		}


		Thread.sleep(3000);
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		Thread.sleep(3000);


		String edited = driver.findElementByXPath("//span[@class='toastMessage slds-text-heading--small forceActionsText']").getText();
		System.out.println(edited);

		
	}
}
