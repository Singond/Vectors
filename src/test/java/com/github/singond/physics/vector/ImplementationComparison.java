package com.github.singond.physics.vector;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

import cz.slanyj.euclideanVector.Vektor;

public class ImplementationComparison {

	private static class HarmonicOscillatorModel {

		private double length;
		private double mass;
		private double stiffness;
		private double initialDisplacement;

		public HarmonicOscillatorModel(double length, double mass, double stiffness,
									   double initialDisplacement) {
			this.length = length;
			this.mass = mass;
			this.stiffness = stiffness;
			this.initialDisplacement = initialDisplacement;
		}
	}

	interface HarmonicOscillatorSolver {
		public void setModel(HarmonicOscillatorModel model);
		public void setStep(double step);
		public void doStep();
		public double getPosition();
	}

	static class HarmonicOscillatorSolverVektor
			implements HarmonicOscillatorSolver {

		private double freeLength;
		private double stiffness;
		private double mass;
		private Vektor support;

		private Vektor position;
		private Vektor force;
		private Vektor velocity;

		private double step;

		@Override
		public void setModel(HarmonicOscillatorModel model) {
			freeLength = model.length;
			stiffness = model.stiffness;
			mass = model.mass;
			support = new Vektor(0, 0, 0);
			double initPosition = model.length + model.initialDisplacement;
			position = new Vektor(0, -initPosition, 0);
			velocity = new Vektor(0, 0, 0);
		}

		@Override
		public void setStep(double step) {
			this.step = step;
		}

		private void calculateForce() {
			Vektor spring = position.minus(support);
			double elongation = spring.magnitude() - freeLength;
			double strain = elongation / freeLength;
			double forceScalar = stiffness * strain;
			force = spring.normalized().negative().times(forceScalar);
		}

		private void calculateVelocity() {
			Vektor acceleration = force.times(1/mass);
			Vektor deltaV = acceleration.times(step);
			velocity = velocity.plus(deltaV);
		}

		private void calculatePosition() {
			Vektor delta = velocity.times(step);
			position = position.plus(delta);
		}

		@Override
		public void doStep() {
			calculateForce();
			calculateVelocity();
			calculatePosition();
		}

		@Override
		public double getPosition() {
			return position.get(1);
		}
	}

	static class HarmonicOscillatorSolverArrayVector
			implements HarmonicOscillatorSolver {

		private double freeLength;
		private double stiffness;
		private double mass;
		private Vector support;

		private Vector position;
		private Vector force;
		private Vector velocity;

		private double step;

		@Override
		public void setModel(HarmonicOscillatorModel model) {
			freeLength = model.length;
			stiffness = model.stiffness;
			mass = model.mass;
			support = ArrayVector.valueOf(0, 0, 0);
			double initPosition = model.length + model.initialDisplacement;
			position = ArrayVector.valueOf(0, -initPosition, 0);
			velocity = ArrayVector.valueOf(0, 0, 0);
		}

		@Override
		public void setStep(double step) {
			this.step = step;
		}

		private void calculateForce() {
			Vector spring = position.minus(support);
			double elongation = spring.magnitude() - freeLength;
			double strain = elongation / freeLength;
			double forceScalar = stiffness * strain;
			force = spring.normalized().negative().times(forceScalar);
		}

		private void calculateVelocity() {
			Vector acceleration = force.times(1/mass);
			Vector deltaV = acceleration.times(step);
			velocity = velocity.plus(deltaV);
		}

		private void calculatePosition() {
			Vector delta = velocity.times(step);
			position = position.plus(delta);
		}

		@Override
		public void doStep() {
			calculateForce();
			calculateVelocity();
			calculatePosition();
		}

		@Override
		public double getPosition() {
			return position.get(1);
		}
	}

