package com.lyao.mo.business.system.bean.po;
import java.io.Serializable;
import java.util.Date;

public class T_customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String userImage;
	private String username;
	private String alias;
	private String password;
	private Integer registerType;
	private Integer status;
	private String validCode;
	private Date inTime;
	private Date activationTime;
	private Integer type;
	private Integer lastHappyId;
	private String remark;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
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

	public Integer getRegisterType() {
		return registerType;
	}

	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(Date activationTime) {
		this.activationTime = activationTime;
	}

	public Integer getLastHappyId() {
		return lastHappyId;
	}

	public void setLastHappyId(Integer lastHappyId) {
		this.lastHappyId = lastHappyId;
	}

	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return type;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

