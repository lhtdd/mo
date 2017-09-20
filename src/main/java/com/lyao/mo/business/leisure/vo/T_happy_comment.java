package com.lyao.mo.business.leisure.vo;
import java.io.Serializable;
import java.util.Date;

public class T_happy_comment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerid;
	private Integer type;
	private String commentinfo;
	private Integer commentedid;
	private Date intime;
	private Integer status;
	private Integer level;
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
	public void setCommentinfo(String commentinfo){
		this.commentinfo=commentinfo;
	}
	public String getCommentinfo(){
		return commentinfo;
	}
	public void setCommentedid(Integer commentedid){
		this.commentedid=commentedid;
	}
	public Integer getCommentedid(){
		return commentedid;
	}
	public void setIntime(Date intime){
		this.intime=intime;
	}
	public Date getIntime(){
		return intime;
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

