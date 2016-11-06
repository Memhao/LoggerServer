package server;

public class Configuration {
	private String name;
	
	private int logSize;
	private int noOfRotations;
	private int noOfLoggingThreads;
	private String logPath;
	private Criteria criteria;
	
	public Configuration(String name,int logSize,int noOfRotation,int noOfLoggingThreads,String logPath,Criteria criteria)
	{
		this.name = name;
		this.logSize = logSize;
		this.noOfRotations = noOfRotation;
		this.noOfLoggingThreads = noOfLoggingThreads;
		this.criteria = criteria;
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
	public int getnoOfLoggingThreads()
	{
		return this.noOfLoggingThreads;
	}
	public String getLogPath()
	{
		return this.logPath;
	}
}
