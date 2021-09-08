package testNg;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import baseClassTestNg.ProjectSpecification;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EditIndividuals extends ProjectSpecification {
	
	
	@Test
	public  void runEditIndividuals() throws InterruptedException  {

		driver.findElementByClassName("slds-icon-waffle_container").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//button[text()='View All']").click();
		Thread.sleep(2000);
		WebElement individualLink = driver.findElementByXPath("//p[text()='Individuals']");

		Actions act = new Actions(driver);
		act.sendKeys(Keys.PAGE_DOWN).build().perform();
		act.moveToElement(individualLink).perform();
		individualLink.click();
		Thread.sleep(10000);
		System.out.println("link clicked");

		WebElement searchTextBox = driver.findElementByXPath("//label[text()='Search this list...']/following::input");
		act.moveToElement(searchTextBox).click();
		searchTextBox.sendKeys("James", Keys.ENTER);

		Thread.sleep(5000);

		WebElement table = driver.findElementByXPath("//table[@data-aura-class='uiVirtualDataTable']");
		List<WebElement> allRows = table.findElements(By.tagName("tr"));

		for (WebElement temRow : allRows)
		{
			WebElement rowTitle = temRow.findElement(By.tagName("th"));
			String Title = rowTitle.findElement(By.xpath("//span[@data-aura-class='forceInlineEditCell']")).getText();
			System.out.println(Title);

			if (Title.contains("James")){
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