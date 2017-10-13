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
		navFolders = (ArrayList<T_navigation_folder>) baseDao.selectList("navigation.selectNavigationFolder");
		for (T_navigation_folder navFolder : navFolders){
			List<T_navigation_url> navURLS = null;
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("customerID", customerID);
			if (navFolder.getId().equals("1")){
				paramMap.put("amount", "10");
				navURLS = baseDao.selectList("navigation.selectURLByHits", paramMap);
			}else {
				paramMap.put("navigationID", navFolder.getId());
				navURLS = baseDao.selectList("navigation.selectURLByCUSIDANDNAVID", paramMap);
			}
			if (navURLS != null && navURLS.size() > 0){
				urlMap.put(String.valueOf(navFolder.getId()), navURLS);
			}
		}
		return urlMap;
	}

	@Override
	public List<T_navigation_url> selectURLForNormal(String customerID,
			String navigationID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveURL(T_navigation_url navURL) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateURLByURLID(T_navigation_url navURL) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteURLByURLID(String urlid) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
