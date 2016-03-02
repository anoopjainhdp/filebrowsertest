/**
 * 
 */
package com.hw.views;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

/**
 * Created by ajain on 2/26/16.
 */
public class FileBrowserViewPage {
    //===========================[Begin]Web Elements on Page==========================
    @FindBy(how = How.CSS, using = "i.fa-home")
    private WebElement iconHome;

    @FindBy(how = How.CSS, using = "i.fa-trash")
    private WebElement iconTrash;

    @FindAll(value = {@FindBy(how = How.CSS, using = "ul.breadcrumb li")})
    private List<WebElement> filePathList;


    @FindBy(how = How.CSS, using = "span.context-text")
    private WebElement msgBar;


    @FindBy(how = How.CSS, using = "i.fa-plus")
    private WebElement btnSelectAll;

    @FindBy(how = How.CSS, using = "i.fa-minus")
    private WebElement btnDeselectAll;

    @FindBy(how = How.CSS, using = "i.fa-folder-o")
    private WebElement btnNewFolder;

    @FindBy(how = How.CSS, using = "i.fa-upload")
    private WebElement btnUpload;

    @FindBy(how = How.CSS, using = "i.fa-comment-o")
    private WebElement btnMessage;


    @FindBy(how = How.CSS, using = "i.fa-folder-open-o")
    private WebElement iconOpen;

    @FindBy(how = How.CSS, using = "i.fa-edit")
    private WebElement iconRename;

    @FindBy(how = How.CSS, using = "i.fa-lock")
    private WebElement iconPermission;

    @FindBy(how = How.CSS, using = "i.fa-trash.fa-lg")
    private WebElement iconDelete;

    @FindBy(how = How.CSS, using = "i.fa-file-o")
    private WebElement iconCopy;

    @FindBy(how = How.CSS, using = "i.fa-share")
    private WebElement iconMove;

    @FindBy(how = How.CSS, using = "i.fa-download")
    private WebElement iconDownload;

    @FindBy(how = How.CSS, using = "i.fa-file-text")
    private WebElement iconConcatenate;


    @FindBy(how = How.CSS, using = "input.input-sm")
    private WebElement txtSearchBox;

    @FindBy(how = How.CSS, using = "i.fa-search")
    private WebElement iconSearch;


    @FindBy(how = How.CSS, using = "div#filesCollectionView")
    private WebElement tblFileData;


    @FindBy(how = How.CSS, using = "div#filesCollectionView div.files-header div:nth-child(1)")
    private WebElement tblHeaderNameColumn;

    @FindBy(how = How.CSS, using = "div#filesCollectionView div.files-header div:nth-child(2)")
    private WebElement tblHeaderSizeColumn;

    @FindBy(how = How.CSS, using = "div#filesCollectionView div.files-header div:nth-child(3)")
    private WebElement tblHeaderLastModifiedColumn;

    @FindBy(how = How.CSS, using = "div#filesCollectionView div.files-header div:nth-child(4)")
    private WebElement tblHeaderOwnerColumn;

    @FindBy(how = How.CSS, using = "div#filesCollectionView div.files-header div:nth-child(5)")
    private WebElement tblHeaderGroupColumn;

    @FindBy(how = How.CSS, using = "div#filesCollectionView div.files-header div:nth-child(6)")
    private WebElement tblHeaderPermissionColumn;


    @FindBy(how = How.CSS, using = "div#filesCollectionView i.fa-reply")
    private WebElement iconBack;


    @FindAll(value = {@FindBy(how = How.CSS, using = "div.file-row div.row div:nth-child(1)")})
    private List<WebElement> fileNameList;

    @FindAll(value = {@FindBy(how = How.CSS, using = "div.file-row div.row div:nth-child(2)")})
    private List<WebElement> fileSizeList;

    @FindAll(value = {@FindBy(how = How.CSS, using = "div.file-row div.row div:nth-child(3)")})
    private List<WebElement> fileLastModifiedList;

    @FindAll(value = {@FindBy(how = How.CSS, using = "div.file-row div.row div:nth-child(4)")})
    private List<WebElement> fileOwnerList;

