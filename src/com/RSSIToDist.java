package com;

import java.awt.List;
import java.util.ArrayList;

public class RSSIToDist {

	/**
	 * ͨ�����й�ʽ������RSSIת��Ϊ�����ߵ�AP�ľ���
	 * ��ʽ��RSSI = - (A + 10*n*log(d))  ת��Ϊ d = 10^(abs(RSSI)-A)/10n 
	 * ����AΪ��׼���루һ��Ϊ1�ף�ʱ�ĳ�����n Ϊ·�����ָ����
	 * @param rssi ��snmp�����л�ȡ�Ľ����ź�ǿ��ֵ��
	 * @return ����Ľ������ΪҪ��ľ����ź�Դ�ľ���
	 */
	public static double rssiToDistance(double rssi){
		//����Ҫ��ľ���
		double dist = 0;
		//d0Ϊ��׼��������ѷ�ΧΪ��45.0 - 49.0
		double d0 = 47.0; 
		//n Ϊ·�����ָ��,���ȡֵ��ΧΪ 3.25 - 4.5
		double n = 4.0; 
		dist = Math.pow(10.0, (double)(Math.abs(rssi) - d0) / (10.0 * n ));
		if(dist != 0)
			return dist;
		else
			return -1;
	}
	
	/**
	 * ͨ��ƽ��ֵ�˲������RSSI��������ߵ�AP�����ľ���
	 * @param rssis һ��RSSIֵ�б�
	 * @return RSSI��ƽ��ֵ
	 */
	public static double avgRSSI (ArrayList<Double> rssis){
		//����Ҫ���صĽ������Ҫ���ƽ��ֵ
		double avgRssi = 0;
		double sum = 0;
		double size = rssis.size();
		for (int i = 0; i < size; i++) {
			sum += rssis.get(i);
		}
		avgRssi = sum / size;
		return avgRssi;
	}
	
	public static void main (String args[]){
		double temp = rssiToDistance(-90);
		System.out.println(temp);
		
		ArrayList<Double> rssis =new ArrayList<Double>();
		for (int i = 0; i < 101; i++) {
			rssis.add((double)i);
		}
		double avg = avgRSSI(rssis);
		System.out.println(avg);
	}
}
