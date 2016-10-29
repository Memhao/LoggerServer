package jobs;

import java.io.BufferedReader;
import java.io.FileReader;

import resource.ISeverity;
import resource.Message;
import resource.IResource;


public class ReadingJob implements Job{
	private final static Message poisonPill = new Message(ISeverity.Severity.EOF, "");
	private final IResource messages;
	private String file;

	private void getFromFile() throws InterruptedException
	{
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				messages.put(new Message(ISeverity.getSeverity(line), line));
				System.out.println(line);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		messages.put(poisonPill);
	}


	public ReadingJob(String file, IResource messages){
		this.file = file;
		this.messages = messages;
	}
	public void execute() {
		try {
			getFromFile();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}

	}

}