	static class HarmonicOscillatorSolverVector3D
			implements HarmonicOscillatorSolver {

		private double freeLength;
		private double stiffness;
		private double mass;
		private Vector support;

		private Vector position;
		private Vector force;
		private Vector velocity;

		private double step;

		@Override
		public void setModel(HarmonicOscillatorModel model) {
			freeLength = model.length;
			stiffness = model.stiffness;
			mass = model.mass;
			support = Vector3D.valueOf(0, 0, 0);
			double initPosition = model.length + model.initialDisplacement;
			position = Vector3D.valueOf(0, -initPosition, 0);
			velocity = Vector3D.valueOf(0, 0, 0);
		}

		@Override
		public void setStep(double step) {
			this.step = step;
		}

		private void calculateForce() {
			Vector spring = position.minus(support);
			double elongation = spring.magnitude() - freeLength;
			double strain = elongation / freeLength;
			double forceScalar = stiffness * strain;
			force = spring.normalized().negative().times(forceScalar);
		}

		private void calculateVelocity() {
			Vector acceleration = force.times(1/mass);
			Vector deltaV = acceleration.times(step);
			velocity = velocity.plus(deltaV);
		}

		private void calculatePosition() {
			Vector delta = velocity.times(step);
			position = position.plus(delta);
		}

		@Override
		public void doStep() {
			calculateForce();
			calculateVelocity();
			calculatePosition();
		}

		@Override
		public double getPosition() {
			return position.get(1);
		}
	}

	static class HarmonicOscillatorSolverVector3DTyped
			implements HarmonicOscillatorSolver {

		private double freeLength;
		private double stiffness;
		private double mass;
		private Vector3D support;

		private Vector3D position;
		private Vector3D force;
		private Vector3D velocity;

		private double step;

		@Override
		public void setModel(HarmonicOscillatorModel model) {
			freeLength = model.length;
			stiffness = model.stiffness;
			mass = model.mass;
			support = Vector3D.valueOf(0, 0, 0);
			double initPosition = model.length + model.initialDisplacement;
			position = Vector3D.valueOf(0, -initPosition, 0);
			velocity = Vector3D.valueOf(0, 0, 0);
		}

		@Override
		public void setStep(double step) {
			this.step = step;
		}

		private void calculateForce() {
			Vector3D spring = position.minus(support);
			double elongation = spring.magnitude() - freeLength;
			double strain = elongation / freeLength;
			double forceScalar = stiffness * strain;
			force = spring.normalized().negative().times(forceScalar);
		}

		private void calculateVelocity() {
			Vector3D acceleration = force.times(1/mass);
			Vector3D deltaV = acceleration.times(step);
			velocity = velocity.plus(deltaV);
		}

		private void calculatePosition() {
			Vector3D delta = velocity.times(step);
			position = position.plus(delta);
		}

		@Override
		public void doStep() {
			calculateForce();
			calculateVelocity();
			calculatePosition();
		}

		@Override
		public double getPosition() {
			return position.get(1);
		}
	}

	static class HarmonicOscillatorSolverVector2D
			implements HarmonicOscillatorSolver {

		private double freeLength;
		private double stiffness;
		private double mass;
		private Vector support;

		private Vector position;
		private Vector force;
		private Vector velocity;

		private double step;

		@Override
		public void setModel(HarmonicOscillatorModel model) {
			freeLength = model.length;
			stiffness = model.stiffness;
			mass = model.mass;
			support = Vector2D.valueOf(0, 0);
			double initPosition = model.length + model.initialDisplacement;
			position = Vector2D.valueOf(0, -initPosition);
			velocity = Vector2D.valueOf(0, 0);
		}

		@Override
		public void setStep(double step) {
			this.step = step;
		}

		private void calculateForce() {
			Vector spring = position.minus(support);
			double elongation = spring.magnitude() - freeLength;
			double strain = elongation / freeLength;
			double forceScalar = stiffness * strain;
			force = spring.normalized().negative().times(forceScalar);
		}

		private void calculateVelocity() {
			Vector acceleration = force.times(1/mass);
			Vector deltaV = acceleration.times(step);
			velocity = velocity.plus(deltaV);
		}

		private void calculatePosition() {
			Vector delta = velocity.times(step);
			position = position.plus(delta);
		}

		@Override
		public void doStep() {
			calculateForce();
			calculateVelocity();
			calculatePosition();
		}

		@Override
		public double getPosition() {
			return position.get(1);
		}
	}

