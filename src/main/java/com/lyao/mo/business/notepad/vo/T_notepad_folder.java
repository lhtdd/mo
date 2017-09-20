package com.lyao.mo.business.notepad.vo;
import java.io.Serializable;
import java.util.Date;


public class T_notepad_folder implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerid;
	private String notepadname;
	private Integer parentid;
	private Integer sort;
	private Date updatetime;
	private Integer type;
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
	public void setNotepadname(String notepadname){
		this.notepadname=notepadname;
	}
	public String getNotepadname(){
		return notepadname;
	}
	public void setParentid(Integer parentid){
		this.parentid=parentid;
	}
	public Integer getParentid(){
		return parentid;
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

