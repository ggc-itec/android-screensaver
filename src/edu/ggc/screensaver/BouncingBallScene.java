package edu.ggc.screensaver;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

/**
 *
 *
 */
public class BouncingBallScene extends Scene {

    private Star[] stars;

    public BouncingBallScene(int width, int height) {
       this.width = width;
       this.height = height;
       spriteCount = 50;
       stars = new Star[spriteCount];
       for (int i = 0; i < spriteCount; i++) {
            Random r = new Random();
            stars[i] = new Star(r.nextInt(width), r.nextInt(height),
                    r.nextInt(20), width, height);
        }
    }

    @Override
    public void renderScene(Canvas canvas) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw(canvas);
            stars[i].tick();
        }
    }
}
