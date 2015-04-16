package com;

import java.awt.List;
import java.util.ArrayList;

public class RSSIToDist {

	/**
	 * 通过现有公式来将人RSSI转换为接收者到AP的距离
	 * 公式：RSSI = - (A + 10*n*log(d))  转换为 d = 10^(abs(RSSI)-A)/10n 
	 * 其中A为基准距离（一般为1米）时的常量，n 为路径损耗指数。
	 * @param rssi 从snmp服务中获取的接收信号强度值。
	 * @return 计算的结果，即为要求的距离信号源的距离
	 */
	public static double rssiToDistance(double rssi){
		//定义要求的距离
		double dist = 0;
		//d0为基准常量。最佳范围为：45.0 - 49.0
		double d0 = 47.0; 
		//n 为路径损耗指数,最佳取值范围为 3.25 - 4.5
		double n = 4.0; 
		dist = Math.pow(10.0, (double)(Math.abs(rssi) - d0) / (10.0 * n ));
		if(dist != 0)
			return dist;
		else
			return -1;
	}
	
	/**
	 * 通过平均值滤波来提高RSSI计算接收者到AP点距离的精度
	 * @param rssis 一个RSSI值列表
	 * @return RSSI的平均值
	 */
	public static double avgRSSI (ArrayList<Double> rssis){
		//定义要返回的结果，即要求的平均值
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
