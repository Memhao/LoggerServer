package jobs;

import logging.ILoggingStrategy;

public class LoggingJob implements Job{
	private ILoggingStrategy loggingStrategy;
	public  LoggingJob(ILoggingStrategy loggingStrategy) {
		// TODO Auto-generated constructor stub
		this.loggingStrategy = loggingStrategy;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		loggingStrategy.log();
	}

}
