package testsToRemove;

import java.util.*;
import java.io.*;
import iqstracing.*;

public class ProvaFuncionament {
	public static void main (String[]args) throws IOException{
		Map<String,String> map = new Hashtable<String,String>();
		map.put("module", "5N");
		map.put("location", "object A");
		map.put("direction", "vertical");
		map.put("way", "down");
		Event event = new Event ("new force", map, "active", "");

		String application = Tracer.setApplication();
		String user = Tracer.setUser();
		String session = Tracer.setSession();

		TracingEngine tracingEngine = new TracingEngine(application, user, session);

		String fileName = "tracer_" + user + "_session_" +
							session + ".xml";

		tracingEngine.startTracingToFile(fileName);
		tracingEngine.startTracingToConsole();

		if (TracingEngine.getTracingStatus())
			TracingEngine.trace(event);

		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String s;
		while ((s = in.readLine()) != null)
			System.out.println(s);

		TracingEngine.setTracingStatus(false);

		TracingEngine.trace(event);

	}
}
