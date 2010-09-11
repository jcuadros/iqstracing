package iqstracing;

class ConsoleTracer extends Tracer {
    protected ConsoleTracer(TracingEngine parent) {
        super(parent);
    }

    protected void trace(Event event, String time,
                    double time_ms, int sequence) {
        String text = XMLTraceWriter.writeEvent(this,
                    event, time, time_ms, sequence);
        System.out.println(text);
    }

    protected void trace(State state, String time,
                    double time_ms, int sequence) {
        String text = XMLTraceWriter.writeState(this,
                    state, time, time_ms, sequence);
        System.out.println(text);
    }
}
