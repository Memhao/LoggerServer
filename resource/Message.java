package resource;

public class Message {
	private String line;
	private String clientID;
	private ISeverity.Severity severity;
	public Message(ISeverity.Severity severity,String clientID, String line) {
		this.line = line;
		this.clientID = clientID;
		this.severity = severity;
	}
	public ISeverity.Severity getSeverity()
	{
		return this.severity;
	}
	public String getClientID()
	{
		return this.clientID;
	}
	public String toString()
	{
		return this.line;
	}
}
