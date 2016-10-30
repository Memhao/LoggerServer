package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import resource.ISeverity;
import resource.Message;
import resource.IResource;

public class CountLoggingStrategy implements ILoggingStrategy{
	private IResource resource;
	private FileHandler fileHandler;
	private String clientFileName;
	private String clientID;
	private Logger logger =  Logger.getLogger("MyLog");
	public  CountLoggingStrategy(IResource resource, String clientFileName, String clientID) {
		this.resource = resource;
		this.clientFileName = clientFileName;
		this.clientID = clientID;
		// TODO Auto-generated constructor stub
		String filePath = "src/out"+this.clientFileName+"_"+this.clientID+"_"+".txt";
		try {
			this.fileHandler = new FileHandler(filePath, 1000, 2, true);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileHandler.setFormatter(new java.util.logging.Formatter() {
		  @Override
		  public String format(LogRecord logRecord) {
		    if(logRecord.getLevel() == Level.INFO) {
		      return "[INFO  " + "]  " + logRecord.getMessage() + "\r\n";
		    } else if(logRecord.getLevel() == Level.WARNING) {
		      return "[WARN  "  + "]  " + logRecord.getMessage() + "\r\n";
		    } else if(logRecord.getLevel() == Level.SEVERE) {
		      return "[ERROR "  + "]  " + logRecord.getMessage() + "\r\n";
		    } else {
		      return "[OTHER "  + "]  " + logRecord.getMessage() + "\r\n";
		    }
		  }
		  });
		logger.addHandler(fileHandler);
	}
	@Override
	public void log() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Message m = resource.take();
				if(m.getSeverity() == ISeverity.Severity.EOF)
				{
					break;
				}
			
				System.out.println(m+"[CONSUMER-"+this.clientID+"_"+this.clientFileName+"]");
				logger.info(m.toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
	}

}
