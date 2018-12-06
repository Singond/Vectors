package com.github.singond.physics.vector;

import java.util.Arrays;
import java.util.List;

/**
 * A specific implementation of the {@code Vector} interface for three dimensions.
 *
 * @author Singon
 */
public final class Vector3D implements Vector {

	private final double x;
	private final double y;
	private final double z;

	private static final int DIMENSION = 3;

	private Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Returns a {@code Vector3D} with the given components.
	 *
	 * @param x x-component of the vector
	 * @param y y-component of the vector
	 * @param z z-component of the vector
	 * @return a {@code Vector3D} with the given components
	 */
	public static final Vector3D valueOf(double x, double y, double z) {
		return new Vector3D(x, y, z);
	}

	private Vector3D instance(double x, double y, double z) {
		return valueOf(x, y, z);
	}

	@Override
	public double get(int component) {
		if (component == 0) return x;
		else if (component == 1) return y;
		else if (component == 2) return z;
		else throw new IndexOutOfBoundsException(
				"Invalid vector component index: " + component);
	}

	@Override
	public int dimension() {
		return DIMENSION;
	}

	@Override
	public List<Double> components() {
		return Arrays.asList(x, y, z);
	}

	@Override
	public double magnitude() {
		return Math.sqrt(x*x + y*y + z*z);
	}

	@Override
	public Vector3D normalized() {
		double mag = magnitude();
		return instance(x/mag, y/mag, z/mag);
	}

	@Override
	public Vector3D negative() {
		return instance(-x, -y, -z);
	}

	@Override
	public Vector3D plus(Vector a) {
		if (a instanceof Vector3D) {
			return plus((Vector3D) a);
		} else {
			checkDimension(a);
			return instance(x + a.get(0), y + a.get(1), z + a.get(2));
		}
	}

	public Vector3D plus(Vector3D a) {
		return instance(x + a.x, y + a.y, z + a.z);
	}

	@Override
	public Vector3D minus(Vector a) {
		if (a instanceof Vector3D) {
			return minus((Vector3D) a);
		} else {
			checkDimension(a);
			return instance(x - a.get(0), y - a.get(1), z - a.get(2));
		}
	}

	public Vector3D minus(Vector3D a) {
		return instance(x - a.x, y - a.y, z - a.z);
	}

	@Override
	public Vector3D times(double scalar) {
		return instance(x * scalar, y * scalar, z * scalar);
	}

	@Override
	public double dotProduct(Vector a) {
		if (a instanceof Vector3D) {
			return dotProduct((Vector3D) a);
		} else {
			checkDimension(a);
			return x * a.get(0) + y * a.get(1) + z * a.get(2);
		}
	}

	public double dotProduct(Vector3D a) {
		return x * a.x + y * a.y + z * a.z;
	}

	@Override
	public Vector3D crossProduct(Vector a) {
		if (a instanceof Vector3D) {
			return crossProduct((Vector3D) a);
		} else if (a.dimension() == 3) {
			double u, v, w;
			u = y * a.get(2) - z * a.get(1);
			v = z * a.get(0) - x * a.get(2);
			w = x * a.get(1) - y * a.get(0);
			return instance(u, v, w);
		} else {
			throw new IllegalVectorDimensionException(
					"The dimension of vector " + a + "is not 3");
		}
	}

	public Vector3D crossProduct(Vector3D a) {
		double u, v, w;
		u = y * a.z - z * a.y;
		v = z * a.x - x * a.z;
		w = x * a.y - y * a.x;
		return instance(u, v, w);
	}

	@Override
	public Vector3D pointwiseProduct(Vector a) {
		if (a instanceof Vector3D) {
			return crossProduct((Vector3D) a);
		} else {
			checkDimension(a);
			return instance(x * a.get(0), y * a.get(1), z * a.get(2));
		}
	}

	public Vector3D pointwiseProduct(Vector3D a) {
		return instance(x * a.x, y * a.y, z * z);
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
		temp = Double.doubleToLongBits(z);
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
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.get(2)))
			return false;
		return true;
	}

	/**
	 * Checks whether the given vector has the same dimensions as this vector
	 * and throws an exception if not.
	 *
	 * @param other the vector whose dimension is being checked
	 * @throws IllegalVectorDimensionException if the dimension of {@code other}
	 *         is not 3
	 */
	private void checkDimension(Vector other) {
		if (other.dimension() != DIMENSION) {
			throw new IllegalVectorDimensionException(
					"The dimension of vector " + other + "is not 3");
		}
	}
}
