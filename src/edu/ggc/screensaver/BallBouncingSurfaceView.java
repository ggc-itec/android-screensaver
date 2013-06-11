package edu.ggc.screensaver;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BallBouncingSurfaceView extends SurfaceView implements Runnable {

	private Thread t = null;
	private SurfaceHolder holder;
	private boolean isRunning = false;
	private float x, y;
	public static final int NUM_STARS = 50;
	private Star[] stars;

	public BallBouncingSurfaceView(Context context) {
		super(context);
		holder = getHolder();
		x = y = 100;
		stars = new Star[NUM_STARS];
	}

	public void initialBalls(Star[] stars) {
		for (int i = 0; i < NUM_STARS; i++) {
			Random r = new Random();
			stars[i] = new Star(r.nextInt(getWidth()), r.nextInt(getHeight()),
					r.nextInt(20), getWidth(), getHeight());
			Log.d("SAVER", stars[i].toString());
		}
	}

	public void drawStars(Star[] stars, Canvas c) {
		for (int i = 0; i < stars.length; i++) {
			stars[i].draw(c);
			stars[i].tick();
		}
	}

    public void changeDirectionOfBall() {
        for(Star star: stars)
        {
            star.flipSpeed();
        }
    }

	@Override
	public void run() {

		while (isRunning) {
			if (!holder.getSurface().isValid()) {
				continue;
			}

			//populate the stars array before drawing any stars
			if (stars[0] == null)
				initialBalls(stars);

			Canvas c = holder.lockCanvas();
			Paint paint = new Paint();
			paint.setColor(Color.WHITE);

			c.drawColor(Color.BLACK);
			c.drawText("Hello World", x++, y, paint);
			drawStars(stars, c);

			holder.unlockCanvasAndPost(c);

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void pause() {
		isRunning = false;
		while (true) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
		t = null;
	}

	public void resume() {
		isRunning = true;
		t = new Thread(this);
		t.start();
	}

}
