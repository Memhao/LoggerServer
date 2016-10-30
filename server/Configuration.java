package server;

public class Configuration {
	private String name;
	private int logSize;
	private int noOfRotations;
	public Configuration(String name,int logSize,int noOfRotation)
	{
		this.setName(name);
		this.setLogSize(logSize);
		this.setNoOfRotations(noOfRotation);
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
}
