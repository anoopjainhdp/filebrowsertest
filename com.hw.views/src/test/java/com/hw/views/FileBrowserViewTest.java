/**
 * 
 */
package com.hw.views;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
	
	/**
	 * Test#02 : Test for the new folder creation
	 * 
	 * Steps
	 * =====
	 * Step#1 : Open the "File Browser View".
	 * Step#2 : Click on "New Folder" button on the top right hand side corner
	 * Step#3 : Provide the folder name and click "Ok"
	 * 
	 * Validations
	 * ============
	 * Validation#1 : Success message should appear after folder is created.
	 * Validation#2 : Check for the new folder if it is created successfully.
	 * 
	 * Cleanup
	 * =======
	 * Step#1 : Delete the folder created.
	 */
	@Test
	public void newFolder(){
		String folderName = "Fld_"+ (new Date()).getTime();
		String expectedMessage = "Successfully created "+page.getUserDir()+(page.getUserDir().endsWith("/")?"":"/")+folderName;
		page.newFolder(folderName);
		Assert.assertEquals(expectedMessage, page.getAlertMessage(),"Success message for new folder creation mismatches. \nActual message :"+page.getAlertMessage()+"\nExpected message :"+expectedMessage);
		Assert.assertTrue(page.isFolderAvailable(folderName),"New folder "+folderName+"is not created");
		
		//ToDo Cleanup Delete the folder created.
	}
	
	/**
	 * Test#03 : Test for the new folder creation with folder name greater than 255 characters
	 * 
	 * Steps
	 * =====
	 * Step#1 : Open the "File Browser View".
	 * Step#2 : Click on "New Folder" button on the top right hand side corner
	 * Step#3 : Provide the folder name and click "Ok"
	 * 
	 * Validations
	 * ============
	 * Validation#1 : Success message should appear after folder is created.
	 * Validation#2 : Check for the new folder if error thrown is correct.
	 */
	@Test
	public void newFolderWithBigName(){
		String folderName = "Fld_"+ (new Date()).getTime();
		for(int i=0;i<250;i++)
			folderName += "A";
		
		String expectedMessage = "Failed to create "+page.getUserDir()+(page.getUserDir().endsWith("/")?"":"/")+folderName;
		page.newFolder(folderName);
		Assert.assertEquals(expectedMessage, page.getFailedAlertMessage(),"Failure message for new folder creation mismatches. \nActual message :"+page.getAlertMessage()+"\nExpected message :"+expectedMessage);
		Assert.assertTrue(!page.isFolderAvailable(folderName),"New folder "+folderName+"is created");
	}
	
	/**
	 * Test#04 : Test for the new folder creation with special character along with other language characters
	 * 
	 * Steps
	 * =====
	 * Step#1 : Open the "File Browser View".
	 * Step#2 : Click on "New Folder" button on the top right hand side corner
	 * Step#3 : Provide the folder name and click "Ok"
	 * 
	 * Validations
	 * ============
	 * Validation#1 : Success message should appear after folder is created.
	 * Validation#2 : Check for the new folder if it is created successfully.
	 * 
	 * Cleanup
	 * =======
	 * Step#1 : Delete the folder created.
	 */
	@Test
	public void newFolderWithSpecialChars(){
		String folderName = "Fld#$Hé!bonjour,Monsieur duCorbeau._"+ (new Date()).getTime();
		String expectedMessage = "Successfully created "+page.getUserDir()+(page.getUserDir().endsWith("/")?"":"/")+folderName;
		page.newFolder(folderName);
		Assert.assertEquals(expectedMessage, page.getAlertMessage(),"Success message for new folder creation mismatches. \nActual message :"+page.getAlertMessage()+"\nExpected message :"+expectedMessage);
		Assert.assertTrue(page.isFolderAvailable(folderName),"New folder "+folderName+"is not created");
		
		//ToDo Cleanup Delete the folder created.
	}
	
	/**
	 * Test#05 : Test for the select all/deselect all
	 * 
	 * Steps
	 * =====
	 * Step#1 : Open the "File Browser View".
	 * Step#2 : Click on "Select All" button on the top right hand side corner
	 * Step#3 : Click on "Deselect All" button on the top right hand side cornor
	 * Step#4 : Click on "Select All" button again on the top right hand side corner
	 * 
	 * Validations
	 * ============
	 * Validation#1 : All the folder must be selected after Step#2
	 * Validation#2 : No folder must be selected after Step#3
	 * Validation#3 : All the folder must be selected after Step#4
	 */
	@Test
	public void selectAll() throws InterruptedException{
		page.selectAll();
		List<FileDetails> fileList = page.getFileDetails();
		List<String> selectedFileNames = page.getSelectedFileNames();
		
		Assert.assertEquals(fileList.size(), selectedFileNames.size(),"Not all files are selected");
		
		page.deSelectAll();
		
		selectedFileNames = page.getSelectedFileNames();
		
		Assert.assertEquals(selectedFileNames.size(), 0,"Some files are still selected");
		
		page.selectAll();
		
		selectedFileNames = page.getSelectedFileNames();
		
		Assert.assertEquals(fileList.size(), selectedFileNames.size(),"Not all files are selected");
	}
	
	
	/**
	 * Test#11 : Test for folder open operation by clicking on folder name
	 * 
	 * Steps
	 * =====
	 * Step#1 : Open the "File Browser View".
	 * Step#2 : Click on folder icon/name
	 * 
	 * Validations
	 * ============
	 * Validation#1 : folder must be opened. Directory path must point to folder name.
	 */
	
	@Test
	public void filesInsideFolder(){
		int folderIndex = 0;
		List<FileDetails> fileDetails = page.getFileDetails();
		page.openFolder(folderIndex);
		
		//ToDo remove wait
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String folderPath = page.getUserDir();
		int counter = 0;
		for(FileDetails file: fileDetails){
			if (file.getSize().replace("--", "").trim().length() == 0){
				if(counter == folderIndex){
					Assert.assertTrue(folderPath.endsWith(file.getName()),"Folder is not opened");
					break;
				}
				counter++;
			}	
		}
	}
	
	/**
	 * Test#12 : Test for go back functionality in folder list
	 * 
	 * Steps
	 * =====
	 * Step#1 : Open the "File Browser View".
	 * Step#2 : Click on folder icon/name
	 * Step#3 : Click on "Go Back" link
	 * 
	 * Validations
	 * ============
	 * Validation#1 : After "Go Back" operation the user directory in file browser view must be changed to old one.
	 */
	
	@Test
	public void goBack(){
		String folderPath = page.getUserDir();
		filesInsideFolder();
		page.goBack();
		String afterFolderPath = page.getUserDir();
		Assert.assertTrue(folderPath.equals(afterFolderPath),"Folder path after go back link is different. Actual : "+afterFolderPath +" Expected : "+folderPath);
	}
	
	
	/**
	 * Test#13 : Test for "Open" operation for file/folder
	 * 
	 * Steps
	 * =====
	 * Step#1 : Open the "File Browser View".
	 * Step#2 : Select the folder
	 * Step#3 : Click on "Open" button
	 * Step#4 : Select the file
	 * Step#5 : Click on "Open" button
	 * 
	 * Validations
	 * ============
	 * Validation#1 : If folder open operation works.
	 * Validation#2 : If file open operation works.
	 */
	@Test
	public void openFileNFolder(){
		int index = 0;
		String folderName = "";
		String fileName = "";
		
		List<FileDetails> fileDetails = page.getFileDetails();
		//ToDo move to function to get the file or folder based on index
		int counter = 0;
		for(FileDetails file: fileDetails){
			if (file.getSize().replace("--", "").trim().length() == 0){
				if(counter == index){
					folderName = file.getName();
					break;
				}
				counter++;
			}	
		}
		for(FileDetails file: fileDetails){
			if (file.getSize().replace("--", "").trim().length() > 0){
				if(counter == index){
					fileName = file.getName();
					break;
				}
				counter++;
			}	
		}
		
		page.openFileOperation(folderName);
		//ToDo Check for folder path
		page.goBack();
		//ToDo Check for folder path
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.openFileOperation(fileName);
		//ToDo Check for open file popup
	}
	
	
	/**
	 * Test#14 : Test for "Rename" operation for file/folder
	 * 
	 * Steps
	 * =====
	 * Step#1 : Open the "File Browser View".
	 * Step#2 : Select the folder
	 * Step#3 : Click on "Rename" button
	 * Step#4 : Select the file
	 * Step#5 : Click on "Rename" button
	 * 
	 * Validations
	 * ============
	 * Validation#1 : If folder Rename operation works.
	 * Validation#2 : If file Rename operation works.
	 */
	@Test
	public void renameFileNFolder(){
		
	}
	
	@Test
	public void uploadFile(){
		page.uploadFile();
	}
	
	@AfterTest
	public void finalize(){
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
	}
}



	
