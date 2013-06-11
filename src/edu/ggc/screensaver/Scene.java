package edu.ggc.screensaver;

import android.graphics.Canvas;

/**
 *
 *
 */
public abstract class Scene {
    int height;
    int width;
    int spriteCount;

    public abstract void renderScene(Canvas canvas);
}
