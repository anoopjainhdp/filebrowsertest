/**
 * 
 */
package com.hw.views;

/**
 * @author ajain
 *
 */
public class FileDetails {
	
	private String name;
	private String size;
	private String lastModified;
	private String owner;
	private String group;
	private String permission;
	
	
	
	/**
	 * @param name
	 */
	public FileDetails(String name) {
		super();
		this.name = name;
	}
	/**
	 * @param name
	 * @param size
	 * @param lastModified
	 * @param owner
	 * @param group
	 * @param permission
	 */
	public FileDetails(String name, String size, String lastModified, String owner, String group, String permission) {
		super();
		this.name = name;
		this.size = size;
		this.lastModified = lastModified;
		this.owner = owner;
		this.group = group;
		this.permission = permission;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return the lastModified
	 */
	public String getLastModified() {
		return lastModified;
	}
	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}
	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	
	/**
	 * Print file details
	 */
	public String toString(){
		String fileDetails="";
		
		fileDetails += "File Name : "+name;
		fileDetails += "/nFile Size : "+size;
		fileDetails += "/nFile Last Modified : "+lastModified;
		fileDetails += "/nFile Owner : "+owner;
		fileDetails += "/nFile Group : "+group;
		fileDetails += "/nFile Permissions : "+permission;
		
		
		return fileDetails;
	}
}
