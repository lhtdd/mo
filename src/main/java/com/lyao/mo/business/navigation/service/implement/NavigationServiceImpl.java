package com.lyao.mo.business.navigation.service.implement;

import com.lyao.mo.business.navigation.bean.po.T_navigation_folder;
import com.lyao.mo.business.navigation.bean.po.T_navigation_url;
import com.lyao.mo.bottom.dao.CommonBaseDao;
import com.lyao.mo.business.navigation.service.NavigationService;
import com.lyao.mo.common.utils.HttpConUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * 
 * @author lyao
 *
 */
@Service
public class NavigationServiceImpl implements NavigationService {

	private final Logger log = Logger.getLogger(NavigationServiceImpl.class);
	@Autowired
	private CommonBaseDao baseDao;

	@Override
	public Map<String, Map<String, Object>> selectURLByCusID(
			String customerID) throws Exception {
		Map<String, Map<String, Object>> returnMap = new LinkedHashMap<String, Map<String, Object>>();
		List<T_navigation_folder> navFolders = (ArrayList<T_navigation_folder>) baseDao
				.selectList("navigation.selectNavigationFolder");
		for (T_navigation_folder navFolder : navFolders) {
			Map<String, Object> urlMap = new LinkedHashMap<String,  Object>(6);
			urlMap.put("folderID", navFolder.getId());
			urlMap.put("folderIcon", navFolder.getNavigationIcon());
			urlMap.put("folderName", navFolder.getNavigationName());
			List<T_navigation_url> navURLS = new ArrayList<T_navigation_url>();
			HashMap<String, Object> paramMap = new HashMap<String, Object>(4);
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
			returnMap.put(navFolder.getNavigationName(), urlMap);
		}
		return returnMap;
	}

	@Override
	public Map<String, List<T_navigation_url>> selectURLForNormal(String customerID,
			String navigationID) throws Exception {
		Map<String, List<T_navigation_url>> returnMap = new HashMap<String, List<T_navigation_url>>(3);
		List<T_navigation_url> commonURLs = null;
		List<T_navigation_url> targetNavURLs = null;
		HashMap<String, Object> paramMap = new HashMap<String, Object>(4);
		paramMap.put("customerID", customerID);
		paramMap.put("amount", 10);
		commonURLs = baseDao.selectList("navigation.selectURLByHits",
				paramMap);
		if (commonURLs == null || commonURLs.size() == 0) {
			//查常用导航夹下的系统预设URL
			paramMap.put("navigationID", "1");
			commonURLs = baseDao.selectList("navigation.selectURLByNAVIDForSys",
					paramMap);
		}
		//如果当前要查询的不是常用文件夹则需要再查询一下该文件夹
		if (!"1".equals(navigationID)){
			paramMap.put("navigationID", navigationID);
			targetNavURLs = baseDao.selectList("navigation.selectURLByCUSIDANDNAVID",
					paramMap);
			if (targetNavURLs == null || targetNavURLs.size() == 0) {
				targetNavURLs = baseDao.selectList("navigation.selectURLByNAVIDForSys",
						paramMap);
			}
		}
		
		if (commonURLs != null && commonURLs.size() > 0){
			returnMap.put("commonURLs", commonURLs);
		}
		if (targetNavURLs != null && targetNavURLs.size() > 0){
			returnMap.put("targetNavURLs", targetNavURLs);
		}
		// 如果为空，则查询系统预设的地址
		return returnMap;
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
		navURL.setCustomerId(customerid);
		navURL.setNavigationId(Integer.valueOf(navid));
		byte[] urlImage = HttpConUtils.getImageFromNetByUrl(url);
		navURL.setUrlImage(urlImage);
		navURL.setUrlName(urlname);
		navURL.setUrl(url);
		navURL.setType(2);
		navURL.setRemark("");
		DateTime dt1 = new DateTime();
		navURL.setInTime(dt1.toString("yyyy-MM-dd HH:mm:ss"));
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
		navURL.setNavigationId(Integer.valueOf(navid));
		navURL.setUrlName(urlname);
		navURL.setUrl(url);
		DateTime dt1 = new DateTime();
		navURL.setUpdateTime(dt1.toString("yyyy-MM-dd HH:mm:ss"));
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
		HashMap<String, Object> paramMap = new HashMap<String, Object>(3);
		paramMap.put("accesstime", accesstime);
		paramMap.put("id", id);
		int updateFlag = baseDao.delete("navigation.updateURLForHits", paramMap);
		if (updateFlag == 1){
			flag = true;
		}
		return flag;
	}

	@Override
	public byte[] selectIcon(String urlID) throws Exception {
		byte[] urlIcon = null;
		Map<String, Object> returnMap = baseDao.selectOne("navigation.selectURLIconByID", Integer.valueOf(urlID));
		urlIcon = (byte[]) returnMap.get("imgBytes");
		return urlIcon;
	}

}
