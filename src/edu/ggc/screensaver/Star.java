package edu.ggc.screensaver;

import android.graphics.*;

public class Star {
	private int x;
	private int y;
	private int radius;
	private int xSpeed;
	private int ySpeed;
	private int width;
	private int height;

	public Star(int x, int y, int radius, int width, int height) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.width = width;
		this.height = height;
		setSpeed();
	}

	public void setSpeed() {
		if (radius < 7) {
			xSpeed = 1;
			ySpeed = 1;
		} else if (radius < 15) {
			xSpeed = 2;
			ySpeed = 2;
		} else {
			xSpeed = 3;
			ySpeed = 3;
		}
	}

	public void draw(Canvas c) {
		/*
		 * left The X coordinate of the left side of the rectangle 
		 * top The Y coordinate of the top of the rectangle 
		 * right The X coordinate of the right side of the rectangle 
		 * bottom The Y coordinate of the bottom of the rectangle
		 */
		RectF rect = new RectF(x, y, x+radius, y+radius);
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		c.drawOval(rect, paint);
	}

	public void tick() {
		if (x > width || x < 0) {
			xSpeed = -xSpeed;
		}

		if (y > height || y < 0) {
			ySpeed = -ySpeed;
		}
		x = x + xSpeed;
		y = y + ySpeed;
	}
}