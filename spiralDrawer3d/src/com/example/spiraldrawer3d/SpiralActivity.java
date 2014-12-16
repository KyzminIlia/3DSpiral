package com.example.spiraldrawer3d;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SpiralActivity extends Activity {
	private final String TAG = SpiralActivity.class.getSimpleName();

	ImageView imageView;
	Bitmap spiral;
	Canvas spiralCanvas;
	Button buttonRotateY;
	Button buttonRotateX;
	Button buttonRotateZ;
	Button buttonScaling;
	SpiralDrawer drawer;
	double angle = 0;
	double kx = 1;
	double ky = 1;
	double kz = 1;
	int scalingCount=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.a_spiral);
		super.onCreate(savedInstanceState);
		imageView = (ImageView) findViewById(R.id.spiral_view);
		buttonRotateY = (Button) findViewById(R.id.rotate_Y);
		buttonRotateX = (Button) findViewById(R.id.rotate_X);
		buttonRotateZ = (Button) findViewById(R.id.rotate_Z);
		buttonScaling = (Button) findViewById(R.id.scaling);
		spiral = Bitmap.createBitmap(640, 960, Bitmap.Config.ARGB_8888);
		spiralCanvas = new Canvas(spiral);
		drawer = new SpiralDrawer();
		drawer.drawSpiral(spiral, Axis.Y, Math.PI / 4, Color.RED, kx, ky, kz);
		imageView.setImageBitmap(spiral);
		buttonRotateX.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				buttonRotateX.setEnabled(false);
				new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						while (angle <= 2 * Math.PI) {
							spiral.eraseColor(Color.WHITE);
							drawer.drawSpiral(spiral, Axis.X, angle,
									Color.RED, kx, ky, kz);
							angle += 2 * Math.PI / 48;
							publishProgress(null);
						}
						angle = 0;

						return null;
					}

					@Override
					protected void onProgressUpdate(Void... values) {
						imageView.setImageBitmap(spiral);
						super.onProgressUpdate(values);
					}
				}.execute();
				buttonRotateX.setEnabled(true);
			}
		});
		buttonRotateZ.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				buttonRotateZ.setEnabled(false);
				new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						while (angle <= 2 * Math.PI) {
							spiral.eraseColor(Color.WHITE);
							drawer.drawSpiral(spiral, Axis.Z, angle,
									Color.RED, kx, ky, kz);
							angle += 2 * Math.PI / 48;
							publishProgress(null);
						}
						angle = 0;
						spiral.eraseColor(Color.WHITE);
						drawer.drawSpiral(spiral, Axis.X, angle,
								Color.RED, kx, ky, kz);
						return null;
					}

					@Override
					protected void onProgressUpdate(Void... values) {
						imageView.setImageBitmap(spiral);
						super.onProgressUpdate(values);
					}
				}.execute();
				buttonRotateZ.setEnabled(true);
			}
		});
		buttonRotateY.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				buttonRotateY.setEnabled(false);
				new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						while (angle <= 2 * Math.PI) {
							spiral.eraseColor(Color.WHITE);
							drawer.drawSpiral(spiral, Axis.Y, angle,
									Color.RED, kx, ky, kz);
							angle += 2 * Math.PI / 48;
							publishProgress(null);
						}
						angle = 0;

						return null;
					}

					@Override
					protected void onProgressUpdate(Void... values) {
						imageView.setImageBitmap(spiral);
						super.onProgressUpdate(values);
					}
				}.execute();
				buttonRotateY.setEnabled(true);
			}
		});
		buttonScaling.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				buttonScaling.setEnabled(false);
				new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						while ((kx <= 2) && (ky <= 2) && (kz <= 2)) {
							spiral.eraseColor(Color.WHITE);
							drawer.drawSpiral(spiral, Axis.Y, Math.PI / 4,
									Color.RED, kx, ky, kz);
							kx++;
							ky++;
							kz++;
							publishProgress(null);
						}
						kx--;
						ky--;
						kz--;
						if (scalingCount ==2){
						while ((kx > 1) && (ky >= 1) && (kz >= 1)) {
							spiral.eraseColor(Color.WHITE);
							drawer.drawSpiral(spiral, Axis.Y, Math.PI / 4,
									Color.RED, kx, ky, kz);
							kx--;
							ky--;
							kz--;
							publishProgress(null);
							scalingCount=0;
						}
						}
						spiral.eraseColor(Color.WHITE);
						drawer.drawSpiral(spiral, Axis.Y, Math.PI/4, Color.RED, kx,
								ky, kz);
						publishProgress(null);
						scalingCount++;
						return null;
					}

					@Override
					protected void onProgressUpdate(Void... values) {
						imageView.setImageBitmap(spiral);
						super.onProgressUpdate(values);
					}
				}.execute();
				buttonScaling.setEnabled(true);
			}
		});

	}
}
