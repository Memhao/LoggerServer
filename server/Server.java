package server;

import java.util.concurrent.ConcurrentLinkedQueue;


import client.Client;
import client.ClientThread;
import jobs.Job;
import jobs.LoggingJob;
import jobs.ReadingJob;
import logging.ILoggingStrategy;
import logging.NameLoggingStrategy;

import resource.UniqResource;

public class Server implements IServer{
	private  UniqResource resource;
	private ConcurrentLinkedQueue<Client> clients;
	private Configuration configuration;
	private String name;
	
	
	
	public  Server(String name,Configuration config) {
		this.name = name;
		this.configuration = config;
		this.clients = new ConcurrentLinkedQueue<Client>();
		resource = UniqResource.getInstance();
	}
	public void subscribeClient(Client client) {
		clients.add(client);
	}
	public String getName()
	{
		return this.name;
	}
	@Override
	public void subscribeClients(ConcurrentLinkedQueue<Client> clients) {
		this.clients = clients;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		ILoggingStrategy logstr = new NameLoggingStrategy(resource, "src/out", 1000, 3,configuration.getnoOfThreadsPerClient());
		Job  job= new LoggingJob(logstr);
		if(!clients.isEmpty())
		{
			for(int i = 0; i < configuration.getnoOfThreadsPerClient(); i++)
			{
				new Thread(new LoggingThread(job)).start();
			}
			for(Client c : clients)
			{
				new Thread(new ClientThread(c.getAddress(), c.getLoggingFileName(), new ReadingJob(c.getPath(), c.getAddress(), resource))).start();			
			}
		}
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
