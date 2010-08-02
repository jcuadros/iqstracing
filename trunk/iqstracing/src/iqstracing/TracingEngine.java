package iqstracing;

import java.text.SimpleDateFormat;
import java.util.*;

public class TracingEngine {
	//Fields:
	String application;
	String user;
	int sequence;
	String session;
	Collection<Tracer> tracerCollection;
	boolean status = true;

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
	public void setTracingStatus(boolean b){
		this.status = b;
	}
	public boolean getTracingStatus(){
		return this.status;
	}

	public void startTracingToFile(String fileName){
		/*
		 * This method checks there is not already a FileTracer for this fileName (full path).
		 * in the tracerCollection. In case the FileTracer exists, nothing is done.
		 * If the desired tracer does not exist, it is created and added to the tracerCollection.
		 */
	}

	public void stopTracingToFile(String fileName){
		/*
		 * This method checks there is a FileTracer for this fileName (full path).
		 * in the tracerCollection. In case the FileTracer does not exists, nothing is done.
		 * If the tracer exists, it is removed from the tracerCollection.
		 */
	}

	public void startTracingToConsole(){
		/*
		 * This method checks there is not already a ConsoleTracer in the tracerCollection.
		 * In case the ConsoleTracer exists, nothing is done.
		 * If the desired tracer does not exist, it is created and added to the tracerCollection.
		 */
	}

	public void stopTracingToConsole(){
		/*
		 * This method checks there is a ConsoleTracer in the tracerCollection.
		 * In case the ConsoleTracer does not exist, nothing is done.
		 * If the tracer exists, it is removed from the tracerCollection.
		 */
	}

	public void trace (Event event){
		String time;
		double time_ms;

		time = (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date());
		time_ms = System.nanoTime()/1000000;

		/*
		 * For each tracer in the tracerCollection its trace(Event) method is called to produce the desired
		 * tracing.
		 */

	}
	public void trace (State state){
		/*
		 * For each tracer in the tracerCollection its trace(State) method is called to produce the desired
		 * tracing.
		 */
	}
}
