package main;

import java.io.File;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import dbscan.DBSCANClusterAnalysis;
import dbscan.TwoStepMerge;

public class TwoStepDBSCANRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String head="getin_";
		String areaName="海淀区";
		boolean isWeekday=true;
		String writePath="D:\\数据\\GPS区域二次聚类上下客数据\\";
		String readPath="D:\\数据\\GPS区域上下客数据\\";
		File[] dateList=new File(readPath).listFiles();
		
		int startNum=0;
		int dayNum=15;
		String hourString="18";
		int minPts=4;
		int eps=20;
		
		int minDataPoints=20;//标识最小聚类对象中的点个数
		TwoStepMerge twoStepDBSCAN=new TwoStepMerge();
		
		String readString=twoStepDBSCAN.TwoStep_MergeAreaData(isWeekday,writePath,readPath,head,areaName, dateList, startNum, dayNum, hourString);
	    DBSCANClusterAnalysis clusterAnalysis=new DBSCANClusterAnalysis();
	    for(minDataPoints=20;minDataPoints<=200;minDataPoints+=20)
	    {
		    String twoStepResultwriteString=readString.substring(0,readString.length()-4)+"_"+minDataPoints+"_cluster.csv";
		    clusterAnalysis.TwoStepHotSpots(readString, minPts, eps,twoStepResultwriteString,areaName,minDataPoints);	    	
	    }

	}

}
