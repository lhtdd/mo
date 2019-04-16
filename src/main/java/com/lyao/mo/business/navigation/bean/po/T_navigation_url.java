package com.lyao.mo.business.navigation.bean.po;
import java.io.Serializable;


public class T_navigation_url implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerid;
	private Integer navigationid;
	private byte[] urlimage;
	private String urlname;
	private String url;
	private Integer type;
	private Integer sort;
	private String intime;
	private String updatetime;
	private String accesstime;
	private Integer hits;
	private String remark;
	
	public T_navigation_url(){
		this.id = 0;
		this.customerid = null;
		this.navigationid = 0;
		this.urlimage = null;
		this.urlname = null;
		this.url = null;
		this.type = 2;
		this.sort = 0;
		this.intime = null;
		this.updatetime = null;
		this.accesstime = null;
		this.hits = 0;
		this.remark = null;
	}

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
	public void setUrlimage(byte[] urlimage){
		this.urlimage=urlimage;
	}
	public byte[] getUrlimage(){
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setSort(Integer sort){
		this.sort=sort;
	}
	public Integer getSort(){
		return sort;
	}
	public void setIntime(String intime){
		this.intime=intime;
	}
	public String getIntime(){
		return intime;
	}
	public void setUpdatetime(String updatetime){
		this.updatetime=updatetime;
	}
	public String getUpdatetime(){
		return updatetime;
	}
	public void setAccesstime(String accesstime){
		this.accesstime=accesstime;
	}
	public String getAccesstime(){
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

