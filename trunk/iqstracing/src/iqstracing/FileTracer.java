package iqstracing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class FileTracer extends Tracer {
	private File file;

	protected FileTracer(TracingEngine parent, String file) {
		super(parent);
		if (file == null || file == "") {
			this.file = new File(parent.getSession() + ".log");
		} else {
			this.file = new File(file);
		}
	}

	protected FileTracer(TracingEngine parent) {
		this(parent, null);
	}

	protected void trace(Event event, String time,
				double time_ms, int sequence) {
		String text = XMLTraceWriter.writeEvent(this,
				event, time, time_ms, sequence);
		FileWriter writer;
		try {
			writer = new FileWriter(file, true);
			try {
				writer.write(text);
			} catch (IOException e) {
				System.err.println("/nCould not trace to the "
						+ "desired file./n");
				e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				System.err.println("/nFile was not closed "
						+ "correctly./n");
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.err.println("/nFile could not be created./n");
			e.printStackTrace();
		}
	}

	public void trace(State state, String time,
				double time_ms, int sequence) {
		String text = XMLTraceWriter.writeState(this,
					state, time, time_ms, sequence);
		FileWriter writer;
		try {
			writer = new FileWriter(file, true);
			try {
				writer.write(text);
			} catch (IOException e) {
				System.err.println("/nCould not trace to the "
						+ "desired file./n");
				e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				System.err.println("/nFile was not closed "
						+ "correctly./n");
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.err.println("/nFile could not be created./n");
			e.printStackTrace();
		}
	}

	protected File getFile() {
		return file;
	}
}
