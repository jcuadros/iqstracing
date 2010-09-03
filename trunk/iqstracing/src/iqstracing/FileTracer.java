package iqstracing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class FileTracer extends Tracer {
	private File file;

	protected FileTracer(TracingEngine parent, String file) {
		super(parent);
		this.file = new File(file);
	}

	protected FileTracer(TracingEngine parent) {
		super(parent);
		this.file = new File(parent.getSession() + ".log");
	}

	protected void trace(Event event, String time,
				double time_ms, int sequence) {
		try {
			String text = XMLTraceWriter.writeEvent(this,
					event, time, time_ms, sequence);
			FileWriter writer = new FileWriter(file, true);
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());

			//FALTA!
			/*
			 *     ChangedCharSetException,
			 *     CharacterCodingException,
			 *     CharConversionException,
			 *     ClosedChannelException,
			 *     EOFException,
			 *     FileLockInterruptionException,
			 *     FileNotFoundException,
			 *     HttpRetryException,
			 *     IIOException,
			 *     InterruptedIOException,
			 *     InvalidPropertiesFormatException,
			 *     JMXProviderException,
			 *     JMXServerErrorException,
			 *     MalformedURLException,
			 *     ObjectStreamException,
			 *     ProtocolException,
			 *     RemoteException,
			 *     SaslException,
			 *     SocketException,
			 *     SSLException,
			 *     SyncFailedException,
			 *     UnknownHostException,
			 *     UnknownServiceException,
			 *     UnsupportedEncodingException,
			 *     UTFDataFormatException,
			 *     ZipException
			 */


		}
	}

	public void trace(State state, String time,
				double time_ms, int sequence) {
		try {
			String text = XMLTraceWriter.writeState(this,
					state, time, time_ms, sequence);
			FileWriter writer = new FileWriter(file, true);
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			System.err.println(e.getStackTrace().toString());
			//FALTA!!

		}
	}

	protected File getFile() {
		return file;
	}
}
