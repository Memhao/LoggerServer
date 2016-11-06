package logging;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import resource.IResource;
import resource.ISeverity;
import resource.Message;
import server.Configuration;

public class ClientNameLoggingStrategy implements ILoggingStrategy {

	private volatile boolean run = true;
	private int pillsRecieved;
	private IResource resource;
	
	private Configuration config;
	private ConcurrentHashMap<String, Logger> clientsName = new ConcurrentHashMap<String, Logger>();

	
	
	
	
	
	public ClientNameLoggingStrategy(IResource resource, Configuration config) {
		// TODO Auto-generated constructor stub
		this.resource = resource;
		this.config = config;
	}

	private Logger getLogger(String clientName) {
		if (!clientsName.containsKey(clientName)) {
			FileHandler fileHandler = null;
			try {
				fileHandler = new FileHandler(config.getLogPath() + clientName+"_%g.txt",
						config.getLogSize(), config.getNoOfRotations(), true);
				fileHandler.setFormatter(new java.util.logging.Formatter() {
					@Override
					public String format(LogRecord logRecord) {
						return "[" + logRecord.getLevel() + "] " + "["
								+ clientName + "]" + logRecord.getMessage()
								+ "\r\n";
					}
				});
				Logger logger = Logger.getLogger(clientName);
				logger.addHandler(fileHandler);
				clientsName.put(clientName, logger);
				return logger;
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clientsName.get(clientName);
	}
	
	private void logMessage(Logger logger, Message message) {

		ISeverity.Severity severity = message.getSeverity();
		switch (severity) {
		case INFO :
		case DEBUG :
			logger.info(message.toString());
			break;
		case WARNING :
			logger.warning(message.toString());
			break;
		case ERROR :
		case CRITICAL :
			logger.severe(message.toString());
			break;
		default :
			break;
		}
	}
	private boolean allGamesAreOver(ISeverity.Severity severity) {

		if (ISeverity.Severity.EOF.equals(severity)) {
			++pillsRecieved;
		}

		return pillsRecieved == config.getnoOfLoggingThreads() ? true : false;
	}
	@Override
	public void log() {
		// TODO Auto-generated method stub
		while(run)
		{
			try {
				Message m = resource.take();
				if (allGamesAreOver(m.getSeverity())) {
					run = false;
				}else{
					System.out.println(m+"[CONSUMER-"+m.getClientID()+"_"+config.getLogPath()+"]");
					String clientID = m.getClientID();
					if(!clientID.equals("")){
						Logger logger = getLogger(m.getClientID());
						logMessage(logger, m);
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
	}

}
