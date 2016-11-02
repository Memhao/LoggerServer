package main;

import client.Client;
import server.Configuration;
import server.Server;

public class Main {
	public static void main(String agb[])
	{
//		System.out.println("HJ");
//		LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();
//		LineBufferResource buffer = new LineBufferResource(queue);
//		
//		ReadingJob job_a = new ReadingJob("src/file.txt","000" ,buffer);
//		ReadingJob job_b = new ReadingJob("src/flie.txt","001" ,buffer);
//		ReadingJob job_c = new ReadingJob("src/fiel.txt","010" ,buffer);
//		
//		ILoggingStrategy rot = new NameLoggingStrategy(buffer,"src/out",1000,3,3);
//		LoggingJob logingJob = new LoggingJob(rot);
//		
//		
//		ClientThread cl0 = new ClientThread("000", "xxx", job_a);
//		ClientThread cl1 = new ClientThread("001", "yyy", job_b);
//		ClientThread cl2 = new ClientThread("010", "zzz", job_c);
//		LoggingThread lg0 = new LoggingThread(logingJob);
//		LoggingThread lg1 = new LoggingThread(logingJob);
//		Thread _cl0 = new Thread(cl0);
//		Thread _cl1 = new Thread(cl1);
//		Thread _cl2 = new Thread(cl2);
//		Thread _lg1 = new Thread(lg0);
//		Thread _lg2 = new Thread(lg1);
//		
//		
//		_cl0.start();
//		_cl1.start();
//		_cl2.start();
//		_lg1.start();
//		_lg2.start();
		Server srv = new Server("", new Configuration("", 1, 2, 2));
		srv.subscribeClient(new Client("199.123.111", "src/file.txt", "cl1"));
		srv.subscribeClient(new Client("199.123.112", "src/flie.txt", "cl2"));
		srv.subscribeClient(new Client("199.123.113", "src/fiel.txt", "cl3"));
		srv.start();
	}
}
