package client;

import jobs.Job;

public class ClientThread implements Runnable{
	private String id;
	private String name;
	private Job job;
	public ClientThread(String id,String name, Job job)
	{
		this.id = id;
		this.job = job;
		this.name = name;
	}
	public void run()
	{
		job.execute();
	}
	public String getName()
	{
		return this.name;
	}
	public String getID()
	{
		return this.id;
	}
	public String toString()
	{
		return this.id+":["+this.name+"]";
	}
}
