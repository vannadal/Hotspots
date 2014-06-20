package dbscan;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
	private List<DataPoint> dataPoints=new ArrayList<DataPoint>();
	private String clusterName;
	public List<DataPoint> GetDataPoints()
	{
		return dataPoints;
	}
	public void SetDataPoints(List<DataPoint> dataPoints)
	{
		this.dataPoints=dataPoints;
	}
	public String GetClusterName()
	{
		return clusterName;
	}
	public void SetClusterName(String clusterName)
	{
		this.clusterName=clusterName;
	}
}
