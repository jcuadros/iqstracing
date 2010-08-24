package iqstracing;

public class TextWriter {

	public static String writeTracingText(Tracer t, Event event, String time,
								double time_ms, int sequence){

		StringBuilder sb = new StringBuilder();
		sb.append("<event ");
		if (t.application != null)
			sb.append("application=\"" + t.application + "\" ");
		if (event.actionName != null)
			sb.append("action=\"" + event.actionName + "\" ");
		if (t.user != null)
			sb.append("user=\"" + t.user + "\" ");
		if (t.session != null)
			sb.append("session=\"" + t.session + "\" ");
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

		return sb.toString();
}

	public static String writeTracingText(Tracer t, State state, String time,
								double time_ms, int sequence){

		StringBuilder sb = new StringBuilder();
		sb.append("<state ");
		if (t.application != null)
			sb.append("application=\"" + t.application + "\" ");
		if (t.user != null)
			sb.append("user=\"" + t.user + "\" ");
		if (t.session != null)
			sb.append("session=\"" + t.session + "\" ");
		sb.append("time=\"" + time + "\" ");
		sb.append("time_ms=\"" + time_ms + "\"");
		sb.append("number=\"" + sequence + "\">");
		if (state.description != null)
			sb.append("<description>" + state.description + "</description>");
		sb.append("</state>");

		return sb.toString();
	}
}
