package com.lyao.mo.common.utils;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lyao
 * @date 2019/6/30 16:44
 * @description
 */
public class CaptchaUtils {

    private final Logger log = Logger.getLogger(CaptchaUtils.class);

    /**
     * 校验图形验证码是否相等
     * @param request
     * @param captcha
     * @return
     */
    public static boolean isLegalCaptcha(HttpServletRequest request, String captcha){
        String kaptchaExpected = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (captcha.equals(kaptchaExpected)){
            return true;
        }
        return false;
    }
}
