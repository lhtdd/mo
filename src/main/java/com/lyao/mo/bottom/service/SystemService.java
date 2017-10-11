package com.lyao.mo.bottom.service;

import java.util.HashMap;

import com.lyao.mo.business.system.bo.CurrentUser;
import com.lyao.mo.business.system.bo.RegisterInfo;


public interface SystemService {
	/**
	 * 执行登录流程
	 * @param curuser
	 * @return
	 * @throws Exception
	 */
	HashMap<String, Object> doLogin(String username, String password) throws Exception;
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 * @throws Exception
	 */
	CurrentUser selectUserByUsername(String username) throws Exception;
	/**
	 * 根据用户ID查询用户
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	CurrentUser selectUserByID(String customerID) throws Exception;
	/**
	 * 根据昵称查询ID,以判断该昵称是否存在，用户注册时使用
	 * @param alias
	 * @return
	 * @throws Exception
	 */
	String selectAlias(String alias) throws Exception;
	/**
	 * 注册用户--手机
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	boolean insertMemberByMobile(RegisterInfo customer) throws Exception;
	/**
	 * 注册用户--邮箱
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	boolean insertMemberByEmail(RegisterInfo customer) throws Exception;
	/**
	 * 激活账号
	 * @param customerID
	 * @param validCode
	 * @return
	 * @throws Exception
	 */
	boolean updateActivation(String customerID, String validCode) throws Exception;
}
