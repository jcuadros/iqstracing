package iqstracing;

import java.io.*;

public class FileTracer extends Tracer {
	File file;

	public FileTracer (String application, String user, String session, String file){
		super(application,user,session);
		this.file = new File(file);
	}

	public FileTracer (String application, String user, String session){
		super(application, user,session);
		this.file = new File(session);
	}

	public void trace(Event event, String time, double time_ms, int sequence){
		try {
			String text = TextWriter.writeTracingText(this, event, time, time_ms, sequence);
			FileWriter writer = new FileWriter(file,true);
			writer.write(text);
			writer.close();
		} catch (IOException ignored) {}
	}

	public void trace(State state, String time, double time_ms, int sequence){
		try {
			String text = TextWriter.writeTracingText(this, state, time, time_ms, sequence);
			FileWriter writer = new FileWriter(file,true);
			writer.write(text);
			writer.close();
		} catch (IOException ignored) {}
	}
}
