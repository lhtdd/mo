package com.lyao.mo.bottom.bean.po;
import java.io.Serializable;
import java.util.Date;


public class T_navigation_url implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerid;
	private Integer navigationid;
	private String urlimage;
	private String urlname;
	private String url;
	private Integer sort;
	private Date intime;
	private Date updatetime;
	private Date accesstime;
	private Integer hits;
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
	public void setNavigationid(Integer navigationid){
		this.navigationid=navigationid;
	}
	public Integer getNavigationid(){
		return navigationid;
	}
	public void setUrlimage(String urlimage){
		this.urlimage=urlimage;
	}
	public String getUrlimage(){
		return urlimage;
	}
	public void setUrlname(String urlname){
		this.urlname=urlname;
	}
	public String getUrlname(){
		return urlname;
	}
	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setSort(Integer sort){
		this.sort=sort;
	}
	public Integer getSort(){
		return sort;
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
	public void setAccesstime(Date accesstime){
		this.accesstime=accesstime;
	}
	public Date getAccesstime(){
		return accesstime;
	}
	public void setHits(Integer hits){
		this.hits=hits;
	}
	public Integer getHits(){
		return hits;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

