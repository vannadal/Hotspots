package dbscan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import main.WeekDay;


public class TwoStepMerge {

	//�ֱ���һ�ܡ�����¡�һ���µ����ݽ��о������
	//����ĩ�빤�����Լ�ȫ������
	//isWeekday��ʶ��ȡ������ĩ���ǹ����յļ���
	//areaName��ʶ�����������
	//dateList��ʶ���ϲ������������б�startNum��ʶ���ϲ����ݶ�Ӧ�ĵ�һ���������б��λ�ã�dayNum��ʶ��������
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
