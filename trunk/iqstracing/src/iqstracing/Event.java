package iqstracing;

import java.util.Hashtable;

/**
 * Event is the class that allows the storage of information of an action done
 * by the user of a program. This information will then be passed to a tracer
 * and traced to keep a record of every action done while using the program.
 * An Event object encapsulates information about:
 * <ul>
 * <li>The name of the logged action
 * <li>The current value of the different parameters that the application has
 * <li>The type of the event, which can be either active or reactive, depending
 * on whether it is an action of the user or a response of the software to a
 * previous event.
 * <li>The action of the user that is related to this reaction, which will be
 * ignored if the type of the event is active.
 * <li>The description of the logged action, which consists on a human-readable
 * version of the traced information, so that it could be extracted to form a
 * narrative.
 * </ul>
 */

public class Event {
	private String actionName;
	private Hashtable<String, String> actionParameters =
			new Hashtable<String, String>();
	private String eventType;
	private String eventPreReference;
	private String description;


	/**
	 * Constructs an Event object, which will contain the information about
	 * an action that will be traced, from the specified arguments.
	 *
	 * @param actionName String representing the name of the logged action
	 *
	 * @param actionParameters Hashtable that contains the current value of
	 * the different parameters that the application has
	 *
	 * @param eventType String representing the type of the event, which can
	 * be either active or reactive, depending on whether it is an action of
	 * the user or a response of the software to a previous event.
	 *
	 * @param eventPreReference String representing the action of the user
	 * which is related to this reaction, which will be ignored if the type
	 * of the event is active.
	 *
	 * @param description String representing the description of the logged
	 * action, which consists on a human-readable version of the traced
	 * information, so that it could be extracted to form a narrative.
	 */
	public Event(String actionName,
				Hashtable<String, String> actionParameters,
				String eventType, String eventPreReference,
				String description) {
		this.actionName = actionName;
		this.actionParameters = actionParameters;
		this.eventType = eventType;
		this.eventPreReference = eventPreReference;
		this.description = description;
	}

	/**
	 * Gets the name of the action which will be traced.
	 *
	 * @return string representing the name of the logged action
	 */
	protected String getActionName() {
		return actionName;
	}

	/**
	 * Gets the group of parameters and their current value,
	 * which may have been changed after the execution of the
	 * action.
	 *
	 * @return map that contains the current value of
	 * the different parameters that the application has
	 */
	protected Hashtable<String, String> getActionParameters() {
		return actionParameters;
	}

	/**
	 * Gets the type of the event.
	 *
	 * @return string that can be either active or reactive,
	 * depending on whether it is an action of the user or a
	 * response of the software to a previous event.
	 */
	protected String getEventType() {
		return eventType;
	}

	/**
	 * Gets the event that contents that action of the user that
	 * is related to this reaction. It will be ignored if the type
	 * of the event is active.
	 *
	 * @return string representing the action of the user
	 * which is related to this reaction
	 */
	protected String getEventPreReference() {
		return eventPreReference;
	}

	/**
	 * Gets the description of the event.
	 *
	 * @return string representing the description of the logged
	 * action, which consists on a human-readable version of the traced
	 * information, so that it could be extracted to form a narrative.
	 */
	protected String getDescription() {
		return description;
	}
}
