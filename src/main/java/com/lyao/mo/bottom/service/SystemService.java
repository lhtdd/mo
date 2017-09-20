package com.lyao.mo.bottom.service;

import com.lyao.mo.bottom.pojo.RegisterInfo;

public interface SystemService {
	/**
	 * 注册用户
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	int insertMember(RegisterInfo customer) throws Exception;
}
