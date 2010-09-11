package iqstracing;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Date;

/**
 * TracingEngine is the class which will control the tracing
 * system for each application. It keeps information about
 * the application being used, its user and the logged session.
 * It controls the order of the traced events and adds
 * the time at which the information was traced.
 * The TracingEngine class provides a set of methods which
 * allow keeping a collection of tracers and giving
 * the information to be traced to them. By default, it traces to
 * console.
 *
 */
public class TracingEngine {
    private String application;
    private String user;
    private String session;
    private LinkedList<Tracer> tracerCollection = new LinkedList<Tracer>();
    private boolean tracingStatus;
    private int sequence = 0;

    /**
     * Constructs a TracingEngine object using the specified
     * string values of application, user and session, which
     * will be assigned to their respective fields. The application
     * can never be null, and in this case the tracing status is set
     * to false, so that nothing can be traced. If the user or session
     * are null, they will be randomly created.
     *
     * @param application  string that represents the program
     * or java applet that requests the tracing of an event
     * or state
     *
     * @param user  string representing the person who is
     * using the application and whose actions are being traced
     *
     * @param session  string that represents the logged session
     */
    public TracingEngine(String application, String user, String session) {
        if (application == null || application == "") {
            this.setTracingStatus(false);
            this.application = "ApplicationNotSpecified";
            System.err.println("Application not specified. "
                + "The information will not be traced.");
        } else {
            this.application = application;
            this.setTracingStatus(true);
        }
        if (user == null || user == "") {
            this.user = RandomGenerator.generate();
        } else {
            this.user = user;
        }
        if (session == null || session == "") {
            this.session = this.user + "_"
            + RandomGenerator.generate();
        } else {
            this.session = session;
        }
        this.tracerCollection.add(new ConsoleTracer(this));
    }


    /**
     * Constructs a TracingEngine object using the specified
     * string values of application and user. The value of
     * session is not specified, so it will be created from
     * the user string and a random sequence of six characters
     * which can be capital letters or numbers.
     *
     * @param application  string that represents the program
     * or java applet that requests the tracing of an event
     * or state
     *
     * @param user  string representing the person who is
     * using the application and whose actions are being traced
     */
    public TracingEngine(String application, String user) {
        this(application, user, null);
    }


    /**
     * Constructs a TracingEngine object using only the specified
     * string value of application. The values of user and session
     * are not specified. The user field will be filled with a
     * randomly created sequence of six characters that can be
     * capital letters or numbers. The session field will be
     * the user string concatenated to a random sequence of six
     * characters.
     *
     * @param application  string that represents the program
     * or java applet that requests the tracing of an event
     * or state
     */
    public TracingEngine(String application) {
        this(application, null, null);
    }

    /**
     * Checks there is not already a FileTracer object for the
     * specified fileName (full path) in the tracerCollection.
     * In case the FileTracer object exists, nothing is done. If
     * the desired tracer does not exist, it is created and added
     * to the tracerCollection.
     *
     * @param fileName  string representing the name of the file
     * where the information will be traced.
     */
    public void startTracingToFile(String fileName) {
        FileTracer ft = new FileTracer(this, fileName);
        String path = ft.getFile().getAbsolutePath().toLowerCase();
        boolean containsFileTracer = false;
        for (Tracer t : tracerCollection) {
            if (t.getClass().getName() == "iqstracing.FileTracer") {
                if (((FileTracer) t).getFile().
                        getAbsolutePath().toLowerCase().
                        equals(path)) {
                    containsFileTracer = true;
                }
            }
        }
        if (!containsFileTracer) {
            tracerCollection.add(ft);
        }
    }


    /**
     * Checks there is a FileTracer for this fileName (full path)
     * in the tracerCollection. In case the FileTracer does not
     * exist, nothing is done. If the FileTracer exists, it is
     * removed from the tracerCollection.
     *
     * @param fileName  string representing the name of the file
     * where the information has been traced.
     */
    public void stopTracingToFile(String fileName) {
        File file;
        if (fileName == null || fileName == "") {
            file = new File(this.session + ".log");
        } else {
            file = new File(fileName);
        }
        FileTracer ft = null;
        for (Tracer t : tracerCollection) {
            if (t.getClass().getName() == "iqstracing.FileTracer") {
                if (((FileTracer) t).getFile().
                        getAbsolutePath().toLowerCase().
                        equals(file.getAbsolutePath().
                        toLowerCase())) {
                    ft = (FileTracer) t;
                }
            }
        }
        if (ft != null) {
            tracerCollection.remove(ft);
        }
    }

