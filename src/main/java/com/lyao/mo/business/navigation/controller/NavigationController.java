package com.lyao.mo.business.navigation.controller;

import com.lyao.mo.business.navigation.bean.po.T_navigation_folder;
import com.lyao.mo.business.navigation.bean.po.T_navigation_url;
import com.lyao.mo.business.navigation.service.NavigationService;
import com.lyao.mo.business.system.bean.CurrentUser;
import com.lyao.mo.common.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 页面导航功能
 * @author lyao
 *
 */
@Controller
@RequestMapping("/navigation")
public class NavigationController {
	private final Logger log = Logger.getLogger(NavigationController.class);
	@Autowired
	private NavigationService navigationServiceImpl;

	/**
	 * 页面加载后，自动查询收藏的记录
	 * 
	 * @param customerID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/url", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap selectURL(
			@RequestParam(value = "cus", required = false) String customerID,
			HttpServletRequest request) {
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag;
		// 获取当前客户ID
		if (StringUtils.isBlank(customerID)) {
			CurrentUser curuser = CommonUtils.getCurrentUser(request);
			if (curuser != null) {
				customerID = curuser.getId();
			}
		}
		// 获取URL
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
		md.put("navFolder", returnMap);
		return md;
	}

	/**
	 * 获取系统预设的导航收藏夹
	 * 
	 * @return
	 */
	@RequestMapping(value = "/folderName", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap selectNavigationFolder() {
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag;
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
		md.put("navFolders", navFolders);
		return md;
	}

	/**
	 * 刷新常用url
	 * 
	 * @param navid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/commonurl/{navid}/authc", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap selectCommonUrl(@PathVariable String navid,
			HttpServletRequest request) {
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag;
		CurrentUser curuser = CommonUtils.getCurrentUser(request);
		Map<String, List<T_navigation_url>> newURLS = null;
		try {
			newURLS = navigationServiceImpl.selectURLForNormal(curuser.getId(),
					navid);
			flag = "yes";
		} catch (Exception e) {
			log.error("刷新常用url失败", e);
			flag = "no";
			errorMsg = "刷新常用url失败";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		md.put("newURLS", newURLS);
		return md;
	}

	@RequestMapping("/urlIcon/{urlID}")
	public String selectIcon(@PathVariable String urlID,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		response.setContentType("image/jpeg");
		try {
			byte[] urlIcon = navigationServiceImpl.selectIcon(urlID);
			InputStream fis = new ByteArrayInputStream(urlIcon);
			OutputStream os = response.getOutputStream();
			try {
				int count = 0;
				byte[] buffer = new byte[1024 * 1024];
				while ((count = fis.read(buffer)) != -1) {
					os.write(buffer, 0, count);
				}
				os.flush();
			} catch (IOException e) {
				log.error("将图片信息输出到客户端页面", e);
			} finally {
				if (os != null) {
					os.close();
				}
				if (fis != null){
					fis.close();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增url
	 * 
	 * @return
	 */
	@RequestMapping(value = "/urloperation/authc", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap urlOperation(@RequestParam String urlName,
			@RequestParam String webLocation,
			@RequestParam String targetFolder, HttpServletRequest request) {
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag;
		CurrentUser curuser = CommonUtils.getCurrentUser(request);
		boolean addFlag;
		try {
			addFlag = navigationServiceImpl.insertURL(urlName, webLocation,
					targetFolder, curuser.getId());
			if (addFlag) {
				flag = "yes";
			} else {
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
	 * 
	 * @param urlName
	 * @param webLocation
	 * @param targetFolder
	 * @param urlid
	 * @return
	 */
	@RequestMapping(value = "/urloperation/authc", method = RequestMethod.PUT)
	@ResponseBody
	public ModelMap urlOperation(@RequestParam String urlName,
			@RequestParam String webLocation,
			@RequestParam String targetFolder, @RequestParam String urlid) {
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag;
		boolean addFlag;
		try {
			addFlag = navigationServiceImpl.updateURLByURLID(urlName,
					webLocation, targetFolder, Integer.valueOf(urlid));
			if (addFlag) {
				flag = "yes";
			} else {
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
	 * 
	 * @param urlid
	 * @return
	 */
	@RequestMapping(value = "/urloperation/authc", method = RequestMethod.DELETE)
	@ResponseBody
	public ModelMap urlOperation(@RequestParam String urlid) {
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag;
		boolean deleteFlag;
		try {
			deleteFlag = navigationServiceImpl.deleteURLByURLID(urlid);
			if (deleteFlag) {
				flag = "yes";
			} else {
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
	 * 
	 * @param urlid
	 * @return
	 */
	@RequestMapping(value = "/visiturl/{urlid}", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap updateURLForHits(@PathVariable String urlid) {
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag;
		boolean updateFlag;
		try {
			DateTime dt1 = new DateTime();
			updateFlag = navigationServiceImpl
					.updateURLForHits(dt1.toString("yyyy-MM-dd HH:mm:ss"),
							Integer.valueOf(urlid));
			if (updateFlag) {
				flag = "yes";
			} else {
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