    @FindAll(value = {@FindBy(how = How.CSS, using = "div.file-row div.row div:nth-child(5)")})
    private List<WebElement> fileGroupList;

    @FindAll(value = {@FindBy(how = How.CSS, using = "div.file-row div.row div:nth-child(6)")})
    private List<WebElement> filePermissionList;


    @FindAll(value = {@FindBy(how = How.CSS, using = "div.file-row.row-selected div.row div:nth-child(1)")})
    private List<WebElement> selectedFileNameList;

    @FindAll(value = {@FindBy(how = How.CSS, using = "div.file-row div.row i.fa-folder-o")})
    private List<WebElement> folderList;

    @FindAll(value = {@FindBy(how = How.CSS, using = "div.file-row div.row i.fa-file-o")})
    private List<WebElement> fileList;
    
    @FindAll(value = {@FindBy(how = How.CSS, using = "div#filesCollectionView div.row:nth-child(2) div>div>div>div>div>div")})
    private List<WebElement> fileOrderList;

    //===========================Pop Up Elements====================================
    @FindBy(how = How.CSS, using = "div.form-group input")
    private WebElement txtNewFolder;

    @FindBy(how = How.CSS, using = "div.modal-footer button i.fa-plus")
    private WebElement btnAdd;

    @FindBy(how = How.CSS, using = "div.modal-footer button i.fa-close")
    private WebElement btnClose;

    @FindBy(how = How.CSS, using = "div.file-picker")
    private WebElement btnUploadFile;

    @FindBy(how = How.CSS, using = "div.flash-messages p")
    private WebElement successAlertMsg;

    @FindBy(how = How.CSS, using = "div.flash-messages div.alert-danger p")
    private WebElement failureAlertMsg;

    @FindBy(how = How.CSS, using = "form div.form-group input")
    private WebElement inpRename;

    @FindBy(how = How.CSS, using = "form i.fa-edit")
    private WebElement btnRename;

    @FindAll(value = { @FindBy(how = How.CSS, using="div#tree ul li" )})
    private List<WebElement> folderListToCopyOrMove;

    @FindBy(how = How.CSS, using = "div.modal-content div.modal-footer i.fa-file-o")
    private WebElement btnCopyOrMove;

    @FindBy(how = How.CSS, using = "div.modal-content div.modal-footer i.fa-trash")
    private WebElement btnDelete;
    
    public enum fileProperties {
        NAME,SIZE,LAST_MODIFIED,OWNER,GROUP,PERMISSION
    }

    //===========================[End]Web Elements on Page==========================

    //===========================[Begin]Actions On Page==========================

    /**
     * View the home folder
     */
    public void viewHomeDir() {
        iconHome.click();
    }

    /**
     * View the trash directory
     */
    public void viewThrashDir() {
        iconTrash.click();
    }

    /**
     * Get the folder path
     *
     * @return Current folder path
     */
    public String getUserDir() {
        String folderPath = "";

        //Hack top create the complete folder path
        int counter = 1;
        for (WebElement filePath : filePathList) {
            if (counter >= 3) folderPath += File.separator;    //Add separator after level two folders
            folderPath += filePath.getText();
            counter++;
        }

        return folderPath;
    }

    /**
     * Get the message for action
     *
     * @return Action message
     */
    public String getMessage() {
        return msgBar.getText();
    }


    /**
     * Get the alert message while creating new folder
     *
     * @return alert message text
     */
    public String getAlertMessage() {
        return successAlertMsg.isDisplayed() ? successAlertMsg.getText() : "";
    }

    /**
     * Get the failure alert message while creating new folder
     *
     * @return failure alert message
     */
    public String getFailedAlertMessage() {
        return failureAlertMsg.isDisplayed() ? failureAlertMsg.getText() : "";
    }

    /**
     * Select all the files or folder
     */
    public void selectAll() {
        btnSelectAll.click();
    }

    /**
     * Deselect all the files or folder
     */
    public void deSelectAll() {
        btnDeselectAll.click();
    }

