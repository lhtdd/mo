package com.lyao.mo.bottom.bean.po;
import java.io.Serializable;
import java.util.Date;

public class T_customer_detail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String realname;
	private Integer sex;
	private Integer idtype;
	private String idnumber;
	private Integer occupation;
	private Date updatetime;
	private String remark;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setRealname(String realname){
		this.realname=realname;
	}
	public String getRealname(){
		return realname;
	}
	public void setSex(Integer sex){
		this.sex=sex;
	}
	public Integer getSex(){
		return sex;
	}
	public void setIdtype(Integer idtype){
		this.idtype=idtype;
	}
	public Integer getIdtype(){
		return idtype;
	}
	public void setIdnumber(String idnumber){
		this.idnumber=idnumber;
	}
	public String getIdnumber(){
		return idnumber;
	}
	public void setOccupation(Integer occupation){
		this.occupation=occupation;
	}
	public Integer getOccupation(){
		return occupation;
	}
	public void setUpdatetime(Date updatetime){
		this.updatetime=updatetime;
	}
	public Date getUpdatetime(){
		return updatetime;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

