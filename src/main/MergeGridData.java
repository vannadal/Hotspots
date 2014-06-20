package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class MergeGridData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	int lngSegNum=81;
    	int atdSegNum=56;
		double eps=25.0;
		String area="����";
		MergeByMonth(area,eps,lngSegNum,atdSegNum);
//		MergeBySeason(area,eps,lngSegNum,atdSegNum);
//		MergeByYear(area,eps,lngSegNum,atdSegNum,area);
	}
	public static int getWeekDayString(int year,int month,int day) 
	{ 
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month-1);
		calendar.set(Calendar.DATE,day);		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1; 
		return dayOfWeek; 
	} 

	/*
 * Ԫ����1��1����3�շżٹ��ݣ���3��
���ڣ�2��3����9�շżٵ��ݣ���7�졣2��12��(������)��13��(������)�ϰ�
�����ڣ�4��5����7�շżٹ��ݣ���3��
�Ͷ��ڣ�5��1����3�շżٹ��ݣ���3��
����ڣ�6��6����8�շżٵ��ݣ���3�졣6��4��(������)��5��(������)�ϰ�
����ڣ�9��12����14�շżٵ��ݣ���3�졣9��10��(������)��11��(������)�ϰ�
����ڣ�10��1����7�շżٵ��ݣ���7�졣10��8��(������)��10��9��(������)�ϰࡣ
 * *
 */
//���������ȵ������и�ʱ���ڵ�OD�㰴����һ���ܶ��������ġ����塢�������ա��ڼ��շ���	
	
	public static void MergeByMonth(String area,double eps,int lngSegNum,int atdSegNum)
	{
		String dayName="";
		String date="";
		String gridTxtPath="";
		String gridTxt="";
		String epsString=String.valueOf((int)eps);
		int Array[]=new int[lngSegNum*atdSegNum];
		String year="2011";
		String month=null;
		int MonthDay[]={31,28,31,30,31,30,31,31,30,31,30,31};
		for(int m=2;m<=2;m++)
		{
			if(m<10)month="0"+m;
			else month=String.valueOf(m);
			int dayNum=MonthDay[m-1];
			for(int hour=6;hour<=21;hour+=3)
			{
				for(int day=1;day<=7;day++)
				{
					for(int i=0;i<Array.length;i++)
						Array[i]=0;
					int day1=day;
					int week=getWeekDayString(Integer.parseInt(year),Integer.parseInt(month),day);
					try{
						while(day1<=dayNum)
						{
							if(day1>=1&&day1<=7)
							{
								day1+=7;
								continue;
							}	
							if(day1<10)dayName="0"+String.valueOf(day1);
							else dayName=String.valueOf(day1);
							date=year+month+dayName;
							gridTxt=".\\MidMif\\"+area+"\\"+year+month+"\\MidMif_"+epsString+"\\"+date+"\\"+date+"_"+String.valueOf(hour)+"_"+String.valueOf(hour+1)+"_"+String.valueOf(hour+2)+"_grid";
							gridTxtPath=gridTxt+".txt";
							File file=new File(gridTxtPath);
							if(!file.exists())
							{
								day1+=7;
								continue;
							}
							BufferedReader reader=new BufferedReader(new FileReader(gridTxtPath));
							String line=null;
							int i=0;
							while((line=reader.readLine())!=null)
							{
								Array[i]+=Integer.parseInt(line);
								i++;
							}
							day1+=7;
						}
						BufferedWriter writer=new BufferedWriter(new FileWriter(new File(".\\MidMif\\"+area+"\\MergeData"+"_"+epsString+"\\"+year+month+"\\"+week+"_"+String.valueOf(hour)+"_"+String.valueOf(hour+1)+"_"+String.valueOf(hour+2)+".txt")));	
						for(int i=0;i<Array.length;i++)
							writer.write(Array[i]+"\n");
						writer.close();
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

	
	public static void MergeBySeason(String area,double eps,int lngSegNum,int atdSegNum)
	{
		String year="2011";
		String monthName="";
		String hourPeriodTxt="";
		int Array[]=new int[lngSegNum*atdSegNum];
		String epsString=String.valueOf((int)eps);
		try{
			for(int hour=6;hour<=21;hour+=3)
			{
				for(int i=0;i<Array.length;i++)
					Array[i]=0;
				for(int week=0;week<=6;week++)
				{
					int month;
					for(month=10;month<=12;month++)
					{
						if(month<10)monthName="0"+String.valueOf(month);
						else monthName=String.valueOf(month);
						hourPeriodTxt=".\\MidMif\\"+area+"\\MergeData"+"_"+epsString+"\\"+year+monthName+"\\"+week+"_"+String.valueOf(hour)+"_"+String.valueOf(hour+1)+"_"+String.valueOf(hour+2)+".txt";
						BufferedReader reader=new BufferedReader(new FileReader(hourPeriodTxt));
						String line=null;
						int i=0;
						while((line=reader.readLine())!=null)
						{
							Array[i]+=Integer.parseInt(line);
							i++;
						}
					}
					BufferedWriter writer=new BufferedWriter(new FileWriter(new File(".\\MidMif\\"+area+"\\MergeData"+"_"+epsString+"\\"+year+"Season\\"+year+area+"season"+String.valueOf(month/3)+"_"+String.valueOf(week)+"_"+String.valueOf(hour)+"_"+String.valueOf(hour+1)+"_"+String.valueOf(hour+2)+".txt")));	
					for(int i=0;i<Array.length;i++)
						writer.write(Array[i]+"\n");
					writer.close();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void MergeByYear(double eps,int lngSegNum,int atdSegNum,String area)
	{
		String year="2011";
		String monthName="";
		String hourPeriodTxt="";
		int Array[]=new int[lngSegNum*atdSegNum];
		String epsString=String.valueOf((int)eps);
		try{
			for(int week=0;week<=6;week++)
			{
				for(int i=0;i<Array.length;i++)
					Array[i]=0;
				for(int hour=6;hour<=21;hour+=3)
				{
					for(int month=4;month<=12;month++)
					{
						if(month<10)monthName="0"+String.valueOf(month);
						else monthName=String.valueOf(month);
						hourPeriodTxt=".\\MidMif\\"+area+"\\MergeData"+"_"+epsString+"\\"+year+monthName+"\\"+week+"_"+String.valueOf(hour)+"_"+String.valueOf(hour+1)+"_"+String.valueOf(hour+2)+".txt";
						File file=new File(hourPeriodTxt);
						if(!file.exists())
						{
							continue;
						}
						BufferedReader reader=new BufferedReader(new FileReader(hourPeriodTxt));
						String line=null;
						int i=0;
						while((line=reader.readLine())!=null)
						{
							Array[i]+=Integer.parseInt(line);
							i++;
						}					
					}
					BufferedWriter writer=new BufferedWriter(new FileWriter(new File(".\\MidMif\\"+area+"\\MergeData"+"_"+epsString+"\\"+year+"\\"+String.valueOf(week)+"_"+String.valueOf(hour)+"_"+String.valueOf(hour+1)+"_"+String.valueOf(hour+2)+".txt")));	
					for(int i=0;i<Array.length;i++)
						writer.write(Array[i]+"\n");
					writer.close();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
}
