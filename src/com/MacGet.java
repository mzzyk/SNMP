package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ͨ�����߲�ѯ�ķ�ʽ����Ҫ��ѯ��MAC��ַ���ַ�������ʽ����URL��ַ���棬Ȼ��ͨ��http��������÷������ݣ�
 * ���������ݴ����һ���ַ���������string��substring ��������ȡ��Ҫ�Ĳ��֡�
 * @author ZYK
 *
 */
public class MacGet {

	/**
	 * 
	 * @param mac Ҫ��ѯ�������ַ  c4-6a-b7-e9-ba-00 С�ף�74-AD-B7-7F-CD-DE�й��ƶ�
	 * @throws Exception
	 */
	public static void captureHtml(String mac) throws Exception {
		
		//Ҫ���ʵ�URL������ͨ��MAC��ַ��ѯ�ƶ��ն˵���������
		String strURL = "http://www.wenzk.com/mac-query?mac="+mac;
		//����һ���µ�URL
		URL url = new URL(strURL);
		//������������
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStreamReader input = new InputStreamReader(
				httpConn.getInputStream(), "utf-8");
		//��ȡҪ���ʵ�����
		BufferedReader bufReader = new BufferedReader(input);
		//����һ���ַ�������������ȡ��������ȫ�������ڸ�string������
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		//һ��һ��ƴ���ַ���
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		String buf = contentBuf.toString();
	
		//Ҫ���ҽ������ʼλ�ã���Ϊ������Ҫ�����ݲ����������ڣ�����4���ַ�,��������Ҫ����ƶ�
		int beginIx = buf.indexOf("����")+4;
		//Ҫ���ҽ���Ľ���λ��
		int endIx = buf.indexOf("<br /><br />���ݸ���Ƶ��");
		
		String result = buf.substring(beginIx,endIx);
		System.out.println("���������ǣ�\n" + result);
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
