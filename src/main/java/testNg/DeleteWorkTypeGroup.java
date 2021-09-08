package testNg;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import baseClassTestNg.ProjectSpecification;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteWorkTypeGroup extends ProjectSpecification {
	
	@Test
	public  void runDeleteWorkTypeGroup() throws InterruptedException {
		
		Actions action = new Actions(driver);
		JavascriptExecutor executor =(JavascriptExecutor)driver;
		
		driver.findElementByClassName("slds-icon-waffle").click();
		driver.findElementByXPath("//button[text()='View All']").click();
		
		action.moveToElement(driver.findElementByXPath("//p[text()='Work Type Groups']")).perform();
		
		driver.findElementByXPath("//p[text()='Work Type Groups']").click();
		
		WebElement workTypeGroup = driver.findElementByXPath("(//a/span[contains(text(),'Work Type Groups')])[1]");
		executor.executeScript("arguments[0].click()", workTypeGroup);
		
		
		driver.findElementByXPath("//input[@type='search' and @name='WorkTypeGroup-search-input']").click();
		driver.findElementByXPath("//input[@type='search' and @name='WorkTypeGroup-search-input']").sendKeys("Salesforce Automation by Sahana", Keys.ENTER);
		
		Thread.sleep(2000);
		List<WebElement> workTypeTableRows = driver.findElementsByXPath("(//table[contains(@class,'forceRecordLayout')]/tbody/tr)");
		
		System.out.println(workTypeTableRows.size());
		int tableSize = workTypeTableRows.size();
		if(tableSize>0) {
			for (WebElement row: workTypeTableRows) {
				System.out.println(row);
				//executor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", row);
				WebElement rows = row;
				WebElement rowDetail = row.findElement(By.xpath(".//th//a"));
				
				executor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", rowDetail);
				String workTypeName = rowDetail.getText();
				if(workTypeName.equalsIgnoreCase("Salesforce Automation by Sahana")) {
					System.out.println(row);
					
					WebElement dropdownBtn = rows.findElement(By.xpath(".//td//a"));
					executor.executeScript("arguments[0].click()", dropdownBtn);
					System.out.println("Pass");
					WebElement deleteBtn = driver.findElementByXPath("//div[text()='Delete']");
					executor.executeScript("arguments[0].click()", deleteBtn);
					driver.findElementByXPath("//span[text()='Delete']").click();
					
					Thread.sleep(5000);
					String successObj = driver.findElementByXPath("//div[contains(@class,'slds-theme--success')]//span[contains(@class,'toastMessage')]").getText();
					System.out.println(successObj);
					if(successObj.indexOf("\"Salesforce Automation by Sahana\" was deleted")>0) {
						System.out.println("Pass");

					}else {
						System.out.println("Fail");
					}
					break;
				}
			}
		}
		
		List<WebElement> workTypeTableRowsAfterDeletion = driver.findElementsByXPath("(//table[contains(@class,'forceRecordLayout')]/tbody/tr)");
		if(workTypeTableRowsAfterDeletion.size()<tableSize) {
			System.out.println("Worktype is deleted successfully");
		}
		
	}
}