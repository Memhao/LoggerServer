package logging;

import resource.IResource;
import server.Configuration;

public class LoggingStrategyFactory {
	   public static final String ClientNameLoggingStrategy = "ClientNameLogginStrategy";
	   public static final String MessageSeverityLoggingStrategy = "MessageSeverityLoggingStrategy";
	
	   public ILoggingStrategy getStrategy(String strategy,IResource resource,Configuration config){
		      if(strategy == null){
		         return null;
		      }		
		      if(strategy.equalsIgnoreCase(ClientNameLoggingStrategy)){
		         return new ClientNameLoggingStrategy(resource,config);
		         
		      } else if(strategy.equalsIgnoreCase(MessageSeverityLoggingStrategy)){
		         return new MessageSeverityLoggingStrategy(resource,config);  
		      }
		      
		      return null;
		   }
}
