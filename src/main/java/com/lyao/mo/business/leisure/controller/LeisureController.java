package com.lyao.mo.business.leisure.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/leisure")
public class LeisureController {
	
	private final Logger log = Logger.getLogger(LeisureController.class);
	/**
	 * 跳转至闲暇小憩的主页
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index",method = RequestMethod.GET)  
	public ModelAndView leisureHome(HttpServletRequest request){  
		ModelAndView md = new ModelAndView();
		md.setViewName("leisure/leisure");
		return md;
	}
	/**
	 * 跳转至开心果的详细页面
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/happyDetail",method = RequestMethod.GET)  
	public ModelAndView leisureHappyDetail(HttpServletRequest request){  
		ModelAndView md = new ModelAndView();
		md.setViewName("leisure/leisure_detail");
		return md;
	} 
	/**
	 * 跳转至发表趣事的主页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/myHappyDetail/authc",method = RequestMethod.GET)  
	public ModelAndView writeHappyDetail(HttpServletRequest request){
		ModelAndView md = new ModelAndView();
		md.setViewName("leisure/leisure_home");
		return md;
	} 
	
	
}