	static class HarmonicOscillatorSolverVector2DTyped
			implements HarmonicOscillatorSolver {

		private double freeLength;
		private double stiffness;
		private double mass;
		private Vector2D support;

		private Vector2D position;
		private Vector2D force;
		private Vector2D velocity;

		private double step;

		@Override
		public void setModel(HarmonicOscillatorModel model) {
			freeLength = model.length;
			stiffness = model.stiffness;
			mass = model.mass;
			support = Vector2D.valueOf(0, 0);
			double initPosition = model.length + model.initialDisplacement;
			position = Vector2D.valueOf(0, -initPosition);
			velocity = Vector2D.valueOf(0, 0);
		}

		@Override
		public void setStep(double step) {
			this.step = step;
		}

		private void calculateForce() {
			Vector2D spring = position.minus(support);
			double elongation = spring.magnitude() - freeLength;
			double strain = elongation / freeLength;
			double forceScalar = stiffness * strain;
			force = spring.normalized().negative().times(forceScalar);
		}

		private void calculateVelocity() {
			Vector2D acceleration = force.times(1/mass);
			Vector2D deltaV = acceleration.times(step);
			velocity = velocity.plus(deltaV);
		}

		private void calculatePosition() {
			Vector2D delta = velocity.times(step);
			position = position.plus(delta);
		}

		@Override
		public void doStep() {
			calculateForce();
			calculateVelocity();
			calculatePosition();
		}

		@Override
		public double getPosition() {
			return position.get(1);
		}
	}

	@Test
	public void harmoscVektor() {
		System.out.println("Implementation using cz.slanyj.euclideanVector.Vektor");
		harmosc(new HarmonicOscillatorSolverVektor(), "vektor");
		System.out.println();
	}

	@Test
	public void harmoscArrayVector() {
		System.out.println("Implementation using com.github.singond.physics.ArrayVector");
		harmosc(new HarmonicOscillatorSolverArrayVector(), "arrayVector");
		System.out.println();
	}

	@Test
	public void harmoscVector3D() {
		System.out.println("Implementation using com.github.singond.physics.Vector3D");
		harmosc(new HarmonicOscillatorSolverVector3D(), "vector3D");
		System.out.println();
	}

	@Test
	public void harmoscVector3DTyped() {
		System.out.println("Implementation using com.github.singond.physics.Vector3D (typed)");
		harmosc(new HarmonicOscillatorSolverVector3DTyped(), "vector3D-typed");
		System.out.println();
	}

	@Test
	public void harmoscVector2D() {
		System.out.println("Implementation using com.github.singond.physics.Vector2D");
		harmosc(new HarmonicOscillatorSolverVector2D(), "vector2D");
		System.out.println();
	}

	@Test
	public void harmoscVector2DTyped() {
		System.out.println("Implementation using com.github.singond.physics.Vector2D (typed)");
		harmosc(new HarmonicOscillatorSolverVector2DTyped(), "vector2D-typed");
		System.out.println();
	}

	private void harmosc(HarmonicOscillatorSolver solver, String suffix) {

		System.out.println("Setting up model of a simple harmonic oscillator");
		HarmonicOscillatorModel model = new HarmonicOscillatorModel(10, 5, 3, 2);
		solver.setModel(model);
		solver.setStep(0.0001);

		int steps = 10_000_000;

		String filename = "harmosc-" + suffix + ".csv";
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(filename);
			System.out.println("Directing output to " + filename);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		PrintWriter printWriter = new PrintWriter(fileWriter);

		System.out.println("Solving " + steps + " steps");
		long start = System.currentTimeMillis();
		for (int i = 0; i < steps; i++) {
			solver.doStep();
			printWriter.println(solver.getPosition());
		}
		printWriter.close();
		long end = System.currentTimeMillis();
		System.out.println("Finished in " + (end - start) + " ms");
	}
}
