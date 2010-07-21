package iqstracing;

public class ConsoleTracer extends Tracer {
	public ConsoleTracer(String user,String session){
		super(user,session);
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
