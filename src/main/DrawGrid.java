package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DrawGrid {

	/**
	 * vannadal
	 */
	
	/*西单区域坐标
	116.350064,39.917497left1
	116.350064,39.905768 left2
	116.376613,39.917497 right1
	116.376613,39.905768 right2
	 * */
	
	/*
	 * 224 230 111 
	 * 228 210 111 
	 * 228 190 111
	 * 228 170 111
	 * 228 150 111
	 * 228 130 111
	 * 从上到下颜色越来越深
	 * 
	 * 
	 * */                    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	int lngSegNum=92;
    	int atdSegNum=54;
		String year="2011";
		double eps=25.0;
		String area="西单";
//		DrawGrids(area,eps,lngSegNum,atdSegNum,date,daySum);
//		DrawWeekGrid(area,eps,lngSegNum,atdSegNum);
//		DrawMonthGrid(area,eps,lngSegNum,atdSegNum);
		DrawSeasonGrid(year,area,eps,lngSegNum,atdSegNum);
//		DrawYearGrid(area,eps,lngSegNum,atdSegNum);
	}
//画出渲染之后的热点区域分布效果、结果以mid、mif文件存储	
	public static void DrawGrids(String area,double eps,int lngSegNum,int atdSegNum,String date,int daySum)
	{
		String dayName="";
		String midFilePath="";
		String mifFilePath="";
		String gridTxtPath="";
		String gridTxt="";
		String epsString=String.valueOf((int)eps);
		int minPointNum=5;
		String date1=date;
		for(int day=1;day<=daySum;day++)
		{
			if(day<10)dayName="0"+String.valueOf(day);
			else dayName=String.valueOf(day);
			date=date1+dayName;
			for(int hour=6;hour<=21;hour+=3)
			{
				gridTxt=".\\MidMif\\"+area+"\\"+date1+"\\MidMif_"+epsString+"\\"+date+"\\"+date+"_"+String.valueOf(hour)+"_"+String.valueOf(hour+1)+"_"+String.valueOf(hour+2)+"_grid";
				gridTxtPath=gridTxt+".txt";
				midFilePath=gridTxt+".mid";
				mifFilePath=gridTxt+".mif";
				DrawGridArea(lngSegNum,atdSegNum,midFilePath,mifFilePath,gridTxtPath,minPointNum);
			}
		}
	}
	
	public static void DrawWeekGrid(String area,double eps,int lngSegNum,int atdSegNum)
	{
		String midFilePath="";
		String mifFilePath="";
		String gridTxtPath="";
		String gridTxt="";
		String epsString=String.valueOf((int)eps);
		int minPointNum=10;
		String monthName="";
		for(int month=2;month<=12;month++)
		{
			if(month<10)monthName="20110"+String.valueOf(month);
			else monthName="2011"+String.valueOf(month);
			for(int day=0;day<=6;day++)
			{			
				for(int hour=6;hour<=21;hour+=3)
				{
					gridTxt=".\\MidMif\\"+area+"\\MergeData_"+epsString+"\\"+monthName+"\\"+day+"_"+String.valueOf(hour)+"_"+String.valueOf(hour+1)+"_"+String.valueOf(hour+2);
					gridTxtPath=gridTxt+".txt";
					midFilePath=gridTxt+".mid";
					mifFilePath=gridTxt+".mif";
					DrawGridArea(lngSegNum,atdSegNum,midFilePath,mifFilePath,gridTxtPath,minPointNum);
				}
			}
		}
	}
	
	public static void DrawMonthGrid(String area,double eps,int lngSegNum,int atdSegNum)
	{
		String midFilePath="";
		String mifFilePath="";
		String gridTxtPath="";
		String gridTxt="";
		String monthName="";
		String epsString=String.valueOf((int)eps);
		int minPointNum=5;
		for(int month=2;month<=12;month++)
		{
			if(month<10)monthName="0"+String.valueOf(month);
			else monthName=String.valueOf(month);
			for(int week=0;week<=6;week++)
			{				
				for(int hour=6;hour<=21;hour+=3)
				{
					gridTxt=".\\MidMif\\"+area+"\\2011"+monthName+"\\MergeData_"+epsString+"\\"+week+"_"+String.valueOf(hour)+"_"+String.valueOf(hour+1)+"_"+String.valueOf(hour+2);
					File file=new File(".\\MidMif\\"+area+"\\MergeData_"+epsString);
					file.mkdir();
					boolean bFile=file.exists();
					if(!bFile)
					{
						System.out.print("File is created failed");
					}
					File file1=new File(".\\MidMif\\"+area+"\\MergeData_"+epsString+"\\2011"+monthName);
					file1.mkdir();
					boolean bFile1=file1.exists();
					if(!bFile1)
					{
						System.out.print("File is created failed");
					}
					
					gridTxtPath=gridTxt+".txt";
					midFilePath=gridTxt+".mid";
					mifFilePath=gridTxt+".mif";
					DrawGridArea(lngSegNum,atdSegNum,midFilePath,mifFilePath,gridTxtPath,minPointNum);
				}
			}
		}
	}
	
	public static void DrawSeasonGrid(String year,String area,double eps,int lngSegNum,int atdSegNum)
	{
		String midFilePath="";
		String mifFilePath="";
		String gridTxtPath="";
		String gridTxt="";
		String epsString=String.valueOf((int)eps);
		int minPointNum=15;
		for(int season=1;season<=1;season++)
		{
			for(int week=0;week<=6;week++)
			{				
				for(int hour=6;hour<=21;hour+=3)
				{
					gridTxt=".\\MidMif\\"+area+"\\MergeData_"+epsString+"\\"+year+"Season\\"+"season"+season+"_"+week+"_"+String.valueOf(hour)+"_"+String.valueOf(hour+1)+"_"+String.valueOf(hour+2);
					String gridTxt1="D:\\出租车分布及行为\\论文实验\\覆盖率统计结果\\着色\\"+year+area+"season"+season+"_"+week+"_"+String.valueOf(hour)+"_"+String.valueOf(hour+1)+"_"+String.valueOf(hour+2);
					File file=new File(".\\MidMif\\"+area+"\\MergeData_"+epsString);
					file.mkdir();
					boolean bFile=file.exists();
					if(!bFile)
					{
						System.out.print("File is created failed");
					}
					File file1=new File(".\\MidMif\\"+area+"\\MergeData_"+epsString+"\\"+year+"Season");
					file1.mkdir();
					boolean bFile1=file1.exists();
					if(!bFile1)
					{
						System.out.print("File is created failed");
					}
					gridTxtPath=gridTxt+".txt";
					midFilePath=gridTxt1+".mid";
					mifFilePath=gridTxt1+".mif";
					DrawGridArea(lngSegNum,atdSegNum,midFilePath,mifFilePath,gridTxtPath,minPointNum);
				}
			}
		}
	}
	
	public static void DrawYearGrid(String year,String area,double eps,int lngSegNum,int atdSegNum)
	{
		String midFilePath="";
		String mifFilePath="";
		String gridTxtPath="";
		String gridTxt="";
		String epsString=String.valueOf((int)eps);
		int minPointNum=50;
		for(int week=0;week<=6;week++)
		{
			for(int hour=6;hour<=21;hour+=3)
			{
				gridTxt=".\\MidMif\\"+area+"\\MergeData_"+epsString+"\\"+year+"\\"+week+"_"+String.valueOf(hour)+"_"+String.valueOf(hour+1)+"_"+String.valueOf(hour+2);
				File file1=new File(".\\MidMif\\"+area+"\\MergeData_"+epsString+"\\"+year);
				file1.mkdir();
				boolean bFile1=file1.exists();
				if(!bFile1)
				{
					System.out.print("File is created failed");
				}
				gridTxtPath=gridTxt+".txt";
				midFilePath=gridTxt+".mid";
				mifFilePath=gridTxt+".mif";
				DrawGridArea(lngSegNum,atdSegNum,midFilePath,mifFilePath,gridTxtPath,minPointNum);
			}
		}
	}
	
	public static void DrawGridArea(int lngSegNum,int atdSegNum,String midFilePath,String mifFilePath,String gridTxtPath,int minPointNum)
	{
		//望京		
//		double lngLeftUp=116.435571;
//		double atdLeftUp=40.014839;
//		double lngRightDown=116.494631;
//		double atdRightDown=39.970503;
//国贸		
//		double lngLeftUp=116.452531;
//		double atdLeftUp=39.919913;
//		double lngRightDown=116.471842;
//		double atdRightDown=39.904951;
//北京西站		
//		double lngLeftUp=116.304182;
//		double atdLeftUp=39.896413;
//		double lngRightDown=116.325068;
//		double atdRightDown=39.883195;
//北航		
//		double lngLeftUp=116.333202;
//		double atdLeftUp=39.987475;
//		double lngRightDown=116.356834;
//		double atdRightDown=39.974917;		
		//西单
		double lngLeftUp=116.350064;
		double atdLeftUp=39.917497;
		double lngRightDown=116.376613;
		double atdRightDown=39.905768;
		
		double lngSeg=(lngRightDown-lngLeftUp)/lngSegNum;
		double atdSeg=(atdRightDown-atdLeftUp)/atdSegNum;
		try{
			BufferedReader reader=new BufferedReader(new FileReader(gridTxtPath));
			BufferedWriter midFile=new BufferedWriter(new FileWriter(new File(midFilePath)));
			BufferedWriter mifFile=new BufferedWriter(new FileWriter(new File(mifFilePath)));
			mifFile.write("Version 300\nCharset \"WindowsSimpChinese\"\nDelimiter \",\"\nCoordSys Earth Projection 1, 0\n" +
					"Columns 2\n  _COL1 Float\n  _COL2 Float\nData\n");
			for(int i=0;i<atdSegNum;i++)
				for(int j=0;j<lngSegNum;j++)
				{
					String line=reader.readLine();
					int num=Integer.parseInt(line);					
					if(num<=minPointNum)
					{
						midFile.write((float)(lngLeftUp+lngSeg*j)+","+(float)(atdLeftUp+atdSeg*i)+"\n");
						mifFile.write("Rect "+(float)(lngLeftUp+lngSeg*j)+" "+(float)(atdLeftUp+atdSeg*i)+" "+(float)(lngLeftUp+lngSeg*(j+1))+" "+(float)(atdLeftUp+atdSeg*(i+1))
								+"\nPen(0,1,0)\n");
					}
//					Pen(2,13,0)
					else
					{
//						long color=65536*255+(250-num/10*30)*256+111;
						long color=65536*255;
						midFile.write((float)(lngLeftUp+lngSeg*j)+","+(float)(atdLeftUp+atdSeg*i)+"\n");
						mifFile.write("Rect "+(float)(lngLeftUp+lngSeg*j)+" "+(float)(atdLeftUp+atdSeg*i)+" "+(float)(lngLeftUp+lngSeg*(j+1))+" "+(float)(atdLeftUp+atdSeg*(i+1))
								+"\nPen(0,1,0)\n"+"Brush(2,"+color+")\n");				
					}
				}	
			mifFile.close();
			midFile.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		

		
	}

}
