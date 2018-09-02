package com.github.singond.physics.vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A generic implementation of the {@code Vector} interface, where the
 * value of the vector is stored as an array of {@code double}s.
 *
 * @author Singon
 */
public class ArrayVector extends AbstractVector<ArrayVector> implements Vector {

	/** The components of the vector. */
	private final double[] value;

	/**
	 * Constructs a new {@code ArrayVector} with the given components.
	 * This uses the given array directly.
	 *
	 * @param components components of the vector
	 */
	private ArrayVector(double... components) {
		value = components.clone();
	}

	/**
	 * Returns an {@code ArrayVector} with the given components.
	 *
	 * @param components components of the vector
	 */
	public static final ArrayVector valueOf(double... components) {
		return new ArrayVector(components.clone());
	}

	@Override
	protected ArrayVector instance(double... components) {
		return valueOf(components);
	}

	@Override
	public List<Double> components() {
		List<Double> result = new ArrayList<>(value.length);
		for (double component : value) {
			result.add(Double.valueOf(component));
		}
		return result;
	}

	@Override
	public double get(int component) {
		return value[component];
	}

	@Override
	public int dimension() {
		return value.length;
	}

//	@Override
//	public double magnitude() {
//		double square = 0;
//		for (double component : value) {
//			square += Math.pow(component, 2);
//		}
//		return Math.sqrt(square);
//	}
//
//	@Override
//	public ArrayVector normalized() {
//		return this.times(1/magnitude());
//	}
//
//	@Override
//	public ArrayVector negative() {
//		return this.times(-1);
//	}

//	@Override
//	public ArrayVector plus(Vector addend) {
//		checkDimension(addend);
//		double[] result = new double[value.length];
//		for (int i=0; i < value.length; i++) {
//			result[i] += addend.get(i);
//		}
//		return ArrayVector.valueOf(result);
//	}
//
//	@Override
//	public ArrayVector minus(Vector subtrahend) {
//		checkDimension(subtrahend);
//		double[] result = new double[value.length];
//		for (int i=0; i < value.length; i++) {
//			result[i] -= subtrahend.get(i);
//		}
//		return ArrayVector.valueOf(result);
//	}
//
//	@Override
//	public ArrayVector times(double scalar) {
//		double[] result = new double[value.length];
//		for (int i=0; i < value.length; i++) {
//			result[i] *= scalar;
//		}
//		return ArrayVector.valueOf(result);
//	}

	@Override
	public String toString() {
		return Arrays.toString(value);
	}
}
