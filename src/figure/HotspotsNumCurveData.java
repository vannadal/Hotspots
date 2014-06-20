package figure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class HotspotsNumCurveData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String fileName="D:\\数据\\GPS区域二次聚类上下客数据\\海淀区\\getin_20140101_15_18.csv";
			String writeString="D:\\数据\\绘图数据\\hotspotsCurveData_getin_20140101_15_18.csv";
			BufferedReader br=new BufferedReader(new FileReader(new File(fileName)));
			String line="";
			int former=-1;
			int count=0;
			int A[]=new int[10000];
			int B[]=new int[10000];
			for(int i=0;i<A.length;i++)
			{
				A[i]=0;
				B[i]=0;
			}
			int sum=0;
			while((line=br.readLine())!=null)
			{
				String item[]=line.split(",");
				int latter=Integer.parseInt(item[0]);
				if(former==latter)
				{
					count++;
					former=latter;
				}
				else
				{
					A[count]++;
					count=1;
					former=latter;
				}
			}
//			A[count]++;
			A[0]=0;
			br.close();
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(writeString)));
			for(int i=0;i<A.length;i++)
			{
				B[i]=A[i];
			}
			for(int i=A.length-2;i>=0;i--)
			{
				A[i]=A[i]+A[i+1];
				System.out.println(A[i]);
			}
			for(int i=0;i<A.length;i++)
			{
				bw.write(i+","+A[i]+","+B[i]+"\n");
			}
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
