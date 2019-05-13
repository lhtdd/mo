package com.lyao.mo.business.notepad.controller;

import com.lyao.mo.business.notepad.bean.po.T_notepad;
import com.lyao.mo.business.notepad.service.NotepadService;
import com.lyao.mo.business.system.bean.CurrentUser;
import com.lyao.mo.common.utils.CommonUtils;
import com.lyao.mo.common.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 
 * @author lyao
 *
 */
@Controller
@RequestMapping("/notepad")
public class NotepadController {
	
	private final Logger log = Logger.getLogger(NotepadController.class);
	@Autowired
	public NotepadService notepadService;

	/**
	 * 记事本主页
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index",method = RequestMethod.GET)  
	public ModelAndView notepadHome(HttpServletRequest request, HttpServletResponse response){
		ModelAndView md = new ModelAndView();
		CurrentUser currentUser = CommonUtils.getCurrentUser(request);
		List<T_notepad> notepads = null;
		if (currentUser != null){
			notepads = notepadService.selectNotepads(currentUser.getId());
		}
		md.addObject("notepads", notepads);
		if (notepads != null && notepads.size() > 0){
			md.addObject("notepadFirst", notepads.get(0));
		}
		md.setViewName("notepad/notepad");
		return md;
	}

	/**
	 * 新增便签
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add/authc", method = RequestMethod.PUT)
	@ResponseBody
	public ModelMap addNotepad(HttpServletRequest request){
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = Constant.BUSINESS_DEAL_FAIL;
		T_notepad notepad = notepadService.addNotepad(CommonUtils.getCurrentUser(request).getId());
		if (notepad != null){
			flag = Constant.BUSINESS_DEAL_SUCCESS;
		}else {
			errorMsg = "新增失败";
		}
		md.put("flag", flag);
		md.put("notepad", notepad);
		md.put("errorMsg", errorMsg);
		return md;
	}

	/**
	 * 保存便签的修改
	 * @param notepad
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/content/authc", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap dealNotepad(@ModelAttribute T_notepad notepad, HttpServletRequest request){
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = "no";
		Integer notepadId = 0;
		if (StringUtils.isEmpty(notepad.getId())){
			CurrentUser curuser = CommonUtils.getCurrentUser(request);
			notepad.setCustomerId(curuser.getId());
			notepadId = notepadService.addNotepad(notepad);
			if (notepadId > 0){
				flag = "yes";
			}
		}else {
			boolean updateFlag = notepadService.updateNameOrContent(notepad.getId(), notepad.getNotepadName(), notepad.getContent());
			if (updateFlag) {
				flag = "yes";
				notepadId = Integer.valueOf(notepad.getId());
			}
		}
		if (flag.equals("no")){
			log.error("更新notepad失败");
			errorMsg = "更新失败";
		}else {
			T_notepad notepad1 = notepadService.selectNotepadById(notepadId.toString());
			md.put("notepad", notepad1);
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		return md;
	}

	/**
	 * 点击单个便签进行查询，返回其内容
	 * @param notepadId
	 * @return
	 */
	@RequestMapping(value = "/{notepadId}/authc", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap getNotepad(@PathVariable String notepadId){
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = "no";
		T_notepad notepad = notepadService.selectNotepadById(notepadId);
		if (notepad != null){
			flag = "yes";
		}else {
			errorMsg = "加载失败";
		}
		md.put("flag", flag);
		md.put("notepad", notepad);
		md.put("errorMsg", errorMsg);
		return md;
	}

	/**
	 * 删除某个便签
	 * @param notepadId
	 * @return
	 */
	@RequestMapping(value = "/{notepadId}/authc", method = RequestMethod.DELETE)
	@ResponseBody
	public ModelMap deleteNotepad(@PathVariable String notepadId){
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = Constant.BUSINESS_DEAL_FAIL;
		boolean delFlag = notepadService.deleteNotePadById(notepadId);
		if (delFlag){
			flag = Constant.BUSINESS_DEAL_SUCCESS;
		}else {
			errorMsg = "删除失败";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		return md;
	}

	/**
	 * 将某个便签置顶
	 * @param notepadId
	 * @return
	 */
	@RequestMapping(value = "/{notepadId}/authc", method = RequestMethod.PUT)
	@ResponseBody
	public ModelMap topNotepad(@PathVariable String notepadId, HttpServletRequest request){
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = Constant.BUSINESS_DEAL_FAIL;
		CurrentUser curuser = CommonUtils.getCurrentUser(request);
		boolean topFlag = notepadService.updateNotepadTops(curuser.getId(), notepadId);
		if (topFlag){
			flag = Constant.BUSINESS_DEAL_SUCCESS;
		}else {
			errorMsg = "置顶失败";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		return md;
	}
}
