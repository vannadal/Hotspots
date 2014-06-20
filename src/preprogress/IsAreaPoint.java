package preprogress;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import dbscan.DBSCANClusterAnalysis;

public class IsAreaPoint {
	private HashMap<String,String> map=new HashMap<String,String>();
	public IsAreaPoint()
	{
		
	}

	static 	boolean  pointInPolygon(int polySides,double polyY[],double polyX[],double x,double y) 
	{
		  int i;
		  boolean  oddNodes=false;
		  for (i=0;i<polySides-1;i++) 
		  {
			  if(polyY[i]<y&&polyY[i+1]>=y)
			  {
					  if(polyY[i]*polyX[i+1]+polyY[i+1]*x+y*polyX[i]<=polyX[i+1]*y+polyY[i+1]*polyX[i]+polyY[i]*x)
					  {
						  oddNodes=!oddNodes;
					  }
			  }
			  if(polyY[i]>y&&polyY[i+1]<=y)
			  {
					  if(polyY[i]*polyX[i+1]+polyY[i+1]*x+y*polyX[i]>=polyX[i+1]*y+polyY[i+1]*polyX[i]+polyY[i]*x)
					  {
						  oddNodes=!oddNodes;
					  }
			  }
		  }
		  return oddNodes; 
	}
	
