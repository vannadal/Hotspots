package preprogress;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ExtractOriginalGPS {
	
	public static void ExtractByTimestamp(String filenameString,String writefilename)
	{
		try {
			File file=new File(filenameString);
			if(file.exists()){
				BufferedReader br = new BufferedReader(new FileReader(filenameString));
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(writefilename)));
				String line="";
				br.readLine();
				while((line=br.readLine()) != null)
				{
					String item[]=line.split(",");
					String timeString=item[10].substring(0,4)+item[10].substring(5,7)
							+item[10].substring(8,10)+item[10].substring(11,13)+item[10].substring(14,16)+item[10].substring(17,19);
					String temp=item[4]+","+item[5]+","+item[6]+","+item[7]+","+item[8]+","+item[9]+","+timeString+"\n";
					bw.write(temp);
				}
				br.close();
				bw.close();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public static void main(String args[])
	{
		String filenameString="C:\\Users\\vannadal\\Desktop\\20131201_001.txt";
		String writefilenameString="C:\\Users\\vannadal\\Desktop\\extract_20131201_001.txt";
		ExtractByTimestamp(filenameString,writefilenameString);
	}
}
