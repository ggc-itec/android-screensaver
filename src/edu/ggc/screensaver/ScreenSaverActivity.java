package edu.ggc.screensaver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class ScreenSaverActivity extends Activity implements OnTouchListener {

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	/** Called when the activity is first created. */
	private RenderingSurfaceView surfaceView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surfaceView = new RenderingSurfaceView(this);
		surfaceView.setOnTouchListener(this);
		setContentView(surfaceView);

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.bouncingball:
                surfaceView.setSceneType(RenderingSurfaceView.SceneType.BOUNCING_BALL);
                return true;
            case R.id.starfield:
                surfaceView.setSceneType(RenderingSurfaceView.SceneType.STAR_FIELD);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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