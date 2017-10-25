package com.lyao.mo.business.navigation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyao.mo.bottom.bean.po.T_navigation_folder;
import com.lyao.mo.bottom.bean.po.T_navigation_url;
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
			CurrentUser curuser = (CurrentUser)request.getSession().getAttribute(Constant.CURRENT_USER);
			if(curuser != null){
				customerID = curuser.getId();
			}
		}
		//获取URL
		Map<String, Map<String, Object>> returnMap = null;
		try {
			returnMap = navigationServiceImpl.selectURLByCusID(customerID);
			flag = "yes";
		} catch (Exception e) {
			log.error("获取URL失败", e);
			flag = "no";
			errorMsg = "获取访问地址失败";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		md.put("navFolder",returnMap);
		return md;
	}
	/**
	 * 获取系统预设的导航收藏夹
	 * @return
	 */
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
			log.error("获取分类文件夹失败", e);
			flag = "no";
			errorMsg = "获取分类位置失败";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		md.put("navFolders",navFolders);
		return md;
	}
	/**
	 * 刷新常用url
	 * @param navid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/commonurl/{navid}/authc",method = RequestMethod.GET)
	@ResponseBody
	public ModelMap selectCommonUrl(@PathVariable String navid, HttpServletRequest request){
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = null;
		CurrentUser curuser = (CurrentUser)request.getSession().getAttribute(Constant.CURRENT_USER);
		Map<String, List<T_navigation_url>> newURLS = null;
		try {
			newURLS = navigationServiceImpl.selectURLForNormal(curuser.getId(), navid);
			flag = "yes";
		} catch (Exception e) {
			log.error("刷新常用url失败", e);
			flag = "no";
			errorMsg = "刷新常用url失败";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		md.put("newURLS",newURLS);
		return md;
	}
	/**
	 * 新增url
	 * @return
	 */
	@RequestMapping(value = "/urloperation/authc",method = RequestMethod.POST) 
	@ResponseBody
	public ModelMap urlOperation(@RequestParam String urlName,
			@RequestParam String webLocation,
			@RequestParam String targetFolder,
			HttpServletRequest request){ 
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = null;
		CurrentUser curuser = (CurrentUser)request.getSession().getAttribute(Constant.CURRENT_USER);
		boolean addFlag = false;
		try {
			addFlag = navigationServiceImpl.insertURL(urlName, webLocation, targetFolder, curuser.getId());
			if (addFlag){
				flag = "yes";
			}else {
				log.error("新增URL失败");
				flag = "no";
				errorMsg = "新增URL失败";
			}
		} catch (Exception e) {
			log.error("新增URL失败", e);
			flag = "no";
			errorMsg = "新增URL失败";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		return md;
	}
	/**
	 * 修改url
	 * @param urlName
	 * @param webLocation
	 * @param targetFolder
	 * @param urlid
	 * @return
	 */
	@RequestMapping(value = "/urloperation/authc",method = RequestMethod.PUT) 
	@ResponseBody
	public ModelMap urlOperation(@RequestParam String urlName,
			@RequestParam String webLocation,
			@RequestParam String targetFolder,
			@RequestParam String urlid){ 
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = null;
		boolean addFlag = false;
		try {
			addFlag = navigationServiceImpl.updateURLByURLID(urlName, webLocation, targetFolder, Integer.valueOf(urlid));
			if (addFlag){
				flag = "yes";
			}else {
				log.error("更新URL失败");
				flag = "no";
				errorMsg = "更新URL失败";
			}
		} catch (Exception e) {
			log.error("更新URL失败", e);
			flag = "no";
			errorMsg = "更新URL失败";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		return md;
	}
	/**
	 * 删除URL
	 * @param urlid
	 * @return
	 */
	@RequestMapping(value = "/urloperation/authc",method = RequestMethod.DELETE) 
	@ResponseBody
	public ModelMap urlOperation(@RequestParam String urlid){ 
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = null;
		boolean deleteFlag = false;
		try {
			deleteFlag = navigationServiceImpl.deleteURLByURLID(urlid);
			if (deleteFlag){
				flag = "yes";
			}else {
				log.error("删除URL失败");
				flag = "no";
				errorMsg = "删除URL失败";
			}
		} catch (Exception e) {
			log.error("删除URL失败", e);
			flag = "no";
			errorMsg = "删除URL失败";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		return md;
	}
	/**
	 * 更新点击次数及访问时间
	 * @param urlid
	 * @return
	 */
	@RequestMapping(value = "/urloperation/authc",method = RequestMethod.GET) 
	@ResponseBody
	public ModelMap updateURLForHits(@RequestParam String urlid){ 
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = null;
		boolean updateFlag = false;
		try {
			DateTime dt1 = new DateTime();
			updateFlag = navigationServiceImpl.updateURLForHits(dt1.toString("yyyy-MM-dd HH:mm:ss"), Integer.valueOf(urlid));
			if (updateFlag){
				flag = "yes";
			}else {
				log.error("更新URL点击次数失败");
				flag = "no";
				errorMsg = "更新URL点击次数失败";
			}
		} catch (Exception e) {
			log.error("更新URL点击次数失败", e);
			flag = "no";
			errorMsg = "更新URL点击次数失败";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		return md;
	}
}
