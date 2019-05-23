package com.lyao.mo.business.leisure.bean;

/**
 * @author lyao
 * @date 2019/5/17 14:08
 * @description
 */
public class HappyItem extends HappyPagingQuery{

    private String customerId;
    private String userImagePath;
    private String alias;
    private String customerType;
    private String happyId;
    private String happyText;
    private String inTime;
    private String collectHits;
    private String contentType;
    private String resource;

    public HappyItem(){
    }

    public HappyItem(int initial, int perPageCount, String customerId){
        super(initial,perPageCount,0);
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getHappyId() {
        return happyId;
    }

    public void setHappyId(String happyId) {
        this.happyId = happyId;
    }

    public String getHappyText() {
        return happyText;
    }

    public void setHappyText(String happyText) {
        this.happyText = happyText;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getCollectHits() {
        return collectHits;
    }

    public void setCollectHits(String collectHits) {
        this.collectHits = collectHits;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
