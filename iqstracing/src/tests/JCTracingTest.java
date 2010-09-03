package tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import iqstracing.Event;
import iqstracing.State;
import iqstracing.TracingEngine;

public class JCTracingTest {

	public static void main(String[] args) {
		//Here the tests will be called
		minimalTest();
		minimalStateTest();
		fullySpecifiedMinimalTest();
		oneEventWithParametersTest();
		oneEventWithParametersAndDescriptionTest();
		oneEventWithDescriptionTest();
		minimalTestToFile();
		multipleEventTest();
		multipleEventAndTracerTest();
		openAndCloseTracersTest();
		simultaneousTracingEnginesTest();
		simultaneousTracingEnginesTest2();
		deleteAndCreateFileTest();
	}

	private static void minimalTest() {
		//Create a tracing engine, which should trace by default
		//(tracingStatus is true) to console
		//User and session should be randomly generated
		TracingEngine te = new TracingEngine("minimalTest");

		//Trace a minimal event
		Event e1 = new Event("START", null, null, null, null);
		te.trace(e1);

		/* EXPECTED
		 * To console:
		 * <event application="minimalTest" action="START"
		 * user="random" session="random" time="20010101000000
		 * UTM" time_ms="1234567" number="1"></event>
		 *
		 * New lines are for readability only.
		 */
	}

	private static void minimalStateTest() {
		//Create a tracing engine, which should trace a state
		//by default (tracingStatus is true) to console
		//User and session should be randomly generated

		TracingEngine te = new TracingEngine("minimalTest");

		//Trace a minimal state
		State s = new State("A brief description.");
		te.trace(s);

		/* EXPECTED
		 * To console:
		 * <state application="minimalTest" user="random"
		 * session="random" time="20010101000000 UTM"
		 * time_ms="1234567" number="1"><description>A brief
		 * description.</description></state>
		 *
		 * New lines are for readability only.
		 */
	}

	private static void fullySpecifiedMinimalTest() {
		//Create a tracing engine, set tracing status to true
		//and add console tracer
		//User and session should be randomly generated
		TracingEngine te = new TracingEngine(
						"fullySpecifiedMinimalTest");
		te.setTracingStatus(true);
		te.startTracingToConsole();

		//Trace a minimal event
		Event e1 = new Event("START", null, null, null, null);
		te.trace(e1);

		/* EXPECTED
		 * To console:
		 * <event application="fullySpecifiedMinimalTest" action="START"
		 * user="random" session="random" time="20010101000000 UTM"
		 * time_ms="1234567" number="1"></event>
		 *
		 * New lines are for readability only.
		 */
	}

	private static void oneEventWithParametersTest() {
		//Create a tracing engine, which should trace by default
		//(tracingStatus is true) to console
		//User and session should be randomly generated
		TracingEngine te = new TracingEngine(
				"oneEventWithParametersTest");

		//Trace an event with parameters
		Hashtable<String, String> parameters =
						new Hashtable<String, String>();
		parameters.put("par1", "1");
		parameters.put("par2", "2");
		parameters.put("par3", "3");

		Event e1 = new Event("START", parameters, null, null, null);
		te.trace(e1);

		/* EXPECTED
		 * To console:
		 * <event application="oneEventWithParametersTest"
		 * action="START" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="1">
		 * <param name="par1" value="1"/><param name="par2" value="2"/>
		 * <param name="par3" value="3"/></event>
		 *
		 * New lines are for readability only. The order of the
		 * 'param' nodes is not relevant.
		 */
	}

	private static void oneEventWithParametersAndDescriptionTest() {
		//Create a tracing engine, which should trace by default
		//(tracingStatus is true) to console
		//User and session should be randomly generated
		TracingEngine te = new TracingEngine(
				"oneEventWithParametersAndDescriptionTest");

		//Trace an event with parameters and description
		Hashtable<String, String> parameters =
					new Hashtable<String, String>();
		parameters.put("par1", "1");
		parameters.put("par2", "2");
		parameters.put("par3", "3");

		String description = "A brief description.";

		Event e1 = new Event("START", parameters, null, null,
								description);
		te.trace(e1);

		/* EXPECTED
		 * To console:
		 * <event application="oneEventWithParametersAndDescriptionTest"
		 * action="START" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="1">
		 * <param name="par1" value="1"/><param name="par2" value="2"/>
		 * <param name="par3" value="3"/><description>
		 * A brief description.</description></event>
		 *
		 * New lines are for readability only. The order of the
		 * 'param' nodes is not relevant.
		 */
	}

