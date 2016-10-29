import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import client.ClientThread;
import jobs.LoggingJob;
import jobs.ReadingJob;
import logging.ILoggingStrategy;
import logging.SizeBasedLoggingStrategy;
import resource.LineBufferResource;
import resource.Message;
import server.LoggingThread;

public class Main {
	public static void main(String arg[])
	{
		/*
		 * Client preparation
		 */
		BlockingQueue<Message> blockz = new LinkedBlockingQueue<Message>();
		LineBufferResource res =  new LineBufferResource(blockz);
		ReadingJob job = new ReadingJob("src/file.txt", res);
		/*
		 * Server preparation
		 */
		ILoggingStrategy rot = new SizeBasedLoggingStrategy(res, "file", "xxx",1000,3);
		LoggingJob logingJob = new LoggingJob(rot);
		
		LoggingThread server = new LoggingThread(logingJob);
		ClientThread client = new ClientThread("xxx", "Memo", job);
		Thread t = new Thread(client);
		Thread u = new Thread(server);
		t.start();
		u.start();


	}
}
