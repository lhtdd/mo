package com.lyao.mo.controller.sysController;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	private final Logger log = Logger.getLogger(MainController.class);
	@RequestMapping(value = "/",method = RequestMethod.GET)  
	public ModelAndView notepadHome(HttpServletResponse response){  
		ModelAndView md = new ModelAndView();
		md.setViewName("notepad");
		return md;
	} 
	@RequestMapping(value = "/leisure",method = RequestMethod.GET)  
	public ModelAndView leisureHome(HttpServletResponse response){  
		ModelAndView md = new ModelAndView();
		md.setViewName("leisure");
		return md;
	} 
	
}
