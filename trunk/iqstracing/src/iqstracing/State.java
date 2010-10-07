package iqstracing;

/**
 * State is the class that contains the information about
 * traces based on states instead of actions. Through a
 * short description, it provides information about such
 * state.
 * <p>
 * Code license: GNU General Public License v2
 * <p>
 * Content license: Creative Commons 3.0 BY-SA
 *
 * @author Núria Coronas
 *
 * @author Jordi Cuadros
 *
 */
public class State {
    private String description;

    /**
     * Constructs a State object from a specified description.
     * This object will then be passed to a tracer.
     *
     * @param description String representing a human-readable
     * version of the state of the application which is being
     * traced
     */
    public State(String description) {
        this.description = description;
    }

    /**
     * Gets the description of the state.
     *
     * @return String representing a human-readable version of
     * the state of the application which is being traced
     */
    protected String getDescription() {
        return description;
    }
}
