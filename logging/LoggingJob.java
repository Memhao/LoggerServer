package logging;

import jobs.Job;

public class LoggingJob implements Job{
	private LoggingStrategy loggingStrategy;
	public  LoggingJob(LoggingStrategy loggingStrategy) {
		// TODO Auto-generated constructor stub
		this.loggingStrategy = loggingStrategy;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		loggingStrategy.log();
	}

}
