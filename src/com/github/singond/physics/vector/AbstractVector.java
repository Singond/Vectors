package com.github.singond.physics.vector;

import java.util.ArrayList;
import java.util.List;

/**
 * A skeletal implementation of the {@code Vector} interface.
 * To fully implement the {@code Vector} interface, it is sufficient to
 * override the {@link #get(int)} and {@link #dimension()} methods.
 * <p>
 * Note for implementors:
 * The performance of this class depends on the performance of
 * {@link #get(int)} and {@link #dimension()}.
 *
 * @author Singon
 * @param T the concrete subtype of {@code Vector} returned by methods
 */
public abstract class AbstractVector<T extends Vector> implements Vector {

	/**
	 * Returns an instance of vector with the given components.
	 *
	 * @param components the components of the vector
	 * @return a vector of type {@code T} with {@code components}
	 */
	protected abstract T instance(double... components);

	@Override
	public List<Double> components() {
		List<Double> result = new ArrayList<>();
		for (int i = 0; i < dimension(); i++) {
			result.add(Double.valueOf(get(i)));
		}
		return result;
	}

	@Override
	public double magnitude() {
		double square = 0;
		for (int i = 0; i < dimension(); i++) {
			square += Math.pow(get(i), 2);
		}
		return Math.sqrt(square);
	}

	@Override
	public T normalized() {
		return this.times(1/magnitude());
	}

	@Override
	public T negative() {
		return this.times(-1);
	}

	@Override
	public T plus(Vector addend) {
		checkDimension(addend);
		double[] result = new double[dimension()];
		for (int i = 0; i < dimension(); i++) {
			result[i] = this.get(i) + addend.get(i);
		}
		return instance(result);
	}

	@Override
	public T minus(Vector subtrahend) {
		checkDimension(subtrahend);
		double[] result = new double[dimension()];
		for (int i = 0; i < dimension(); i++) {
			result[i] = this.get(i) - subtrahend.get(i);
		}
		return instance(result);
	}

	@Override
	public T times(double scalar) {
		double[] result = new double[dimension()];
		for (int i = 0; i < dimension(); i++) {
			result[i] = this.get(i) * scalar;
		}
		return instance(result);
	}

	@Override
	public double dotProduct(Vector a) {
		double result = 0;
		for (int i = 0; i < dimension(); i++) {
			result += this.get(i) * a.get(i);
		}
		return result;
	}

	@Override
	public T crossProduct(Vector a) {
		if (dimension() == 3) {
			double[] result = new double[3];
			result[0] = this.get(1) * a.get(2) - this.get(2) * a.get(1);
			result[1] = this.get(2) * a.get(0) - this.get(0) * a.get(2);
			result[2] = this.get(0) * a.get(1) - this.get(1) * a.get(0);
			return instance(result);
		} else {
			throw new IllegalArgumentException(
					"Cross product is not defined for dimension " + dimension());
		}
	}

	@Override
	public T pointwiseProduct(Vector a) {
		checkDimension(a);
		double[] result = new double[dimension()];
		for (int i = 0; i < dimension(); i++) {
			result[i] = this.get(i) * a.get(i);
		}
		return instance(result);
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
	 * the formula used cannot yield numbers above 1.</p>
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
		for (int i = 0; i < dimension(); i++) {
			long temp = Double.doubleToLongBits(get(i));
			result = prime * result + (int) (temp ^ (temp >>> 32));
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Vector)) return false;
		Vector other = (Vector) obj;
		for (int i = 0; i < dimension(); i++) {
			if (Double.doubleToLongBits(get(i))
			    != Double.doubleToLongBits(other.get(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether the given vector has the same dimensions as this vector
	 * and throws an exception if not.
	 *
	 * @param other the vector whose dimension is being checked
	 * @throws IllegalVectorDimensionException if the dimension of {@code other}
	 *         does not match the dimension of this vector
	 */
	private void checkDimension(Vector other) {
		if (other.dimension() != dimension()) {
			throw new IllegalVectorDimensionException(
					"The dimension of vector " + other + "is not" + dimension());
		}
	}
}
