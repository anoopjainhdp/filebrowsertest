/**
 * 
 */
package com.hw.views;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

/**
 * @author ajain
 *
 */
public class FileBrowserViewPage {
	
	//===========================[Begin]Web Elements on Page==========================
	@FindBy(how = How.CSS, using="i.fa-home")
	private WebElement iconHome;
	
	@FindBy(how = How.CSS, using="i.fa-trash")
	private WebElement iconTrash;
	
	@FindAll(value = { @FindBy(how = How.CSS, using="ul.breadcrumb li" )})
	private List<WebElement> filePathList;
	
	
	
	@FindBy(how = How.CSS, using="span.context-text")
	private WebElement msgBar;
	
	
	
	@FindBy(how = How.CSS, using="i.fa-plus")
	private WebElement btnSelectAll;
	
	@FindBy(how = How.CSS, using="i.fa-minus")
	private WebElement btnDeselectAll;
	
	@FindBy(how = How.CSS, using="i.fa-folder-o")
	private WebElement btnNewFolder;
	
	@FindBy(how = How.CSS, using="i.fa-upload")
	private WebElement btnUpload;
	
	@FindBy(how = How.CSS, using="i.fa-comment-o")
	private WebElement btnMessage;
	
	
	
	@FindBy(how = How.CSS, using="i.fa-folder-open-o")
	private WebElement iconOpen;
	
	@FindBy(how = How.CSS, using="i.fa-edit")
	private WebElement iconRename;
	
	@FindBy(how = How.CSS, using="i.fa-lock")
	private WebElement iconPermission;
	
	@FindBy(how = How.CSS, using="i.fa-trash")
	private WebElement iconDelete;
	
	@FindBy(how = How.CSS, using="i.fa-file-o")
	private WebElement iconCopy;
	
	@FindBy(how = How.CSS, using="i.fa-share")
	private WebElement iconMove;
	
	@FindBy(how = How.CSS, using="i.fa-download")
	private WebElement iconDownload;
	
	@FindBy(how = How.CSS, using="i.fa-file-text")
	private WebElement iconConcatenate;
	
	
	
	
	@FindBy(how = How.CSS, using="input.input-sm")
	private WebElement txtSearchBox;
	
	@FindBy(how = How.CSS, using="i.fa-search")
	private WebElement iconSearch;
	
	
	
	@FindBy(how = How.CSS, using="div#filesCollectionView")
	private WebElement tblFileData;
	
	
	
	@FindBy(how = How.CSS, using="div#filesCollectionView div.files-header div:nth-child(1)")
	private WebElement tblHeaderNameColumn;
	
	@FindBy(how = How.CSS, using="div#filesCollectionView div.files-header div:nth-child(2)")
	private WebElement tblHeaderSizeColumn;
	
	@FindBy(how = How.CSS, using="div#filesCollectionView div.files-header div:nth-child(3)")
	private WebElement tblHeaderLastModifiedColumn;
	
	@FindBy(how = How.CSS, using="div#filesCollectionView div.files-header div:nth-child(4)")
	private WebElement tblHeaderOwnerColumn;
	
	@FindBy(how = How.CSS, using="div#filesCollectionView div.files-header div:nth-child(5)")
	private WebElement tblHeaderGroupColumn;
	
	@FindBy(how = How.CSS, using="div#filesCollectionView div.files-header div:nth-child(6)")
	private WebElement tblHeaderPermissionColumn;
	
	
	@FindBy(how = How.CSS, using="div#filesCollectionView i.fa-reply")
	private WebElement iconBack;
	
	
	@FindAll(value = { @FindBy(how = How.CSS, using="div.file-row div.row div:nth-child(1)" )})
	private List<WebElement> fileNameList;
	
	@FindAll(value = { @FindBy(how = How.CSS, using="div.file-row div.row div:nth-child(2)" )})
	private List<WebElement> fileSizeList;
	
	@FindAll(value = { @FindBy(how = How.CSS, using="div.file-row div.row div:nth-child(3)" )})
	private List<WebElement> fileLastModifiedList;
	
