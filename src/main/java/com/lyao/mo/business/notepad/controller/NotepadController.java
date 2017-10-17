package com.lyao.mo.business.notepad.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/notepad")
public class NotepadController {
	
	private final Logger log = Logger.getLogger(NotepadController.class);
	/**
	 * 记事本主页
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index",method = RequestMethod.GET)  
	public ModelAndView notepadHome(HttpServletResponse response){  
		ModelAndView md = new ModelAndView();
		
		md.setViewName("notepad/notepad");
		return md;
	}   
}
