package com.lyao.mo.business.leisure.bean.po;
import java.io.Serializable;
import java.util.Date;

public class T_happy_comment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerId;
	private Integer type;
	private String commentInfo;
	private Integer commentedId;
	private Date inTime;
	private Integer status;
	private Integer level;
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

	public String getCommentInfo() {
		return commentInfo;
	}

	public void setCommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}

	public Integer getCommentedId() {
		return commentedId;
	}

	public void setCommentedId(Integer commentedId) {
		this.commentedId = commentedId;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setLevel(Integer level){
		this.level=level;
	}
	public Integer getLevel(){
		return level;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

