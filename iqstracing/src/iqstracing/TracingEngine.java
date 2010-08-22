package iqstracing;

import java.text.SimpleDateFormat;
import java.util.*;

public class TracingEngine {
	String application;
	String user;
	String session;
	static Collection<Tracer> tracerCollection = new LinkedList<Tracer>();
	static boolean status = true;
	static int sequence = 0;

	/**
	 * Constructs a TracingEngine object using the specified
	 * string values of application, user and session, which
	 * will be assigned to their respective fields.
	 *
	 * @param application  string that represents the program
	 * or java applet that requests the tracing of an event
	 *
	 * @param user  string representing the person who is
	 * using the application and whose actions are being traced
	 *
	 * @param session  string that represents the logged session
	 */
	public TracingEngine (String application, String user, String session){
		this.application = application;
		this.user = user;
		this.session = session;
	}


	/**
	 * Constructs a TracingEngine object using the specified
	 * string values of application and user. The value of
	 * session is not specified, so it will be created from
	 * the user string and a random sequence of six characters
	 * which can be capital letters or numbers.
	 *
	 * @param application  string that represents the program
	 * or java applet that requests the tracing of an event
	 *
	 * @param user  string representing the person who is
	 * using the application and whose actions are being traced
	 */
	public TracingEngine (String application, String user){
		this(application,user,user + "_" + RandomGenerator.generate(6));
	}


	/**
	 * Constructs a TracingEngine object using only the specified
	 * string value of application. The values of user and session
	 * are not specified. The user field will be filled with a
	 * randomly created sequence of six characters that can be
	 * capital letters or numbers. The session field will be
	 * the user string concatenated to a random sequence of six
	 * characters.
	 *
	 * @param application  string that represents the program
	 * or java applet that requests the tracing of an event
	 */
	public TracingEngine (String application){
		this.application = application;
		this.user = RandomGenerator.generate(6);
		this.session = this.user + "_" + RandomGenerator.generate(6);
	}


	/**
	 * Sets the status field to the specified boolean value. This
	 * value will be true by default and setting it to false
	 * would easily stop the tracing system.
	 *
	 * @param b  boolean expression whose value will be assigned to
	 * the status field
	 */
	public void setTracingStatus(boolean b){
		status = b;
		if (b == false)
			tracerCollection.removeAll(tracerCollection);
	}


	/**
	 * Gets the value of the status field, which will determine
	 * whether the TracingEngine is tracing or not by means of
	 * a boolean.
	 *
	 * @return the boolean value of the status field
	 */
	public boolean getTracingStatus(){
		return status;
	}

	/**
	 * Checks there is not already a FileTracer object for the
	 * specified fileName (full path) in the tracerCollection.
	 * In case the FileTracer object exists, nothing is done. If
	 * the desired tracer does not exist, it is created and added
	 * to the tracerCollection.
	 *
	 * @param fileName  string representing the name of the file
	 * where the information will be traced.
	 */
	public void startTracingToFile(String fileName){
		FileTracer fileTracer = new FileTracer(user, session, fileName);
		if (!tracerCollection.contains(fileTracer))
			tracerCollection.add(fileTracer);
	}


	/**
	 * Checks there is a FileTracer for this fileName (full path)
	 * in the tracerCollection. In case the FileTracer does not
	 * exist, nothing is done. If the FileTracer exists, it is
	 * removed from the tracerCollection.
	 *
	 * @param fileName  string representing the name of the file
	 * where the information has been traced.
	 */
	public void stopTracingToFile(String fileName){
		FileTracer fileTracer = new FileTracer(application, user, session, fileName);
		if (tracerCollection.contains(fileTracer))
			tracerCollection.remove(fileTracer);
	}


	/**
	 * Checks there is not already a ConsoleTracer in the
	 * tracerCollection. In case the ConsoleTracer exists,
	 * nothing is done. If the desired tracer does not exist,
	 * it is created and added to the tracerCollection.
	 */
	public void startTracingToConsole(){
		ConsoleTracer consoleTracer = new ConsoleTracer(application, user, session);
		if (!tracerCollection.contains(consoleTracer))
			tracerCollection.add(consoleTracer);
	}


	/**
	 * Checks there is a ConsoleTracer in the tracerCollection.
	 * In case the ConsoleTracer does not exist, nothing is done.
	 * If the tracer exists, it is removed from the tracerCollection.
	 */
	public void stopTracingToConsole(){
		ConsoleTracer consoleTracer = new ConsoleTracer(application, user, session);
		if (tracerCollection.contains(consoleTracer))
			tracerCollection.remove(consoleTracer);
	}


	/**
	 * For each tracer in the tracerCollection, its trace(Event) method
	 * is called to produce the desired tracing.
	 *
	 * @param event  Event object that stores all the information about
	 * the action that will be traced
	 */
	public void trace (Event event){
		String time;
		double time_ms;

		time = (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date());
		time_ms = System.nanoTime()/1000000;

		if (tracerCollection.isEmpty())
			tracerCollection.add(new ConsoleTracer(application, user, session));

		for (Tracer t : tracerCollection){
			sequence++;
			t.trace(event, time, time_ms, sequence);
		}

	}


	/**
	 * For each tracer in the tracerCollection its trace(State) method is
	 * called to produce the desired tracing.
	 *
	 * @param state  State object.....
	 */
	public void trace (State state){
		String time;
		double time_ms;

		time = (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date());
		time_ms = System.nanoTime()/1000000;

		if (tracerCollection.isEmpty())
			tracerCollection.add(new ConsoleTracer(application, user, session));

		for (Tracer t : tracerCollection){
			sequence++;
			t.trace(state, time, time_ms, sequence);
		}
	}
}
