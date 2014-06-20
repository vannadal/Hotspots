package main;

import preprogress.IsAreaPoint;
public class HotspotsDiscovery {
	/**
	 * @param args
	 */
	
	public static void main(String[] args)
	{
		IsAreaPoint isAreaPoint=new IsAreaPoint();
		int edgeNum=12;//选择区域的边数
		String areaEdgePathString="D:\\出租车热点发现\\staticdata\\areaEdge.txt";
		int beginMonth=1;
		int endMonth=1;
		int eps=20;
		String head="getin_";
		String writePath="D:\\数据\\GPS区域上下客数据";
		String areaName="海淀区";
		int minDataPoints=0;
		String filePathString="D:\\数据\\GPS按手机号分类_有序";
//		isAreaPoint.GetAreaPoint(edgeNum,areaEdgePathString,beginMonth,endMonth,eps);
		isAreaPoint.GetAreaPoint(minDataPoints,edgeNum, areaEdgePathString, beginMonth, endMonth, eps, filePathString, head, writePath, areaName);
	}

}
