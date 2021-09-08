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

public class UpdateContact {

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
		
		//2) Login with the credentials"
		driver.findElementById("username").sendKeys("matschie@testleaf.com");
		driver.findElementById("password").sendKeys("SelBootcamp$123");
		
		//3) Click Login
		driver.findElementById("Login").click();
		
		//4)Click on menu button from the Left corner
		driver.findElementByClassName("slds-icon-waffle").click();
		
		//5) Click 'view All'
		driver.findElementByXPath("//button[text()='View All']").click();
		
		//6) Click on contacts under 'All Items'"
		
		action.moveToElement(driver.findElementByXPath("//p[text()='Contacts']")).perform();
		driver.findElementByXPath("//p[text()='Contacts']").click();
		
		//6) Click on contacts
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
		
		//7) Get the size of conatcts available and print the list
		List<WebElement> data = driver.findElementsByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody//tr//th");
		System.out.println("Total contacts :" +data.size());
		WebElement q;

		for(int i=0;i<data.size();i++)
		{ 
			q= data.get(i);

			System.out.println(q.getText()); 
		}
		
		//8) search for the contact using unique name 
		driver.findElementByXPath("//input[@name='Contact-search-input']").sendKeys("Sahana",Keys.ENTER);
		String store =driver.findElementByXPath("//a[@title='Sahana baasha']").getText();
		System.out.println("Get the Contact Text :" +store);
		Thread.sleep(3000);
		
		//9) Click on the dropdown icon available in the unique contact and select edit
		List<WebElement> clickDropdwn= driver.findElementsByXPath("//a[contains(@class,'rowActionsPlaceHolder ')]/span/span");
		WebElement clickdrop = driver.findElementByXPath("//a[contains(@class,'rowActionsPlaceHolder ')]/span/span");
		executor.executeScript("arguments[0].click()", clickdrop);
		driver.findElementByXPath("//a[@title='Edit']").click();
		
		//10)Update Email with your persinal mail id
		driver.findElementByXPath("//input[@name='Email']").sendKeys("aameenait2008@gmail.com");
		
		//11)Update Lead Source as Partner Referral from bottom 
		List<WebElement> leadSource = driver.findElementsByXPath("(//input[@class='slds-input slds-combobox__input'])[5]");
		WebElement leadSourceClick = driver.findElementByXPath("(//input[@class='slds-input slds-combobox__input'])[5]");
		executor.executeScript("arguments[0].click()", leadSourceClick);
		driver.findElementByXPath("//span[text()='Partner Referral']").click();
		
		//12) Update MailingAddress with personal address
		driver.findElementByXPath("(//textarea[@name='street'])[1]").sendKeys("Sivan koil atreet,Rasipuram.");
		Thread.sleep(2000);
		
		//13) Update Level as Tertiary
		List<WebElement> level = driver.findElementsByXPath("(//input[@class='slds-input slds-combobox__input'])[6]");
		WebElement levelClick = driver.findElementByXPath("(//input[@class='slds-input slds-combobox__input'])[6]");
		executor.executeScript("arguments[0].click()", levelClick);
		driver.findElementByXPath("//span[text()='Tertiary']").click();
		Thread.sleep(2000);
		
		//14) Update title as Automation Testing
		driver.findElementByXPath("//input[@name='Title']").sendKeys("Automation Testing");
		Thread.sleep(3000);
				
		//15) Click Save and Verify and print Email
		driver.findElementByXPath("//button[@name='SaveEdit']").click();
		
		driver.findElementByXPath("//span[@class='toastMessage slds-text-heading--small forceActionsText']").isDisplayed();
		String updated = driver.findElementByXPath("//span[@class='toastMessage slds-text-heading--small forceActionsText']").getText();
		
		if(updated.indexOf("Contact \"Mrs.Sahana baasha\"was saved.")<0)
		{
			System.out.println("Contact Updated Sucessfully");
		}
		else
		{
			System.out.println();
		}
		WebElement getEmail = driver.findElementByXPath("//a[text()='aameenait2008@gmail.com']");
		String email=driver.findElementByXPath("//a[text()='aameenait2008@gmail.com']").getText();
		
		System.out.println("Updated Email id is :" +email);
		
		
		
		driver.quit();

	}

}
