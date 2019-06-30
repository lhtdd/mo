package com.lyao.mo.common.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class GenerateID {

	/**
	 * 生成客户的注册号，根据手机号+随机数做MD5
	 * @param mobile
	 * @return
	 */
	public static String generateCustomerID(String mobile){
		String ID;
		ID = MD5Utils.encodeByMD5(mobile + RandomUtils.generateString(6) + getMillisecond());
		return ID;
	}
	
	public static String getMillisecond(){
		Calendar calendar = new GregorianCalendar();
		StringBuilder millisecond = new StringBuilder();
		millisecond.append(calendar.get(Calendar.HOUR_OF_DAY));
		millisecond.append(calendar.get(Calendar.MINUTE));
		millisecond.append(calendar.get(Calendar.SECOND));
		millisecond.append(calendar.get(Calendar.MILLISECOND));
		return millisecond.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(GenerateID.generateCustomerID("1"));
		System.out.println(GenerateID.generateCustomerID("2"));
	}
}
