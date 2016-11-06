package logging;

import resource.IResource;
import server.Configuration;
import server.Criteria;

public class LoggingStrategyFactory {
	
	
	   public static ILoggingStrategy getStrategy(Criteria strategy,IResource resource,Configuration config){
		      if(strategy.equals(Criteria.CLIENT)){
		         return new ClientNameLoggingStrategy(resource,config);
		         
		      } else if(strategy.equals(Criteria.SEVERITY)){
		         return new MessageSeverityLoggingStrategy(resource,config);  
		      }
		      return null;
		   }
}
