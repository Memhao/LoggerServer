package resource;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UniqResource implements IResource{
	private final BlockingQueue<Message> bucket;
	
	private static final UniqResource instance = new UniqResource();
	private  UniqResource() {
		// TODO Auto-generated constructor stub
		this.bucket = new  LinkedBlockingQueue<>();
	}
	
	
	public synchronized static UniqResource  getInstance()
	{
		if(instance == null)
		{
			return new UniqResource();
		}
		return instance;
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
