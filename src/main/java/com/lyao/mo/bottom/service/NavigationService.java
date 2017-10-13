package com.lyao.mo.bottom.service;

import java.util.List;
import java.util.Map;

import com.lyao.mo.bottom.bean.po.T_navigation_url;



public interface NavigationService {
	/**
	 * 通过当前用户的ID查询其下的所有URL
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	Map<String,List<T_navigation_url>> selectURLByCusID(String customerID) throws Exception;
	
	/**
	 * 更具用户ID及某个导航夹ID查询其下的URL
	 * @param customerID
	 * @param navigationID
	 * @return
	 * @throws Exception
	 */
	List<T_navigation_url> selectURLForNormal(String customerID, String navigationID) throws Exception;
	
	/**
	 * 新增一个URL
	 * @param navURL
	 * @return
	 * @throws Exception
	 */
	boolean saveURL(T_navigation_url navURL) throws Exception;
	
	/**
	 * 修改一个URL信息
	 * @param navURL
	 * @return
	 * @throws Exception
	 */
	boolean updateURLByURLID(T_navigation_url navURL) throws Exception;
	
	/**
	 * 删除一个URL
	 * @param urlid
	 * @return
	 * @throws Exception
	 */
	boolean deleteURLByURLID(String urlid) throws Exception;
	
}