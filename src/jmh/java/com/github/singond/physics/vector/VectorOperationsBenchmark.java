package com.github.singond.physics.vector;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class VectorOperationsBenchmark {

	@State(Scope.Thread)
	public static class VectorOperands {
		public Vector a = ArrayVector.valueOf(Math.random(), Math.random(), Math.random());
		public Vector b = ArrayVector.valueOf(Math.random(), Math.random(), Math.random());
	}

	@Benchmark
	public void addVektor(VectorOperands o) {
		Vector sum = o.a.plus(o.b);
	}
}
