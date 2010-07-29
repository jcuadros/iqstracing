package iqstracing;

import java.io.*;

public class FileTracer extends Tracer {
	File file;

	public FileTracer (String user, String session, String file){
		super(user,session);
		this.file = new File(file);
	}
	public FileTracer (String user, String session){
		super(user,session);
		this.file = new File(session);
	}

	public void trace(Event event, String time, double time_ms, int sequence){

	}

	public void trace(State state, String time, double time_ms, int sequence){

	}
}
