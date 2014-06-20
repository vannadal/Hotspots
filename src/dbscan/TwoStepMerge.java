package dbscan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import main.WeekDay;


public class TwoStepMerge {

	//分别以一周、半个月、一个月的数据进行聚类分析
	//分周末与工作日以及全部集合
	//isWeekday标识提取的是周末还是工作日的集合
	//areaName标识聚类的区域名
	//dateList标识待合并的数据日期列表，startNum标识待合并数据对应的第一个日期在列表的位置，dayNum标识聚类天数
	public String TwoStep_MergeAreaData(boolean isWeekday,String writePath,String readPath,String head,String areaName,File[] dateList,int startNum,int dayNum,String hourString)
	{
		try 
		{

			File file=new File(writePath+areaName);
			if(!file.exists())
			{
				file.mkdir();
			}
			String writeString=writePath+"\\"+areaName+"\\"+head+dateList[0].getName().substring(0,8)+"_"+dayNum+"_"+hourString+".csv";
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(writeString)));
			WeekDay weekDay=new WeekDay();
			
			if(isWeekday)
			{
				
				for(int i=startNum;i<startNum+dayNum;i++)
				{
					String dateString=dateList[i].getName().substring(0,8);
					if(weekDay.IsWeekday(dateString))
					{
						String fileString=readPath+dateString+"\\"+areaName+"\\"+head+dateString+hourString+".csv";
						BufferedReader br=new BufferedReader(new FileReader(new File(fileString)));
						String line="";
						while((line=br.readLine())!=null)
						{
							String item[]=line.split(",");
							if(item.length>=4)
							{
								bw.write(line+"\n");	
							}
							
						}		
					}		
				}	
			}
			else
			{
				for(int i=startNum;i<startNum+dayNum;i++)
				{
					String dateString=dateList[i].getName().substring(0,8);
					if(!weekDay.IsWeekday(dateString))
					{
						String fileString=readPath+dateString+"\\"+areaName+"\\"+head+dateString+hourString+".csv";
						BufferedReader br=new BufferedReader(new FileReader(new File(fileString)));
						String line="";
						String item[]=line.split(",");
						if(item.length>=4)
						{
							bw.write(line+"\n");	
						}		
					}
					
				}
			}
			bw.close();
			return writeString;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
}
