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
		String area="北航";
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
 * 元旦：1月1日至3日放假公休，共3天
春节：2月3日至9日放假调休，共7天。2月12日(星期六)、13日(星期日)上班
清明节：4月5日至7日放假公休，共3天
劳动节：5月1日至3日放假公休，共3天
端午节：6月6日至8日放假调休，共3天。6月4日(星期六)、5日(星期日)上班
中秋节：9月12日至14日放假调休，共3天。9月10日(星期日)、11日(星期六)上班
国庆节：10月1日至7日放假调休，共7天。10月8日(星期日)、10月9日(星期六)上班。
 * *
 */
//将聚类后的热点区域中各时段内的OD点按照周一，周二周三周四、周五、周六周日、节假日分类	
	
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
