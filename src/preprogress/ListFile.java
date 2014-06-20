package preprogress;

import java.io.*;

public class ListFile {
	public static String listFileStr = "";
	public void listFile(String dirpath) {
		File file=new File(dirpath);
		File list[]=file.listFiles();
		for (int i=0;i<list.length;i++) {
			try {
				if (list[i].isDirectory()) {
					new ListFile().listFile(list[i].toString());
				} 
				else {
					listFileStr += list[i].getAbsolutePath() + "\r\n";
				}

			} catch (Exception e) {
				listFileStr+="Access denied:"+list[i].getAbsolutePath()
						+"\r\n";
			}
		}
	}

	public void writeName(String dirpath, String filename) {
		try {
			File saveFile=new File(filename);
			FileWriter fw=new FileWriter(saveFile);
			ListFile lf=new ListFile();
			lf.listFile(dirpath);
			fw.write(listFileStr);
			listFileStr="";
			fw.close();
		} catch (IOException e) {
			System.out.println("IO error!\r\n" + e.toString());
		}
	}
	
	public static void ListCDataFile7_12(String area)
	{
		try {
			int MonthDay[]={31,28,31,30,31,30,31,31,30,31,30,31};
			String date="";
			for(int i=10;i<=12;i++)
			{
				if(i<10)
				{
					date="20110"+i;
					File file=new File(".\\data\\"+area+"\\"+date);
					file.mkdir();
					boolean bFile=file.exists();
					if(!bFile)
					{
						System.out.print("File is created failed");
					}
				}
				else 
				{
					date="2011"+i;
					File file=new File(".\\data\\"+area+"\\"+date);
					file.mkdir();
					boolean bFile=file.exists();
					if(!bFile)
					{
						System.out.print("File is created failed");
					}
				}
				for(int day=1;day<=MonthDay[i-1];++day)
				{
					ListFile lf=new ListFile();
					if(day<10)
					{				
						File file=new File(".\\data\\"+area+"\\"+date+"\\"+date+"0"+day);
						file.mkdir();
						boolean bFile=file.exists();
						if(!bFile)
						{
							System.out.print("File is created failed");
						}
//						lf.writeName("\\\\192.168.47.200\\Backups\\CData\\CData\\"+date+"0"+String.valueOf(day),".\\data\\"+area+"\\"+date+"\\"+date+"0"+String.valueOf(day)+"\\"+date+"0"+String.valueOf(day)+".txt");
//						lf.writeName("\\\\192.168.47.92\\beijing-2011\\"+date+"0"+String.valueOf(day),".\\data\\"+area+"\\"+date+"\\"+date+"0"+String.valueOf(day)+"\\"+date+"0"+String.valueOf(day)+".txt");
						lf.writeName("I:\\数据\\CData2011\\"+date+"0"+String.valueOf(day),".\\data\\"+area+"\\"+date+"\\"+date+"0"+String.valueOf(day)+"\\"+date+"0"+String.valueOf(day)+".txt");

					}
					else
					{
						File file=new File(".\\data\\"+area+"\\"+date+"\\"+date+day);
						file.mkdir();
						boolean bFile=file.exists();
						if(!bFile)
						{
							System.out.print("File is created failed");
						}
//						lf.writeName("\\\\192.168.47.200\\Backups\\CData\\CData\\"+date+String.valueOf(day),".\\data\\"+area+"\\"+date+"\\"+date+String.valueOf(day)+"\\"+date+String.valueOf(day)+".txt");
//						lf.writeName("\\\\192.168.47.92\\beijing-2011\\"+date+String.valueOf(day),".\\data\\"+area+"\\"+date+"\\"+date+String.valueOf(day)+"\\"+date+String.valueOf(day)+".txt");
						lf.writeName("I:\\数据\\CData2011\\"+date+String.valueOf(day),".\\data\\"+area+"\\"+date+"\\"+date+String.valueOf(day)+"\\"+date+String.valueOf(day)+".txt");

					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	public static void ListCDataFile2_6(String area)
	{
		try {
			int MonthDay[]={31,28,31,30,31,30,31,31,30,31,30,31};
			String date="";
			for(int i=2;i<=6;i++)
			{
				if(i<10)
				{
					date="20110"+i;
					File file=new File(".\\data\\"+area+"\\"+date);
					file.mkdir();
					boolean bFile=file.exists();
					if(!bFile)
					{
						System.out.print("File is created failed");
					}
				}
				else 
				{
					date="2011"+i;
					File file=new File(".\\data\\"+area+"\\"+date);
					file.mkdir();
					boolean bFile=file.exists();
					if(!bFile)
					{
						System.out.print("File is created failed");
					}
				}
				for(int day=1;day<=MonthDay[i-1];++day)
				{
					ListFile lf=new ListFile();
					if(day<10)
					{				
						File file=new File(".\\data\\"+area+"\\"+date+"\\"+date+"0"+day);
						file.mkdir();
						boolean bFile=file.exists();
						if(!bFile)
						{
							System.out.print("File is created failed");
						}
						lf.writeName("\\\\192.168.47.200\\Backups\\CData\\CData\\"+date+"0"+String.valueOf(day),".\\data\\"+area+"\\"+date+"\\"+date+"0"+String.valueOf(day)+"\\"+date+"0"+String.valueOf(day)+".txt");
//						lf.writeName("\\\\192.168.47.92\\beijing-2011\\"+date+"0"+String.valueOf(day),".\\data\\"+area+"\\"+date+"\\"+date+"0"+String.valueOf(day)+"\\"+date+"0"+String.valueOf(day)+".txt");
					}
					else
					{
						File file=new File(".\\data\\"+area+"\\"+date+"\\"+date+day);
						file.mkdir();
						boolean bFile=file.exists();
						if(!bFile)
						{
							System.out.print("File is created failed");
						}
						lf.writeName("\\\\192.168.47.200\\Backups\\CData\\CData\\"+date+String.valueOf(day),".\\data\\"+area+"\\"+date+"\\"+date+String.valueOf(day)+"\\"+date+String.valueOf(day)+".txt");
//						lf.writeName("\\\\192.168.47.92\\beijing-2011\\"+date+String.valueOf(day),".\\data\\"+area+"\\"+date+"\\"+date+String.valueOf(day)+"\\"+date+String.valueOf(day)+".txt");
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	public static void CreateMidMifFile(String area,String eps)
	{
		try {
			int MonthDay[]={31,28,31,30,31,30,31,31,30,31,30,31};
			String date="";

			for(int i=2;i<=12;i++)
			{
				if(i<10)
				{
					date="20110"+i;
					File file=new File(".\\MidMif\\"+area+"\\"+date);
					file.mkdir();
					boolean bFile=file.exists();
					if(!bFile)
					{
						System.out.print("File is created failed");
					}
					File file1=new File(".\\MidMif\\"+area+"\\"+date+"\\MidMif_"+eps);
					file1.mkdir();
					boolean bFile1=file.exists();
					if(!bFile1)
					{
						System.out.print("File is created failed");
					}
					
				}
				else 
				{
					date="2011"+i;
					File file=new File(".\\MidMif\\"+area+"\\"+date);
					file.mkdir();
					boolean bFile=file.exists();
					if(!bFile)
					{
						System.out.print("File is created failed");
					}
					File file1=new File(".\\MidMif\\"+area+"\\"+date+"\\MidMif_"+eps);
					file1.mkdir();
					boolean bFile1=file.exists();
					if(!bFile1)
					{
						System.out.print("File is created failed");
					}
				}
				for(int day=1;day<=MonthDay[i-1];++day)
				{
					if(day<10)
					{				
						File file=new File(".\\MidMif\\"+area+"\\"+date+"\\MidMif_"+eps+"\\"+date+"0"+day);
						file.mkdir();
						boolean bFile=file.exists();
						if(!bFile)
						{
							System.out.print("File is created failed");
						}
					}
					else
					{
						File file=new File(".\\MidMif\\"+area+"\\"+date+"\\MidMif_"+eps+"\\"+date+day);
						file.mkdir();
						boolean bFile=file.exists();
						if(!bFile)
						{
							System.out.print("File is created failed");
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public static void main(String args[]) {	

		String area="北航";
		String eps="25";
//		ListCDataFile2_6(area);
		ListCDataFile7_12(area);

//		CreateMidMifFile(area,eps);//创建MidMif文件夹
	}
}
