/**
 * 
 */
package com.hw.views;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author ajain
 *
 */
public class FileBrowserViewTest {
	WebDriver driver = new FirefoxDriver();
	FileBrowserViewPage page = null;
	
	@BeforeTest
	public void init() throws InterruptedException{
		
		driver.get("http://172.22.78.195:8080/");
		
		WebElement login = driver.findElement(By.cssSelector("input.login-user-name"));
		WebElement pwd = driver.findElement(By.cssSelector("input.login-user-password"));
		WebElement btn = driver.findElement(By.cssSelector("button.login-btn"));
		
		login.sendKeys("admin");
		pwd.sendKeys("admin");
		btn.click();
		
		Thread.sleep(10000);
		
		driver.get("http://172.22.78.195:8080/#/main/views/FILES/1.0.0/HDFSView");

		
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i.fa-trash")));
		
		page = PageFactory.initElements(driver,FileBrowserViewPage.class);
	}
	
	@Test
	public void demoTest() throws InterruptedException{
	

	page.viewThrashDir();
	
	System.out.println(page.getUserDir());
	
	page.viewHomeDir();
	page.selectAll();
	
//	page.search("hive");
	
	List<FileDetails> fileDetails = page.getFileDetails();
	
	for(int i=0;i<fileDetails.size();i++)
		System.out.println(fileDetails.get(i));
	
	page.search("hive");
	
	fileDetails = page.getFileDetails();
	
	for(int i=0;i<fileDetails.size();i++)
		System.out.println(fileDetails.get(i));

	page.deSelectAll();
	
	page.selectFile(1);
	
	Thread.sleep(10000);
	
	driver.close();
	}

	/**
	 * Test#01 : Test for the layout of default screen 
	 * 
	 * Steps
	 * =====
	 * Step#1 : Open the "File Browser View".
	 * 
	 * Validations
	 * ============
	 * Validation#1 : Home button must be displayed in layout.
	 * Validation#2 : Trash button must be displayed in layout.
	 * Validation#3 : Folder path must be displayed in layout.
	 * Validation#4 : Message bar must be displayed in layout.
	 * Validation#5 : SelectAll button must be displayed in layout.
	 * Validation#6 : NewFolder button must be displayed in layout.
	 * Validation#7 : Upload button must be displayed in layout.
	 * Validation#8 : Message icon must be displayed in layout.
	 * Validation#9 : Search box must be displayed in layout.
	 * Validation#10 : Table must show folders & files in root folder.
	 */
	@Test
	public void defaultBehavior(){
		page.isViewReady();
	}
	
	@Test
	public void newFolder(){
		String folderName = "Fld_"+ (new Date()).getTime();
		page.newFolder(folderName);
		Assert.assertTrue(page.isFolderAvailable(folderName),"New folder "+folderName+"is not created");
	}
	
	@Test
	public void selectAll() throws InterruptedException{
		page.selectAll();
		Thread.sleep(2000);
		page.deSelectAll();
		Thread.sleep(10000);
	}
	
	@Test
	public void uploadFile(){
		page.uploadFile();
	}
	
	@AfterTest
	public void finalize(){
		driver.close();
	}
}



