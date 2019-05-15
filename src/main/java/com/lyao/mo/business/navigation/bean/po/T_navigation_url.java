package com.lyao.mo.business.navigation.bean.po;
import java.io.Serializable;


public class T_navigation_url implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerId;
	private Integer navigationId;
	private byte[] urlImage;
	private String urlName;
	private String url;
	private Integer type;
	private Integer sort;
	private String inTime;
	private String updateTime;
	private String accessTime;
	private Integer hits;
	private String remark;
	
	public T_navigation_url(){
		this.id = 0;
		this.customerId = null;
		this.navigationId = 0;
		this.urlImage = null;
		this.urlName = null;
		this.url = null;
		this.type = 2;
		this.sort = 0;
		this.inTime = null;
		this.updateTime = null;
		this.accessTime = null;
		this.hits = 0;
		this.remark = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getNavigationId() {
		return navigationId;
	}

	public void setNavigationId(Integer navigationId) {
		this.navigationId = navigationId;
	}

	public byte[] getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(byte[] urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public String getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

