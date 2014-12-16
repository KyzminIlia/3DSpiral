package com.example.spiraldrawer3d;

public class RotateMatrix {

	double[][] mMatrix;

	public RotateMatrix() {
		mMatrix = new double[3][3];
	}

	public void setRotateY(double angle) {
		mMatrix[0][0] = Math.cos(angle);
		mMatrix[0][2] = Math.sin(angle);
		mMatrix[1][1] = 1;
		mMatrix[2][0] = -Math.sin(angle);
		mMatrix[2][2] = Math.cos(angle);
	}

	public void setRotateX(double angle) {
		mMatrix[0][0] = 1;
		mMatrix[1][1] = Math.cos(angle);
		mMatrix[1][2] = -Math.sin(angle);
		mMatrix[2][1] = Math.sin(angle);
		mMatrix[2][2] = Math.cos(angle);
	}

	public void setRotateZ(double angle) {
		mMatrix[2][2] = 1;
		mMatrix[0][0] = Math.cos(angle);
		mMatrix[0][1] = -Math.sin(angle);
		mMatrix[1][0] = Math.sin(angle);
		mMatrix[1][2] = Math.cos(angle);
	}

	public double[][] getArrayMatrix() {
		return mMatrix;
	}

}
