package com.lyao.mo.business.navigation.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/navigation")
public class NavigationController {
	private final Logger log = Logger.getLogger(NavigationController.class);
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/url/authc",method = RequestMethod.GET)  
	public ModelAndView addURL(){ 
		ModelAndView md = new ModelAndView();
		md.setViewName("notepad/notepad");
		return md;
	}
}
