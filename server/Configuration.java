package server;

public class Configuration {
	private String name;
	private int logSize;
	private int noOfRotations;
	private int noOfThreadsPerClient;
	public Configuration(String name,int logSize,int noOfRotation,int noOfThreadsPerClient)
	{
		this.setName(name);
		this.setLogSize(logSize);
		this.setNoOfRotations(noOfRotation);
		this.noOfThreadsPerClient = noOfThreadsPerClient;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLogSize() {
		return logSize;
	}
	public void setLogSize(int logSize) {
		this.logSize = logSize;
	}
	public int getNoOfRotations() {
		return noOfRotations;
	}
	public void setNoOfRotations(int noOfRotations) {
		this.noOfRotations = noOfRotations;
	}
	public int getnoOfThreadsPerClient()
	{
		return this.noOfThreadsPerClient;
	}
}
