package com.lyao.mo.business.system.bean.po;
import java.io.Serializable;
import java.util.Date;

public class T_customer_detail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String realName;
	private Integer sex;
	private Integer idType;
	private String idNumber;
	private Integer occupation;
	private Date updateTime;
	private String remark;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Integer getOccupation() {
		return occupation;
	}

	public void setOccupation(Integer occupation) {
		this.occupation = occupation;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

