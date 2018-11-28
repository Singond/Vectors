package com.github.singond.physics.vector;

import java.util.List;

/**
 * A vector as used in physics: a quantity with magnitude and direction.
 * The vector is represented by a fixed-order sequence of numbers
 * (the <em>components</em> of the vector in a vector base).
 * The number of the vector's components is called the <em>dimension</em>
 * of the vector.
 *
 * @author Singon
 */
public interface Vector {

	// TODO Consider returning more generic type like Number in the interface

	/**
	 * Returns the components of this vector as a list.
	 * The returned list is immutable.
	 *
	 * @return the components of this vector
	 */
	List<Double> components();

	/**
	 * Returns the given component of this vector.
	 *
	 * @param component the (zero-based) index of the component
	 * @return the {@code component}th component of this vector.
	 * @throws IndexOutOfBoundsException if {@code component} is lower than
	 *         {@code 0} or higher than or equal to {@code dimension()}
	 */
	double get(int component);

	/**
	 * Returns the dimension of this vector (the number of its components).
	 *
	 * @return the number of the components of the vector
	 */
	int dimension();

	/**
	 * Returns the Euclidean norm of this vector, sometimes called the
	 * "length" or "magnitude".
	 * <p>
	 * Given a vector <tt>v</tt> of components <tt>[a, b, ..., n]</tt>,
	 * the Euclidean norm of that vector is defined as:
	 * <pre>
	 * |v| = sqrt(a^2 + b^2 + ... + n^2).
	 * </pre>
	 *
	 * @return the Euclidean norm of this vector
	 */
	double magnitude();

	/**
	 * Returns the normalized vector; that is a vector with the same dimension
	 * and direction as this vector, but with the magnitude equal to one.
	 *
	 * @return the normalized vector to this vector
	 */
	Vector normalized();

	/**
	 * Returns the negative vector to this one.
	 *
	 * @return the negative vector
	 */
	Vector negative();

	/**
	 * Returns a vector which is the sum of this vector and the given vector.
	 * The vectors must be of the same dimension.
	 *
	 * @param addend the vector to be added to this vector
	 * @return {@code this + addend}
	 * @throws IllegalVectorDimensionException if the dimension of {@code a}
	 *         is not equal to the dimension of this vector
	 */
	Vector plus(Vector addend);

	/**
	 * Returns a vector which is the result of subtracting the given vector
	 * from this vector.
	 * The vectors must be of the same dimension.
	 *
	 * @param subtrahend the vector to be subtracted from this vector
	 * @return {@code this - addend}
	 * @throws IllegalVectorDimensionException if the dimension of {@code a}
	 *         is not equal to the dimension of this vector
	 */
	Vector minus(Vector subtrahend);

	/**
	 * Returns the product of this vector and the given scalar.
	 *
	 * @param scalar the number to multiply this vector by
	 * @return {@code this * scalar}
	 */
	Vector times(double scalar);

	/**
	 * Returns the dot product of this vector with the given vector.
	 * The vectors must be of the same dimension.
	 *
	 * @param a the vector to multiply this vector by
	 * @return {@code this . a}
	 * @throws IllegalVectorDimensionException if the dimension of {@code a}
	 *         is not equal to the dimension of this vector
	 */
	double dotProduct(Vector a);

	/**
	 * Returns the cross product of this vector with the given vector.
	 * The vectors must be of dimension 3.
	 *
	 * @param a the vector to multiply this vector by
	 * @return {@code this x a}
	 * @throws IllegalVectorDimensionException if the dimension of {@code a}
	 *         or the dimension of this vector is not three
	 */
	Vector crossProduct(Vector a);

	/**
	 * Returns the pointwise product of this vector with the given vector.
	 * The vectors must be of the same dimension.
	 * <p>
	 * The pointwise product of two vectors (also called the Hadamard product)
	 * is the result of multiplying the two vectors component by component.
	 * For vectors <tt>a = [a1, a2, ..., an]</tt> and
	 * <tt>b = [b1, b2, ..., bn]</tt>, their pointwise product is defined as:
	 * <pre>
	 * a .* b = [a1 * b1, a2 * b2, ..., an * bn]
	 * </pre>
	 *
	 * @param a the vector to multiply this vector by
	 * @return {@code this .* a}
	 * @throws IllegalVectorDimensionException if the dimension of {@code a}
	 *         is not equal to the dimension of this vector
	 */
	Vector pointwiseProduct(Vector a);

	/**
	 * Returns the smallest angle between the given vector and this vector
	 * in radians. The angle is a positive number between 0 and pi (inclusive).
	 *
	 * @param a the vector forming the other ray of the angle
	 * @return the smallest angle between {@code this} and {@code a} in radians
	 */
	double angleWith(Vector a);

	/**
	 * Indicates whether the given object is equal to this one.
	 * Two vectors are equal if they have the same dimensions and their
	 * components at matching positions are equal.
	 *
	 * @param o the reference object with which to compare
	 * @return {@code true}, if {@code o} is also a {@code Vector},
	 *         the dimension of {@code o} is equal to the dimension of this
	 *         vector, and the components of the vectors at matching positions
	 *         are equal
	 */
	@Override
	boolean equals(Object o);

	/**
	 * Returns the hash code value for this vector. The hash code of a vector
	 * object is defined to be the result of the following calculation:
	 * <pre>{@code
	 * int hashCode = 1;
	 * for (int i = 0; i < dimension(); i++) {
	 *     long temp = Double.doubleToLongBits(get(i));
	 *     result = 31 * hashCode + (int) (temp ^ (temp >>> 32));
	 * }}</pre>
	 * <p>
	 * This ensures that {@code vector1.equals(vector2)} implies that
	 * {@code vector1.hashCode() == vector2.hashCode()} for any two vectors,
	 * {@code vector1} and {@code vector2},
	 * as required by the general contract of {@code Object.hashCode()}.
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	int hashCode();
}
