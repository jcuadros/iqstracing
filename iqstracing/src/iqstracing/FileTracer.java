package iqstracing;

import java.io.*;

public class FileTracer extends Tracer {
	File file;

	public FileTracer (String application, String user, String session, String file){
		super(application,user,session);
		if (file.toLowerCase().endsWith(".xml"))
			this.file = new File(file);
		else
			this.file = new File(file + ".xml");
	}
	public FileTracer (String application, String user, String session){
		super(application, user,session);
		this.file = new File(session + ".xml");
	}

	public void trace(Event event, String time, double time_ms, int sequence){
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<event ");
			if (this.application != null)
				sb.append("<application=\"" + this.application + "\" ");
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
				sb.append("action_ref=\"" + event.eventPreReference + "\"");
			sb.append("number=\"" + sequence + "\">");
			if (event.actionParameters != null)
				for (String key : event.actionParameters.keySet())
					sb.append("<param name=\"" + key + "\" value=\"" + event.actionParameters.get(key)
								+ "\"/>");
			if (event.description != null)
				sb.append("\t<description>" + event.description + "</description>");
			sb.append("</event>");

			FileWriter writer = new FileWriter(file,true);
			writer.write(sb.toString());
			writer.close();
		} catch (IOException ignored) {}
	}

	public void trace(State state, String time, double time_ms, int sequence){
		try {
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

			FileWriter writer = new FileWriter(file,true);
			writer.write(sb.toString());
			writer.close();
		} catch (IOException ignored) {}
	}
}
