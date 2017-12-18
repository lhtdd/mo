package com.lyao.mo.bottom.bean.bo;

import java.io.Serializable;

public class ValidCodeEmail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String userName;
    private String receiver;
    private String validURL;

    public ValidCodeEmail() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getValidURL() {
        return validURL;
    }

    public void setValidURL(String validURL) {
        this.validURL = validURL;
    }

    public String createInfo() {
        StringBuilder content = new StringBuilder("");
        content.append("您好," + this.userName + "<br>");
        content.append("&nbsp;&nbsp;&nbsp;&nbsp;您已注册为《默》的用户，现在只差一步激活。请点击下面的链接完成激活。<br>");
        content.append("&nbsp;&nbsp;&nbsp;&nbsp;<a href=" + this.validURL + ">" + this.validURL + "</a><br>");
        return content.toString();
    }
}
