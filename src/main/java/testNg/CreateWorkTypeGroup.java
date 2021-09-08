package testNg;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import baseClassTestNg.ProjectSpecification;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateWorkTypeGroup extends ProjectSpecification {

	@Test
	public void runCreateWorkTypeGroup() throws InterruptedException {
		
		
		Actions action = new Actions(driver);
		JavascriptExecutor executor =(JavascriptExecutor)driver;
		driver.findElementByClassName("slds-icon-waffle").click();
		driver.findElementByXPath("//button[text()='View All']").click();
		
		action.moveToElement(driver.findElementByXPath("//p[text()='Work Type Groups']")).perform();
		
		driver.findElementByXPath("//p[text()='Work Type Groups']").click();
		
		List<WebElement> worktypegroupobjs = driver.findElementsByXPath("//a/span[contains(text(),'Work Type Groups')]");
		
		WebElement workTypeGroup = driver.findElementByXPath("(//a/span[contains(text(),'Work Type Groups')])[1]");
		executor.executeScript("arguments[0].click()", workTypeGroup);
		
		
		//driver.findElementByXPath("//a/span[contains(text(),'Work Type Groups')]").click();
		/*
		 * List<WebElement> dropdownObjs = driver.
		 * findElementsByXPath("//a[contains(@title,'Work Type Groups')]//following-sibling::one-app-nav-bar-item-dropdown//lightning-icon"
		 * );
		 * 
		 * WebElement dropdownBtn = driver.
		 * findElementByXPath("//a[contains(@title,'Work Type Groups')]//following-sibling::one-app-nav-bar-item-dropdown//lightning-icon"
		 * );
		 * 
		 * executor.executeScript("arguments[0].click()", dropdownBtn);
		 * Thread.sleep(3000);
		 * 
		 * WebElement newWorkTypeGroup =
		 * driver.findElement(By.xpath("//span[text()='New Work Type Group']"));
		 * executor.executeScript("arguments[0].click()",newWorkTypeGroup);
		 */
		
		WebElement newCreate= driver.findElementByXPath("//div[@title='New']");
		executor.executeScript("arguments[0].click()", newCreate);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//label[descendant::span[text()='Work Type Group Name']]/following-sibling::input")).sendKeys("Salesforce Automation by Sahana");
		driver.findElementByXPath("//button[@title='Save']").click();
		
		driver.findElementByXPath("//div[contains(@class,'slds-page-header__title')]//span[@class='uiOutputText' and text()='Salesforce Automation by Sahana']").isDisplayed();
		
		String successObj = driver.findElementByXPath("//div[contains(@class,'slds-theme--success')]//span[contains(@class,'toastMessage')]").getText();
		
		if(successObj.indexOf("\"Salesforce Automation by Sahana\" was created")>0) {
			System.out.println("Pass");
			
		}else {
			System.out.println("Fail");
		}
		
	}

}