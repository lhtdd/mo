package com.lyao.mo.business.system.vo;
import java.io.Serializable;
import java.util.Date;

public class T_navigation_folder implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerid;
	private String navigationname;
	private Integer sort;
	private Date updatetime;
	private String type;
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
	public void setNavigationname(String navigationname){
		this.navigationname=navigationname;
	}
	public String getNavigationname(){
		return navigationname;
	}
	public void setSort(Integer sort){
		this.sort=sort;
	}
	public Integer getSort(){
		return sort;
	}
	public void setUpdatetime(Date updatetime){
		this.updatetime=updatetime;
	}
	public Date getUpdatetime(){
		return updatetime;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

