package client;

public class Client {
	private String address;
	private String path;
	private String loggingFileName;

	public Client(String address, String path,String loggingFileName)
	{
		this.address = address;
		this.path = path;
		this.loggingFileName = loggingFileName;
	}
	public String getPath()
	{
		return this.path;
	}
	public String getAddress()
	{
		return this.address;
	}
	public String getLoggingFileName() {
		return loggingFileName;
	}
	public String toString()
	{
		return this.address;
	}
}
