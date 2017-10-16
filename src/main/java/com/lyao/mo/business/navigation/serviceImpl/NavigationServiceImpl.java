package com.lyao.mo.business.navigation.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyao.mo.bottom.bean.po.T_navigation_folder;
import com.lyao.mo.bottom.bean.po.T_navigation_url;
import com.lyao.mo.bottom.dao.CommonBaseDao;
import com.lyao.mo.bottom.service.NavigationService;

public class NavigationServiceImpl implements NavigationService {

	private final Logger log = Logger.getLogger(NavigationServiceImpl.class);
	@Autowired
	private CommonBaseDao baseDao;

	@Override
	public Map<String, List<T_navigation_url>> selectURLByCusID(
			String customerID) throws Exception {
		Map<String, List<T_navigation_url>> urlMap = new LinkedHashMap<String, List<T_navigation_url>>();
		List<T_navigation_folder> navFolders = null;
		navFolders = (ArrayList<T_navigation_folder>) baseDao
				.selectList("navigation.selectNavigationFolder");
		for (T_navigation_folder navFolder : navFolders) {
			List<T_navigation_url> navURLS = null;
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("customerID", customerID);
			paramMap.put("navigationID", navFolder.getId());
			if (navFolder.getId().equals("1")) {
				paramMap.put("amount", "10");
				navURLS = baseDao.selectList("navigation.selectURLByHits",
						paramMap);
			} else {
				navURLS = baseDao.selectList(
						"navigation.selectURLByCUSIDANDNAVID", paramMap);
			}
			// 如果为空，则查询系统预设的地址
			if (navURLS == null) {
				navURLS = baseDao.selectList(
						"navigation.selectURLByNAVIDForSys", paramMap);
			}
			if (navURLS != null && navURLS.size() > 0) {
				urlMap.put(String.valueOf(navFolder.getId()), navURLS);
			}
		}
		return urlMap;
	}

	@Override
	public List<T_navigation_url> selectURLForNormal(String customerID,
			String navigationID) throws Exception {
		List<T_navigation_url> navURLS = null;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customerID", customerID);
		paramMap.put("navigationID", navigationID);
		navURLS = baseDao.selectList("navigation.selectURLByCUSIDANDNAVID",
				paramMap);
		// 如果为空，则查询系统预设的地址
		if (navURLS == null) {
			navURLS = baseDao.selectList("navigation.selectURLByNAVIDForSys",
					paramMap);
		}
		return navURLS;
	}

	@Override
	public boolean saveURL(T_navigation_url navURL) throws Exception {
		boolean flag = false;
		int saveFlag = baseDao.insert("navigation.saveURL", navURL);
		if (saveFlag == 1){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean updateURLByURLID(T_navigation_url navURL) throws Exception {
		boolean flag = false;
		int updateFlag = baseDao.update("navigation.updateURL", navURL);
		if (updateFlag == 1){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteURLByURLID(String id) throws Exception {
		boolean flag = false;
		int deleteFlag = baseDao.delete("navigation.deleteURL", id);
		if (deleteFlag == 1){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean updateURLForHits(String accesstime, Integer id)
			throws Exception {
		boolean flag = false;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accesstime", accesstime);
		paramMap.put("id", id);
		int updateFlag = baseDao.delete("navigation.updateURLForHits", paramMap);
		if (updateFlag == 1){
			flag = true;
		}
		return flag;
	}

}
