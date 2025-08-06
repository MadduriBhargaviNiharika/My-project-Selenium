package dataDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDriven_Excel {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		FileInputStream fs = new FileInputStream("Data.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fs);

		XSSFSheet sheet = workbook.getSheetAt(0);

		int Rows = sheet.getLastRowNum();

		System.out.println(Rows);

		int Columns = sheet.getRow(0).getLastCellNum();

		System.out.println(Columns);

		sheet.getRow(0).createCell(5).setCellValue("Result");

		for (int i = 1; i <= Rows; i++) {

			String FirstName = sheet.getRow(i).getCell(0).toString();
			String LastName = sheet.getRow(i).getCell(1).toString();
			String Address = sheet.getRow(i).getCell(2).toString();
			String Email = sheet.getRow(i).getCell(3).toString();
			String Phone = sheet.getRow(i).getCell(4).toString();

			WebDriverManager.chromedriver().setup();

			WebDriver driver = new ChromeDriver();

			driver.manage().window().maximize();

			driver.get("https://demo.automationtesting.in/Register.html");

			driver.findElement(By.xpath("//*[@placeholder='First Name']")).sendKeys(FirstName);
			driver.findElement(By.xpath("//*[@placeholder='Last Name']")).sendKeys(LastName);
			driver.findElement(By.xpath("//*[@ng-model='Adress']")).sendKeys(Address);
			driver.findElement(By.xpath("//*[@ng-model='EmailAdress']")).sendKeys(Email);
			driver.findElement(By.xpath("//*[@ng-model='Phone']")).sendKeys(Phone);
			
			sheet.getRow(i).createCell(5).setCellValue("PASS");

			Thread.sleep(2000);
			driver.close();

		}
		
		FileOutputStream fo = new FileOutputStream("Data.xlsx");
		workbook.write(fo);
		fo.close();
		fs.close();

	}

}
