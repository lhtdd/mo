package com.lyao.mo.business.system.serviceImpl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyao.mo.bottom.dao.CommonBaseDao;
import com.lyao.mo.bottom.pojo.RegisterInfo;
import com.lyao.mo.bottom.pojo.email.ValidCodeEmail;
import com.lyao.mo.bottom.service.SystemService;
import com.lyao.mo.common.utils.GenerateID;
import com.lyao.mo.common.utils.RandomUtils;
import com.lyao.mo.common.utils.Sendmail;

@Service
public class SystemServiceImpl implements SystemService{

	@Autowired
	private CommonBaseDao baseDao;
	
	@Override
	public int insertMemberByMobile(RegisterInfo customer) throws Exception {
		int i = 0;
		int j = 0;
		customer.setId(GenerateID.generateCustomerID(customer.getSex()));
		DateTime dt1 = new DateTime();
		customer.setIntime(dt1.toString("yyyy-MM-dd HH:mm:ss"));
		customer.setUpdateTime(dt1.toString("yyyy-MM-dd HH:mm:ss"));
		i = baseDao.insert("system.insertIntoCustomer", customer);
		j = baseDao.insert("system.insertIntoCustomerDetail", customer);
		return i+j;
	}

	@Override
	public int insertMemberByEmail(RegisterInfo customer) throws Exception {
		int i = 0;
		int j = 0;
		customer.setId(GenerateID.generateCustomerID(customer.getSex()));
		DateTime dt1 = new DateTime();
		customer.setIntime(dt1.toString("yyyy-MM-dd HH:mm:ss"));
		customer.setUpdateTime(dt1.toString("yyyy-MM-dd HH:mm:ss"));
		//生成验证码
		String validCode = RandomUtils.generateString(8);
		customer.setValidCode(validCode);
		i = baseDao.insert("system.insertIntoCustomer", customer);
		j = baseDao.insert("system.insertIntoCustomerDetail", customer);
		//发送激活邮件
		if(i+j == 2){
			ValidCodeEmail validCodeEmail = new ValidCodeEmail();
			validCodeEmail.setUserName(customer.getUsername());
			validCodeEmail.setReceiver(customer.getUsername());
			validCodeEmail.setValidURL("http://127.0.0.1:8082/mo/member/activation/"+validCode+"/"+customer.getId());
			Sendmail sendEmail = new Sendmail(validCodeEmail);
			sendEmail.start();
		}
		return i+j;
	}
	
}
