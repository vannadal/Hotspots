package preprogress;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExtractTrack {
private BufferedReader br;
private HashMap<String,String> map=new HashMap<String,String>();
public void Track(String RootFilePath,int flag) throws Exception{
	File[] files1,files2;
	File file=new File(RootFilePath),wfile;
	files1=file.listFiles();
	String record,time,writePath;
	String[] st;
//	for(int i=0;i<files1.length;i++)
	for(int i=27;i<=30;i++)
	{
		files2=new File(RootFilePath).listFiles();
		br=new BufferedReader(new FileReader(files2[i]));
		String filenameString=files2[i].getName().substring(0,8);
		File file3=new File("D:\\数据\\GPS按手机号分类\\"+filenameString);
		if(!file3.exists()) file3.mkdir();
		record=br.readLine();
		String temp="";
		long count=0;
		while((record=br.readLine())!=null)
		{
			count++;
			st=record.split(",");
			System.out.println(count);
			if(map.containsKey(st[1]))
			{
				temp=map.get(st[1]);
				temp=temp+record+"\n";
				map.put(st[1],temp);
			}
			else {
				map.put(st[1],record+"\n");
			}
			if(count==1500000)
			{
				Iterator iter = map.entrySet().iterator(); 
				while (iter.hasNext()) { 
				    Map.Entry entry = (Map.Entry) iter.next(); 
				    Object key = entry.getKey();
				    BufferedWriter bw=new BufferedWriter(new FileWriter(new File("D:\\数据\\GPS按手机号分类\\"+filenameString+"\\"+String.valueOf(key)+".csv"),true));
				    Object val = entry.getValue();
				    bw.write(String.valueOf(val));
				    bw.close();
				}
				map.clear();
				count=0;
			}
		}
		Iterator iter = map.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    Object key = entry.getKey();
		    BufferedWriter bw=new BufferedWriter(new FileWriter(new File(String.valueOf(key)+".csv"),true));
		    Object val = entry.getValue();
		    bw.write(String.valueOf(val));
		    bw.close();
		}
		map.clear();

	}
}
public static void main(String[] args){
	ExtractTrack extractTrack=new ExtractTrack();
	try {
		extractTrack.Track("E:\\数据\\金银建GPS数据_8字段",1);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
