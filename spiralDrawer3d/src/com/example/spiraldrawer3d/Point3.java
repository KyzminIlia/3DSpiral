package com.example.spiraldrawer3d;

public class Point3 {
	public final static double SMALL_RADIUS = 20;

	private final int SCREEN_WIDTH = 320;
	private final int SCREEN_HEIGHT = 480;

	private double x = 0;
	private double y = 0;
	private double z = 0;

	public Point3(double alpha, double betta, double bigRadius) {
		x = (bigRadius + SMALL_RADIUS * Math.cos(alpha)) * Math.sin(betta);
		y = (bigRadius + SMALL_RADIUS * Math.cos(alpha)) * Math.cos(betta);
		z = SMALL_RADIUS * Math.sin(alpha);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public double[] getPointArray() {
		double[] value = new double[3];
		value[0] = x;
		value[1] = y;
		value[2] = z;
		return value;
	}

	public void rotate(Axis axis, double angle) {
		RotateMatrix matrix = new RotateMatrix();
		double[] value = new double[3];
		switch (axis) {
		case X:
			matrix.setRotateX(angle);
			break;
		case Y:
			matrix.setRotateY(angle);
			break;
		case Z:
			matrix.setRotateZ(angle);
			break;
		default:
			matrix.setRotateY(angle);
			break;
		}
		value = multipli(getPointArray(), matrix);
		this.x = value[0];
		this.y = value[1];
		this.z = value[2];
		this.x = checkX(this.x + SCREEN_WIDTH / 2);
		this.y = checkY(this.y + SCREEN_HEIGHT / 2);
		this.z = checkZ(this.z + SCREEN_HEIGHT / 2);
	}

	public void scaling(double kx, double ky, double kz) {
		ScalingMatrix matrix = new ScalingMatrix();
		matrix.setScaling(kx, ky, kz);
		double[] value = new double[3];
		value = multipli(getPointArray(), matrix);
		this.x = value[0];
		this.y = value[1];
		this.z = value[2];
		if (kx != 1) {
			this.x = checkX(this.x - SCREEN_WIDTH / kx);
			this.y = checkY(this.y - SCREEN_WIDTH / kx);
			this.z = checkY(this.z - SCREEN_WIDTH / kx);
		} else {
			this.x = checkX(this.x);
			this.y = checkY(this.y);
			this.z = checkY(this.z);
		}

	}

	private double[] multipli(double[] A, RotateMatrix rotateMatix) {
		double value[] = new double[3];
		double B[][] = rotateMatix.getArrayMatrix();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				value[i] += A[j] * B[j][i];
			}
		}
		return value;
	}

	private double[] multipli(double[] A, ScalingMatrix sMatix) {
		double value[] = new double[3];
		double B[][] = sMatix.getArrayMatrix();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				value[i] += A[j] * B[j][i];
			}
		}
		return value;
	}

	private double checkX(double x) {
		if (x < 0)
			x = 0;
		if (x >= SCREEN_WIDTH)
			x = SCREEN_WIDTH - 1;
		return x;
	}

	private double checkY(double y) {
		if (y < 0)
			y = 0;
		if (y >= SCREEN_HEIGHT)
			y = SCREEN_HEIGHT - 1;
		return y;
	}

	private double checkZ(double z) {
		if (z < 0)
			z = 0;
		if (z >= SCREEN_HEIGHT)
			z = SCREEN_HEIGHT - 1;
		return z;
	}
}
