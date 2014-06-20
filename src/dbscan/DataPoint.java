package dbscan;

public class DataPoint {
	public String dataPointName;
	public  double lng;
	public double atd;
	public int direction;
	public boolean isKey;
	public int speed;
	public DataPoint()
	{
		
	}
	public DataPoint(double lng,double atd,int direction,int speed,boolean isKey)
	{
		this.lng=lng;
		this.atd=atd;
		this.isKey=isKey;
		this.speed=speed;
		this.direction=direction;
	}
	public DataPoint(double lng,double atd,boolean isKey)
	{
		this.lng=lng;
		this.atd=atd;
		this.isKey=isKey;

	}
	public void  SetIsKey(boolean isKey)
	{
		this.isKey=isKey;
	}
	public boolean  GetIsKey()
	{
		return isKey;
	}

	
	public void  Setlng(double lng)
	{
		this.lng=lng;
	}
	public double Getlng()
	{
		return lng;
	}
	public void  Setatd(double atd)
	{
		this.atd=atd;
	}
	public double  Getatd()
	{
		return atd;
	}
	public void SetDirection(int direction)
	{
		this.direction=direction;
	}
	public int GetDirection()
	{
		return direction;
	}
	public void SetSpeed(int speed)
	{
		this.speed=speed;
	}
	public int GetSpeed()
	{
		return speed;
	}
	
}
