package com.github.singond.physics.vector;

/**
 * Thrown to indicate that the dimension of a vector is not valid in the
 * given context.
 *
 * @author Singon
 */
@SuppressWarnings("serial")
public class IllegalVectorDimensionException extends RuntimeException {

	public IllegalVectorDimensionException() {
		super();
	}

	public IllegalVectorDimensionException(Vector vector) {
		super("Invalid dimension of vector " + vector
				+ ": " + vector.dimension());
	}

	public IllegalVectorDimensionException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalVectorDimensionException(String message) {
		super(message);
	}

	public IllegalVectorDimensionException(Throwable cause) {
		super(cause);
	}

}
