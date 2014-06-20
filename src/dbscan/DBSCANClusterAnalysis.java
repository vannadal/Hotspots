package dbscan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DBSCANClusterAnalysis{
	
	public void HotSpots(String dataFileString,int minPts,int eps,String tempWriterFilename,int minDataPoints)
	{//根据dataFileString文件中的经纬度及DBSCAN的相关参数，获取热点区域划分并保存至文件中
		ArrayList<DataPoint> dataPoints=new ArrayList<DataPoint>();
//		int minPts=4;//minPts is defined as 4 


		BufferedReader reader=null;
    	try{

			reader=new BufferedReader(new FileReader(new File(dataFileString)));
    		String line=null;
    		while((line=reader.readLine())!=null)
    		{
    			String item[]=line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分   
		        double lng=Double.parseDouble(item[0]);
		        double atd=Double.parseDouble(item[1]);
		        dataPoints.add(new DataPoint(lng,atd,false));
    		}
    		DBSCANClusterAnalysis ca=new DBSCANClusterAnalysis();
    		List<Cluster> clusterList=ca.doDbscanAnalysis(dataPoints,eps,minPts);
    		DisplayClusterList(clusterList,tempWriterFilename,minDataPoints);
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();		
    	}
	}
	public void TwoStepHotSpots(String dataFileString,int minPts,int eps,String tempWriterFilename,String areaName,int minDataPoints)
	{//根据dataFileString文件中的经纬度及DBSCAN的相关参数，获取热点区域划分并保存至文件中
		ArrayList<DataPoint> dataPoints=new ArrayList<DataPoint>();

		BufferedReader reader=null;
    	try{
			reader=new BufferedReader(new FileReader(new File(dataFileString)));
    		String line=null;
    		while((line=reader.readLine())!=null)
    		{
    			String item[]=line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分   
		        double lng=Double.parseDouble(item[1]);
		        double atd=Double.parseDouble(item[2]);
		        int direction=Integer.parseInt(item[3]);
		        int speed=Integer.parseInt(item[4]);
		        dataPoints.add(new DataPoint(lng,atd,direction,speed,false));
    		}
    		DBSCANClusterAnalysis ca=new DBSCANClusterAnalysis();
    		List<Cluster> clusterList=ca.doDbscanAnalysis(dataPoints,eps,minPts);
    		DisplayClusterList(clusterList,tempWriterFilename,minDataPoints);
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();		
    	}
	}

	
	public void HotSpots(String dataFileString,int minPts,int eps,String tempWriterFilename,String areaName,int minDataPoints)
	{//根据dataFileString文件中的经纬度及DBSCAN的相关参数，获取热点区域划分并保存至文件中
		ArrayList<DataPoint> dataPoints=new ArrayList<DataPoint>();

		BufferedReader reader=null;
    	try{
			reader=new BufferedReader(new FileReader(new File(dataFileString)));
    		String line=null;
    		while((line=reader.readLine())!=null)
    		{
    			String item[]=line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分   
		        double lng=Double.parseDouble(item[2]);
		        double atd=Double.parseDouble(item[3]);
		        int speed=Integer.parseInt(item[4]);
		        int direction=Integer.parseInt(item[6]);
		        dataPoints.add(new DataPoint(lng,atd,direction,speed,false));
    		}
    		DBSCANClusterAnalysis ca=new DBSCANClusterAnalysis();
    		List<Cluster> clusterList=ca.doDbscanAnalysis(dataPoints,eps,minPts);
    		DisplayClusterList(clusterList,tempWriterFilename,minDataPoints);
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();		
    	}
	}

	
	public void DisplayClusterList(List<Cluster> clusterList,String tempWriterFilename,int minDataPoints)
	{//minDataPoints 设定最小的写入到文件的聚类对象
		int clusterNum=0;
		try {
			
			BufferedWriter bWriter=new BufferedWriter(new FileWriter(new File(tempWriterFilename)));
			if(clusterList!=null)
			{
				for(Cluster tempCluster:clusterList)
				{
					
					if(tempCluster.GetDataPoints()!=null&&tempCluster.GetDataPoints().size()>=minDataPoints)
					{

						for(DataPoint dp:tempCluster.GetDataPoints())
						{//之前的文件里面只需要写入经纬度，现在已更改成四个字段
							
							float lng=(float)dp.lng;
							float atd=(float)dp.atd;
							int direction=dp.direction;
							int speed=dp.speed;
							bWriter.write(clusterNum+","+lng+","+atd+","+direction+","+speed+"\n");
						}
						clusterNum++;
					}
				}
			}
			bWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
	public List<Cluster>doDbscanAnalysis(List<DataPoint> dataPoints,double eps,int minPts)
	{
		List<Cluster>clusterList=new ArrayList<Cluster>();
		for(int i=0;i<dataPoints.size();i++)
		{
			DataPoint dp=dataPoints.get(i);
			//判断核心对象并且将
			List<DataPoint> arrivableObjects=IsKeyAndReturnObjects(dp,dataPoints,eps,minPts);
			if(arrivableObjects!=null)
			{
				Cluster tempCluster=new Cluster();
				tempCluster.SetClusterName("Cluster"+i);
				tempCluster.SetDataPoints(arrivableObjects);
				clusterList.add(tempCluster);			
			}
		}

			for(int i=0;i<clusterList.size();i++)
			{
				for(int j=0;j<clusterList.size();j++)
				{
					if(i!=j)
					{
						Cluster cluster1=clusterList.get(i);
						Cluster cluster2=clusterList.get(j);
						List<DataPoint> dataPoints1=cluster1.GetDataPoints();
						List<DataPoint> dataPoints2=cluster2.GetDataPoints();
						boolean flag=MergeList(dataPoints1,dataPoints2);
						if(flag)
						{
							cluster1.SetDataPoints(dataPoints1);
							clusterList.set(i,cluster1);
							clusterList.set(j,new Cluster());
						}
					}
				}
			}

		return clusterList;
	}
	
//判断是否是核心对象并返回可达对象的点集合	
	public List<DataPoint>IsKeyAndReturnObjects(DataPoint dataPoint,List<DataPoint> dataPoints,double eps,int minPts)
	{
		List<DataPoint> arrivableObjects=new ArrayList<DataPoint>();
		for(DataPoint dp:dataPoints)
		{
			double distance=GetDistance(dataPoint,dp);
			if(distance<=eps)
			{
				arrivableObjects.add(dp);
			}
		}
		if(arrivableObjects.size()>=minPts)
		{
			dataPoint.SetIsKey(true);
			return arrivableObjects;
		}
		return null;
	}
	//计算两点间经纬度距离，返回距离（m）
	public double GetDistance(DataPoint dp1,DataPoint dp2)
	{
		double dim1Lng=dp1.Getlng();
		double dim1Atd=dp1.Getatd();
		double dim2Lng=dp2.Getlng();
		double dim2Atd=dp2.Getatd();
		double lng=Math.abs(dim1Lng-dim2Lng);
		double atd=Math.abs(dim1Atd-dim2Atd);
		double L=(3.1415926*6370/180)*Math.sqrt(lng*lng*(Math.sin((90-(dp1.atd))*(3.1415926/180)))*(Math.sin((90-(dp1.atd))*(3.1415926/180)))+atd*atd);
    	return L*1000;
	}
	public int GridNum(double lng1,double atd1,int lngSegNum,int atdSegNum,double lngLeftUp,double atdLeftUp,double lngRightDown,double atdRightDown)
	{
		double lngSeg=Math.abs(lngRightDown-lngLeftUp)/lngSegNum;
		double atdSeg=Math.abs(atdRightDown-atdLeftUp)/atdSegNum;
		int gridnum=(int)(Math.abs(lng1-lngLeftUp)/lngSeg)+lngSegNum*(int)(Math.abs(atd1-atdLeftUp)/atdSeg);		
			return gridnum;
	}
	//将可达对象的点合并成为一个集合
	public boolean MergeList(List<DataPoint>dps1,List<DataPoint>dps2)
	{
		boolean flag=false;
		if(dps1==null||dps2==null||dps1.size()==0||dps2.size()==0)
		{
			return flag;
		}
		for(DataPoint dp:dps2)
		{
			if(dp.GetIsKey()&&IsContain(dp,dps1))
			{
				flag=true;
				break;
			}
		}
		
		if(flag)
		{
			for(DataPoint dp:dps2)
			{
				if(!IsContain(dp,dps1))
				{
					DataPoint tempDp=new DataPoint(dp.Getlng(),dp.Getatd(),dp.GetIsKey());
					dps1.add(tempDp);
				}
			}
		}
		return flag;
	}
	//判断点是否属于某个集合
	public boolean IsContain(DataPoint dp,List<DataPoint> dps)
	{
		boolean flag=false;
		double lng,atd;
		for(DataPoint tempDp:dps)
		{
			lng=tempDp.lng;
			atd=tempDp.atd;
			if((Math.abs(lng-dp.lng)<1e-9)&&(Math.abs(atd-dp.atd)<1e-9))
			{
				flag=true;
				break;
			}
		}
		return flag;
	}
}
