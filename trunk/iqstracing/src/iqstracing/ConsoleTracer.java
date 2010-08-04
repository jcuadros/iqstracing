package iqstracing;

import java.text.SimpleDateFormat;
import java.util.*;

public class ConsoleTracer extends Tracer {
	public ConsoleTracer(String User,String Session){
		super(User,Session);
	}

	public void trace(Event event, String time, double time_ms, int sequence){
		StringBuilder sb = new StringBuilder();
		sb.append("<event application=\"" + this.application + "\" ");
		sb.append("action=\"" + event.actionName + "\" ");
		sb.append("user=\"" + this.user + "\" ");
		sb.append("session=\"" + this.session + "\" ");
		sb.append("number=\"" + sequence + "\" ");
		sb.append("time=\"" + time + "\" ");
		sb.append("time_ms=\"" + time_ms + "\" ");
		sb.append("type=\"" + event.eventType + "\" ");
		if (event.eventType.toLowerCase() == "reactive")
			sb.append("action_ref=\"" + event.eventPreReference + "\"");
		sb.append(">\n");
		if (event.actionParameters != null){
			for (String key : event.actionParameters.keySet())
				sb.append("\t<param name=\"" + key + "\" value=\"" + event.actionParameters.get(key)
							+ "\"/>\n");
		}
		//if (event.description != null)
		//	sb.append("\t<description>" + event.description + "</description>");

		sb.append("</event>");
		System.out.println(sb.toString());
	}

	public void trace(State state, String time, double time_ms, int sequence){
		StringBuilder sb = new StringBuilder();
		sb.append("<state application=\"" + this.application + "\" ");
		sb.append("user=\"" + this.user + "\" ");
		sb.append("session=\"" + this.session + "\" ");
		sb.append("number=\"" + sequence + "\" ");
		sb.append("time=\"" + time + "\" ");
		sb.append("time_ms=\"" + time_ms + "\"");
		sb.append(">\n");
		sb.append("\t<description>" + state.description + "</description>\n");
		sb.append("</state>");
		System.out.println(sb.toString());
	}




	//Per comprovar que funciona:
	public static void main (String[]args){
		Tracer t = new ConsoleTracer("USER", "SESSION");
		Map<String,String> map = new Hashtable<String,String>();
		map.put("distribution", "Distribution normal");
		map.put("mu", "0");
		map.put("sigma", "1");
		Event e = new Event ("reset_vars", map, "active", null);
		t.trace(e, (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date()),
				System.nanoTime()/1000000, 1);

		Map<String,String> map2 = new Hashtable<String,String>();
		Event e2 = new Event("reset_vars", map2, "active", null);
		t.trace(e2, (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date()),
				System.nanoTime()/1000000, 2);

		State state = new State("State information");
		t.trace(state, (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date()),
				System.nanoTime()/1000000, 3);
	}
}
