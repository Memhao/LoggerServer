package server;

import client.Client;
import client.ClientThread;
import jobs.Job;
import jobs.LoggingJob;
import jobs.ReadingJob;
import logging.ILoggingStrategy;
import logging.LoggingStrategyFactory;
import resource.UniqResource;

public class ClientServer {
	private String name;
	private Configuration config;
	private UniqResource resource;
	
	public ClientServer(String name, Configuration config)
	{
		this.name = name;
		this.config = config;
		this.resource = UniqResource.getInstance();
		
	}
	public void startClient(Client client)
	{
		//TODO start client on action
		new Thread(new ClientThread(client.getAddress(), client.getLoggingFileName(), new ReadingJob(client.getPath(), client.getAddress(), resource))).start();	

	}
	public void startServer()
	{
		ILoggingStrategy logstr = LoggingStrategyFactory.getStrategy(config.getCriteria(),resource,config);	
		Job  job= new LoggingJob(logstr);
		for(int i = 0; i < config.getnoOfLoggingThreads(); i++)
		{
			new Thread(new LoggingThread(job)).start();
		}
	}
	public void stopServer()
	{
		
	}
	public String getName()
	{
		return this.name;
	}
}
