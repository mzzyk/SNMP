package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 通过在线查询的方式，把要查询的MAC地址以字符串的形式加在URL地址后面，然后通过http请求来获得返回数据，
 * 将返回数据存放在一个字符串里，最后用string的substring 函数来截取需要的部分。
 * @author ZYK
 *
 */
public class MacGet {

	/**
	 * 
	 * @param mac 要查询的物理地址  c4-6a-b7-e9-ba-00 小米；74-AD-B7-7F-CD-DE中国移动
	 * @throws Exception
	 */
	public static void captureHtml(String mac) throws Exception {
		
		//要访问的URL，用来通过MAC地址查询移动终端的生产厂商
		String strURL = "http://www.wenzk.com/mac-query?mac="+mac;
		//构造一个新的URL
		URL url = new URL(strURL);
		//建立网络连接
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStreamReader input = new InputStreamReader(
				httpConn.getInputStream(), "utf-8");
		//读取要访问的数据
		BufferedReader bufReader = new BufferedReader(input);
		//定义一个字符串变量，将读取到的数据全部保存在该string变量里
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		//一行一行拼接字符串
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		String buf = contentBuf.toString();
	
		//要查找结果的起始位置，因为真正需要的数据不包括“属于：”这4个字符,所以索引要向后移动
		int beginIx = buf.indexOf("属于")+4;
		//要查找结果的结束位置
		int endIx = buf.indexOf("<br /><br />数据更新频率");
		
		String result = buf.substring(beginIx,endIx);
		System.out.println("生产厂商是：\n" + result);
	}

	public static void main(String args[]) {
		try {
			captureHtml("74-51-BA-5E-99-DB");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
