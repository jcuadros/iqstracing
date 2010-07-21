package iqstracing;
import java.util.*;

public class Event {
	//Fields:
	String actionName;
	Map <String,Integer> actionParameters = new Hashtable<String,Integer>();
	String eventType; //String?
	String eventPreReference; //String?

	public Event (String actionName, Map<String,Integer> actionParameters,
								String eventType,String eventPreReference){
		this.actionName = actionName;
		this.actionParameters = actionParameters;
		this.eventType = eventType;
		this.eventPreReference = eventPreReference;
	}
}
