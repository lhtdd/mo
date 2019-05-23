package com.lyao.mo.business.leisure.bean.po;
import java.io.Serializable;
import java.util.Date;


public class T_happy_collect implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerId;
	private Integer type;
	private Integer happyId;
	private Date dealTime;
	private Integer transmitPlatform;
	private Integer status;
	private String remark;

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getHappyId() {
		return happyId;
	}

	public void setHappyId(Integer happyId) {
		this.happyId = happyId;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public Integer getTransmitPlatform() {
		return transmitPlatform;
	}

	public void setTransmitPlatform(Integer transmitPlatform) {
		this.transmitPlatform = transmitPlatform;
	}

	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

