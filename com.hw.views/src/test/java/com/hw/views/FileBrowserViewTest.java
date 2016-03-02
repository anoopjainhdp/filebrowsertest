/**
 * 
 */
package com.hw.views;

import java.util.ArrayList;
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
	
	 /**
     * Test#01 : Test for the layout of default screen
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * <p>
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
    public void defaultBehavior() {
        page.isViewReady();
    }

    /**
     * Test#02 : Test for the new folder creation
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Click on "New Folder" button on the top right hand side corner
     * Step#3 : Provide the folder name and click "Ok"
     * <p>
     * Validations
     * ============
     * Validation#1 : Success message should appear after folder is created.
     * Validation#2 : Check for the new folder if it is created successfully.
     * <p>
     * Cleanup
     * =======
     * Step#1 : Delete the folder created.
     */
    @Test
    public void newFolder() {
        String folderName = "Fld_" + (new Date()).getTime();				//Unique name for new folder
        String expectedMessage = "Successfully created " + page.getUserDir() + (page.getUserDir().endsWith("/") ? "" : "/") + folderName;	//Success message to be printed 
        
        //Action - Create new folder
        page.newFolder(folderName);											
        
        //Validation - 1. Check for success message 2. Check if folder is created from UI
        Assert.assertEquals(expectedMessage, page.getAlertMessage(), "Success message for new folder creation mismatches. \nActual message :" + page.getAlertMessage() + "\nExpected message :" + expectedMessage);
        Assert.assertTrue(page.isFolderAvailable(folderName), "New folder " + folderName + "is not created");

        //Cleanup - Delete the new folder
        page.deleteFileOperation(folderName);
    }

    /**
     * Test#03 : Test for the new folder creation with folder name greater than 255 characters
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Click on "New Folder" button on the top right hand side corner
     * Step#3 : Provide the folder name and click "Ok"
     * <p>
     * Validations
     * ============
     * Validation#1 : Failure message should appear after folder is created.
     * Validation#2 : Check for the new folder if error thrown is correct.
     */
    @Test
    public void newFolderWithBigName() {
        String folderName = "Fld_" + (new Date()).getTime();		//Unique folder name
        for (int i = 0; i < 250; i++)
            folderName += "A";										//make name bigger than 255 character

        String expectedMessage = "Failed to create " + page.getUserDir() + (page.getUserDir().endsWith("/") ? "" : "/") + folderName;	//Failure message to be printed.
        
        //Action - Create a new folder with name more than 255 characters
        page.newFolder(folderName);
        
        //Validation - 1. Check for failure message 2. Check if folder is not created from UI
        Assert.assertEquals(expectedMessage, page.getFailedAlertMessage(), "Failure message for new folder creation mismatches. \nActual message :" + page.getAlertMessage() + "\nExpected message :" + expectedMessage);
        Assert.assertTrue(!page.isFolderAvailable(folderName), "New folder " + folderName + "is created");
    }

    /**
     * Test#04 : Test for the new folder creation with special character along with other language characters
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Click on "New Folder" button on the top right hand side corner
     * Step#3 : Provide the folder name and click "Ok"
     * <p>
     * Validations
     * ============
     * Validation#1 : Success message should appear after folder is created.
     * Validation#2 : Check for the new folder if it is created successfully.
     * <p>
     * Cleanup
     * =======
     * Step#1 : Delete the folder created.
     */
    @Test
    public void newFolderWithSpecialChars() {
        String folderName = "Fld#$Hé!bonjour,Monsieur duCorbeau._" + (new Date()).getTime();	//Folder name with french characters
        String expectedMessage = "Successfully created " + page.getUserDir() + (page.getUserDir().endsWith("/") ? "" : "/") + folderName;	//Success message to be printed.
        
        //Action - Create a new folder with internationalize character set
        page.newFolder(folderName);
        
        //Validation - 1. Check for success message 2. Check if folder is created from UI
        Assert.assertEquals(expectedMessage, page.getAlertMessage(), "Success message for new folder creation mismatches. \nActual message :" + page.getAlertMessage() + "\nExpected message :" + expectedMessage);
        Assert.assertTrue(page.isFolderAvailable(folderName), "New folder " + folderName + "is not created");

        //Cleanup - Delete the new folder
        page.deleteFileOperation(folderName);
    }

    /**
     * Test#05 : Test for the select all/deselect all
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Click on "Select All" button on the top right hand side corner
     * Step#3 : Click on "Deselect All" button on the top right hand side cornor
     * Step#4 : Click on "Select All" button again on the top right hand side corner
     * <p>
     * Validations
     * ============
     * Validation#1 : All the folder must be selected after Step#2
     * Validation#2 : No folder must be selected after Step#3
     * Validation#3 : All the folder must be selected after Step#4
     */
    @Test
    public void selectAll() throws InterruptedException {
    	//Action - Select all files/folders
        page.selectAll();
        
        //Validation - 1. Check if all folder/files are selected.
        List<FileDetails> fileList = page.getFileDetails();
        List<String> selectedFileNames = page.getSelectedFileNames();
        Assert.assertEquals(fileList.size(), selectedFileNames.size(), "Not all files are selected");

        //Action - DeSelect all files/folders
        page.deSelectAll();
        
        //Validation - 1. Check if no folder/files are selected.
        selectedFileNames = page.getSelectedFileNames();
        Assert.assertEquals(selectedFileNames.size(), 0, "Some files are still selected");

        //Action - Again Select all files/folders
        page.selectAll();
        
        //Validation - 1. Check if all folder/files are selected.
        selectedFileNames = page.getSelectedFileNames();
        Assert.assertEquals(fileList.size(), selectedFileNames.size(), "Not all files are selected");
    }

    /**
     * Test#6 : Test for upload text/csv file
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Click on "Upload" button
     * Step#3 : Drag N Drop file to upload
     * <p>
     * Validations
     * ============
     * Validation#1 : File must be uploaded
     */
    @Test
    public void uploadCSVFile() {
        page.uploadFile();
        //ToDo Test the uploaded file
    }

    /**
     * Test#7 : Test for upload binary file
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Click on "Upload" button
     * Step#3 : Drag N Drop file to upload
     * <p>
     * Validations
     * ============
     * Validation#1 : File must be uploaded
     */
    @Test
    public void uploadBinaryFile() {
        page.uploadFile();
        //ToDo Test the uploaded file
    }

    /**
     * Test#8 : Test for message box
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Click on message icon
     * <p>
     * Validations
     * ============
     * Validation#1 :
     */
    @Test
    public void testMessage() {
        page.getMessageHistory();
    }

    /**
     * Test#9 : Test for searching file/folder
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Click on search box and search for existing file/folder name.
     * <p>
     * Validations
     * ============
     * Validation#1 : Search result must be as expected.
     */
    @Test
    public void testSearch() {
        //ToDo Get the criteria by finding common substring.

    	//Search string 
        String searchCriteria = "a";
        
        //Action - Search the files based on search criteria
        page.search(searchCriteria);

        //Validation - 1. Validate that the filter file/folder has search substring.
        List<FileDetails> fileDetailList = page.getFileDetails();
        for (FileDetails file : fileDetailList) {
            Assert.assertTrue(file.getName().contains(searchCriteria), "Search mismatch. Search Criteria : " + searchCriteria + " File name : " + file.getName());
        }
    }

  
    /**
     * Test#10 : Test for sorting file/folder
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Click on sort by name for existing file/folder name.
     * <p>
     * Validations
     * ============
     * Validation#1 : Sort result must be same as number of files before sorting.
     * Validation#2 : Sort result must be sorted as expected.
     */
    @Test
    public void testSortByOwner() throws InterruptedException{
    	//Action - Sort the table based on owner
        List<FileDetails>  fileDetailList = page.getFileDetails();
        page.clicktoSortBy("OWNER");

        //Validation - 1. Validate that the number of files are same as expected. 2. The file must be in sorted order
        List<FileDetails>  fileDetailListAfterSort = page.getSortedFileDetails();
        System.out.println(fileDetailListAfterSort);
        Assert.assertEquals(fileDetailList.size(), fileDetailListAfterSort.size(),"Mismatch in the number of files after sorting");

        List<String>owners = new ArrayList<String>();
        for(FileDetails details:fileDetailListAfterSort){
            owners.add(details.getOwner());
            System.out.println(details.getOwner());
        }
        Assert.assertTrue(isSorted(owners),"Issue with sorting of the files");
    }
    private boolean isSorted(List<String> orderedList)
    {
        boolean sorted = true;
        for (int i = 1; i < orderedList.size(); i++) {
            if (orderedList.get(i-1).compareTo(orderedList.get(i)) > 0)
                sorted = false;
        }

        return sorted;
    }

    /**
     * Test#11 : Test for folder open operation by clicking on folder name
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Click on folder icon/name
     * <p>
     * Validations
     * ============
     * Validation#1 : folder must be opened. Directory path must point to folder name.
     */

    @Test
    public void filesInsideFolder() {
    	//ToDo Need to be fixed
        int folderIndex = 0;
        List<FileDetails> fileDetails = page.getFileDetails();
        page.openFolder(folderIndex);
        
        String folderPath = page.getUserDir();
        int counter = 0;
        for (FileDetails file : fileDetails) {
            if (file.getSize().replace("--", "").trim().length() == 0) {
                if (counter == folderIndex) {
                    Assert.assertTrue(folderPath.endsWith(file.getName()), "Folder is not opened");
                    break;
                }
                counter++;
            }
        }
    }

    /**
     * Test#12 : Test for go back functionality in folder list
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Click on folder icon/name
     * Step#3 : Click on "Go Back" link
     * <p>
     * Validations
     * ============
     * Validation#1 : After "Go Back" operation the user directory in file browser view must be changed to old one.
     */

    @Test
    public void goBack() {
        String folderPath = page.getUserDir();
        filesInsideFolder();
        page.goBack();
        String afterFolderPath = page.getUserDir();
        Assert.assertTrue(folderPath.equals(afterFolderPath), "Folder path after go back link is different. Actual : " + afterFolderPath + " Expected : " + folderPath);
    }


    /**
     * Test#13 : Test for "Open" operation for file/folder
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Select the folder
     * Step#3 : Click on "Open" button
     * Step#4 : Select the file
     * Step#5 : Click on "Open" button
     * <p>
     * Validations
     * ============
     * Validation#1 : If folder open operation works.
     * Validation#2 : If file open operation works.
     */
    @Test
    public void openFileNFolder() {
        int index = 0;
        String folderName = "";
        String fileName = "";

        List<FileDetails> fileDetails = page.getFileDetails();
        //ToDo move to function to get the file or folder based on index
        int counter = 0;
        for (FileDetails file : fileDetails) {
            if (file.getSize().replace("--", "").trim().length() == 0) {
                if (counter == index) {
                    folderName = file.getName();
                    break;
                }
                counter++;
            }
        }
        for (FileDetails file : fileDetails) {
            if (file.getSize().replace("--", "").trim().length() > 0) {
                if (counter == index) {
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
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Select the folder
     * Step#3 : Click on "Rename" button
     * Step#4 : Select the file
     * Step#5 : Click on "Rename" button
     * <p>
     * Validations
     * ============
     * Validation#1 : If folder Rename operation works.
     * Validation#2 : If file Rename operation works.
     */
    @Test
    public void renameFileNFolder() {
        int index = 0;
        String folderName = "";
        String fileName = "";

        List<FileDetails> fileDetails = page.getFileDetails();
        //ToDo move to function to get the file or folder based on index
        int counter = 0;
        for (FileDetails file : fileDetails) {
            if (file.getSize().replace("--", "").trim().length() == 0) {
                if (counter == index) {
                    folderName = file.getName();
                    break;
                }
                counter++;
            }
        }
        for (FileDetails file : fileDetails) {
            if (file.getSize().replace("--", "").trim().length() > 0) {
                if (counter == index) {
                    fileName = file.getName();
                    break;
                }
                counter++;
            }
        }


        String newName = folderName+"_New";

        page.renameFileOperation(folderName,newName);


        page.search(newName);

        List<FileDetails> fileList = page.getFileDetails();

        boolean isFileRenamed = false;

        for(FileDetails file: fileList)
            if(file.getName().equals(newName)) isFileRenamed = true;

        Assert.assertTrue(isFileRenamed,"File rename operation failed");
    }

    /**
     * Test#14 : Test for "Copy" operation for file/folder
     * <p>
     * Steps
     * =====
     * Step#1 : Open the "File Browser View".
     * Step#2 : Select the folder
     * Step#3 : Click on "Copy" button
     * Step#4 : Select the folder to copy
     * Step#5 : Click on "Copy" button
     * <p>
     * Validations
     * ============
     * Validation#1 : If folder Rename operation works.
     * Validation#2 : If file Rename operation works.
     */
    @Test
    public void copyFileNFolder() {
        int index = 0;
        String folderName = "";
        String fileName = "";
        String newFolderName ="w";

        List<FileDetails> fileDetails = page.getFileDetails();
        //ToDo move to function to get the file or folder based on index
        int counter = 0;
        for (FileDetails file : fileDetails) {
            if (file.getSize().replace("--", "").trim().length() == 0) {
                if (counter == index) {
                    folderName = file.getName();
                    break;
                }
                counter++;
            }
        }
        for (FileDetails file : fileDetails) {
            if (file.getSize().replace("--", "").trim().length() > 0) {
                if (counter == index) {
                    fileName = file.getName();
                    break;
                }
                counter++;
            }
        }

        page.copyFileOperation(folderName, newFolderName);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        page.selectFile(newFolderName);

        List<FileDetails> fileList = page.getFileDetails();

        boolean isfileCopied = false;

        for(FileDetails file: fileList)
            if(file.getName().equals(newFolderName)) isfileCopied = true;

        Assert.assertTrue(isfileCopied,"File copy operation failed");
    }

    @Test
    public void downloadFile(){
        page.downloadOperation("Test1");
    }

    @AfterTest
    public void finalize() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.close();
    }
}

