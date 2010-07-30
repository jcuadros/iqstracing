package iqstracing;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class TracingEngine {
	//Fields:
	String application;
	String user;
	int sequence;
	String session;
	String time;
	double time_ms;
	Collection<Tracer> tracerCollection;
	boolean status = true;

	//Constructors:
	public TracingEngine (String application, String user, String session){
		this.application = application;
		this.user = user;
		this.session = session;
		this.time = (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date());
		this.time_ms = System.nanoTime()/1000000;
	}

	public TracingEngine (String application, String user){
		this.application = application;
		this.user = user;
		this.session = user + "_" + RandomGenerator.generate(6);
		this.time = (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date());
		this.time_ms = System.nanoTime()/1000000;
	}
	public TracingEngine (String application){
		this.application = application;
		this.user = RandomGenerator.generate(6);
		this.session = this.user + "_" + RandomGenerator.generate(6);
		this.time = (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date());
		this.time_ms = System.nanoTime()/1000000;
	}

	//Methods:
	public void setTracingStatus(boolean b){
		this.status = b;
	}
	public boolean getTracingStatus(){
		return this.status;
	}

	public void startTracingToFile(File file){

	}

	public void stopTracingToFile(File file){

	}

	public void startTracingToConsole(){

	}

	public void stopTracingToConsole(){

	}

	public void trace (Event event){

	}
	public void trace (State state){

	}

	public static void main (String[]args){

	}
}
