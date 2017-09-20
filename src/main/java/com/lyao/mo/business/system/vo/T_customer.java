package com.lyao.mo.business.system.vo;
import java.io.Serializable;
import java.util.Date;

public class T_customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String userimage;
	private String username;
	private String alias;
	private String password;
	private Integer registertype;
	private Integer status;
	private String validcode;
	private Date intime;
	private Date activationtime;
	private Integer type;
	private String remark;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setUserimage(String userimage){
		this.userimage=userimage;
	}
	public String getUserimage(){
		return userimage;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}
	public void setAlias(String alias){
		this.alias=alias;
	}
	public String getAlias(){
		return alias;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setRegistertype(Integer registertype){
		this.registertype=registertype;
	}
	public Integer getRegistertype(){
		return registertype;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setValidcode(String validcode){
		this.validcode=validcode;
	}
	public String getValidcode(){
		return validcode;
	}
	public void setIntime(Date intime){
		this.intime=intime;
	}
	public Date getIntime(){
		return intime;
	}
	public void setActivationtime(Date activationtime){
		this.activationtime=activationtime;
	}
	public Date getActivationtime(){
		return activationtime;
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

