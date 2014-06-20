package preprogress;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class RunFeature {
private BufferedReader br;
private HashMap<String,BufferedWriter> map=new HashMap<String,BufferedWriter>(); //存放已经打开的BufferedWriter
public BufferedWriter IsExit(String Filename) throws Exception{
	if(map.containsKey(Filename))return map.get(Filename);
	else 
	{
		map.put(Filename, new BufferedWriter(new FileWriter(new File(Filename),true)));
	    return map.get(Filename);
	}
}

public void Ratio(String RootFilePath,int flag) throws Exception{
	File[] files1,files2;
	File file=new File(RootFilePath),wfile;
	String record,time,writePath;
	String[] st;
	String file1name="20140101";
	try {
	for(int i=0;i<1;i++)
	{
		String writePath1="I:\\0527"+"\\"+"1.csv";
		BufferedWriter bw1=new BufferedWriter(new FileWriter(new File(writePath1)));
		System.out.println("");
		wfile=new File("I:\\0527\\金银建GPS_按手机号分类"+"\\"+file1name);
		if(!wfile.exists())wfile.mkdirs();
		
		files2=(new File("I:\\0527\\金银建GPS_按手机号分类\\20140101")).listFiles();
		for(int j=0;j<files2.length;j++)
		{
		br=new BufferedReader(new FileReader(files2[j]));
		record=br.readLine();
		int inFile=0;
		int outFile=0;
		
		while((record=br.readLine())!=null)
		{
		st=record.split(",");
		if(st[0].contains(file1name))inFile++;
		else outFile++;
		}
		
		if(outFile==0)
			bw1.write(0+","+outFile+"\n");
		else {
			bw1.write(outFile*1.0/(inFile+outFile)+","+outFile+"\n");
		}
		}
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	
}


public void Computing(String RootFilePath,int flag) throws Exception{
	File[] files1,files2;
	File file=new File(RootFilePath),wfile;
	files1=file.listFiles();
	String record,time,writePath;
	String[] st;
	for(int i=0;i<files1.length;i++)
	{
		System.out.println(files1[i].getName());
		wfile=new File("I:\\0527\\金银建GPS_按手机号分类"+"\\"+files1[i].getName());
		if(!wfile.exists())wfile.mkdirs();
		files2=files1[i].listFiles();
		for(int j=0;j<files2.length;j++)
		{
		br=new BufferedReader(new FileReader(files2[j]));
		record=br.readLine();
		while((record=br.readLine())!=null)
		{
		st=record.split(",");
		if(st.length<13)continue;
		
		//JYJ现在的数据格式
		if(flag==1)
		{
		time=st[3].substring(0,4)+st[3].substring(5,7)+st[3].substring(8,10)+
				st[3].substring(11,13)+st[3].substring(14,16)+st[3].substring(17,19);
		writePath="I:\\0527\\金银建GPS_按手机号分类"+"\\"+files1[i].getName()+"\\"+st[1]+".csv";
		BufferedWriter bw=IsExit(writePath);
		bw.write(time+","+st[11]+","+st[12]+","+st[6]+","+st[7]+","+st[9]+","+"\n");
		bw.flush();
		}
		
		//JYJ之前的数据格式
		else{
			time=st[3];
			writePath="I:\\0527\\金银建GPS_按手机号分类"+"\\"+files1[i].getName()+"\\"+st[2]+".csv";
			BufferedWriter bw=IsExit(writePath);
			bw.write(time+","+st[4]+","+st[5]+","+st[8]+","+st[9]+","+st[10]+","+st[11]+"\n");
			bw.flush();
		}

		}
		}
	}
}
public static void main(String[] args){
	RunFeature rf=new RunFeature();
	try {

		rf.Ratio("I:\\0527\\金银建GPS\\JYJ",1);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
