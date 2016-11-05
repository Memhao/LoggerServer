package jobs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import resource.ISeverity;
import resource.Message;
import resource.IResource;


public class ReadingJob implements Job{
	private final static Message poisonPill = new Message(ISeverity.Severity.EOF,"" ,"");
	private final IResource messages;
	private String file;
	private String clientID;
	private void getFromFile() throws InterruptedException
	{

			try {
				BufferedReader br = new BufferedReader(new FileReader(file)); 
				String line;
				while ((line = br.readLine()) != null) {
					messages.put(new Message(ISeverity.getSeverity(line),clientID ,line));
					System.out.println(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		messages.put(poisonPill);
		System.out.println("__________________________");
	}


	public ReadingJob(String file,String clientID,IResource messages){
		this.file = file;
		this.messages = messages;
		this.clientID = clientID;
	}
	public void execute() {
		try {
			getFromFile();
		} catch (InterruptedException e) {
			e.printStackTrace(); //--reading was finished  
			Thread.currentThread().interrupt(); //--preserve the request for interruption
			return;//-- here comes the termination
		}

	}

}
