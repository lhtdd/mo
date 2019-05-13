package com.lyao.mo.business.notepad.bean.po;
import java.io.Serializable;

public class T_notepad implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String folderId;
	private String customerId;
	private String notepadName;
	private String type;
	private String content;
	private String sort;
	private String isTop;
	private String inTime;
	private String updateTime;
	private String image;
	private String remark;

	public T_notepad(){
		this.type = "2";
		this.isTop = "0";
		this.remark = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
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

