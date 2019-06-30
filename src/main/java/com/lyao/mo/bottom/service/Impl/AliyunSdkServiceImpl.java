package com.lyao.mo.bottom.service.Impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyao.mo.bottom.bean.to.SmsResponse;
import com.lyao.mo.bottom.service.AliyunSdkService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author lyao
 * @date 2019/6/27 14:34
 * @description
 */
@Service("aliyunSdkService")
public class AliyunSdkServiceImpl implements AliyunSdkService{

    private final Logger log = Logger.getLogger(AliyunSdkServiceImpl.class);

    @Override
    public SmsResponse sendShortMessage(String mobile, String validCode) {
        SmsResponse smsResponse = new SmsResponse();
        String code = null;
        String message = null;
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIn9Ak3rgBlkzJ", "5jK0imwz92ZSbajJppiU7kQU21UfvY");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", "mo");
        request.putQueryParameter("TemplateCode", "SMS_169104005");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+validCode+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("阿里云返回短信验证码发送结果：" + response.getData());
            ObjectMapper mapper = new ObjectMapper();
            smsResponse = mapper.readValue(response.getData(), SmsResponse.class);
        } catch (ServerException e) {
            log.error("发送短信验证码失败", e);
            code = "error";
            message = e.getMessage();
        } catch (ClientException e) {
            log.error("发送短信验证码失败", e);
            code = "error";
            message = e.getMessage();
        } catch (IOException e) {
            log.error("验证码响应信息转JSON实例失败", e);
            code = "error";
            message = e.getMessage();
        }
        if ("error".equals(code)){
            smsResponse.setCode(code);
            smsResponse.setMessage(message);
        }
        return smsResponse;
    }
}
