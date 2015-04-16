package com;

import java.io.*;
import java.util.regex.*;
import java.util.*;

/**
 * ����һ��MAC��ַ��Ȼ������ļ�������MAC��ַ��Ӧ����ҵ�����ҵ�������������
 * �ļ��л�����ҵ��ע�����ϸ��ַ
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
		List<String> strList = new ArrayList<String>();// ����һ��List�洢��ȡ���ı�����
		int flags = 0;// ����һ��flag�洢keyword���ֵ���
		// �Ӹ�����MAC��ַ��ȡ��������ҵ�Ĺؼ����֣�
		String keyword = mac.substring(0, 8);
		Pattern p = Pattern.compile(keyword);// ����Pattern��compile��������Ҫƥ�������
		Matcher m;
		int i = 0;
		try {
			//MAC��ַ����ҵ���ƶ����ļ�
			Reader re = new FileReader(new File(
					"F:\\EcpliseForJAVA\\Workspace0\\SNMP\\res\\oui.txt"));
			BufferedReader bre = new BufferedReader(re);
			while (bre.ready()) {
				String str = bre.readLine();
				strList.add(str);
				m = p.matcher(str);
				if (m.find())// ��������ƥ����Ӵ��Ƿ����
				{
					flags = i;// ��¼ƥ�����
					break;
				}
				i++;
			}
			//����ҵ�������������ƣ����û�в��ҵ��������˽Ӫ������ʾ��MAC��ַΪ¼�롣
			if (flags != 0) {
				String result = strList.get(flags).substring(20);
				System.out.println("MAC��" + mac + "���ڣ� " + result);
			}
			else {
				System.out.println("˽Ӫ");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
