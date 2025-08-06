package pages;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.openqa.selenium.WebDriver;

public class Loginpage {

	
	public WebDriver driver;
	
	By username = By.xpath("//*[@id='user-name']");
	By password = By.xpath("//*[@id='password']");
	By login = By.xpath("//*[@id='login-button']");
	
	//Constructor
	public Loginpage(WebDriver driver)
	{
		this.driver= driver;
	}
	
	//page methods
	
	public void signOn(String Username , String Password)
	{
		driver.findElement(username).sendKeys(Username);
		driver.findElement(password).sendKeys(Password);
		driver.findElement(login).click();
		Reporter.log("Successfully logged into application");
		
	}

}