	private static void oneEventWithDescriptionTest() {
		//Create a tracing engine, which should trace by default
		//(tracingStatus is true) to console
		//User and session should be randomly generated
		TracingEngine te = new TracingEngine(
				"oneEventWithDescriptionTest");

		//Trace an event with description and without parameters
		String description = "A brief description.";

		Event e1 = new Event("START", null, null, null,
								description);
		te.trace(e1);

		/* EXPECTED
		 * To console:
		 * <event application="oneEventWithDescriptionTest"
		 * action="START" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="1">
		 * <description>A brief description.</description></event>
		 *
		 * New lines are for readability only.
		 */
	}

	private static void minimalTestToFile() {
		//Create a tracing engine, which should trace by default
		//(tracingStatus is true) to console
		//User and session should be randomly generated
		TracingEngine te = new TracingEngine("minimalTestToFile");
		te.stopTracingToConsole();

		//Clear logtest1.log file and set tracing to it
		new File("logtest1.log").delete();
		te.startTracingToFile("logtest1.log");

		//Trace a minimal event
		Event e1 = new Event("START", null, null, null, null);
		te.trace(e1);

		/* EXPECTED
		 * To file "logtest1.log":
		 * <event application="minimalTestToFile" action="START"
		 * user="random" session="random" time="20010101000000
		 * UTM" time_ms="1234567" number="1"></event>
		 *
		 * Nothing should be traced to console.
		 * New lines are for readability only.
		 */
	}

	private static void multipleEventTest() {
		//Create a tracing engine, which should trace by default
		//(tracingStatus is true) to console more than one event
		//User and session should be randomly generated

		TracingEngine te = new TracingEngine("multipleEventTest");

		//Trace an event with parameters and description
		Hashtable<String, String> parameters1 =
					new Hashtable<String, String>();
		parameters1.put("par1", "1");
		parameters1.put("par2", "2");
		parameters1.put("par3", "3");

		String description = "A brief description.";

		Event e1 = new Event("START", parameters1, null, null,
								description);

		Hashtable<String, String> parameters2 =
					new Hashtable<String, String>();
		parameters2.put("par4", "4");
		parameters2.put("par5", "5");
		parameters2.put("par6", "6");
		parameters2.put("par7", "7");
		parameters2.put("par8", "8");

		Event e2 = new Event("SECOND EVENT", parameters2, null,
								null, null);

		te.trace(e1);
		te.trace(e2);

		/* EXPECTED
		 * To Console:
		 *
		 * <event application="multipleEventTest" action="START"
		 * user="random" session="random" time="20010101000000
		 * UTM" time_ms="1234567" number="1"><param name="par1"
		 * value="1"/><param name="par2" value="2"/><param
		 * name="par3" value="3"/><description>A brief description.
		 * </description></event>
		 * <event application="multipleEventTest" action="SECOND EVENT"
		 * user="random" session="random" time="20010101000000
		 * UTM" time_ms="1234567" number="2"><param name="par4"
		 * value="4"/><param name="par5" value="5"/><param
		 * name="par6" value="6"/><param name="par7" value="7"/>
		 * <param name="par8" value="8"/><description>A brief
		 * description.</description></event>
		 *
		 * New lines are for readability only.
		 * The order of the 'param' nodes is not relevant.
		 */
	}

