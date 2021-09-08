package baseClassTestNg;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProjectSpecification {
	public ChromeDriver driver;
	public ChromeOptions options;

	
	@Parameters({"url","uName","pwd"})
	@BeforeMethod
	public void login(String url,String uName,String pwd) {

		WebDriverManager.chromedriver().setup();
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElementById("username").sendKeys(uName);
		driver.findElementById("password").sendKeys(pwd) ;
		driver.findElementById("Login").click();
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}


}
