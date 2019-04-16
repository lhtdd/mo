package com.lyao.mo.business.notepad.bean.po;
import java.io.Serializable;

public class T_notepad implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer folderId;
	private String customerId;
	private String notepadName;
	private String content;
	private String inTime;
	private String updateTime;
	private String image;
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFolderId() {
		return folderId;
	}

	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getNotepadName() {
		return notepadName;
	}

	public void setNotepadName(String notepadName) {
		this.notepadName = notepadName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

