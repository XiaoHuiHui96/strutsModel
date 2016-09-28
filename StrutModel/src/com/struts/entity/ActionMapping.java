package com.struts.entity;

/**
 * Struts≈‰÷√µƒ µÃÂ
 * @author Administrator
 *
 */
public class ActionMapping {
	
	private String path;
	private String type;
	private String name;
	private String nameType;
	private String success;
	private String error;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public ActionMapping(String path, String type, String name, String nameType, String success, String error) {
		super();
		this.path = path;
		this.type = type;
		this.name = name;
		this.nameType = nameType;
		this.success = success;
		this.error = error;
	}
	public ActionMapping(String path, String type, String success, String error) {
		super();
		this.path = path;
		this.type = type;
		this.success = success;
		this.error = error;
	}
	public ActionMapping() {
		super();
	}
	
}
