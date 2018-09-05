package com.github.singond.physics.vector;

/**
 * Provides formatted string representation of a vector.
 *
 * @author Singon
 */
public interface VectorFormatter {

	/**
	 * Returns a formatted string representing the given vector.
	 *
	 * @param vector the vector to be formatted
	 * @return the vector formatted as string
	 */
	String format(Vector vector);
}
