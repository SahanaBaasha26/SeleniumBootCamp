
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

public class DeleteContact extends ProjectSpecification
{
	@Test
	public void runDeleteContact() throws InterruptedException {

		Actions action = new Actions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		driver.findElementByClassName("slds-icon-waffle").click();
		driver.findElementByXPath("//button[text()='View All']").click();
		action.moveToElement(driver.findElementByXPath("//p[text()='Contacts']")).perform();
		driver.findElementByXPath("//p[text()='Contacts']").click();
		List<WebElement> contacts = driver.findElementsByXPath("//span[text()='Contacts']");

		WebElement findContacts = driver.findElementByXPath("//span[text()='Contacts'][1]");
		executor.executeScript("arguments[0].click()", findContacts);
		List<WebElement> dropdownObjs = driver.findElements(By.xpath(
				"//a[contains(@title,'Contacts')]//following-sibling::one-app-nav-bar-item-dropdown//lightning-icon"));

		WebElement dropdownBtn = driver.findElement(By.xpath(
				"//a[contains(@title,'Contacts')]//following-sibling::one-app-nav-bar-item-dropdown//lightning-icon"));

		executor.executeScript("arguments[0].click()", dropdownBtn);

		WebElement allContact = driver.findElement(By.xpath("//span[text()='All Contacts']"));
		executor.executeScript("arguments[0].click()", allContact);
		Thread.sleep(3000);
		// driver.findElementByXPath("(//span[@class='slds-checkbox--faux'])[1]").click();

		List<WebElement> data = driver.findElementsByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody//tr//th");
		System.out.println("Total contacts :" +data.size());
		WebElement q;

		for(int i=0;i<data.size();i++)
		{ 
			q= data.get(i);

			System.out.println(q.getText()); 
		}

		driver.findElementByXPath("//input[@name='Contact-search-input']").sendKeys("Sahana",Keys.ENTER);
		String store =driver.findElementByXPath("//a[@title='Sahana baasha']").getText();

		System.out.println("Get the Contact Text :" +store);
		Thread.sleep(3000);
		List<WebElement> deleteC = driver.findElementsByXPath("//a[contains(@class,'rowActionsPlaceHolder ')]/span/span");

		WebElement deleteContac = driver.findElementByXPath("//a[contains(@class,'rowActionsPlaceHolder ')]/span/span");
		executor.executeScript("arguments[0].click()", deleteContac);

		driver.findElementByXPath("//a[@title='Delete']").click();

		driver.findElementByXPath("//span[text()='Delete']").click();
		driver.findElementByXPath("//span[@class='toastMessage slds-text-heading--small forceActionsText']").isDisplayed();

		String contactDeleted=driver.findElementByXPath("//span[@class='toastMessage slds-text-heading--small forceActionsText']").getText();

		if(contactDeleted.indexOf("Contact\"Sahana baasha\"was deleted.")<0)
		{

			System.out.println("Contact Deleted Successfully");  
		}
		else
		{
			System.out.println("Contact not Deleted");
		}

		

		/* for(WebElement j:data) {
		 * System.out.println(j.getText());
		 * }
		 */


	}
}



