package com.furiapolitehnicii.constants;

import com.furiapolitehnicii.loggingserver.resources.ISeverity;
import com.furiapolitehnicii.loggingserver.resources.Message;

public interface Constants {

	Message POISON_PILL = new Message(ISeverity.Severity.EOF, "", "");
	String SEVERITY_PATTERN = "[\\s]*[\\[][\\s]*(INFO|DEBUG|WARNING|ERROR|CRITICAL)[\\s]*[\\]][\\s]*(.*)";
	String THREE_DIGIT_PATTERN = "[1-9][0-9]{0,2}";
	String FIVE_DIGIT_PATTERN = "[1-9][0-9]{0,4}";
	String PATH_PATERN = "(?:[a-zA-Z]\\:|\\/\\/[\\w\\.]+\\/[\\w.$]+|[a-zA-Z0-9]*)\\/(?:[\\w]+\\/)*\\w([\\w.])+";

}
