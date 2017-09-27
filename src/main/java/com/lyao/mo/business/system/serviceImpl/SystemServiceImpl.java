package com.lyao.mo.business.system.serviceImpl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyao.mo.bottom.bean.pojo.ValidCodeEmail;
import com.lyao.mo.bottom.dao.CommonBaseDao;
import com.lyao.mo.bottom.service.SystemService;
import com.lyao.mo.business.system.bo.RegisterInfo;
import com.lyao.mo.common.utils.GenerateID;
import com.lyao.mo.common.utils.MD5Utils;
import com.lyao.mo.common.utils.RandomUtils;
import com.lyao.mo.common.utils.Sendmail;

@Service
public class SystemServiceImpl implements SystemService{

	@Autowired
	private CommonBaseDao baseDao;
	
	@Override
	public boolean insertMemberByMobile(RegisterInfo customer) throws Exception {
		boolean flag = false;
		int i = 0;
		int j = 0;
		customer.setId(GenerateID.generateCustomerID(customer.getSex()));
		DateTime dt1 = new DateTime();
		customer.setIntime(dt1.toString("yyyy-MM-dd HH:mm:ss"));
		customer.setUpdateTime(dt1.toString("yyyy-MM-dd HH:mm:ss"));
		customer.setPassword(MD5Utils.encryptPassword(customer.getPassword(), customer.getId()));
		i = baseDao.insert("system.insertIntoCustomer", customer);
		j = baseDao.insert("system.insertIntoCustomerDetail", customer);
		if (i+j == 2){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean insertMemberByEmail(RegisterInfo customer) throws Exception {
		boolean flag = false;
		int i = 0;
		int j = 0;
		customer.setId(GenerateID.generateCustomerID(customer.getSex()));
		customer.setPassword(MD5Utils.encryptPassword(customer.getPassword(), customer.getId()));
		DateTime dt1 = new DateTime();
		customer.setIntime(dt1.toString("yyyy-MM-dd HH:mm:ss"));
		customer.setUpdateTime(dt1.toString("yyyy-MM-dd HH:mm:ss"));
		//生成验证码
		String validCode = RandomUtils.generateString(8);
		customer.setValidCode(validCode);
		//设置用户状态为未激活，默认为正常。
		customer.setStatus(1);
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
			flag = true;
		}
		return flag;
	}
	
}
