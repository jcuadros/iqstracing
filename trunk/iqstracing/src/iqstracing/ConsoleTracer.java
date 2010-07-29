package iqstracing;

import java.text.SimpleDateFormat;
import java.util.*;

public class ConsoleTracer extends Tracer {
	public ConsoleTracer(String User,String Session){
		super(User,Session);
	}

	public void trace(Event event, String time, long time_ms, int sequence){
		StringBuilder sb = new StringBuilder();
		sb.append("<event application=\"" + this.application + "\" ");
		sb.append("action=\"" + event.actionName + "\" ");
		sb.append("user=\"" + this.user + "\" ");
		sb.append("session=\"" + this.session + "\" ");
		sb.append("time=\"" + time + "\" ");
		sb.append("session=\"" + session + "\" ");
		sb.append("time_ms=\"" + time_ms + "\" ");
		sb.append("number=\"" + sequence + "\" ");
		sb.append("type=\"" + event.eventType + "\" ");
		sb.append("action_ref=\"" + event.eventPreReference + "\"");
		sb.append(">\n");
		if (event.actionParameters != null){
			for (String key : event.actionParameters.keySet())
				sb.append("\t<param name=\"" + key + "\" value=\"" + event.actionParameters.get(key)
							+ "\"/>\n");
		}
		sb.append("</event>");
		System.out.println(sb.toString());
	}

	public void trace(State state, String time, long time_ms, int sequence){

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
				System.currentTimeMillis(), 1);

		Map<String,String> map2 = new Hashtable<String,String>();
		Event e2 = new Event("reset_vars", map2, "active", null);
		t.trace(e2, (new SimpleDateFormat("yyyyMMddHHmmss zzz")).format(new Date()),
				System.currentTimeMillis(), 2);
	}
}
