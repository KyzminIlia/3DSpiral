package com.example.spiraldrawer3d;

public class ScalingMatrix {
	double[][] mMatrix;

	public ScalingMatrix() {
		mMatrix = new double[3][3];
	}

	public void setScaling(double kx, double ky, double kz) {
		mMatrix[0][0] = kx;
		mMatrix[1][1] = ky;
		mMatrix[2][2] = kz;
	}

	public double[][] getArrayMatrix() {
		return mMatrix;
	}
}
