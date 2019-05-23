package com.lyao.mo.business.leisure.bean.po;
import java.io.Serializable;
import java.util.Date;

public class T_happy implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerId;
	private String happyText;
	private String happyImage;
	private String happyVideo;
	private Date inTime;
	private Integer status;
	private Integer praiseHits;
	private Integer treadHits;
	private Integer commentHits;
	private Integer transmitHits;
	private Integer collectHits;
	private Integer contentType;
	private Integer qualityType;
	private Integer resource;
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

	public String getHappyText() {
		return happyText;
	}

	public void setHappyText(String happyText) {
		this.happyText = happyText;
	}

	public String getHappyImage() {
		return happyImage;
	}

	public void setHappyImage(String happyImage) {
		this.happyImage = happyImage;
	}

	public String getHappyVideo() {
		return happyVideo;
	}

	public void setHappyVideo(String happyVideo) {
		this.happyVideo = happyVideo;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPraiseHits() {
		return praiseHits;
	}

	public void setPraiseHits(Integer praiseHits) {
		this.praiseHits = praiseHits;
	}

	public Integer getTreadHits() {
		return treadHits;
	}

	public void setTreadHits(Integer treadHits) {
		this.treadHits = treadHits;
	}

	public Integer getCommentHits() {
		return commentHits;
	}

	public void setCommentHits(Integer commentHits) {
		this.commentHits = commentHits;
	}

	public Integer getTransmitHits() {
		return transmitHits;
	}

	public void setTransmitHits(Integer transmitHits) {
		this.transmitHits = transmitHits;
	}

	public Integer getCollectHits() {
		return collectHits;
	}

	public void setCollectHits(Integer collectHits) {
		this.collectHits = collectHits;
	}

	public Integer getContentType() {
		return contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public Integer getQualityType() {
		return qualityType;
	}

	public void setQualityType(Integer qualityType) {
		this.qualityType = qualityType;
	}

	public Integer getResource() {
		return resource;
	}

	public void setResource(Integer resource) {
		this.resource = resource;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

