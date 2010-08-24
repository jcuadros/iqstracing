package iqstracing;

import java.text.SimpleDateFormat;
import java.util.*;

public class ConsoleTracer extends Tracer {
	public ConsoleTracer(String application, String user,String session){
		super(application,user,session);
	}

	public void trace(Event event, String time, double time_ms, int sequence){
		String text = TextWriter.writeTracingText(this, event, time, time_ms, sequence);
		System.out.println(text);
	}

	public void trace(State state, String time, double time_ms, int sequence){
		String text = TextWriter.writeTracingText(this, state, time, time_ms, sequence);
		System.out.println(text);
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
