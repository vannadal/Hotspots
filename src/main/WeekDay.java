package main;

import java.util.Calendar; 
public class WeekDay { 
	public WeekDay() { 
		super(); 
		// TODO Auto-generated constructor stub 
	} 
	public int getWeekDayString(int year,int month,int day) 
	{ 
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month-1);
		calendar.set(Calendar.DATE,day);		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1; 
		return dayOfWeek; 
	}
	public int getWeekDayString(String date) 
	{ 
		int year=Integer.parseInt(date.substring(0,4));
		int month=Integer.parseInt(date.substring(4,6));
		int day=Integer.parseInt(date.substring(6,8));
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month-1);
		calendar.set(Calendar.DATE,day);		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1; 
		return dayOfWeek; 
	}
	
	public boolean IsWeekday(String date) 
	{ 
		int year=Integer.parseInt(date.substring(0,4));
		int month=Integer.parseInt(date.substring(4,6));
		int day=Integer.parseInt(date.substring(6,8));
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month-1);
		calendar.set(Calendar.DATE,day);		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1; 
		if(dayOfWeek==0||dayOfWeek==6)return false;
		else return true;
	}
	
	public static void main(String args[])
	{
//		WeekDay week=new WeekDay();
//		System.out.println(week.getWeekDayString(2011, Integer.parseInt("6"),19));
		String date="\\\\192.168.47.200\\Backups\\CData\\CData\\20110101\\CData_20110101041353.txt";
		System.out.println(Integer.valueOf(date.substring(date.length()-9,date.length()-8)));
	}
}