package iqstracing;

public class ConsoleTracer extends Tracer {
	public ConsoleTracer(String User,String Session){
		super(User,Session);
	}

	public void trace(Event event, String time, double time_ms, int sequence){
		
	}

	public void trace(State state, String time, double time_ms, int sequence){

	}

}
