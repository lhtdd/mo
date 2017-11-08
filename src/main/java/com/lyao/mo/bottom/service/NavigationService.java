package com.lyao.mo.bottom.service;

import java.util.List;
import java.util.Map;

import com.lyao.mo.bottom.bean.po.T_navigation_folder;
import com.lyao.mo.bottom.bean.po.T_navigation_url;



public interface NavigationService {
	/**
	 * 通过当前用户的ID查询其下的所有URL
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	Map<String, Map<String, Object>> selectURLByCusID(String customerID) throws Exception;
	
	/**
	 * 根据用户ID及某个导航夹ID查询其下的URL,同时查询常用导航夹下的URL
	 * @param customerID
	 * @param navigationID
	 * @return
	 * @throws Exception
	 */
	Map<String, List<T_navigation_url>> selectURLForNormal(String customerID, String navigationID) throws Exception;
	
	/**
	 * 查询系统预设的几个导航夹
	 * @return
	 * @throws Exception
	 */
	List<T_navigation_folder> selectNavigationFolder() throws Exception;
	
	/**
	 * 根据urlid获取其icon
	 * @param urlID
	 * @return
	 * @throws Exception
	 */
	byte[] selectIcon(String urlID) throws Exception;
	
	/**
	 * 新增一个URL
	 * @param navURL
	 * @return
	 * @throws Exception
	 */
	boolean insertURL(String urlname, String url, String navid, String customerid) throws Exception;
	
	/**
	 * 修改一个URL信息
	 * @param navURL
	 * @return
	 * @throws Exception
	 */
	boolean updateURLByURLID(String urlname, String url, String navid, Integer urlid) throws Exception;
	
	/**
	 * 更新某条记录的访问时间及点击次数
	 * @param accesstime
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean updateURLForHits(String accesstime, Integer id) throws Exception;
	
	/**
	 * 删除一个URL
	 * @param urlid
	 * @return
	 * @throws Exception
	 */
	boolean deleteURLByURLID(String urlid) throws Exception;
	
}
