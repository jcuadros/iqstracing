package iqstracing;

public class FileTracer extends Tracer {
	String file; //String?

	public FileTracer (String user, String session, String file){
		super(user,session);
		this.file = file;
	}
	public FileTracer (String user, String session){
		super(user,session);
		this.file = session;
	}

	public void trace(Event event, String time, String time_ms, int sequence){

	}

	public void trace(State state, String time, String time_ms, int sequence){

	}

	public void setApplication(){

	}

	public void setUser(){

	}

	public void setSession(){

	}
}
