package com.lyao.mo.business.leisure.bean.po;
import java.io.Serializable;
import java.util.Date;

public class T_happy implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String customerid;
	private String happytext;
	private String happyimage;
	private String happyvideo;
	private Date intime;
	private Integer status;
	private Integer priasehits;
	private Integer treadhits;
	private Integer commethits;
	private Integer transmithits;
	private Integer collecthits;
	private Integer contenttype;
	private Integer qualitytype;
	private Integer resource;
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
	public void setHappytext(String happytext){
		this.happytext=happytext;
	}
	public String getHappytext(){
		return happytext;
	}
	public void setHappyimage(String happyimage){
		this.happyimage=happyimage;
	}
	public String getHappyimage(){
		return happyimage;
	}
	public void setHappyvideo(String happyvideo){
		this.happyvideo=happyvideo;
	}
	public String getHappyvideo(){
		return happyvideo;
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
	public void setPriasehits(Integer priasehits){
		this.priasehits=priasehits;
	}
	public Integer getPriasehits(){
		return priasehits;
	}
	public void setTreadhits(Integer treadhits){
		this.treadhits=treadhits;
	}
	public Integer getTreadhits(){
		return treadhits;
	}
	public void setCommethits(Integer commethits){
		this.commethits=commethits;
	}
	public Integer getCommethits(){
		return commethits;
	}
	public void setTransmithits(Integer transmithits){
		this.transmithits=transmithits;
	}
	public Integer getTransmithits(){
		return transmithits;
	}
	public void setCollecthits(Integer collecthits){
		this.collecthits=collecthits;
	}
	public Integer getCollecthits(){
		return collecthits;
	}
	public void setContenttype(Integer contenttype){
		this.contenttype=contenttype;
	}
	public Integer getContenttype(){
		return contenttype;
	}
	public void setQualitytype(Integer qualitytype){
		this.qualitytype=qualitytype;
	}
	public Integer getQualitytype(){
		return qualitytype;
	}
	public void setResource(Integer resource){
		this.resource=resource;
	}
	public Integer getResource(){
		return resource;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

