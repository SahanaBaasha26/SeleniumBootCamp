package testNg;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseClassTestNg.ProjectSpecification;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateIndividuals extends ProjectSpecification {
	
	@Test
	public void runCreateIndividuals() throws InterruptedException {

		Actions   action = new Actions(driver);

		driver.findElementByClassName("slds-icon-waffle").click();
		driver.findElementByXPath("//button[text()='View All']").click();
		WebElement Individuals = driver.findElementByXPath("//p[text()='Individuals']");
		action.moveToElement(Individuals).perform();
		driver.findElementByXPath("//p[text()='Individuals']").click();
		Thread.sleep(3000);
		List<WebElement> newIndividualObj = driver.findElements(By.xpath("//a[contains(@title,'Individuals')]/following-sibling::one-app-nav-bar-item-dropdown//lightning-icon"));
		System.out.println(newIndividualObj.size());
		if(newIndividualObj.size()>0){
			newIndividualObj.get(0).click();
			Thread.sleep(3000);
			WebElement newIndividual = driver.findElementByXPath("//span[text()='New Individual']");
			//newIndividual.click();
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", newIndividual);

		}else {
			driver.findElementByXPath("//div[text()='New']").click();
		}

		driver.findElementByClassName("lastName").sendKeys("Kumar");
		driver.findElementByXPath("//button[@title='Save']").click();
		System.out.println(driver.findElementByXPath("//div[contains(@class,'active')]//span[@class='uiOutputText' and text()='Kumar']").isDisplayed());
		driver.findElementByXPath("//div[contains(@class,'active')]//span[@class='uiOutputText' and text()='Kumar']").isDisplayed();


	}

}