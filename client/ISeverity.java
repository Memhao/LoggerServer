package client;

import client.ISeverity.Severity;

public interface ISeverity {
	enum Severity {
		DEBUG,
		INFO, 
		WARNING ,
		ERROR, 
		CRITICAL ,
		NOSEVERITY , 
		EOF
	}
	public static Severity getSeverity(String line)
	{
		Severity s = Severity.NOSEVERITY;
		if(line.contains("[WARNING]"))
			s= Severity.WARNING;
		else if(line.contains("[DEBUG]"))
			s= Severity.DEBUG;
		else if(line.contains("[CRITICAL]"))
			s= Severity.CRITICAL;
		else if(line.contains("[ERROR]"))
			s= Severity.ERROR;
		else if(line.contains("[INFO]"))
			s= Severity.INFO;
		else 
			s= Severity.NOSEVERITY;
		return s;
	}
}
