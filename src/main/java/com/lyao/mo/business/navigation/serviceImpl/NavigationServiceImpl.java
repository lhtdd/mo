package com.lyao.mo.business.navigation.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyao.mo.bottom.bean.po.T_navigation_folder;
import com.lyao.mo.bottom.bean.po.T_navigation_url;
import com.lyao.mo.bottom.dao.CommonBaseDao;
import com.lyao.mo.bottom.service.NavigationService;
@Service
public class NavigationServiceImpl implements NavigationService {

	private final Logger log = Logger.getLogger(NavigationServiceImpl.class);
	@Autowired
	private CommonBaseDao baseDao;

	@Override
	public Map<String, Map<String, Object>> selectURLByCusID(
			String customerID) throws Exception {
		Map<String, Map<String, Object>> returnMap = new LinkedHashMap<String, Map<String, Object>>();
		List<T_navigation_folder> navFolders = null;
		navFolders = (ArrayList<T_navigation_folder>) baseDao
				.selectList("navigation.selectNavigationFolder");
		for (T_navigation_folder navFolder : navFolders) {
			Map<String, Object> urlMap = new LinkedHashMap<String,  Object>();
			urlMap.put("folderID", navFolder.getId());
			urlMap.put("folderIcon", navFolder.getNavigationicon());
			urlMap.put("folderName", navFolder.getNavigationname());
			List<T_navigation_url> navURLS = new ArrayList<T_navigation_url>();
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("navigationID", navFolder.getId());
			if (StringUtils.isNotBlank(customerID)){
				paramMap.put("customerID", customerID);
				if (navFolder.getId() == 1 ) {
					paramMap.put("amount", 10);
					navURLS = baseDao.selectList("navigation.selectURLByHits",
							paramMap);
				} else {
					navURLS = baseDao.selectList(
							"navigation.selectURLByCUSIDANDNAVID", paramMap);
				}
			}
			// 如果为空，则查询系统预设的地址
			if (navURLS == null || navURLS.size() == 0) {
				navURLS = baseDao.selectList(
						"navigation.selectURLByNAVIDForSys", paramMap);
			}
			if (navURLS != null && navURLS.size() > 0) {
				urlMap.put("URLS", navURLS);
			}
			returnMap.put(navFolder.getNavigationname(), urlMap);
		}
		return returnMap;
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
	public List<T_navigation_folder> selectNavigationFolder() throws Exception {
		List<T_navigation_folder> navFolders = null;
		navFolders = (ArrayList<T_navigation_folder>) baseDao
				.selectList("navigation.selectNavigationFolder");
		return navFolders;
	}
	
	@Override
	public boolean insertURL(String urlname, String url, String navid, String customerid) throws Exception {
		boolean flag = false;
		T_navigation_url navURL = new T_navigation_url();
		navURL.setCustomerid(customerid);
		navURL.setNavigationid(Integer.valueOf(navid));
		navURL.setUrlimage("");
		navURL.setUrlname(urlname);
		navURL.setUrl(url);
		navURL.setType(2);
		navURL.setRemark("");
		DateTime dt1 = new DateTime();
		navURL.setIntime(dt1.toString("yyyy-MM-dd HH:mm:ss"));
		int saveFlag = baseDao.insert("navigation.saveURL", navURL);
		if (saveFlag == 1){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean updateURLByURLID(String urlname, String url, String navid, Integer urlid) throws Exception {
		boolean flag = false;
		T_navigation_url navURL = new T_navigation_url();
		navURL.setId(urlid);
		navURL.setNavigationid(Integer.valueOf(navid));
		navURL.setUrlname(urlname);
		navURL.setUrl(url);
		DateTime dt1 = new DateTime();
		navURL.setUpdatetime(dt1.toString("yyyy-MM-dd HH:mm:ss"));
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
