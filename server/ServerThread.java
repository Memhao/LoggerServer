package server;

import jobs.Job;

public class ServerThread implements Runnable{
	private Job job;

	public  ServerThread(Job job) {
		// TODO Auto-generated constructor stub
		this.job = job;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		job.execute();
	}
}
