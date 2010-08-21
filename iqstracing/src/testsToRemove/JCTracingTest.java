package testsToRemove;

import java.util.Hashtable;

import iqstracing.Event;
import iqstracing.TracingEngine;

public class JCTracingTest {

	public static void main(String[] args) {
		//Here the tests will be called
		minimalTest();
		fullySpecifiedMinimalTest();
		oneEventWithParametersTest();
	}

	private static void minimalTest() {
		//Create a tracing engine, which should trace by default (tracingStatus is true) to console
		//User and session should be randomly generated
		TracingEngine te=new TracingEngine("minimalTest");

		//Trace a minimal event
		Event e1=new Event("START", null, null, null);
		te.trace(e1);

		/* EXPECTED
		 * To console:
		 * <event application="minimalTest" action="START" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="1"></event>
		 *
		 * New lines are for readability only.
		 */
	}

	private static void fullySpecifiedMinimalTest() {
		//Create a tracing engine, set tracing status to true and add console tracer
		//User and session should be randomly generated
		TracingEngine te=new TracingEngine("fullySpecifiedMinimalTest");
		te.setTracingStatus(true);
		te.startTracingToConsole();

		//Trace a minimal event
		Event e1=new Event("START", null, null, null);
		te.trace(e1);

		/* EXPECTED
		 * To console:
		 * <event application="fullySpecifiedMinimalTest" action="START" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="1"></event>
		 *
		 * New lines are for readability only.
		 */
	}

	private static void oneEventWithParametersTest() {
		//Create a tracing engine, which should trace by default (tracingStatus is true) to console
		//User and session should be randomly generated
		TracingEngine te=new TracingEngine("minimalTest");

		//Trace an event with parameters
		Hashtable<String, String> parameters=new Hashtable<String,String>();
		parameters.put("par1", "1");
		parameters.put("par2", "2");
		parameters.put("par3", "3");

		Event e1=new Event("START", parameters, null, null);
		te.trace(e1);

		/* EXPECTED
		 * To console:
		 * <event application="minimalTest" action="START" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="1"><param name="par1" value="1"/>
		 * <param name="par2" value="2"/><param name="par3" value="3"/></event>
		 *
		 * New lines are for readability only. The order of the 'param' nodes is not relevant.
		 */
	}

}
