package contact;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteContact {
	
	 
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		
		//1) Launch the app
		
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		//2) Click Login
		//3) Login with the credentials"
		
		driver.findElementById("username").sendKeys("matschie@testleaf.com");
		driver.findElementById("password").sendKeys("SelBootcamp$123");
		driver.findElementById("Login").click();
		
		//4)Click on menu button from the Left corner
		
		driver.findElementByClassName("slds-icon-waffle").click();
		
		//5) Click 'view All'
	
		driver.findElementByXPath("//button[text()='View All']").click();
		
		//6) Click on contacts under 'All Items'"
		
		action.moveToElement(driver.findElementByXPath("//p[text()='Contacts']")).perform();
		driver.findElementByXPath("//p[text()='Contacts']").click();
		
		//7) Click on contacts
		
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
		
		//8) Get the size of conatcts available and print the list
		
		List<WebElement> data = driver.findElementsByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody//tr//th");
		System.out.println("Total contacts :" +data.size());
		WebElement q;

		for(int i=0;i<data.size();i++)
		{ 
			q= data.get(i);

			System.out.println(q.getText()); 
		}
		
		//9) search for the contact using unique name 
		
		driver.findElementByXPath("//input[@name='Contact-search-input']").sendKeys("Sahana",Keys.ENTER);
		
		//10) Get the text of Contact name and store it 
		
		String store =driver.findElementByXPath("//a[@title='Sahana baasha']").getText();
		System.out.println("Get the Contact Text :" +store);
		Thread.sleep(3000);
		
		//11) Click on the dropdown icon available in the unique contact and select Delete
		
		List<WebElement> deleteC = driver.findElementsByXPath("//a[contains(@class,'rowActionsPlaceHolder ')]/span/span");
		WebElement deleteContac = driver.findElementByXPath("//a[contains(@class,'rowActionsPlaceHolder ')]/span/span");
		executor.executeScript("arguments[0].click()", deleteContac);

		driver.findElementByXPath("//a[@title='Delete']").click();

		driver.findElementByXPath("//span[text()='Delete']").click();
		driver.findElementByXPath("//span[@class='toastMessage slds-text-heading--small forceActionsText']").isDisplayed();

		String contactDeleted=driver.findElementByXPath("//span[@class='toastMessage slds-text-heading--small forceActionsText']").getText();
		
		//12) Verify whether the Contact is Deleted
		
		if(contactDeleted.indexOf("Contact\"Sahana baasha\"was deleted.")<0)
		{

			System.out.println("Contact Deleted Successfully");  
		}
		else
		{
			System.out.println("Contact not Deleted");
		}

		driver.quit(); 

		/* for(WebElement j:data) {
		 * System.out.println(j.getText());
		 * }
		 */


	}
}
