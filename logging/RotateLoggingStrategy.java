package logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import resource.ISeverity;
import resource.Message;
import resource.Resource;

public class RotateLoggingStrategy implements LoggingStrategy {

	private Resource resource;
	private PrintWriter out;
	private String clientFileName;
	private String clientID;
	public RotateLoggingStrategy(Resource resource, String clientFileName, String clientID){
		this.resource = resource;
		this.clientFileName = clientFileName;
		this.clientID = clientID;
		
		
		try {
			String fileName = "src/out"+this.clientFileName+"_"+this.clientID+"_"+".txt";
			
			Path path = Paths.get(fileName);
			System.out.println(path.toString());
			if(Files.exists(path))
				out = new PrintWriter(new FileWriter(fileName+"1"));	
			else 
				out = new PrintWriter(new FileWriter(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					out.close();
					break;
				}
			
				System.out.println(m+"[CONSUMER-"+this.clientID+"_"+this.clientFileName+"]");
				out.write(m.toString()+"\n");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
	}

}
