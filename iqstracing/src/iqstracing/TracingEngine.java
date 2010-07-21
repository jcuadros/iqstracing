package iqstracing;

import java.util.*;

public class TracingEngine {
	//Fields:
	String application;
	String user;
	int sequence;
	String session;
	String time;
	String time_ms;
	Collection<String> tracerCollection;
	boolean status;

	//Constructors:
	public TracingEngine (String application, String user, String session){
		this.application = application;
		this.user = user;
		this.session = session;
	}
	public TracingEngine (String application, String user){
		this.application = application;
		this.user = user;
		this.session = user + "_" + RandomGenerator.generate(6);
	}
	public TracingEngine (String application){
		this.application = application;
		this.user = RandomGenerator.generate(6);
		this.session = this.user + "_" + RandomGenerator.generate(6);
	}

	//Methods:
	/*public boolean setStatus(){
		return status;
	}
	public boolean getStatus(){
		return status;
	}
	public void startTracingToFile(){
	}
	public void stopTracingToFile(){
	}
	public void startTracingToConsole(){
	}
	public void stopTracingToConsole(){
	}
	public void trace (Event event){
	}
	public void trace (State state){
	}*/
}
