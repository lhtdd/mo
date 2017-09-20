package com.lyao.mo.business.notepad.vo;
import java.io.Serializable;
import java.util.Date;

public class T_notepad implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer folderid;
	private String customerid;
	private String notepadname;
	private String content;
	private Date intime;
	private Date updatetime;
	private String image;
	private String remark;

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setFolderid(Integer folderid){
		this.folderid=folderid;
	}
	public Integer getFolderid(){
		return folderid;
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
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setIntime(Date intime){
		this.intime=intime;
	}
	public Date getIntime(){
		return intime;
	}
	public void setUpdatetime(Date updatetime){
		this.updatetime=updatetime;
	}
	public Date getUpdatetime(){
		return updatetime;
	}
	public void setImage(String image){
		this.image=image;
	}
	public String getImage(){
		return image;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

