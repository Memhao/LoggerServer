package client;

public class Message {
	private String line;
	private ISeverity.Severity severity;
	public Message(ISeverity.Severity severity, String line) {
		this.line = line;
		this.severity = severity;
	}
	public ISeverity.Severity getSeverity()
	{
		return this.severity;
	}
	public String toString()
	{
		return this.line;
	}
}
