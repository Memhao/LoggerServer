package client;

import jobs.Job;

public class ClientThread implements Runnable{
	private String id;
	private String loggingFileName;
	private Job job;
	public ClientThread(String id,String loggingFileName, Job job)
	{
		this.id = id;
		this.job = job;
		this.loggingFileName = loggingFileName;
	}
	public void run()
	{
		job.execute();
	}
	public String getName()
	{
		return this.loggingFileName;
	}
	public String getID()
	{
		return this.id;
	}
	public String toString()
	{
		return this.id+":["+this.loggingFileName+"]";
	}
}