	private static void multipleEventAndTracerTest() {
		//Create a tracing engine, which should trace by default
		//(tracingStatus is true) to console more than one event
		//User and session should be randomly generated

		TracingEngine te = new TracingEngine(
					"multipleEventAndTracerTest");

		String file1 = "FileTest1.log";
		String file2 = "FileTest2.log";
		te.startTracingToFile(file1);
		te.startTracingToFile(file2);

		//Trace an event with parameters and description
		Hashtable<String, String> parameters1 =
					new Hashtable<String, String>();
		parameters1.put("par1", "1");
		parameters1.put("par2", "2");
		parameters1.put("par3", "3");

		String description = "A brief description.";

		Event e1 = new Event("START", parameters1, null, null,
								description);

		Hashtable<String, String> parameters2 =
					new Hashtable<String, String>();
		parameters2.put("par4", "4");
		parameters2.put("par5", "5");

		Event e2 = new Event("SECOND EVENT", parameters2, null,
								null, null);

		Event e3 = new Event("THIRD EVENT", null, null, null, null);

		te.trace(e1);
		te.trace(e2);
		te.trace(e3);

		/* EXPECTED
		 * To Console, "FileTest1.log" and "FileTest2.log":
		 *
		 * <event application="multipleEventAndTracerTest"
		 * action="START" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="1">
		 * <param name="par1" value="1"/><param name="par2" value="2"/>
		 * <param name="par3" value="3"/><description>A brief
		 * description.</description></event><event
		 * application="multipleEventAndTracerTest"
		 * action="SECOND EVENT" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="2">
		 * <param name="par4" value="4"/><param name="par5" value="5"/>
		 * <description>A brief description.</description></event><event
		 * application="multipleEventAndTracerTest" action="THIRD EVENT"
		 * user="random" session="random" time="20010101000000
		 * UTM" time_ms="1234567" number="3"></event>
		 *
		 * New lines are for readability only.
		 * The order of the 'param' nodes is not relevant.
		 */
	}

