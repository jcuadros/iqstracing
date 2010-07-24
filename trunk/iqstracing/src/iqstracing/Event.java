package iqstracing;
import java.util.*;

public class Event {
	//Fields:
	String actionName;
	Map <String,String> actionParameters = new Hashtable<String,String>();
	String eventType; //String?
	String eventPreReference; //String?

	public Event (String actionName, Map<String,String> actionParameters,
								String eventType,String eventPreReference){
		this.actionName = actionName;
		this.actionParameters = actionParameters;
		this.eventType = eventType;
		this.eventPreReference = eventPreReference;
	}
}