	@FindAll(value = { @FindBy(how = How.CSS, using="div.file-row div.row div:nth-child(4)" )})
	private List<WebElement> fileOwnerList;
	
	@FindAll(value = { @FindBy(how = How.CSS, using="div.file-row div.row div:nth-child(5)" )})
	private List<WebElement> fileGroupList;
	
	@FindAll(value = { @FindBy(how = How.CSS, using="div.file-row div.row div:nth-child(6)" )})
	private List<WebElement> filePermissionList;
	
	
	@FindAll(value = { @FindBy(how = How.CSS, using="div.file-row.row-selected div.row div:nth-child(1)" )})
	private List<WebElement> selectedFileNameList;
	
	@FindAll(value = { @FindBy(how = How.CSS, using="div.file-row div.row i.fa-folder-o" )})
	private List<WebElement> folderList;
	
	@FindAll(value = { @FindBy(how = How.CSS, using="div.file-row div.row i.fa-file-o" )})
	private List<WebElement> fileList;
	
	//===========================Pop Up Elements====================================
	@FindBy(how = How.CSS, using="div.form-group input")
	private WebElement txtNewFolder;
	
	@FindBy(how = How.CSS, using="div.modal-footer button i.fa-plus")
	private WebElement btnAdd;
	
	@FindBy(how = How.CSS, using="div.modal-footer button i.fa-close")
	private WebElement btnClose;
	
	@FindBy(how = How.CSS, using="i.fa-cloud-upload")
	private WebElement btnUploadFile;
	
	@FindBy(how = How.CSS, using="div.flash-messages p")
	private WebElement successAlertMsg;
	
	@FindBy(how = How.CSS, using="div.flash-messages div.alert-danger p")
	private WebElement failureAlertMsg;
	
	
	//===========================[End]Web Elements on Page==========================
	
	//===========================[Begin]Actions On Page==========================
	
