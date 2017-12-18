package com.lyao.mo.common.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.lyao.mo.bottom.bean.bo.ValidCodeEmail;
import com.lyao.mo.common.properties.PropertiesFactory;
import com.lyao.mo.common.properties.QQMailProperties;

public class Sendmail extends Thread {
	
	private Object emailInfo;
	private static Properties prop;
	private static String host;
	private static String username;
	private static String password;
	public Sendmail(Object emailInfo) {
		this.emailInfo = emailInfo;
	}
	
	static {
		QQMailProperties qqMailProperties = (QQMailProperties)PropertiesFactory.create(QQMailProperties.class);
		prop = qqMailProperties.getProperties();
		host = prop.getProperty("mail.smtp.host");
		username = prop.getProperty("mail.user");
		password = prop.getProperty("mail.password");
	}
	
	@Override
	public void run() {
		try {
			Session session = Session.getInstance(prop);
			session.setDebug(true);
			Transport ts = session.getTransport();
			ts.connect(host, username, password);
			Message message = createEmail(session, emailInfo);
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Message createEmail(Session session, Object emailInfo) throws Exception {
		MimeMessage message = null;
		if(emailInfo.getClass().getName().equals(ValidCodeEmail.class.getName())){
			ValidCodeEmail ValidCodeEmail = (ValidCodeEmail)emailInfo;
			message = (MimeMessage) validCodeEmail(session, ValidCodeEmail);
		}
		return message;
	}
	
	public Message validCodeEmail(Session session, ValidCodeEmail emailInfo) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipient(Message.RecipientType.TO,
				new InternetAddress(emailInfo.getReceiver()));
		message.setSubject("默--注册激活");
		message.setContent(emailInfo.createInfo(), "text/html;charset=UTF-8");
		message.saveChanges();
		return message;
	}
	
	public static void main(String[] args) {
		ValidCodeEmail validCodeEmail = new ValidCodeEmail();
		validCodeEmail.setUserName("lihao");
		validCodeEmail.setReceiver("15505414671@163.com");
		validCodeEmail.setValidURL("http://www.baidu.com");
		Sendmail sendEmail = new Sendmail(validCodeEmail);
		sendEmail.start();
	}
}
