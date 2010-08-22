package iqstracing;

import java.text.SimpleDateFormat;
import java.util.*;

public class ConsoleTracer extends Tracer {
	public ConsoleTracer(String application, String user,String session){
		super(application,user,session);
	}

	public void trace(Event event, String time, double time_ms, int sequence){
		StringBuilder sb = new StringBuilder();
		sb.append("<event ");
		if (this.application != null)
			sb.append("application=\"" + this.application + "\" ");
		if (event.actionName != null)
			sb.append("action=\"" + event.actionName + "\" ");
		if (this.user != null)
			sb.append("user=\"" + this.user + "\" ");
		if (this.session != null)
			sb.append("session=\"" + this.session + "\" ");
		sb.append("time=\"" + time + "\" ");
		sb.append("time_ms=\"" + time_ms + "\" ");
		if (event.eventType != null)
			sb.append("type=\"" + event.eventType + "\" ");
		if (event.eventPreReference != null)
			sb.append("action_ref=\"" + event.eventPreReference + "\" ");
		sb.append("number=\"" + sequence + "\">");
		if (event.actionParameters != null)
			for (String key : event.actionParameters.keySet())
				sb.append("<param name=\"" + key + "\" value=\"" + event.actionParameters.get(key)
							+ "\"/>");
		if (event.description != null)
			sb.append("<description>" + event.description + "</description>");
		sb.append("</event>");

		System.out.println(sb.toString());
	}

	public void trace(State state, String time, double time_ms, int sequence){
		StringBuilder sb = new StringBuilder();
		sb.append("<state ");
		if (this.application != null)
			sb.append("application=\"" + this.application + "\" ");
		if (this.user != null)
			sb.append("user=\"" + this.user + "\" ");
		if (this.session != null)
			sb.append("session=\"" + this.session + "\" ");
		sb.append("time=\"" + time + "\" ");
		sb.append("time_ms=\"" + time_ms + "\"");
		sb.append("number=\"" + sequence + "\">");
		if (state.description != null)
			sb.append("<description>" + state.description + "</description>");
		sb.append("</state>");
		System.out.println(sb.toString());
	}




	//Per comprovar que funciona:
	public static void main (String[]args){
		Tracer t = new ConsoleTracer("APP", "USER", "SESSION");
		Map<String,String> map = new Hashtable<String,String>();
		map.put("distribution", "Distribution normal");
		map.put("mu", "0");
		map.put("sigma", "1");
		Event e = new Event ("reset_vars", map, "active", null, "Vars have been reseted");
		t.trace(e, (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date()),
				System.nanoTime()/1000000, 1);

		Map<String,String> map2 = new Hashtable<String,String>();
		Event e2 = new Event("reset_vars", map2, "active", null, null);
		t.trace(e2, (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date()),
				System.nanoTime()/1000000, 2);

		State state = new State("State information");
		t.trace(state, (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date()),
				System.nanoTime()/1000000, 3);
	}
}
