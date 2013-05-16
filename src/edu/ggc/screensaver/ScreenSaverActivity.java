package edu.ggc.screensaver;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class ScreenSaverActivity extends Activity implements OnTouchListener {

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	/** Called when the activity is first created. */
	private StarSurfaceView surfaceView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surfaceView = new StarSurfaceView(this);
		surfaceView.setOnTouchListener(this);
		setContentView(surfaceView);

	}

	@Override
	protected void onPause() {
		super.onPause();
		surfaceView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		surfaceView.resume();
	}


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}
}