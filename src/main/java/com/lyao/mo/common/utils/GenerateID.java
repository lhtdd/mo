package com.lyao.mo.common.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class GenerateID {

	/**
	 * 生成客户的注册号，根据性别进行区分
	 * @param sex
	 * @return
	 */
	public static String generateCustomerID(String sex){
		String ID = null;
		
		if (sex.equals("1")){
			ID = "M"+RandomUtils.generateString(6)+getMillisecond();
		}else{
			ID = "O"+RandomUtils.generateString(6)+getMillisecond();
		}
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
