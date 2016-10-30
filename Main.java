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
		
		BlockingQueue<Message> blocky = new LinkedBlockingQueue<Message>();
		LineBufferResource rey =  new LineBufferResource(blocky);
		ReadingJob joby = new ReadingJob("src/flie.txt", rey);
		/*
		 * Server preparation
		 */
		ILoggingStrategy rot = new SizeBasedLoggingStrategy(res, "file", "xxx",1000,3);
		LoggingJob logingJob = new LoggingJob(rot);
		
		ILoggingStrategy roy = new SizeBasedLoggingStrategy(rey, "flie", "yyy",1000,3);
		LoggingJob logingJoby = new LoggingJob(roy);
		
		
		LoggingThread server = new LoggingThread(logingJob);
		ClientThread client = new ClientThread("xxx", "Memo", job);
		LoggingThread server0 = new LoggingThread(logingJoby);
		ClientThread client0 = new ClientThread("xxx", "Memo", joby);
		Thread t = new Thread(client);
		Thread u = new Thread(server);
		Thread w = new Thread(client0);
		Thread x = new Thread(server0);
		t.start();
		w.start();
		u.start();
		x.start();


	}
}
