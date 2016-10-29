import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import client.ClientThread;
import jobs.ReadingJob;
import logging.LoggingJob;
import logging.RotateLoggingStrategy;
import resource.LineBufferResource;
import resource.Message;
import server.ServerThread;

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
		RotateLoggingStrategy rot = new RotateLoggingStrategy(res, "file", "xxx");
		LoggingJob logingJob = new LoggingJob(rot);
		
		ServerThread server = new ServerThread(logingJob);
		ClientThread client = new ClientThread("xxx", "Memo", job);
		Thread t = new Thread(client);
		Thread u = new Thread(server);
		t.start();
		u.start();


	}
}
