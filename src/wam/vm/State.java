/**
 * State.java
 *
 * Represents a state in the While Language.
 */

package wam.vm;

import java.util.*;

import wam.code.*;

public class State {
    /**
     * The current set of values in the State.
     */
    private Map<String,Integer> values;

    /**
     * Create a new State with the given default configuration.
     *
     * @param value The default state space.
     */
    public State(Map<String,Integer> values) {
        this.values = new TreeMap<String,Integer>(values);
    }

    /**
     * Copy an existing State.
     */
    public State(State state) {
        this.values = new TreeMap<String,Integer>(state.values);
    }

    /**
     * Create a new, empty, State.
     */
    public State() {
        this.values = new TreeMap<String,Integer>();
    }

    /**
     * Get a value from the State.
     *
     * @param id The id of the variable to check.
     */
    public Integer get(String name) {
        return values.get(name);
    }

    /**
     * Set a value in the State.
     *
     * @param name The name of the variable to update.
     * @param value The new value of the variable.
     */
    public void set(String name, Integer value) {
        values.put(name, value);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("(");

        if (values.size() > 0) {
            buffer.append("\n");
            for (Map.Entry<String,Integer> entry : values.entrySet())
                buffer.append(String.format("    %s -> %d\n", entry.getKey(), entry.getValue()));
        }

        buffer.append(")");

        return buffer.toString();
    }
}
