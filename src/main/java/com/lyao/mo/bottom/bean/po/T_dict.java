package com.lyao.mo.bottom.bean.po;
import java.io.Serializable;
import java.util.Date;

public class T_dict implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String dictitem;
	private String dictgroup;
	private Integer sort;
	private Integer status;
	private Date intime;
	private String remark;

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setDictitem(String dictitem){
		this.dictitem=dictitem;
	}
	public String getDictitem(){
		return dictitem;
	}
	public void setDictgroup(String dictgroup){
		this.dictgroup=dictgroup;
	}
	public String getDictgroup(){
		return dictgroup;
	}
	public void setSort(Integer sort){
		this.sort=sort;
	}
	public Integer getSort(){
		return sort;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setIntime(Date intime){
		this.intime=intime;
	}
	public Date getIntime(){
		return intime;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

