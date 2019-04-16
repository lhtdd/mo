package com.lyao.mo.business.notepad.controller;

import com.lyao.mo.business.notepad.bean.po.T_notepad;
import com.lyao.mo.business.notepad.service.NotepadService;
import com.lyao.mo.business.system.bean.CurrentUser;
import com.lyao.mo.common.utils.CommonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		md.setViewName("notepad/notepad");
		return md;
	}   
}
