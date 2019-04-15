/*
 * CRITTERS Visitor.java
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * <Analaura Rodriguez>
 * <ar55665>
 * <16225>
 * <Kevin Han>
 * <kdh2789>
 * <16190>
 * Slip days used: <0>
 * Spring 2019
 */

package assignment5;

public interface Visitor<T> {
    /**
     * @return {@code true} if the algorithm should visit more results,
     * {@code false} if it should terminate now.
     */
    public boolean visit(T t);
}
