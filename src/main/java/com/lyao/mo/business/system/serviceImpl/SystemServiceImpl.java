package com.lyao.mo.business.system.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyao.mo.bottom.dao.CommonBaseDao;
import com.lyao.mo.bottom.pojo.RegisterInfo;
import com.lyao.mo.bottom.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService{

	@Autowired
	private CommonBaseDao baseDao;
	
	@Override
	public int insertMember(RegisterInfo customer) throws Exception {
		int i = 0;
		int j = 0;
		i = baseDao.insert("system.insertIntoCustomer", customer);
		j = baseDao.insert("system.insertIntoCustomerDetail", customer);
		return i+j;
	}
	
}
