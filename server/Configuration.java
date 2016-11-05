package server;

public class Configuration {
	private String name;
	
	private int logSize;
	private int noOfRotations;
	private int noOfThreadsPerClient;
	private String logPath;
	private Criteria criteria;
	
	public Configuration(String name,int logSize,int noOfRotation,int noOfThreadsPerClient,String logPath,Criteria criteria)
	{
		this.name = name;
		this.logSize = logSize;
		this.noOfRotations = noOfRotation;
		this.noOfThreadsPerClient = noOfThreadsPerClient;
		this.criteria = criteria;
	}
	public int getNoOfThreadsPerClient() {
		return noOfThreadsPerClient;
	}
	public Criteria getCriteria() {
		return criteria;
	}
	public String getName() {
		return name;
	}
	public int getLogSize() {
		return logSize;
	}
	public int getNoOfRotations() {
		return noOfRotations;
	}
	public int getnoOfThreadsPerClient()
	{
		return this.noOfThreadsPerClient;
	}
	public String getLogPath()
	{
		return this.logPath;
	}
}