    /**
     * Create new folder
     */
    public void newFolder(String folderName) {
        btnNewFolder.click();
        txtNewFolder.sendKeys(folderName);
        btnAdd.click();
    }

    /**
     * Upload the file
     */
    public void uploadFile() {
        btnUpload.click();
        //ToDo How to upload the file from OS?
        btnUploadFile.click();
        btnUploadFile.sendKeys("/Users/ajain/npm-debug.log");
    }

    /**
     * Get the message history
     */
    public void getMessageHistory() {
        btnMessage.click();
    }

    /**
     * Search inside the folder
     */
    public void search(String searchCriteria) {
        txtSearchBox.sendKeys(searchCriteria);
        iconSearch.click();
    }


    /**
     * Get the file or folder details
     */
    public List<FileDetails> getFileDetails() {
    	//Put the delay to wait for action to be completed before getting the folder list.
    	//[ToDo] Move sleep to function
    	try {
    		Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        //[ToDo] Find better way to get all data in single call using Javascript
        List<FileDetails> fileDetails = new ArrayList<FileDetails>();

        for (WebElement elemFileName : fileNameList)
            if (elemFileName.getText().trim().length() > 0) fileDetails.add(new FileDetails(elemFileName.getText()));

        int counter = 0;
        for (WebElement elemFileSize : fileSizeList)
            if (fileNameList.get(counter).getText().trim().length() > 0)
                fileDetails.get(counter++).setSize(elemFileSize.getText());

        counter = 0;
        for (WebElement elemFileLastModified : fileLastModifiedList)
            if (fileNameList.get(counter).getText().trim().length() > 0)
                fileDetails.get(counter++).setLastModified(elemFileLastModified.getText());

        counter = 0;
        for (WebElement elemFileOwner : fileOwnerList)
            if (fileNameList.get(counter).getText().trim().length() > 0)
                fileDetails.get(counter++).setOwner(elemFileOwner.getText());

        counter = 0;
        for (WebElement elemFileGroup : fileGroupList)
            if (fileNameList.get(counter).getText().trim().length() > 0)
                fileDetails.get(counter++).setGroup(elemFileGroup.getText());

        counter = 0;
        for (WebElement elemFilePermission : filePermissionList)
            if (fileNameList.get(counter).getText().trim().length() > 0)
                fileDetails.get(counter++).setPermission(elemFilePermission.getText());

        counter = 0;
        for (WebElement elemFileOrder : fileOrderList){
        	String style = elemFileOrder.getAttribute("style");
        	if(!style.contains("matrix3d")) continue;
        	String transformArrayStr = style.split("matrix3d")[1];
        	int endOfArray = transformArrayStr.indexOf(")");
        	String[] transformArray = transformArrayStr.substring(1, endOfArray).split(",");
        	
        	fileDetails.get(counter++).setSortOrder(Integer.parseInt(transformArray[13].trim()));
        }
        	
        
        
        return fileDetails;
    }
    
    /**
     * Get the file or folder details
     */
    public List<FileDetails> getSortedFileDetails() {
    	List<FileDetails> fileDetailList = getFileDetails();
    	Collections.sort(fileDetailList, new Comparator<FileDetails>(){
    		public int compare(FileDetails f1, FileDetails f2) {
				return f1.getSortOrder() - f2.getSortOrder();
			}
    	});
    	return fileDetailList;
    }
    

    /**
     * Get the selected file names.
     *
     * @return
     */
    public List<String> getSelectedFileNames() {
        List<String> selectedFileNames = new ArrayList<String>();

        for (WebElement elemFileName : selectedFileNameList)
            if (elemFileName.getText().trim().length() > 0) selectedFileNames.add(elemFileName.getText());

        return selectedFileNames;
    }

    /**
     * Go back
     */
    public void goBack() {
        iconBack.click();
    }

    /**
     * Click on the folder to open it
     *
     * @param folderIndex
     */
    public void openFolder(int folderIndex) {
        if (folderIndex < folderList.size()) folderList.get(folderIndex).click();
    }

    /**
     * Select the file or folder
     */
    public void selectFile(int fileIndex) {
        List<FileDetails> files = getFileDetails();

        fileNameList.get(files.size() - fileIndex).click();
    }

    /**
     * Select the file or folder
     */
    public void selectFile(String fileName) {
        List<FileDetails> files = getFileDetails();

        int fileIndex = getFileIndex(files, fileName);

        fileSizeList.get(fileIndex).click();
    }

    /**
     * Click on the open icon to open folder/file
     *
     * @param fileName
     */
    public void openFileOperation(String fileName) {
        selectFile(fileName);
        iconOpen.click();
    }


    /**
     * Click on the rename icon to rename folder/file
     *
     * @param fileName
     */
    public void renameFileOperation(String fileName,String newName) {
        selectFile(fileName);
        iconRename.click();
        inpRename.clear();
        inpRename.sendKeys(newName);
        btnRename.click();
    }

    /**
     * Click on copy icon to copy the file
     *
     * @param fileName
     * @param folderName
     */
    public void copyFileOperation(String fileName,String folderName){
        selectFile(fileName);
        iconCopy.click();

        for(WebElement folder:folderListToCopyOrMove){
            if(folder.getText().trim().equals(folderName))
                folder.click();
        }

        btnCopyOrMove.click();
    }
    
    /**
     * Click on delete icon to delete the file/folder
     * 
     * @param fileName
     */
    public void deleteFileOperation(String fileName){
    	selectFile(fileName);
    	iconDelete.click();
    	btnDelete.click();
    }

    public void downloadOperation(String fileName){
        selectFile(fileName);
        iconDownload.click();
        
    }

    public void clicktoSortBy(String fieldName){
        fileProperties property = null;
        for (fileProperties prop : fileProperties.values()) {
            if (prop.name().equalsIgnoreCase(fieldName)) {
                property=fileProperties.valueOf(prop.name());
            }
        }
        switch(property){
        case NAME: tblHeaderNameColumn.click(); break;
        case SIZE: tblHeaderSizeColumn.click(); break;
        case LAST_MODIFIED: tblHeaderLastModifiedColumn.click(); break;
        case OWNER: tblHeaderOwnerColumn.click(); break;
        case GROUP: tblHeaderGroupColumn.click(); break;
        case PERMISSION: tblHeaderPermissionColumn.click(); break;
        }
    }
    //===========================[End]Actions On Page==========================

    /**
     * Get the file name index from the list
     *
     * @param files
     * @param fileName
     * @return index for the file
     */
    private int getFileIndex(List<FileDetails> files, String fileName) {
        for (int i = 0; i < files.size(); i++)
            if (files.get(i).getName().equals(fileName))
                return i;

        return -1;
    }

    /**
     * Get the file name index from the files displayed on File Browser UI
     *
     * @param fileName
     * @return file name index
     */
    private int getFileIndex(String fileName) {
        List<FileDetails> files = getFileDetails();
        return getFileIndex(files, fileName);
    }

    /**
     * Test if default layout fine while opening the view.
     *
     * @return if default layout is fine.
     */
    public void isViewReady() {
        Assert.assertTrue(iconHome.isDisplayed(), "Home Icon is not displayed");
        Assert.assertTrue(iconTrash.isDisplayed(), "Trash Icon is not displayed");
        Assert.assertTrue(filePathList.get(0).isDisplayed(), "Folder name is not displayed");
        Assert.assertTrue(msgBar.isDisplayed(), "Message bar is not displayed");
        Assert.assertTrue(btnSelectAll.isDisplayed(), "SelectAll button is not displayed");
        Assert.assertTrue(btnNewFolder.isDisplayed(), "NewFolder button is not displayed");
        Assert.assertTrue(btnUpload.isDisplayed(), "Upload button is not displayed");
        Assert.assertTrue(btnMessage.isDisplayed(), "Message button is not displayed");
    }

    /**
     * Check if folder is available in HDFS
     *
     * @param folderName
     * @return folder availability
     */
    public boolean isFolderAvailable(String folderName) {
        List<FileDetails> files = getFileDetails();

        for (FileDetails file : files)
            if (file.getName().equals(folderName))
                return true;

        return false;
    }

}




