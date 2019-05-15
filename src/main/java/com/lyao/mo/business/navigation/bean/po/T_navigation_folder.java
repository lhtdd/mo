package com.lyao.mo.business.navigation.bean.po;
import java.io.Serializable;
import java.util.Date;

public class T_navigation_folder implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerId;
	private String navigationName;
	private String navigationIcon;
	private Integer sort;
	private Date updateTime;
	private String type;
	private String remark;

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

	public String getNavigationName() {
		return navigationName;
	}

	public void setNavigationName(String navigationName) {
		this.navigationName = navigationName;
	}

	public String getNavigationIcon() {
		return navigationIcon;
	}

	public void setNavigationIcon(String navigationIcon) {
		this.navigationIcon = navigationIcon;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

