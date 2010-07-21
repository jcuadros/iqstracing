package iqstracing;

public abstract class Tracer {
	String user;
	String session;

	public Tracer(String user, String session){
		this.user = user;
		this.session = session;
	}
	public abstract void trace(Event event, String time, String time_ms, int sequence);
	public abstract void trace(State state, String time, String time_ms, int sequence);
	public abstract void setApplication();
	public abstract void setUser();
	public abstract void setSession();
}
