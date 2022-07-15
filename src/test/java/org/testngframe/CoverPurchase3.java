package org.testngframe;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CoverPurchase3 {
	static long Start;
	@BeforeClass
	public static void browserLaunch() {
		System.out.println("Browser Launch");
	}
	@AfterClass
	public static void browserQuit() {
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
		System.out.println("Login");
	}
	@Test(priority=2)
	public void search() {
		System.out.println("search");
	}
	@Test(priority=3)
	public void cover() {
		System.out.println("cover");
	}
	@Test(priority=4)
	public void window() {
		System.out.println("window");
	}
	@Test(priority=5)
	public void screenShot() {
		System.out.println("screenShot");
	}
}
