package individuals;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditIndividuals {
	public static void main(String[] args) throws InterruptedException  {
		
		
		
		
		
		
		
		//8.Enter the first name as 'Ganesh'.
		//9. Click on Save and Verify the first name as 'Ganesh'

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
		// 1. Login to https://login.salesforce.com
		driver.get("https://login.salesforce.com");
		Thread.sleep(3000);
		driver.findElementById("username").sendKeys("matschie@testleaf.com");
		driver.findElementById("password").sendKeys("SelBootcamp$123");
		driver.findElementById("Login").click();
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		//2. Click on the toggle menu button from the left corner
		driver.findElementByClassName("slds-icon-waffle_container").click();
		Thread.sleep(2000);
		
		//3. Click View All and click Individuals from App Launcher
		driver.findElementByXPath("//button[text()='View All']").click();
		Thread.sleep(2000);
		WebElement individualLink = driver.findElementByXPath("//p[text()='Individuals']");

		Actions act = new Actions(driver);
		act.sendKeys(Keys.PAGE_DOWN).build().perform();
		act.moveToElement(individualLink).perform();
		individualLink.click();
		Thread.sleep(10000);
		System.out.println("link clicked");
		
		//4. Click on the Individuals tab 
		WebElement searchTextBox = driver.findElementByXPath("//label[text()='Search this list...']/following::input");
		act.moveToElement(searchTextBox).click();
		
		//5. Search the Individuals 'Kumar'
		searchTextBox.sendKeys("Kumar", Keys.ENTER);

		Thread.sleep(5000);
		//6. Click on the Dropdown icon and Select Edit
		WebElement table = driver.findElementByXPath("//table[@data-aura-class='uiVirtualDataTable']");
		List<WebElement> allRows = table.findElements(By.tagName("tr"));

		for (WebElement temRow : allRows)
		{
			WebElement rowTitle = temRow.findElement(By.tagName("th"));
			String Title = rowTitle.findElement(By.xpath("//span[@data-aura-class='forceInlineEditCell']")).getText();
			System.out.println(Title);

			if (Title.contains("Kumar")){
				temRow.findElement(By.xpath("//td[@class='slds-cell-edit cellContainer']")).click();
				Thread.sleep(2000);
				driver.findElementByXPath("//a[@title='Edit']").click();
				Thread.sleep(2000);
				driver.findElementByXPath("//input[contains(@class,'lastName compoundBLRadius')]").clear();
				Thread.sleep(2000);
				driver.findElementByXPath("//input[contains(@class,'lastName compoundBLRadius')]").sendKeys("baasha");
				Thread.sleep(2000);
				driver.findElementByXPath("(//span[text()='Save'])[2]").click();
				Thread.sleep(2000);
				driver.navigate().refresh();
				Thread.sleep(2000);

				String updatedName = driver.findElement(By.xpath("//span[@data-aura-class='forceInlineEditCell']")).getText();

				if(updatedName.contains("James"))
				{
					System.out.println("Name updated");
				}
				else
				{
					System.out.println("Failed");
				} 

				break;
			}
			else
			{
				System.out.println("Inidividual in the name baasha is not exist");
			}
		}
	}
}