package com.lyao.mo.bottom.service;

import com.lyao.mo.bottom.bean.to.SmsResponse;

/**
 * @author lyao
 * @date 2019/6/27 14:32
 * @description
 */
public interface AliyunSdkService {

    /**
     * 发送短信验证码
     * @param mobile
     * @param validCode
     * @return
     */
    SmsResponse sendShortMessage(String mobile, String validCode);
}
