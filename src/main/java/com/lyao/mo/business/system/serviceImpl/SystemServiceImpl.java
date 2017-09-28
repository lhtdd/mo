package com.lyao.mo.business.system.serviceImpl;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyao.mo.bottom.bean.pojo.ValidCodeEmail;
import com.lyao.mo.bottom.dao.CommonBaseDao;
import com.lyao.mo.bottom.service.SystemService;
import com.lyao.mo.business.system.bo.CurrentUser;
import com.lyao.mo.business.system.bo.RegisterInfo;
import com.lyao.mo.common.utils.GenerateID;
import com.lyao.mo.common.utils.MD5Utils;
import com.lyao.mo.common.utils.RandomUtils;
import com.lyao.mo.common.utils.Sendmail;

@Service
public class SystemServiceImpl implements SystemService{

	private final Logger log = Logger.getLogger(SystemServiceImpl.class);
	@Autowired
	private CommonBaseDao baseDao;
	
	@Override
	public HashMap<String, Object> doLogin(String username, String password)
			throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String flag = null;
		String errorMsg = null;
		CurrentUser seluser = null;
		seluser = baseDao.selectOne("system.selectUserByName", username);
		if (seluser == null){
			flag = "0";
			errorMsg = "用户名或密码错误";
			log.warn("用户名:" + username + " 不存在");
		}else{
			String curPassword = MD5Utils.encryptPassword(password, username);
			if (!seluser.getPassword().equals(curPassword)){
				flag = "0";
				errorMsg = "用户名或密码错误";
				log.warn("用户名:" + username + " 登录密码错误");
			}else{
				flag = "1";
				resultMap.put("seluser", seluser);
			}
		}
		resultMap.put("flag", flag);
		resultMap.put("errorMsg", errorMsg);
		return resultMap;
	}
	
	@Override
	public CurrentUser selectUserByUsername(String username) throws Exception {
		CurrentUser seluser = null;
		seluser = baseDao.selectOne("system.selectUserByName", username);
		return seluser;
	}
	@Override
	public CurrentUser selectUserByID(String customerID) throws Exception {
		CurrentUser seluser = null;
		seluser = baseDao.selectOne("system.selectUserByID", customerID);
		return seluser;
	}
	
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

	@Override
	public boolean doActivation(String customerID, String validCode)
			throws Exception {
		boolean flag = false;
		String selVaildCode = null;
		selVaildCode = baseDao.selectOne("system.selectValidCode", customerID);
		if (StringUtils.isNotBlank(selVaildCode)){
			if (selVaildCode.equals(validCode)){
				int i = 0;
				HashMap<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("customerID", customerID);
				DateTime dt1 = new DateTime();
				paramMap.put("activationTime", dt1.toString("yyyy-MM-dd HH:mm:ss"));
				i = baseDao.update("system.activation", paramMap);
				if (i == 1){
					flag = true;
					log.warn("激活成功");
				}else {
					log.warn("激活失败");
				}
				
			}else {
				log.warn("用户：" + customerID + "激活码不正确");
			}
		}else{
			log.warn("不存在用户：" + customerID);
		}
		return flag;
	}

}
