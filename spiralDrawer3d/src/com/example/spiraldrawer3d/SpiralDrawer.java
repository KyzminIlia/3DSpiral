package com.example.spiraldrawer3d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;

public class SpiralDrawer {

	private final double ALPHA = 2 * Math.PI;
	private final double BETTA = 4 * Math.PI;

	private final double STEP_ALPHA = ALPHA / 20;
	private final double STEP_BETTA = BETTA / 30;

	private final double POINTS_COUNT = 20 * 30;

	private final int STEPS_BETTA = (int) (BETTA / STEP_BETTA);

	private final double STEP_RADIUS = Point3.SMALL_RADIUS * Math.PI * 50
			/ (150 * 50);

	Point3 points[];
	Point3 p;
	Point3[] prevAlpha;
	double bigRadius;
	Axis axis;
	double angle;

	public void drawSpiral(Bitmap dest, Axis axis, double angle, int color,
			double kx, double ky, double kz) {
		p = null;
		prevAlpha = new Point3[STEPS_BETTA + 1];
		bigRadius = 0;
		this.axis = axis;
		this.angle = angle;
		Paint paint = new Paint();
		paint.setColor(color);
		Canvas canvas = new Canvas(dest);
		for (double alpha = 0; alpha <= ALPHA; alpha += STEP_ALPHA) {
			Point3 prev = null;
			int cur = 0;
			bigRadius = 15;
			for (double betta = 0; betta <= BETTA; betta += STEP_BETTA, cur++) {
				p = new Point3(alpha, betta, bigRadius);
				p.rotate(axis, angle);
				p.scaling(kx, ky, kz);
				if (betta != 0) {
					if (axis == Axis.Z) {
						canvas.drawLine((float) p.getX(), (float) p.getZ(),
								(float) prev.getX(), (float) prev.getZ(), paint);
					} else {
						canvas.drawLine((float) p.getX(), (float) p.getY(),
								(float) prev.getX(), (float) prev.getY(), paint);
					}

				}
				if (alpha != 0) {
					if (axis == Axis.Z) {
						canvas.drawLine((float) p.getX(), (float) p.getZ(),
								(float) prevAlpha[cur].getX(),
								(float) prevAlpha[cur].getZ(), paint);
					} else {
						canvas.drawLine((float) p.getX(), (float) p.getY(),
								(float) prevAlpha[cur].getX(),
								(float) prevAlpha[cur].getY(), paint);
					}
				}
				prevAlpha[cur] = p;
				prev = p;
				bigRadius += 5;
			}
		}
	}

}
