package com.github.singond.physics.vector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrayVectorCalculations {

	/**
	 * A shorthand for {@code ArrayVector.valueOf(...)}.
	 */
	private ArrayVector arrayVect(double... components) {
		return ArrayVector.valueOf(components);
	}

	@Test
	public void operations() {
		sum(arrayVect(1, 2, 3), arrayVect(6, 7, -1), arrayVect(7, 9, 2));
		sum(arrayVect(0, 0, 0), arrayVect(8, -10, 3), arrayVect(8, -10, 3));
		diff(arrayVect(1, 2, 3), arrayVect(6, 7, -1), arrayVect(-5, -5, 4));
		dot(arrayVect(1, 2, 3), arrayVect(6, 7, -1), 17);
		cross(arrayVect(1, 2, 3), arrayVect(6, 7, -1), arrayVect(-23, 19, -5));
	}

	private void sum(Vector a, Vector b, Vector expected) {
		Vector sum = a.plus(b);
		assertEquals(expected, sum);
		System.out.format("%s + %s = %s%n", a, b, sum);
	}

	private void diff(Vector a, Vector b, Vector expected) {
		Vector diff = a.minus(b);
		assertEquals(expected, diff);
		System.out.format("%s - %s = %s%n", a, b, diff);
	}

	private void dot(Vector a, Vector b, double expected) {
		double prod = a.dotProduct(b);
		assertEquals(expected, prod, 1e-12);
		System.out.format("%s . %s = %s%n", a, b, prod);
	}

	private void cross(Vector a, Vector b, Vector expected) {
		Vector prod = a.crossProduct(b);
		assertEquals(expected, prod);
		System.out.format("%s x %s = %s%n", a, b, prod);
	}
}
