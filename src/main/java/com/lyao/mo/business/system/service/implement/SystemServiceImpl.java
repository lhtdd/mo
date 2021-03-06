package com.lyao.mo.business.system.service.implement;

import com.lyao.mo.bottom.bean.bo.ValidCodeEmail;
import com.lyao.mo.bottom.dao.CommonBaseDao;
import com.lyao.mo.business.system.bean.CurrentUser;
import com.lyao.mo.business.system.bean.RegisterInfo;
import com.lyao.mo.business.system.service.SystemService;
import com.lyao.mo.common.utils.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * @author lyao
 * @date
 */
@Service
public class SystemServiceImpl implements SystemService{

	private final Logger log = Logger.getLogger(SystemServiceImpl.class);
	@Autowired
	private CommonBaseDao baseDao;
	
	@Override
	public HashMap<String, Object> doLogin(String username, String password)
			throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>(4);
		String flag = null;
		String errorMsg = null;
		CurrentUser seluser;
		seluser = baseDao.selectOne("system.selectUserByName", username);
		if (seluser == null){
			flag = "0";
			errorMsg = "用户名或密码错误";
			log.warn("用户名:" + username + " 不存在");
		}else{
			if (2 == seluser.getStatus()){
				String curPassword = MD5Utils.encryptPassword(password, username);
				if (!seluser.getPassword().equals(curPassword)){
					flag = "0";
					errorMsg = "用户名或密码错误";
					log.warn("用户名:" + username + " 登录密码错误");
				}else{
					flag = "1";
					resultMap.put("seluser", seluser);
				}
			}else if (3 == seluser.getStatus()){
				flag = "0";
				errorMsg = "该用户被禁止登陆";
				log.warn("用户名:" + username + " 被禁止登陆");
			}else if (seluser.getStatus() == 1){
				flag = "0";
				errorMsg = "该用户尚未激活";
				log.warn("用户名:" + username + " 尚未激活");
			}
		}
		resultMap.put("flag", flag);
		resultMap.put("errorMsg", errorMsg);
		return resultMap;
	}
	
	@Override
	public CurrentUser selectUserByUsername(String username) throws Exception {
		CurrentUser seluser;
		seluser = baseDao.selectOne("system.selectUserByName", username);
		return seluser;
	}
	
	@Override
	public CurrentUser selectUserByID(String customerID) throws Exception {
		CurrentUser seluser;
		seluser = baseDao.selectOne("system.selectUserByID", customerID);
		return seluser;
	}
	
	@Override
	public String selectAlias(String alias) throws Exception {
		String id;
		id = baseDao.selectOne("system.selectAlias", alias);
		return id;
	}
	
	@Override
	public boolean insertMemberByMobile(RegisterInfo customer) throws Exception {
		boolean flag = false;
		int i;
		customer.setId(GenerateID.generateCustomerID(null));
		String currentDate = DateUtils.dateTimeToDateString(new Date());
		customer.setInTime(currentDate);
		customer.setUpdateTime(currentDate);
		customer.setPassword(MD5Utils.encryptPassword(customer.getPassword(), customer.getUsername()));
		i = baseDao.insert("system.insertIntoCustomer", customer);
		if (1 == i){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean insertMemberByEmail(RegisterInfo customer) throws Exception {
		boolean flag = false;
		int i;
		customer.setId(GenerateID.generateCustomerID(customer.getSex()));
		customer.setPassword(MD5Utils.encryptPassword(customer.getPassword(), customer.getUsername()));
		String currentDate = DateUtils.dateTimeToDateString(new Date());
		customer.setInTime(currentDate);
		customer.setUpdateTime(currentDate);
		//生成验证码
		String validCode = RandomUtils.generateString(8);
		customer.setValidCode(validCode);
		//设置用户状态为未激活，默认为正常。
		customer.setStatus(1);
		i = baseDao.insert("system.insertIntoCustomer", customer);
		//发送激活邮件
		if(1 == i){
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
	public boolean updateActivation(String customerID, String validCode)
			throws Exception {
		boolean flag = false;
		HashMap<String, Object> resultMap = new HashMap<String, Object>(4);
		String selVaildCode;
		Integer status;
		resultMap = baseDao.selectOne("system.selectValidCode", customerID);
		status = MapUtils.getInteger(resultMap, "status");
		selVaildCode = MapUtils.getString(resultMap, "validCode");
		// 判断当前用户状态，待激活状态则激活，否则不予激活，也可以再加上激活时间，超出时间不予激活
		if (status != null && status == 1){
			if (StringUtils.isNotBlank(selVaildCode)){
				if (selVaildCode.equals(validCode)){
					int i = 0;
					HashMap<String, String> paramMap = new HashMap<String, String>(3);
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
		}else{
			log.warn("当前用户" + customerID + "状态：" + status);
		}
		return flag;
	}

	@Override
	public boolean updateLastHappyIdByCustomerId(CurrentUser currentUser) {
		int i = baseDao.update("system.updateLastHappyIdByCustomerId", currentUser);
		if (i > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePasswordByUsername(RegisterInfo customer) {
		customer.setPassword(MD5Utils.encryptPassword(customer.getPassword(), customer.getUsername()));
		int i = baseDao.update("system.updatePassword", customer);
		if (i > 0) {
			return true;
		}
		return false;
	}
}
