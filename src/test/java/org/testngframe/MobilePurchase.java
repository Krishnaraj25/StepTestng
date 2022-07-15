package org.testngframe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobilePurchase {
	static long Start;
	static WebDriver driver;
	static String Text;
	static String Text1;
	@DataProvider(name="mobile")
	public Object[][] mobileName(){
		return  new Object[][] {{"Redmi Mobiles"}};
	}
	@Parameters("URL")
	@BeforeClass
	public static void browserLaunch(String url) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		System.out.println("Browser Launch");
	}
	@AfterClass
	public static void browserQuit() {
		driver.quit();
		System.out.println("Browser Quit");
	}
	@BeforeMethod
	public void startTime() {
		Start=System.currentTimeMillis();
	}
	@AfterMethod
	public void endTime() {
		long End =System.currentTimeMillis();
		System.out.println("Time Taken:"+(End-Start));
	}
	@Test(priority=1)
	public void login() {
		WebElement X=driver.findElement(By.xpath("//button[text()='✕']"));
		X.click();
	}
	@Test(priority=2,dataProvider="mobile")
	public void search(String name) {
		WebElement Search=driver.findElement(By.xpath("//input[@type='text']"));
		Search.sendKeys(name,Keys.ENTER);
	}
	@Test(priority=3)
	public void mobile() throws Throwable {
		Thread.sleep(1000);
		WebElement Mob1=driver.findElement(By.xpath("(//div[@class='_4rR01T'])[1]"));
		Text=Mob1.getText();
		System.out.println(Text);
		Thread.sleep(1000);
		Mob1.click();
		Thread.sleep(1000);
		WebElement Mob2=driver.findElement(By.xpath("(//div[@class='_4rR01T'])[2]"));
		Mob2.click();
		Thread.sleep(1000);
		WebElement Mob3=driver.findElement(By.xpath("(//div[@class='_4rR01T'])[3]"));
		Mob3.click();
	}
	@Test(priority=4)
	public void window() throws Throwable {
		Set<String> WH=driver.getWindowHandles();
		List<String> WH1=new ArrayList<String>(WH);
		for(String Y:WH1) {
		driver.switchTo().window(Y);
		driver.switchTo().window(WH1.get(3));
		}
		WebElement Mob=driver.findElement(By.xpath("//span[@class='B_NuCI']"));
		Text1=Mob.getText();
		System.out.println(Text1);
		File F=new File("C:\\Users\\ASUS\\eclipse-workspace\\StepTestng\\src\\test\\resources\\Mobile1.xlsx");
		Workbook WB=new XSSFWorkbook();
		Sheet S=WB.createSheet("Mobile");
		for(int i=0;i<1;i++) {
		Row R=S.createRow(i);
		for(int j=0;j<2;j++) {
		Cell C=R.createCell(j);
		if(j==0) {
			C.setCellValue(Text);}
		if(j==1) {
			C.setCellValue(Text1);}
		}}
		FileOutputStream F2=new FileOutputStream(F);
		WB.write(F2);
	}
	@Test(priority=5)
	public void screenShot() throws Throwable {
		TakesScreenshot TK=(TakesScreenshot)driver;
		File F1=TK.getScreenshotAs(OutputType.FILE);
		File L= new File("C:\\Users\\ASUS\\eclipse-workspace\\StepTestng\\src\\test\\resources\\SS");
		FileUtils.copyFile(F1,L);
		SoftAssert A=new SoftAssert();
		A.assertEquals(Text, Text1);
	}
}
