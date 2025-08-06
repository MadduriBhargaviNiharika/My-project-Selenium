package dataDriven;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDriven_CSV {

	public static void main(String[] args) throws IOException, Exception {

		File f1 = new File("Data.txt");

		FileReader fr = new FileReader(f1);

		BufferedReader br = new BufferedReader(fr);

		File f2 = new File("output.txt");

		FileWriter fw = new FileWriter(f2);
		BufferedWriter bw = new BufferedWriter(fw);

		String line = "";

		while ((line = br.readLine()) != null) {

			// line = firstName1,LastName1,Address1,Email1,Phone1

			String[] data = line.split(",");

			WebDriverManager.chromedriver().setup();

			WebDriver driver = new ChromeDriver();

			driver.manage().window().maximize();

			driver.get("https://demo.automationtesting.in/Register.html");

			driver.findElement(By.xpath("//*[@placeholder='First Name']")).sendKeys(data[0]);
			driver.findElement(By.xpath("//*[@placeholder='Last Name']")).sendKeys(data[1]);
			driver.findElement(By.xpath("//*[@ng-model='Adress']")).sendKeys(data[2]);
			driver.findElement(By.xpath("//*[@ng-model='EmailAdress']")).sendKeys(data[3]);
			driver.findElement(By.xpath("//*[@ng-model='Phone']")).sendKeys(data[4]);

			Thread.sleep(5000);
			driver.close();

			bw.write(line + ": Testcase passed");
			bw.newLine();

		}

		br.close();
		fr.close();
		bw.close();
		fw.close();
	}

}