    /**
     * Checks there is not already a ConsoleTracer in the
     * tracerCollection. In case the ConsoleTracer exists,
     * nothing is done. If the desired tracer does not exist,
     * it is created and added to the tracerCollection.
     */
    public void startTracingToConsole() {
        ConsoleTracer consoleTracer = new ConsoleTracer(this);
        if (!hasConsoleTracer()) {
            tracerCollection.add(consoleTracer);
        }
    }


    /**
     * Checks there is a ConsoleTracer in the tracerCollection.
     * In case the ConsoleTracer does not exist, nothing is done.
     * If the tracer exists, it is removed from the tracerCollection.
     */
    public void stopTracingToConsole() {
        ConsoleTracer ct = null;
        for (Tracer t : tracerCollection) {
            if (t.getClass().getName()
                    == "iqstracing.ConsoleTracer") {
                ct = (ConsoleTracer) t;
            }
        }
        if (ct != null) {
            tracerCollection.remove(ct);
        }
    }


    /**
     * Traces the information stored in an event adding information about
     * the time when the trace was written and the logged application, user
     * and session. For each tracer in the tracerCollection, its
     * trace(Event) method is called to produce the desired tracing. It only
     * traces if tracingStatus is true and the name of the action which the
     * event represents is specified.
     *
     * @param event  Event object that stores all the information about
     * the action that will be traced
     */
    public void trace(Event event) {
        String time;
        double time_ms;

        time = (new SimpleDateFormat("yyyyMMddHHmmss zzz"))
                            .format(new Date());
        time_ms = System.nanoTime()/1000000;

        if (tracingStatus) {
            if (event.getActionName() != null
                    && event.getActionName() != "") {
                sequence++;
                for (Tracer t : tracerCollection) {
                    t.trace(event, time, time_ms, sequence);
                }
            } else {
            System.err.println("Action name of the event not "
                + "specified. Event could not be traced.");
            }
        }
    }


    /**
     * Traces the information stored in a state adding information about
     * the time when the trace was written and the logged application, user
     * and session. For each tracer in the tracerCollection its trace(State)
     * method is called to produce the desired tracing. It only traces if
     * tracingStatus is true.
     *
     * @param state  State object that contains the information to be traced
     * in traces based on states instead of actions.
     */
    public void trace(State state) {
        String time;
        double time_ms;

        time = (new SimpleDateFormat("yyyyMMddHHmmss zzz"))
                            .format(new Date());
        time_ms = System.nanoTime()/1000000;

        if (tracingStatus) {
            sequence++;
            for (Tracer t : tracerCollection) {
                t.trace(state, time, time_ms, sequence);
            }
        }
    }


    /**
     * Sets the tracingStatus to the specified boolean value. This
     * value will be true by default and setting it to false
     * will easily stop the tracing system.
     *
     * @param tracingStatus  boolean expression whose value will be
     * assigned to the tracingStatus field
     */
    public void setTracingStatus(boolean tracingStatus) {
        if (this.application != null || this.application == "") {
            this.tracingStatus = false;
        } else {
            this.tracingStatus = tracingStatus;
        }
    }


    /**
     * Gets the value of the tracingStatus, which will determine
     * whether the TracingEngine is tracing or not by means of
     * a boolean.
     *
     * @return the boolean value of the tracingStatus field
     */
    public boolean getTracingStatus() {
        return tracingStatus;
    }

    /**
     * Gets the user of the application.
     *
     * @return string representing the person who is
     * using the application and whose actions are being traced
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user of the application to the specified value.
     *
     * @param user string representing the person who is
     * using the application and whose actions are being traced
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the logged session.
     *
     * @return string which represents the logged session
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets the session to the specified value.
     *
     * @param session string which represents the logged session
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * Gets the application which requests the logging.
     *
     * @return string representing the program or java applet
     * which requests the tracing of an event or state
     */
    public String getApplication() {
        return application;
    }

    /**
     * Sets the application to the specified value.
     *
     * @param application string that represents the program
     * or java applet which requests the tracing of an event
     * or state
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * Checks there is a ConsoleTracer in the tracerCollection.
     *
     * @return boolean value which is true when the
     * tracerCollection contains a ConsoleTracer and false
     * when it does not.
     */
    private boolean hasConsoleTracer() {
        boolean b = false;
        for (Tracer t : tracerCollection) {
            if (t.getClass().getName()
                    == "iqstracing.ConsoleTracer") {
                b = true;
            }
        }
        return b;
    }
}
