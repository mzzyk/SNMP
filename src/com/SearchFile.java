package com;

import java.io.*;
import java.util.regex.*;
import java.util.*;

/**
 * 输入一个MAC地址，然后查找文件，将该MAC地址对应的企业名称找到并解析出来。
 * 文件中还有企业的注册的详细地址
 * @author ZYK
 *
 */
public class SearchFile {
	public static void main(String arg[]) {

		 String mac = "74-AD-B7-7F-CD-DE";
//		String mac = "c4-6a-b7-e9-ba-00";
//		String mac = "74-51-BA-5E-99-DB";
		
		macSearch(mac.toUpperCase());
	}

	public static void macSearch(String mac) {
		List<String> strList = new ArrayList<String>();// 定义一个List存储读取的文本内容
		int flags = 0;// 定义一个flag存储keyword出现的行
		// 从给定的MAC地址中取出代表企业的关键部分；
		String keyword = mac.substring(0, 8);
		Pattern p = Pattern.compile(keyword);// 调用Pattern的compile方法编译要匹配的正则
		Matcher m;
		int i = 0;
		try {
			//MAC地址与企业名称对照文件
			Reader re = new FileReader(new File(
					"F:\\EcpliseForJAVA\\Workspace0\\SNMP\\res\\oui.txt"));
			BufferedReader bre = new BufferedReader(re);
			while (bre.ready()) {
				String str = bre.readLine();
				strList.add(str);
				m = p.matcher(str);
				if (m.find())// 查找正则匹配的子串是否存在
				{
					flags = i;// 记录匹配的行
					break;
				}
				i++;
			}
			//如果找到，输出厂商名称；如果没有查找到，输出“私营”，表示该MAC地址为录入。
			if (flags != 0) {
				String result = strList.get(flags).substring(20);
				System.out.println("MAC：" + mac + "属于： " + result);
			}
			else {
				System.out.println("私营");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
