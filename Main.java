import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import client.ClientThread;
import client.Message;
import client.ReadingJob;
import resource.LineBufferResource;

public class Main {
	public static void main(String arg[])
	{
	
		BlockingQueue<Message> blockz = new LinkedBlockingQueue<Message>();
		LineBufferResource res =  new LineBufferResource(blockz);
		
		ReadingJob job = new ReadingJob("src/file.txt", res);
		ClientThread client = new ClientThread("xxx", "Memo", job);
		Thread t = new Thread(client);
		t.start();


	}
}
