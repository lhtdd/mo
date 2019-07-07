package com.lyao.mo.business.home.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lyao
 * @date 2019/7/7 13:58
 * @description
 */
@Controller
@RequestMapping("home/")
public class HomeController {

    private final Logger log = Logger.getLogger(HomeController.class);

    @RequestMapping(value = "about", method = RequestMethod.GET)
    public ModelAndView contactUs(){
        ModelAndView md = new ModelAndView();
        md.setViewName("home/about");
        return md;
    }
}
