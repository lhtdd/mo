package com.lyao.mo.business.notepad.bean.po;
import java.io.Serializable;
import java.util.Date;


public class T_notepad_folder implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerId;
	private String notepadName;
	private Integer parentId;
	private Integer sort;
	private Date updateTime;
	private Integer type;
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

	public String getNotepadName() {
		return notepadName;
	}

	public void setNotepadName(String notepadName) {
		this.notepadName = notepadName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

