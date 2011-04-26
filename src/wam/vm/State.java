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
     * A flag telling whether the state is normal or abnormal (in an
     * exceptional state).
     */
    private boolean isNormal;

    /**
     * Create a new State with the given default configuration.
     *
     * @param value The default state space.
     */
    public State(Map<String,Integer> values) {
        this.values = new TreeMap<String,Integer>(values);
        this.isNormal = true;
    }

    /**
     * Copy an existing State.
     */
    public State(State state) {
        this.values = new TreeMap<String,Integer>(state.values);
        this.isNormal = state.isNormal;
    }

    /**
     * Create a new, empty, State.
     */
    public State() {
        this.values = new TreeMap<String,Integer>();
        this.isNormal = true;
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

    /**
     * Set the abnormal flag in the state.
     *
     * @param value The new value of the isNormal field.
     */
    public void setIsNormal(boolean value) {
        isNormal = value;
    }

    /**
     * @return true iff this state is in a normal state.
     */
    public boolean isNormal() {
        return isNormal;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        if (isNormal())
            buffer.append("s[");
        else
            buffer.append("ŝ[");

        if (values.size() > 0) {
            boolean first = true;
            for (Map.Entry<String,Integer> entry : values.entrySet()) {
                if (first)
                    first = false;
                else
                    buffer.append(", ");
                buffer.append(String.format("%s ↦ %d", entry.getKey(), entry.getValue()));
            }
        }

        buffer.append("]");

        return buffer.toString();
    }
}
