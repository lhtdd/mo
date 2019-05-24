package com.lyao.mo.business.leisure.controller;

import com.lyao.mo.business.leisure.bean.HappyItem;
import com.lyao.mo.business.leisure.bean.HappyPagingQuery;
import com.lyao.mo.business.leisure.bean.po.T_happy_collect;
import com.lyao.mo.business.leisure.service.LeisureService;
import com.lyao.mo.business.system.bean.CurrentUser;
import com.lyao.mo.business.system.service.SystemService;
import com.lyao.mo.common.utils.CommonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 
 * @author lyao
 *
 */
@Controller
@RequestMapping("/leisure")
public class LeisureController {
	
	private final Logger log = Logger.getLogger(LeisureController.class);
	@Autowired
	private LeisureService leisureService;
	@Autowired
	private SystemService systemService;

	/**
	 * 跳转至闲暇小憩的主页
	 * @return
	 */
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView leisureHome(){
		ModelAndView md = new ModelAndView();
		md.setViewName("leisure/leisure");
		return md;
	}

	/**
	 * 跳转至我的收藏主页
	 * @return
	 */
	@RequestMapping(value = "/index_mine/authc",method = RequestMethod.GET)
	public ModelAndView myLeisureHome(){
		ModelAndView md = new ModelAndView();
		md.setViewName("leisure/leisure_mine");
		return md;
	}

	/**
	 * 分页加载
	 * @param page
	 * @param happyId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/happy/{page}/{happyId}",method = RequestMethod.GET)
	@ResponseBody
	public ModelMap leisureHome(@PathVariable String page, @PathVariable String happyId,HttpServletRequest request){
		ModelMap md = new ModelMap();
		boolean moreHappyItems = true;
		int perPageCount = 5;
        int lastHappyId = Integer.valueOf(happyId);
		if (lastHappyId == 0){
			if (CommonUtils.isOnline(request)){
				lastHappyId = CommonUtils.getCurrentUser(request).getLastHappyId();
			}
		}
		HappyPagingQuery happyPagingQuery= new  HappyPagingQuery(perPageCount, lastHappyId);
		List<HappyItem> happyItems = leisureService.queryHappyItems(happyPagingQuery);
		if (happyItems == null || happyItems.size() < perPageCount){
			moreHappyItems = false;
		}
		if (CommonUtils.isOnline(request)){
			if (happyItems == null || happyItems.size() ==0){
				lastHappyId = 0;
			}else {
				lastHappyId = Integer.valueOf(happyItems.get(happyItems.size()-1).getHappyId());
			}
			CurrentUser currentUser = CommonUtils.getCurrentUser(request);
			currentUser.setLastHappyId(lastHappyId);
			systemService.updateLastHappyIdByCustomerId(currentUser);
		}
		md.put("happyItems", happyItems);
		md.put("moreHappyItems", moreHappyItems);
		return md;
	}

	/**
	 * 分页加载我的收藏
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/happy/{page}/authc",method = RequestMethod.GET)
	@ResponseBody
	public ModelMap leisureHomeOfMine(@PathVariable String page,HttpServletRequest request){
		ModelMap md = new ModelMap();
		boolean moreHappyItems = true;
		int perPageCount = 5;
		CurrentUser currentUser = CommonUtils.getCurrentUser(request);
		HappyItem happyItem = new HappyItem((Integer.valueOf(page) - 1) * perPageCount, perPageCount, currentUser.getId());
		List<HappyItem> happyItems = leisureService.queryHappyItemsByCustomerId(happyItem);
		if (happyItems != null && happyItems.size() < perPageCount){
			moreHappyItems = false;
		}
		md.put("happyItems", happyItems);
		md.put("moreHappyItems", moreHappyItems);
		return md;
	}

	/**
	 * 收藏
	 * @param happyId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/collection/authc", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap collectHappy(@RequestParam String happyId, HttpServletRequest request){
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = "no";
		CurrentUser currentUser = CommonUtils.getCurrentUser(request);
		List<T_happy_collect> happy_collects = leisureService.queryHappyCollectsByHappyId(Integer.valueOf(happyId),currentUser.getId());
		if (happy_collects != null && happy_collects.size() > 0){
			flag = "yes";
			errorMsg = "已收藏";
		}else {
			boolean collectFlag = leisureService.addHappyCollect(Integer.valueOf(happyId), currentUser.getId());
			if (collectFlag) {
				flag = "yes";
				errorMsg = "收藏成功";
			}
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		return md;
	}

	/**
	 * 取消收藏
	 * @param happyId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/collection/authc", method = RequestMethod.PUT)
	@ResponseBody
	public ModelMap cancelCollectHappy(@RequestParam String happyId, HttpServletRequest request){
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = "no";
		CurrentUser currentUser = CommonUtils.getCurrentUser(request);
		List<T_happy_collect> happy_collects = leisureService.queryHappyCollectsByHappyId(Integer.valueOf(happyId),currentUser.getId());
		if (happy_collects == null || happy_collects.size() == 0){
			flag = "yes";
			errorMsg = "已取消";
		}else {
			boolean collectFlag = leisureService.cancelHappyCollect(happyId, currentUser.getId());
			if (collectFlag) {
				flag = "yes";
				errorMsg = "取消成功";
			}
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		return md;
	}

	/**
	 * 跳转至开心果的详细页面
	 * @return
	 */
	@RequestMapping(value = "/myCollection",method = RequestMethod.GET)
	public ModelAndView myCollection(){
		ModelAndView md = new ModelAndView();
		md.setViewName("leisure/leisure_mine");
		return md;
	} 

}
