package com.lyao.mo.business.system.controller;

import com.lyao.mo.bottom.bean.to.SmsResponse;
import com.lyao.mo.bottom.service.AliyunSdkService;
import com.lyao.mo.common.utils.CaptchaUtils;
import com.lyao.mo.common.utils.Constant;
import com.lyao.mo.common.utils.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author lyao
 * @date 2019/6/27 14:21
 * @description
 */
@RequestMapping("/sms")
@Controller
public class ShortMessageController {

    private final Logger log = Logger.getLogger(ShortMessageController.class);
    private final static String OK = "OK";
    @Autowired
    private AliyunSdkService aliyunSdkService;

    @RequestMapping(value = "/captcha", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap getNoteCaptcha(String mobile, String validCode, HttpServletRequest request){
        ModelMap md = new ModelMap();
        String flag = "no";
        String message;
        final HttpSession session = request.getSession();
        boolean isLegalCaptcha = CaptchaUtils.isLegalCaptcha(request, validCode);
        if (isLegalCaptcha) {
            String shortMessageCode = (String) session.getAttribute(Constant.SHORT_MESSAGE_CODE);
            if (StringUtils.isNotEmpty(shortMessageCode)) {
                message = "之前的验证码依然有效，请使用它吧";
            }else {
                String code = RandomUtils.generateNumberCode(4);
                SmsResponse smsResponse = aliyunSdkService.sendShortMessage(mobile, code);
                //SmsResponse smsResponse = new SmsResponse();
                //smsResponse.setCode(OK);
                if (OK.equals(smsResponse.getCode())){
                    session.setAttribute(Constant.SHORT_MESSAGE_CODE, code);
                    final Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            session.removeAttribute(Constant.SHORT_MESSAGE_CODE);
                            log.warn(Constant.SHORT_MESSAGE_CODE + "已删除");
                            timer.cancel();
                        }
                    },2*60*1000);
                    flag = "yes";
                    message = "验证码已发送";
                }else {
                    message = smsResponse.getMessage();
                }
            }
        }else {
            message = "图形验证码错误";
        }
        md.put("flag", flag);
        md.put("message", message);
        return md;
    }
}
