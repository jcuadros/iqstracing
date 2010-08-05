package iqstracing;

import java.text.SimpleDateFormat;
import java.util.*;

public class TracingEngine {
	//Fields:
	String application;
	String user;
	String session;
	static Collection<Tracer> tracerCollection = new LinkedList<Tracer>();
	static boolean status = true;
	static int sequence = 0;

	//Constructors:
	public TracingEngine (String application, String user, String session){
		this.application = application;
		this.user = user;
		this.session = session;
	}

	public TracingEngine (String application, String user){
		this(application,user,user + "_" + RandomGenerator.generate(6));
	}

	public TracingEngine (String application){
		this.application = application;
		this.user = RandomGenerator.generate(6);
		this.session = this.user + "_" + RandomGenerator.generate(6);
	}

	//Methods:
	public static void setTracingStatus(boolean b){
		status = b;
		if (b == false){
			tracerCollection.removeAll(tracerCollection);
		}
	}
	public static boolean getTracingStatus(){
		return status;
	}

	public void startTracingToFile(String fileName){
		/*
		 * This method checks there is not already a FileTracer for this fileName (full path).
		 * in the tracerCollection. In case the FileTracer exists, nothing is done.
		 * If the desired tracer does not exist, it is created and added to the tracerCollection.
		 */
		FileTracer fileTracer = new FileTracer(user, session, fileName);
		if (!tracerCollection.contains(fileTracer))
			tracerCollection.add(fileTracer);

	}

	public void stopTracingToFile(String fileName){
		/*
		 * This method checks there is a FileTracer for this fileName (full path).
		 * in the tracerCollection. In case the FileTracer does not exists, nothing is done.
		 * If the tracer exists, it is removed from the tracerCollection.
		 */
		FileTracer fileTracer = new FileTracer(user, session, fileName);
		if (tracerCollection.contains(fileTracer))
			tracerCollection.remove(fileTracer);
	}

	public void startTracingToConsole(){
		/*
		 * This method checks there is not already a ConsoleTracer in the tracerCollection.
		 * In case the ConsoleTracer exists, nothing is done.
		 * If the desired tracer does not exist, it is created and added to the tracerCollection.
		 */
		ConsoleTracer consoleTracer = new ConsoleTracer(user, session);
		if (!tracerCollection.contains(consoleTracer))
			tracerCollection.add(consoleTracer);
	}

	public void stopTracingToConsole(){
		/*
		 * This method checks there is a ConsoleTracer in the tracerCollection.
		 * In case the ConsoleTracer does not exist, nothing is done.
		 * If the tracer exists, it is removed from the tracerCollection.
		 */
		ConsoleTracer consoleTracer = new ConsoleTracer(user, session);
		if (tracerCollection.contains(consoleTracer))
			tracerCollection.remove(consoleTracer);
	}

	public static void trace (Event event){
		String time;
		double time_ms;

		time = (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date());
		time_ms = System.nanoTime()/1000000;

		/*
		 * For each tracer in the tracerCollection its trace(Event) method is called to produce the desired
		 * tracing.
		 */

		for (Tracer t : tracerCollection){
			sequence++;
			t.trace(event, time, time_ms, sequence);
		}

	}
	public static void trace (State state){
		/*
		 * For each tracer in the tracerCollection its trace(State) method is called to produce the desired
		 * tracing.
		 */
		String time;
		double time_ms;

		time = (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date());
		time_ms = System.nanoTime()/1000000;

		for (Tracer t : tracerCollection){
			sequence++;
			t.trace(state, time, time_ms, sequence);
		}
	}
}
