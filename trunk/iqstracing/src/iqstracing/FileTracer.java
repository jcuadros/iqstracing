package iqstracing;

import java.io.*;

public class FileTracer extends Tracer {
	File file;

	public FileTracer (String user, String session, String file){
		super(user,session);
		if (file.toLowerCase().endsWith(".xml"))
			this.file = new File(file);
		else
			this.file = new File(file + ".xml");
	}
	public FileTracer (String user, String session){
		super(user,session);
		this.file = new File(session + ".xml");
	}

	public void trace(Event event, String time, double time_ms, int sequence){
		try {
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
			FileWriter writer = new FileWriter(file,true);
			writer.write(sb.toString());
			writer.close();
		} catch (IOException ignored) {}
	}

	public void trace(State state, String time, double time_ms, int sequence){
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<state application=\"" + this.application + "\" ");
			sb.append("user=\"" + this.user + "\" ");
			sb.append("session=\"" + this.session + "\" ");
			sb.append("number=\"" + sequence + "\" ");
			sb.append("time=\"" + time + "\" ");
			sb.append("time_ms=\"" + time_ms + "\" ");
			sb.append(">\n");
			sb.append("\t<description>" + state.description + "</descrition>");
			sb.append("</state>");
			FileWriter writer = new FileWriter(file,true);
			writer.write(sb.toString());
			writer.close();
		} catch (IOException ignored) {}
	}
}
