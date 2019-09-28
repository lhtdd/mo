package com.lyao.mo.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class HttpConUtils {
	private final static Logger log = Logger.getLogger(HttpConUtils.class.getName());
	//private final static String FAVICON_API = "http://api.byi.pw/favicon/?url=";
	private final static String FAVICON_API = "http://statics.dnspod.cn/proxy_favicon/_/favicon?domain=";
	/**
	 * 根据地址获得数据的字节流
	 * 
	 * @param strUrl
	 *            网络连接地址
	 * @return
	 */
	public static byte[] getImageFromNetByUrl(String strUrl) {
		byte[] btImg = null;
		try {
			URL targetUrl = new URL(strUrl);
			//String a = targetUrl.getProtocol()+"://"+targetUrl.getHost();
			String a = targetUrl.getHost();
			URL url = new URL(FAVICON_API + a);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();
			btImg = readInputStream(inStream);
		} catch (Exception e) {
			log.info("获取URL失败");
			e.printStackTrace();
		}
		return btImg;
	}

	/**
	 * 从输入流中获取数据
	 * 
	 * @param inStream
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dd = new String(HttpConUtils.getImageFromNetByUrl("http://webmagic.io/favicon.ico"));
		System.out.println(dd);
	}

}
