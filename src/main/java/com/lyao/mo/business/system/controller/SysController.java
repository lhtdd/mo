package com.lyao.mo.business.system.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SysController {
	
	private final Logger log = Logger.getLogger(SysController.class);
	@RequestMapping(value = "/",method = RequestMethod.GET)  
	public ModelAndView index(){ 
		ModelAndView md = new ModelAndView();
		md.setViewName("notepad/notepad");
		return md;
	} 
	
	@RequestMapping(value = "/member/{type}",method = RequestMethod.GET)  
	public ModelAndView doLogin(@PathVariable String type){ 
		ModelAndView md = new ModelAndView();
		md.addObject("type", type);
		md.setViewName("login");
		return md;
	} 
	@RequestMapping(value = "/member/doRegister",method = RequestMethod.GET)  
	public ModelAndView doRegister(){ 
		ModelAndView md = new ModelAndView();
		md.setViewName("login");
		return md;
	} 
	
}
