package iqstracing;

/**
 * State is the class that contains the information about
 * traces based on states instead of actions. Through a
 * short description, it provides information about such
 * state.
 */
public class State {
	String description;

	/**
	 * Constructs a State object from a specified description.
	 * This object will then be passed to a tracer.
	 *
	 * @param description String representing a human-readable
	 * version of the state of the application which is being
	 * traced
	 */
	public State(String description){
		this.description = description;
	}
}
