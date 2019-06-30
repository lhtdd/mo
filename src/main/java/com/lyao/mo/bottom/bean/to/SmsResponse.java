package com.lyao.mo.bottom.bean.to;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lyao
 * @date 2019/6/29 15:41
 * @description
 */
public class SmsResponse {

    @JsonProperty("BizId")
    private String bizId;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("RequestId")
    private String requestId;

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
