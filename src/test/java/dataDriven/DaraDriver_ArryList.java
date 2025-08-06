package dataDriven;

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import com.github.dockerjava.api.model.Driver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DaraDriver_ArryList {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ArrayList<String> uid = new ArrayList<String>();
		ArrayList<String> uidCriteria = new ArrayList<String>();
		ArrayList<String> pwd = new ArrayList<String>();
		ArrayList<String> pwdCriteria = new ArrayList<String>();

		uid.add("standard_user");
		uidCriteria.add("valid");
		pwd.add("secret_sauce");
		pwdCriteria.add("valid");

		uid.add("standard_user1");
		uidCriteria.add("Invalid");
		pwd.add("secret_sauce");
		pwdCriteria.add("valid");

		uid.add("standard_user");
		uidCriteria.add("valid");
		pwd.add("secret_sauce1");
		pwdCriteria.add("Invalid");

		ExtentReports er = new ExtentReports("DataDrivenByArrayList.html", true);

		ExtentTest et = er.startTest("Validating the Login functionality");

		for (int i = 0; i < uid.size(); i++) {

			WebDriverManager.chromedriver().setup();

			WebDriver driver = new ChromeDriver();

			driver.manage().window().maximize();

			driver.get("https://www.saucedemo.com/");

			driver.findElement(By.id("user-name")).sendKeys(uid.get(i));
			driver.findElement(By.id("password")).sendKeys(pwd.get(i));

			Thread.sleep(5000);
			driver.findElement(By.id("login-button")).click();

			try {
				if (uidCriteria.get(i).equalsIgnoreCase("valid") && pwdCriteria.get(i).equalsIgnoreCase("valid")) {
					WebElement products = driver.findElement(By.xpath("//*[text()='Products']"));

					if (products.isDisplayed()) {
						et.log(LogStatus.PASS, "Valid username and valid password Login testcase is passed");
					} else {
						et.log(LogStatus.FAIL, "Valid username and valid password Login testcase is failed");
					}
				} else if (uidCriteria.get(i).equalsIgnoreCase("Invalid")
						&& pwdCriteria.get(i).equalsIgnoreCase("valid")) {
					WebElement error = driver.findElement(By.xpath(
							"//*[text()='Epic sadface: Username and password do not match any user in this service123']"));

					if (error.isDisplayed()) {
						et.log(LogStatus.PASS, "InValid username and valid password Login testcase is passed");
					} else {
						et.log(LogStatus.FAIL, "InValid username and valid password Login testcase is failed"
								+ et.addScreenCapture(DaraDriver_ArryList.screentshot(driver)));
					}
				} else if (uidCriteria.get(i).equalsIgnoreCase("valid")
						&& pwdCriteria.get(i).equalsIgnoreCase("Invalid")) {
					WebElement error = driver.findElement(By.xpath(
							"//*[text()='Epic sadface: Username and password do not match any user in this service']"));

					if (error.isDisplayed()) {
						et.log(LogStatus.PASS, "Valid username and Invalid password Login testcase is passed");
					} else {
						et.log(LogStatus.FAIL, "Valid username and Invalid password Login testcase is failed");
					}
				} else {
					et.log(LogStatus.ERROR,
							"Test case got failed" + et.addScreenCapture(DaraDriver_ArryList.screentshot(driver)));
				}
			}

			catch (Exception e) {

				et.log(LogStatus.FAIL, "InValid username and valid password Login testcase is failed"
						+ et.addScreenCapture(DaraDriver_ArryList.screentshot(driver)));
			}
			driver.close();
			er.endTest(et);
			er.flush();

		}

	}

	public static String screentshot(WebDriver driver) throws Exception {
		String ssname = "FailedTestcaseName.jpg";

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(ssname);

		FileHandler.copy(source, dest);
		return ssname;

	}

}
