package main;

import preprogress.IsAreaPoint;
public class HotspotsDiscovery {
	/**
	 * @param args
	 */
	
	public static void main(String[] args)
	{
		IsAreaPoint isAreaPoint=new IsAreaPoint();
		int edgeNum=12;//ѡ������ı���
		String areaEdgePathString="D:\\���⳵�ȵ㷢��\\staticdata\\areaEdge.txt";
		int beginMonth=1;
		int endMonth=1;
		int eps=20;
		String head="getin_";
		String writePath="D:\\����\\GPS�������¿�����";
		String areaName="������";
		int minDataPoints=0;
		String filePathString="D:\\����\\GPS���ֻ��ŷ���_����";
//		isAreaPoint.GetAreaPoint(edgeNum,areaEdgePathString,beginMonth,endMonth,eps);
		isAreaPoint.GetAreaPoint(minDataPoints,edgeNum, areaEdgePathString, beginMonth, endMonth, eps, filePathString, head, writePath, areaName);
	}

}
