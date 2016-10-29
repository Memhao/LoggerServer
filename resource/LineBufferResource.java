package resource;

import java.util.concurrent.BlockingQueue;


public class LineBufferResource implements Resource{
	private final BlockingQueue<Message> bucket;
	public  LineBufferResource(BlockingQueue<Message> bucket) {
		// TODO Auto-generated constructor stub
		this.bucket = bucket;
	}
	@Override
	public void put(Message message) throws InterruptedException {
		// TODO Auto-generated method stub
		bucket.put(message);
	}

	@Override
	public Message take() throws InterruptedException {
		// TODO Auto-generated method stub
		return bucket.take();
	}

	
}
