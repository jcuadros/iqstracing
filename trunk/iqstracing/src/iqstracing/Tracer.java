package iqstracing;

abstract class Tracer {
	private TracingEngine parent;

	protected Tracer(TracingEngine parent) {
		this.parent = parent;
	}

	protected abstract void trace(Event event, String time,
				double time_ms, int sequence);

	protected abstract void trace(State state, String time,
				double time_ms, int sequence);

	protected TracingEngine getParent() {
		return parent;
	}
}
