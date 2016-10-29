package server;

import jobs.Job;

public class LoggingThread implements Runnable{
	private Job job;

	public  LoggingThread(Job job) {
		// TODO Auto-generated constructor stub
		this.job = job;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		job.execute();
	}
}
