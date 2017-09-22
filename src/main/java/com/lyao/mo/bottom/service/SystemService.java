package com.lyao.mo.bottom.service;

import com.lyao.mo.bottom.pojo.RegisterInfo;

public interface SystemService {
	/**
	 * 注册用户--手机
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	int insertMemberByMobile(RegisterInfo customer) throws Exception;
	/**
	 * 注册用户--邮箱
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	int insertMemberByEmail(RegisterInfo customer) throws Exception;
}