	private static void openAndCloseTracersTest() {
		//Create a tracing engine, which should trace by default
		//(tracingStatus is true) to console and different files,
		//which will be started or stopped at different times
		//User and session should be randomly generated
		TracingEngine te = new TracingEngine("openAndCloseTracersTest");

		//Tracers:
		String file1 = "openAndCloseTracersTest1.log";
		String file2 = "openAndCloseTracersTest2.log";

		//Different events:
		Hashtable<String, String> parameters1 =
					new Hashtable<String, String>();
		parameters1.put("par1", "1");
		parameters1.put("par2", "2");
		parameters1.put("par3", "3");

		String description = "A brief description.";

		Event e1 = new Event("START", parameters1, null, null,
								description);

		Hashtable<String, String> parameters2 =
					new Hashtable<String, String>();
		parameters2.put("par4", "4");
		parameters2.put("par5", "5");
		Event e2 = new Event("SECOND EVENT", parameters2, null,
						null, null);

		Event e3 = new Event("THIRD EVENT", null, null, null, null);

		String description4 = "A description";
		Event e4 = new Event("FOURTH EVENT", null, null,
				null, description4);

		Event e5 = new Event("FIFTH EVENT", null, "active", null, null);

		Event e6 = new Event("SIXTH EVENT", null, "reactive",
				"e5", null);

		//Tracing:
		te.startTracingToFile(file2);
		te.trace(e1);

		te.startTracingToFile(file1);
		te.startTracingToFile(file1);
		te.stopTracingToFile(file2);
		te.trace(e2);

		te.startTracingToFile(file2);
		te.trace(e3);

		te.trace(e4);

		te.stopTracingToConsole();
		te.trace(e5);

		te.startTracingToConsole();
		te.stopTracingToFile(file2);
		te.trace(e6);


		/* EXPECTED
		 *
		 * To console:
		 *
		 * (events 1, 2, 3, 4, 6)
		 *
		 * <event application="openAndCloseTracersTest"
		 * action="START" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="1">
		 * <param name="par1" value="1"/><param name="par2" value="2"/>
		 * <param name="par3" value="3"/><description>
		 * A brief description.</description></event><event
		 * application="openAndCloseTracersTest" action="SECOND EVENT"
		 * user="random" session="random" time="20010101000000 UTM"
		 * time_ms="1234567" number="2"><param name="par4" value="4"/>
		 * <param name="par5" value="5"/></event><event
		 * application="openAndCloseTracersTest" action="THIRD EVENT"
		 * user="random" session="random" time="20010101000000
		 * UTM" time_ms="1234567" number="3"></event><event
		 * application="openAndCloseTracersTest" action="FOURTH EVENT"
		 * user="random" session="random" time="20010101000000
		 * UTM" time_ms="1234567" number="4"><description>
		 * A description</description></event><event
		 * application="openAndCloseTracersTest" action="SIXTH EVENT"
		 * user="random" session="random" time="20010101000000
		 * UTM" time_ms="1234567" type ="reactive" action_ref="e5"
		 * number="6"></event>
		 *
		 *
		 * To file "openAndCloseTracersTest1.log":
		 *
		 * (events 2, 3, 4, 5, 6)
		 *
		 * <event application="openAndCloseTracersTest"
		 * action="SECOND EVENT" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="2">
		 * <param name="par4" value="4"/><param name="par5" value="5"/>
		 * </event><event application="openAndCloseTracersTest"
		 * action="THIRD EVENT" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="3">
		 * </event><event application="openAndCloseTracersTest"
		 * action="FOURTH EVENT" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="4">
		 * <description>A description</description></event><event
		 * application="openAndCloseTracersTest" action="FIFTH EVENT"
		 * user="random" session="random" time="20010101000000
		 * UTM" time_ms="1234567" type="active" number="5"></event>
		 * <event application="openAndCloseTracersTest"
		 * action="SIXTH EVENT" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" type ="reactive"
		 * action_ref="e5" number="6"></event>
		 *
		 *
		 * To file "openAndCloseTracersTest2.log":
		 *
		 * (events 1, 3, 4, 5)
		 *
		 * <event application="openAndCloseTracersTest"
		 * action="START" user="random" session="random"
		 * time="20010101000000 UTM" time_ms="1234567" number="1">
		 * <param name="par1" value="1"/><param name="par2" value="2"/>
		 * <param name="par3" value="3"/><description>
		 * A brief description.</description></event><event
		 * application="openAndCloseTracersTest" action="SECOND EVENT"
		 * user="random" session="random" time="20010101000000 UTM"
		 * time_ms="1234567" number="2"><param name="par4" value="4"/>
		 * <param name="par5" value="5"/><description>A brief
		 * description.</description></event><event
		 * application="openAndCloseTracersTest" action="THIRD EVENT"
		 * user="random" session="random" time="20010101000000
		 * UTM" time_ms="1234567" number="3"></event><event
		 * application="openAndCloseTracersTest" action="FOURTH EVENT"
		 * user="random" session="random" time="20010101000000
		 * UTM" time_ms="1234567" number="4"><description>
		 * A description</description></event><event
		 * application="openAndCloseTracersTest" action="FIFTH EVENT"
		 * user="random" session="random" time="20010101000000
		 * UTM" time_ms="1234567" type="active" number="5"></event>
		 *
		 *
		 * New lines are for readability only. The order of the
		 * 'param' nodes is not relevant.
		 */
	}