	public void GetAreaPoint(int minDataPoints,int edgeNum,String areaEdgePathString,int beginMonth,int endMonth,int eps,String filePathString,String head,String writePath,String areaName)
	{
		
		double polyX[]=new double[edgeNum];
		double polyY[]=new double[edgeNum];
		int polySides=0,i=0;
		int minPts=4;
		double minX=360.0,maxX=0.0;
		double minY=360.0,maxY=0.0;
		BufferedReader reader=null,tStampFile=null;
    	try{
    		reader=new BufferedReader(new FileReader(new File(areaEdgePathString)));
    		String tempString=null;
    		while((tempString=reader.readLine())!=null)
    		{
    			polyX[i]=Double.parseDouble(tempString.substring(0,tempString.indexOf(",")));
    			if(polyX[i]<minX)minX=polyX[i];//lng
    			if(polyX[i]>maxX)maxX=polyX[i];  		
    			polyY[i]=Double.parseDouble(tempString.substring(tempString.indexOf(",")+1));
    			if(polyY[i]<minY)minY=polyY[i];//atd	
    			if(polyY[i]>maxY)maxY=polyY[i];
    			i++;
    		}
    		polySides=i;
    		String monthString="";
    		int MonthDay[]={31,28,31,30,31,30,31,31,30,31,30,31};
    		String dateString="";

    		for(int m=beginMonth;m<=endMonth;m++)
    		{
    			if(m<10)monthString="2014"+"0"+m;
    			else monthString="2014"+m;
//	    		for(int day=1;day<=MonthDay[m-1];++day)
    			for(int day=28;day<=31;++day)
	    		{
    				if(day<10)dateString=monthString+"0"+day;
    				else {
						dateString=monthString+day;
					}
    				String filenameString=filePathString+"\\"+head+dateString+".csv";
					if(!new File(filenameString).exists())
					{
						continue;
					}
					String tStampFilename=null;
					tStampFile=new BufferedReader(new FileReader(new File(filenameString)));
					while((tStampFilename=tStampFile.readLine())!=null)
		    		{
				            String item[] = tStampFilename.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分  
					        if(item[0].length()<5)continue;
				            String timestamp=item[0].substring(0,10);
				            double lng=Double.parseDouble(item[2]);
					        double atd=Double.parseDouble(item[3]);
					        String temp="";
					        if(lng<minX||lng>maxX||atd<minY||atd>maxY)
			        		{
			        			continue;
			        		}
			        		else if(pointInPolygon(polySides,polyY,polyX,lng,atd))
			        		{
						        if(map.containsKey(timestamp))
								{
									temp=map.get(timestamp);
									temp=temp+tStampFilename+"\n";
									map.put(timestamp,temp);
								}
								else {
									map.put(timestamp,tStampFilename+"\n");
								}
			        		}
		    		}
					for(int hour=0;hour<=23;hour+=2)
					{
						String timestampString1="",timestampString2;
						if(hour<10)
						{	timestampString1=dateString+"0"+hour;
						    timestampString2=dateString+"0"+(hour+1);
						}
						    else {
							timestampString1=dateString+hour;
							timestampString2=dateString+(hour+1);
						}
						Iterator iter = map.entrySet().iterator(); 
						while (iter.hasNext()) { 
						    Map.Entry entry = (Map.Entry) iter.next(); 
						    Object key = entry.getKey();
						    if(key.equals(timestampString1)||key.equals(timestampString2))
						    try {
						    	if(!new File(writePath+"\\"+dateString).exists())
						    	{
						    		new File(writePath+"\\"+dateString).mkdir();
						    	}
						    	String writerFilenameString=writePath+"\\"+dateString+"\\"+head+String.valueOf(timestampString1)+".csv";
						    	BufferedWriter bw=new BufferedWriter(new FileWriter(new File(writerFilenameString),true));
							    Object val = entry.getValue();
							    bw.write(String.valueOf(val));
							    bw.close();
							  
							    String tempPathString=writePath+"\\"+dateString+"\\"+areaName;
							    if(!new File(tempPathString).exists())
							    {
							    	new File(tempPathString).mkdir();
							    }
							    String tempWriteString=tempPathString+"\\"+head+String.valueOf(timestampString1)+".csv";
							    DBSCANClusterAnalysis clusterAnalysis=new DBSCANClusterAnalysis();
					    		clusterAnalysis.HotSpots(writerFilenameString, minPts, eps,tempWriteString,areaName,minDataPoints);
			    				
							    
							} catch (Exception e) {
								// TODO: handle exception
							}
						    	
						}
						
					}
					map.clear();
			   		}
	    		}
    	}
    	catch(IOException e){    
    		e.printStackTrace();
    	}		
				
	}
	
	
//从五分钟一个的时间段文件中分离出点
	public void GetAreaPoint(int minDataPoints,int edgeNum,String areaEdgePathString,int beginMonth,int endMonth, int eps)
	{
		double polyX[]=new double[edgeNum];
		double polyY[]=new double[edgeNum];
		int polySides=0,i=0;
		int minPts=4;
		double minX=360.0,maxX=0.0;
		double minY=360.0,maxY=0.0;
		BufferedReader reader=null,tStampFile=null;
    	try{
    		reader=new BufferedReader(new FileReader(new File(areaEdgePathString)));
    		String tempString=null;
    		while((tempString=reader.readLine())!=null)
    		{
    			polyX[i]=Double.parseDouble(tempString.substring(0,tempString.indexOf(",")));
    			if(polyX[i]<minX)minX=polyX[i];//lng
    			if(polyX[i]>maxX)maxX=polyX[i];  		
    			polyY[i]=Double.parseDouble(tempString.substring(tempString.indexOf(",")+1));
    			if(polyY[i]<minY)minY=polyY[i];//atd	
    			if(polyY[i]>maxY)maxY=polyY[i];
    			i++;
    		}
    		polySides=i;
    		String month="";
    		int MonthDay[]={31,28,31,30,31,30,31,31,30,31,30,31};
    		String date="";
    		String t_string="";
    		for(int m=beginMonth;m<=endMonth;m++)
    		{
    			if(m<10)month="2014"+"0"+m;
    			else month="2014"+m;
//	    		for(int day=1;day<=MonthDay[m-1];++day)
    			for(int day=1;day<=7;++day)
	    		{
	    			int hour=8;
	    			for(int t=12*hour+1;t<=12*(hour+2);t++)//8-10点的数据
	    			{
	    				date=(day<10)?month+"0"+String.valueOf(day):month+String.valueOf(day);
			    		polySides=i;

			    		if(t<10)
			    			t_string="00"+t;
			    		else if (t<100) {
			    			t_string="0"+t;
			    		}
			    			else {
								t_string=String.valueOf(t);
							}
	    				String filename="D:\\数据\\extract_GPS0312-0402\\"+date+"\\"+date+"_"+t_string+".txt";
						if(!new File(filename).exists())
						{
							continue;
						}
						
	    				tStampFile=new BufferedReader(new FileReader(new File(filename)));
			    		String tStampFilename=null;
			    		String tempFilename="D:\\出租车热点发现\\processingdata\\"+date+"_"+hour+".csv";
			    		String tempWriterFilename="D:\\出租车热点发现\\result\\"+date+"_"+hour+".csv";
			    		BufferedWriter writer=new BufferedWriter(new FileWriter(tempFilename));
			    		
			    		while((tStampFilename=tStampFile.readLine())!=null)
			    		{
					            String item[] = tStampFilename.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分  
					            if(item.length<=3)continue;

						        double lng=Double.parseDouble(item[0]);
						        double atd=Double.parseDouble(item[1]);
						        if(item[5].length()!=1)continue;
						        int event=Integer.parseInt(item[5]);
						        if(event==2||event==3||event==4)
						        {
						        	continue;
						        }
						        
						        else if(lng<minX||lng>maxX||atd<minY||atd>maxY)
				        		{
				        			continue;
				        		}
				        		else if(pointInPolygon(polySides,polyY,polyX,lng,atd))
				        		{
				        			writer.write(lng+","+atd+"\n");
				        		}
			    		}
			    		writer.close();
			    		DBSCANClusterAnalysis clusterAnalysis=new DBSCANClusterAnalysis();
			    		clusterAnalysis.HotSpots(tempFilename, minPts, eps,tempWriterFilename,minDataPoints);
	    				
	    			}
	    		}
	    	}
    	
    	}
    	catch(IOException e){    
    		e.printStackTrace();
    	}		
	}

	public static int GetHour(String filename,String date)
	{
		for(int hour=0;hour<=23;hour++)
		{
			if(hour<10)
			{
				if(filename.contains(date+"0"+hour))return hour;
			}
			else
			{
				if(filename.contains(date+hour))return hour;
			}
		}
		return -1;
	}
}
