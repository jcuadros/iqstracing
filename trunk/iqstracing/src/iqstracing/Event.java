package iqstracing;
import java.util.*;

/**
 * Event is the class that allows the storage of information of an action done by
 * the user of a program. This information will then be passed to a tracer and
 * traced to keep a record of every action done while using the program.
 * An Event object encapsulates information about:
 * <ul>
 * <li>The name of the logged action
 * <li>The current value of the different parameters that the application has
 * <li>The type of the event, which can be either active or reactive, depending
 * on whether it is an action of the user or a response of the software to a previous event.
 * <li>The action of the user that is related to this reaction, which will be ignored if the
 * type of the event is active.
 * <li>The description of the logged action, which consists on a human-readable version
 * of the traced information, so that it could be extracted to form a narrative.
 * </ul>
 */

public class Event {
	String actionName;
	Map <String,String> actionParameters = new Hashtable<String,String>();
	String eventType;
	String eventPreReference;
	String description;


	/**
	 * Constructs an Event object, which will contain the information about
	 * an action that will be traced, from the specified arguments.
	 *
	 * @param actionName String representing the name of the logged action
	 *
	 * @param actionParameters Map that contains the current value of the
	 * different parameters that the application has
	 *
	 * @param eventType String representing the type of the event, which can be either
	 * active or reactive, depending on whether it is an action of the user or a
	 * response of the software to a previous event.
	 *
	 * @param eventPreReference String representing the action of the user which is
	 * related to this reaction, which will be ignored if the type of the event is active.
	 *
	 * @param description String representing the description of the logged action,
	 * which consists on a human-readable version of the traced information, so that
	 * it could be extracted to form a narrative.
	 */
	public Event (String actionName, Map<String,String> actionParameters,
				String eventType,String eventPreReference, String description){
		this.actionName = actionName;
		this.actionParameters = actionParameters;
		this.eventType = eventType;
		this.eventPreReference = eventPreReference;
		this.description = description;
	}
}