	private static void simultaneousTracingEnginesTest() {
		//Create two tracing engines, which should both trace by default
		//(tracingStatus is true) to console and to a same file.
		//User and session should be randomly generated
		TracingEngine te1 = new TracingEngine(
				"simultaneousTracingEnginesTest1");
		TracingEngine te2 = new TracingEngine(
				"simultaneousTracingEnginesTest2");

		Event[] e = new Event[5];
		for (int i = 0; i < 5; i++) {
			e[i] = new Event("EVENT " + Integer.toString(i+1),
							null, null, null, null);
		}

		String file = "simultaneousTracingEnginesTest.log";
		te1.startTracingToFile(file);
		te2.startTracingToFile(file);
		for (Event event : e) {
			te1.trace(event);
			te2.trace(event);
		}

		/* EXPECTED
		 *
		 * To console and file "simultaneousTracingEnginesTest.log":
		 *
		 * <event application="simultaneousTracingEnginesTest1"
		 * action="EVENT 1" user="FYRS60" session="FYRS60_CJ4L37"
		 * time="20100831212030 GMT" time_ms="9.3712739E7" number="1">
		 * </event>
		 * <event application="simultaneousTracingEnginesTest2"
		 * action="EVENT 1" user="LGFCTB" session="LGFCTB_93VGC8"
		 * time="20100831212030 GMT" time_ms="9.3712749E7" number="1">
		 * </event>
		 * <event application="simultaneousTracingEnginesTest1"
		 * action="EVENT 2" user="FYRS60" session="FYRS60_CJ4L37"
		 * time="20100831212030 GMT" time_ms="9.371275E7" number="2">
		 * </event>
		 * <event application="simultaneousTracingEnginesTest2"
		 * action="EVENT 2" user="LGFCTB" session="LGFCTB_93VGC8"
		 * time="20100831212030 GMT" time_ms="9.3712751E7" number="2">
		 * </event>
		 * <event application="simultaneousTracingEnginesTest1"
		 * action="EVENT 3" user="FYRS60" session="FYRS60_CJ4L37"
		 * time="20100831212030 GMT" time_ms="9.3712757E7" number="3">
		 * </event>
		 * <event application="simultaneousTracingEnginesTest2"
		 * action="EVENT 3" user="LGFCTB" session="LGFCTB_93VGC8"
		 * time="20100831212030 GMT" time_ms="9.3712765E7" number="3">
		 * </event>
		 * <event application="simultaneousTracingEnginesTest1"
		 * action="EVENT 4" user="FYRS60" session="FYRS60_CJ4L37"
		 * time="20100831212030 GMT" time_ms="9.3712766E7" number="4">
		 * </event>
		 * <event application="simultaneousTracingEnginesTest2"
		 * action="EVENT 4" user="LGFCTB" session="LGFCTB_93VGC8"
		 * time="20100831212030 GMT" time_ms="9.3712768E7" number="4">
		 * </event>
		 * <event application="simultaneousTracingEnginesTest1"
		 * action="EVENT 5" user="FYRS60" session="FYRS60_CJ4L37"
		 * time="20100831212030 GMT" time_ms="9.3712769E7" number="5">
		 * </event>
		 * <event application="simultaneousTracingEnginesTest2"
		 * action="EVENT 5" user="LGFCTB" session="LGFCTB_93VGC8"
		 * time="20100831212030 GMT" time_ms="9.371277E7" number="5">
		 * </event>
		 *
		 *
		 * New lines are for readability only.
		 */
	}

	private static void simultaneousTracingEnginesTest2() {
		//Create two tracing engines, which should both trace by default
		//(tracingStatus is true) to console and to a same file.
		//User and session should be randomly generated
		TracingEngine te1 = new TracingEngine(
					"first application");
		TracingEngine te2 = new TracingEngine(
					"second application");

		Event[] e1 = new Event[5];
		for (int i = 0; i < 5; i++) {
			e1[i] = new Event("EVENT " + Integer.toString(i+1),
						null, null, null, null);
		}
		Event[] e2 = new Event[2];
		e2[0] = new Event("FIRST EVENT", null, null, null, null);
		e2[1] = new Event("SECOND EVENT", null, null, null, null);

		String file = "anotherSimultaneousTest.log";
		te1.startTracingToFile(file);
		te2.startTracingToFile(file);
		for (Event e : e1) {
			te1.trace(e);
		}
		for (Event e : e2) {
			te2.trace(e);
		}

		/* EXPECTED
		 *
		 * To console and file "anotherSimultaneousTest.log":
		 *
		 * <event application="first application" action="EVENT 1"
		 * user="FYRS60" session="FYRS60_CJ4L37" time="20100831212030
		 * GMT" time_ms="9.3712739E7" number="1"></event>
		 * <event application="first application" action="EVENT 2"
		 * user="FYRS60" session="FYRS60_CJ4L37" time="20100831212030
		 * GMT" time_ms="9.371275E7" number="2"></event>
		 * <event application="first application" action="EVENT 3"
		 * user="FYRS60" session="FYRS60_CJ4L37" time="20100831212030
		 * GMT" time_ms="9.3712757E7" number="3"></event>
		 * <event application="first application" action="EVENT 4"
		 * user="FYRS60" session="FYRS60_CJ4L37" time="20100831212030
		 * GMT" time_ms="9.3712766E7" number="4"></event>
		 * <event application="first application" action="EVENT 5"
		 * user="FYRS60" session="FYRS60_CJ4L37" time="20100831212030
		 * GMT" time_ms="9.3712769E7" number="5"></event>
		 * <event application="second application" action="FIRST EVENT"
		 * user="LGFCTB" session="LGFCTB_93VGC8" time="20100831212030
		 * GMT" time_ms="9.3712749E7" number="1"></event>
		 * <event application="second application" action="SECOND EVENT"
		 * user="LGFCTB" session="LGFCTB_93VGC8" time="20100831212030
		 * GMT" time_ms="9.3712751E7" number="2"></event>
		 *
		 *
		 * New lines are for readability only.
		 */
	}

