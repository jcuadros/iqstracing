package iqstracing;

class XMLTraceWriter {

    protected static String writeEvent(Tracer t, Event event, String time,
                    double time_ms, int sequence){

        StringBuilder sb = new StringBuilder();
        sb.append("<event ");
        if (t.getParent().getApplication() != null) {
            sb.append("application=\"" + t.getParent().
                    getApplication() + "\" ");
        }
        if (event.getActionName() != null) {
            sb.append("action=\"" + event.getActionName() + "\" ");
        }
        if (t.getParent().getUser() != null) {
            sb.append("user=\"" + t.getParent().getUser() + "\" ");
        }
        if (t.getParent().getSession() != null) {
            sb.append("session=\"" + t.getParent().getSession()
                    + "\" ");
        }
        sb.append("time=\"" + time + "\" ");
        sb.append("time_ms=\"" + time_ms + "\" ");
        if (event.getEventType() != null) {
            sb.append("type=\"" + event.getEventType() + "\" ");
        }
        if (event.getEventPreReference() != null) {
            sb.append("action_ref=\"" + event.getEventPreReference()
                    + "\" ");
        }
        sb.append("number=\"" + sequence + "\">");
        if (event.getActionParameters() != null) {
            for (String key : event.getActionParameters().
                            keySet()) {
                sb.append("<param name=\"" + key + "\" value=\""
                + event.getActionParameters().get(key)
                + "\"/>");
            }
        }
        if (event.getDescription() != null) {
            sb.append("<description>" + event.getDescription()
                    + "</description>");
        }
        sb.append("</event>");

        return sb.toString();
    }

    protected static String writeState(Tracer t, State state, String time,
                    double time_ms, int sequence) {

        StringBuilder sb = new StringBuilder();
        sb.append("<state ");
        if (t.getParent().getApplication() != null) {
            sb.append("application=\"" + t.getParent().
                    getApplication() + "\" ");
        }
        if (t.getParent().getUser() != null) {
            sb.append("user=\"" + t.getParent().getUser() + "\" ");
        }
        if (t.getParent().getSession() != null) {
            sb.append("session=\"" + t.getParent().getSession()
                    + "\" ");
        }
        sb.append("time=\"" + time + "\" ");
        sb.append("time_ms=\"" + time_ms + "\" ");
        sb.append("number=\"" + sequence + "\">");
        if (state.getDescription() != null) {
            sb.append("<description>" + state.getDescription()
                    + "</description>");
        }
        sb.append("</state>");

        return sb.toString();
    }
}
