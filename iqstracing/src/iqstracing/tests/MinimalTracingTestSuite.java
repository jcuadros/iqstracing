package iqstracing.tests;

import java.io.File;

import iqstracing.Event;
import iqstracing.TracingEngine;

public class MinimalTracingTestSuite {
    public static void main(String[]args) {
        minimalTest();
        minimalTestToFile();
        minimalMultipleEventTest();
    }

    private static void minimalTest() {
        //Create a tracing engine, which should trace by default
        //(tracingStatus is true) to console
        //User and session should be randomly generated
        TracingEngine te = new TracingEngine("minimalTest");

        //Trace a minimal event
        Event e1 = new Event("START");
        //Event e1 = new Event("START", null, null, null, null, null);
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
        Event e1 = new Event("START");
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

    private static void minimalMultipleEventTest() {
        //Create a tracing engine, which should trace by default
        //(tracingStatus is true) to console
        TracingEngine te = new TracingEngine(
                "minimalMultipleEventTest");

        //Events:
        Event e1 = new Event("E1");
        Event e2 = new Event("E2");
        Event e3 = new Event("E3");

        te.trace(e1);
        te.trace(e2);
        te.trace(e3);

        /*
         * EXPECTED
         * To console:
         * <event application="minimalMultipleEventTest" action="E1"
         * user=random session=random time="20100905221246 GMT"
         * time_ms="2.0183868E7" number="1"></event>
         * <event application="minimalMultipleEventTest" action="E2"
         * user=random session=random time="20100905221246 GMT"
         * time_ms="2.0183868E7" number="2"></event>
         * <event application="minimalMultipleEventTest" action="E3"
         * user=random session=random time="20100905221246 GMT"
         * time_ms="2.0183868E7" number="3"></event>
         *
         * New lines are for readability only.
         */
    }
}