	private static void deleteAndCreateFileTest() {
		//Create two tracing engines, which should both trace by default
		//(tracingStatus is true) to console and to a same file.
		//User and session should be randomly generated
		TracingEngine te1 = new TracingEngine(
					"deleteAndCreateFileTest1");
		TracingEngine te2 = new TracingEngine(
					"deleteAndCreateFileTest2");

		Event[] e1 = new Event[5];
		for (int i = 0; i < 5; i++) {
			e1[i] = new Event("EVENT " + Integer.toString(i+1),
						null, null, null, null);
		}
		Event[] e2 = new Event[2];
		e2[0] = new Event("FIRST EVENT", null, null, null, null);
		e2[1] = new Event("SECOND EVENT", null, null, null, null);

		String fileName = "existentFile.log";
		try {
			FileWriter out = new FileWriter(fileName);
			out.close();
			File file = new File(fileName);
			file.delete();
		} catch (IOException ignored) {}

		te1.startTracingToFile(fileName);
		te2.startTracingToFile(fileName);
		for (Event e : e1) {
			te1.trace(e);
		}
		for (Event e : e2) {
			te2.trace(e);
		}

       /* EXPECTED
		*
		* To console and file "existentFile.log":
		*
		* <event application="deleteAndCreateFileTest1" action="EVENT 1"
		* user="FYRS60" session="FYRS60_CJ4L37" time="20100831212030
		* GMT" time_ms="9.3712739E7" number="1"></event>
		* <event application="deleteAndCreateFileTest1" action="EVENT 2"
		* user="FYRS60" session="FYRS60_CJ4L37" time="20100831212030
		* GMT" time_ms="9.371275E7" number="2"></event>
		* <event application="deleteAndCreateFileTest1" action="EVENT 3"
		* user="FYRS60" session="FYRS60_CJ4L37" time="20100831212030
		* GMT" time_ms="9.3712757E7" number="3"></event>
		* <event application="deleteAndCreateFileTest1" action="EVENT 4"
		* user="FYRS60" session="FYRS60_CJ4L37" time="20100831212030
		* GMT" time_ms="9.3712766E7" number="4"></event>
		* <event application="deleteAndCreateFileTest1" action="EVENT 5"
		* user="FYRS60" session="FYRS60_CJ4L37" time="20100831212030
		* GMT" time_ms="9.3712769E7" number="5"></event>
		* <event application="deleteAndCreateFileTest2"
		* action="FIRST EVENT" user="LGFCTB" session="LGFCTB_93VGC8"
		* time="20100831212030 GMT" time_ms="9.3712749E7" number="1">
		* </event><event application="deleteAndCreateFileTest2"
		* action="SECOND EVENT" user="LGFCTB" session="LGFCTB_93VGC8"
		* time="20100831212030 GMT" time_ms="9.3712751E7" number="2">
		* </event>
		*
		*
		* New lines are for readability only.
		*/
	}

}
