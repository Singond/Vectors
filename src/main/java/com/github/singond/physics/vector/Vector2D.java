package com.github.singond.physics.vector;

import java.util.Arrays;
import java.util.List;

/**
 * A specific implementation of the {@code Vector} interface for two dimensions.
 *
 * @author Singon
 */
public final class Vector2D implements Vector {

	private final double x;
	private final double y;

	private static final int DIMENSION = 2;

	private Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns a {@code Vector2D} with the given components.
	 *
	 * @param x x-component of the vector
	 * @param y y-component of the vector
	 * @return a {@code Vector2D} with the given components
	 */
	public static final Vector2D valueOf(double x, double y) {
		return new Vector2D(x, y);
	}

	private Vector2D instance(double x, double y) {
		return valueOf(x, y);
	}

	@Override
	public double get(int component) {
		if (component == 0) return x;
		else if (component == 1) return y;
		else throw new IndexOutOfBoundsException(
				"Invalid vector component index: " + component);
	}

	@Override
	public int dimension() {
		return DIMENSION;
	}

	@Override
	public List<Double> components() {
		return Arrays.asList(x, y);
	}

	@Override
	public double magnitude() {
		return Math.sqrt(x*x + y*y);
	}

	@Override
	public Vector2D normalized() {
		double mag = magnitude();
		return instance(x/mag, y/mag);
	}

	@Override
	public Vector2D negative() {
		return instance(-x, -y);
	}

	@Override
	public Vector2D plus(Vector a) {
		if (a instanceof Vector2D) {
			return plus((Vector2D) a);
		} else {
			checkDimension(a);
			return instance(x + a.get(0), y + a.get(1));
		}
	}

	public Vector2D plus(Vector2D a) {
		return instance(x + a.x, y + a.y);
	}

	@Override
	public Vector2D minus(Vector a) {
		if (a instanceof Vector2D) {
			return minus((Vector2D) a);
		} else {
			checkDimension(a);
			return instance(x - a.get(0), y - a.get(1));
		}
	}

	public Vector2D minus(Vector2D a) {
		return instance(x - a.x, y - a.y);
	}

	@Override
	public Vector2D times(double scalar) {
		return instance(x * scalar, y * scalar);
	}

	@Override
	public double dotProduct(Vector a) {
		if (a instanceof Vector2D) {
			return dotProduct((Vector2D) a);
		} else {
			checkDimension(a);
			return x * a.get(0) + y * a.get(1);
		}
	}

	public double dotProduct(Vector2D a) {
		return x * a.x + y * a.y;
	}

	@Override
	public Vector2D crossProduct(Vector a) {
		throw new UnsupportedOperationException(
				"The cross product is not defined for two dimensions");
	}

	@Override
	public Vector2D pointwiseProduct(Vector a) {
		if (a instanceof Vector2D) {
			return crossProduct(a);
		} else {
			checkDimension(a);
			return instance(x * a.get(0), y * a.get(1));
		}
	}

	public Vector2D pointwiseProduct(Vector2D a) {
		return instance(x * a.x, y * a.y);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This method uses the following formula for calculating the angle:
	 * <pre>
	 * angle = arccos((this . a) / (|this| * |a|)),</pre>
	 * where <tt>|a|</tt> denotes the magnitude of vector <tt>a</tt>.
	 * Due to rounding errors, the arccosine may receive
	 * an argument greater than 1 (in absolute value).
	 * The method truncates all numbers above 1 to allow for this.
	 * No check is made for the magnitude of the error,
	 * it is assumed to be small, because in exact mathematics,
	 * the formula used cannot yield numbers above 1.
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public double angleWith(Vector a) {
		double cosine = this.dotProduct(a) / (this.magnitude() * a.magnitude());
		if (cosine < -1) cosine = -1;
		else if (cosine > 1) cosine = 1;
		return Math.acos(cosine);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Vector)) return false;
		Vector other = (Vector) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.get(0)))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.get(1)))
			return false;
		return true;
	}

	/**
	 * Checks whether the given vector has the same dimensions as this vector
	 * and throws an exception if not.
	 *
	 * @param other the vector whose dimension is being checked
	 * @throws IllegalVectorDimensionException if the dimension of {@code other}
	 *         is not 2
	 */
	private void checkDimension(Vector other) {
		if (other.dimension() != DIMENSION) {
			throw new IllegalVectorDimensionException(
					"The dimension of vector " + other + "is not 2");
		}
	}
}
