package preprogress;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
public class DataClassifyByDate {
	
		private BufferedReader br;
		public File wfile;
//		public void MakeDir()
//		{
//			for(int i=2000;i<=2015;i++)
//			{
//				for(int j=1;j<=12;j++)
//					if(j<10)
//						{
//						wfile=new File("H:\\0527\\JYJGPSCleaned"+"\\"+i+"0"+j);
//						if(!wfile.exists())wfile.mkdirs();
//						}
//					else 
//					{
//						wfile=new File("H:\\0527\\JYJGPSCleaned"+"\\"+i+String.valueOf(j));
//						if(!wfile.exists())wfile.mkdirs();
//					}
//			}
//		}
		
		public void Classify(String RootFilePath) throws Exception{
			File[] files1,files2;
			File file=new File(RootFilePath),wfile;
			files1=file.listFiles();
			String record,time,writePath;
			String[] st;
			for(int i=1;i<files1.length;i++)
//			for(int i=1;i<=7;i++)
			{
				System.out.println(files1[i].getName());
				wfile=new File("D:\\数据\\GPS按手机号分类"+"\\"+files1[i].getName());
				if(!wfile.exists())wfile.mkdirs();
				files2=files1[i].listFiles();
				writePath="D:\\数据\\GPS按手机号分类"+"\\"+files1[i].getName()+".csv";
				BufferedWriter bw=new BufferedWriter(new FileWriter(new File(writePath),true));

				for(int j=0;j<files2.length;j++)
				{
					br=new BufferedReader(new FileReader(files2[j]));
					record=br.readLine();
					while((record=br.readLine())!=null)
					{
						st=record.split(",");
						if(st.length<13)continue;
						
						time=st[3].substring(0,4)+st[3].substring(5,7)+st[3].substring(8,10)+
								st[3].substring(11,13)+st[3].substring(14,16)+st[3].substring(17,19);
						if(!time.substring(0,4).equals("2014"))continue;
						
						bw.write(time+","+st[1]+","+st[11]+","+st[12]+","+st[6]+","+st[7]+","+st[9]+","+"\n");
						
					}
				}
				bw.close();
			}
		}
		public static void main(String[] args){
			
			DataClassifyByDate rf=new DataClassifyByDate();
			try {
//				rf.MakeDir();
				rf.Classify("E:\\数据\\金银建GPS数据_8字段");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
