package com.lyao.mo.business.navigation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lyao.mo.bottom.bean.po.T_navigation_folder;
import com.lyao.mo.bottom.service.NavigationService;
import com.lyao.mo.business.system.bo.CurrentUser;
import com.lyao.mo.common.utils.Constant;
@Controller
@RequestMapping("/navigation")
public class NavigationController {
	private final Logger log = Logger.getLogger(NavigationController.class);
	@Autowired
	private NavigationService navigationServiceImpl;
	/**
	 * 导航
	 * @return
	 */
	@RequestMapping(value = "/url/authc",method = RequestMethod.GET)  
	public ModelAndView addURL(){ 
		ModelAndView md = new ModelAndView();
		md.setViewName("notepad/notepad");
		return md;
	}
	/**
	 * 页面加载后，自动查询收藏的记录
	 * @param customerID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/url",method = RequestMethod.GET)
	@ResponseBody
	public ModelMap selectURL(@RequestParam(value = "cus", required = false) String customerID,HttpServletRequest request){
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = null;
		// 获取当前客户ID
		if (StringUtils.isBlank(customerID)){
			CurrentUser curuer = (CurrentUser)request.getSession().getAttribute(Constant.CURRENT_USER);
			if(curuer != null){
				customerID = curuer.getId();
			}
		}
		//获取URL
		Map<String, Map<String, Object>> returnMap = null;
		try {
			returnMap = navigationServiceImpl.selectURLByCusID(customerID);
			flag = "yes";
		} catch (Exception e) {
			log.error("获取URL失败");
			flag = "no";
			errorMsg = "获取访问地址失败";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		md.put("navFolder",returnMap);
		return md;
	}
	
	@RequestMapping(value = "/folderName",method = RequestMethod.GET)
	@ResponseBody
	public ModelMap selectNavigationFolder(){
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = null;
		List<T_navigation_folder> navFolders = null;
		try {
			navFolders = navigationServiceImpl.selectNavigationFolder();
			flag = "yes";
		} catch (Exception e) {
			log.error("获取分类文件夹失败");
			flag = "no";
			errorMsg = "获取分类位置失败";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		md.put("navFolders",navFolders);
		return md;
	}
}