	/**
	 * View the home folder
	 */
	public void viewHomeDir(){
		iconHome.click();
		//[ToDo] Remove wait for action.
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * View the trash directory
	 */
	public void viewThrashDir(){
		iconTrash.click();
		//[ToDo] Remove wait for action.
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the folder path
	 * @return Current folder path
	 */
	public String getUserDir(){
		String folderPath = "";
		
		//Hack top create the complete folder path
		int counter = 1;
		for(WebElement filePath : filePathList){
			if(counter >= 3) folderPath += File.separator;	//Add separator after level two folders
			folderPath += filePath.getText();
			counter++;
		}
		
		return folderPath;
	}
	
	/**
	 * Get the message for action
	 * @return Action message
	 */
	public String getMessage(){
		return msgBar.getText();
	}
	

	public String getAlertMessage(){
		return successAlertMsg.isDisplayed()?successAlertMsg.getText():"";
	}
	
	public String getFailedAlertMessage(){
		return failureAlertMsg.isDisplayed()?failureAlertMsg.getText():"";
	}
	
	/**
	 * Select all the files or folder
	 */
	public void selectAll(){
		btnSelectAll.click();
	}
	
	/**
	 * Deselect all the files or folder
	 */
	public void deSelectAll(){
		btnDeselectAll.click();
	}
	
	/**
	 * Create new folder
	 */
	public void newFolder(String folderName){
		btnNewFolder.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtNewFolder.sendKeys(folderName);
		btnAdd.click();
	}
	
	/**
	 * Upload the file
	 */
	public void uploadFile(){
		btnUpload.click();
		//ToDo How to upload the file from OS?
		btnUploadFile.sendKeys("/Users/ajain/npm-debug.log");
	}
	
	/**
	 * Get the message history
	 */
	public void getMessageHistory(){
		btnMessage.click();
		//[ToDo] Add upload file logic
	}
	
	/**
	 * Search inside the folder
	 */
	public void search(String searchCriteria){
		txtSearchBox.sendKeys(searchCriteria);
		iconSearch.click();
		//[ToDo] Remove wait for action.
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	
	/**
	 * Get the file or folder details
	 */
	public List<FileDetails> getFileDetails(){
		//[ToDo] Find better way to get all data in single call using Javascript
		List<FileDetails> fileDetails = new ArrayList<FileDetails>();
		
		for(WebElement elemFileName: fileNameList)
			if(elemFileName.getText().trim().length() > 0) fileDetails.add(new FileDetails(elemFileName.getText()));
		
		int counter = 0;
		for(WebElement elemFileSize: fileSizeList)
			if(fileNameList.get(counter).getText().trim().length() > 0) fileDetails.get(counter++).setSize(elemFileSize.getText());
		
		counter = 0;
		for(WebElement elemFileLastModified: fileLastModifiedList)
			if(fileNameList.get(counter).getText().trim().length() > 0) fileDetails.get(counter++).setLastModified(elemFileLastModified.getText());
		
		counter = 0;
		for(WebElement elemFileOwner: fileOwnerList)
			if(fileNameList.get(counter).getText().trim().length() > 0) fileDetails.get(counter++).setOwner(elemFileOwner.getText());

		counter = 0;
		for(WebElement elemFileGroup: fileGroupList)
			if(fileNameList.get(counter).getText().trim().length() > 0) fileDetails.get(counter++).setGroup(elemFileGroup.getText());
		
		counter = 0;
		for(WebElement elemFilePermission: filePermissionList)
			if(fileNameList.get(counter).getText().trim().length() > 0) fileDetails.get(counter++).setPermission(elemFilePermission.getText());
		
		
		return fileDetails;
	}
	
	/**
	 * Get the selected file names.
	 * @return
	 */
	public List<String> getSelectedFileNames(){
		List<String> selectedFileNames = new ArrayList<String>();
		
		for(WebElement elemFileName: selectedFileNameList)
			if(elemFileName.getText().trim().length() > 0) selectedFileNames.add(elemFileName.getText());
		
		return selectedFileNames;
	}

	/**
	 * Go back
	 */
	public void goBack(){
		iconBack.click();
	}
	
	/**
	 * Click on the folder to open it
	 * @param folderIndex
	 */
	public void openFolder(int folderIndex){
		if(folderIndex < folderList.size()) folderList.get(folderIndex).click();
	}
	
	/**
	 * Select the file or folder
	 */
	public void selectFile(int fileIndex){
		List<FileDetails> files = getFileDetails();
		
		fileNameList.get(files.size() - fileIndex).click();
	}
	
	/**
	 * Select the file or folder
	 */
	public void selectFile(String fileName){
		List<FileDetails> files = getFileDetails();
		
		int fileIndex = getFileIndex(files,fileName);
		
		fileNameList.get(fileIndex).click();
	}
	
	public void openFileOperation(String fileName){
		selectFile(fileName);
		iconOpen.click();
	}
	
	//===========================[End]Actions On Page==========================

	private int getFileIndex(List<FileDetails> files,String fileName){
		for(int i=0;i<files.size();i++)
			if(files.get(i).getName().equals(fileName))
				return i;
		
		return -1;
	}
	
	private int getFileIndex(String fileName){
		List<FileDetails> files = getFileDetails();
		return getFileIndex(files,fileName);
	}
	
	/**
	 * Test if default layout fine while opening the view.
	 * @return if default layout is fine.
	 */
	public void isViewReady(){
		Assert.assertTrue(iconHome.isDisplayed(), "Home Icon is not displayed");
		Assert.assertTrue(iconTrash.isDisplayed(), "Trash Icon is not displayed");
		Assert.assertTrue(filePathList.get(0).isDisplayed(), "Folder name is not displayed");
		Assert.assertTrue(msgBar.isDisplayed(), "Message bar is not displayed");
		Assert.assertTrue(btnSelectAll.isDisplayed(), "SelectAll button is not displayed");
		Assert.assertTrue(btnNewFolder.isDisplayed(), "NewFolder button is not displayed");
		Assert.assertTrue(btnUpload.isDisplayed(), "Upload button is not displayed");
		Assert.assertTrue(btnMessage.isDisplayed(), "Message button is not displayed");
	}
	
	public boolean isFolderAvailable(String folderName){
		List<FileDetails> files = getFileDetails();
		
		for(FileDetails file:files)
			if(file.getName().equals(folderName))
				return true;
		
		return false;
	}

}



