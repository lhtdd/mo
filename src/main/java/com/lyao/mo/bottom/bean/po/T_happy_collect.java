package com.lyao.mo.bottom.bean.po;
import java.io.Serializable;
import java.util.Date;


public class T_happy_collect implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerid;
	private Integer type;
	private Integer happyid;
	private Date dealtime;
	private Integer transmitplatform;
	private Integer status;
	private String remark;

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setCustomerid(String customerid){
		this.customerid=customerid;
	}
	public String getCustomerid(){
		return customerid;
	}
	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return type;
	}
	public void setHappyid(Integer happyid){
		this.happyid=happyid;
	}
	public Integer getHappyid(){
		return happyid;
	}
	public void setDealtime(Date dealtime){
		this.dealtime=dealtime;
	}
	public Date getDealtime(){
		return dealtime;
	}
	public void setTransmitplatform(Integer transmitplatform){
		this.transmitplatform=transmitplatform;
	}
	public Integer getTransmitplatform(){
		return transmitplatform;
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

