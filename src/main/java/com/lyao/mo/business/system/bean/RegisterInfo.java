package com.lyao.mo.business.system.bean;

import java.io.Serializable;
/**
 * @author lyao
 * @date
 */
public class RegisterInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String alias;
	private String password;
	private String sex;
	private String registerType;
	private String validCode;
	private String shortMessageCode;
	private String inTime;
	private String updateTime;
	private Integer status;

	public RegisterInfo(){
		this.status = 2;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getShortMessageCode() {
		return shortMessageCode;
	}

	public void setShortMessageCode(String shortMessageCode) {
		this.shortMessageCode = shortMessageCode;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
