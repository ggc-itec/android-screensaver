package edu.ggc.screensaver;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 *
 *
 */
public class RenderingSurfaceView extends SurfaceView implements Runnable{

    private Thread t = null;
    private SurfaceHolder holder;
    private boolean isRunning = false;
    private Scene scene;
    private SceneType type;

    public enum SceneType { BOUNCING_BALL, STAR_FIELD };

    public RenderingSurfaceView(Context context)
    {
        super(context);
        holder = getHolder();
        type = SceneType.BOUNCING_BALL;
    }

    public void setSceneType(SceneType type) {
        synchronized (scene) {
            scene = null;
            this.type = type;
        }
    }

    @Override
    public void run() {
        while (isRunning) {

            if (!holder.getSurface().isValid()) {
                continue;
            }

              if(scene == null)
              {
                if(type == SceneType.BOUNCING_BALL)
                    scene = new BouncingBallScene(getWidth(),getHeight());
                else if(type == SceneType.STAR_FIELD)
                    scene = new StarFieldScene(getWidth(),getHeight());
              }


            Canvas c = holder.lockCanvas();
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);

            c.drawColor(Color.BLACK);
            //c.drawText("Hello World", 100, 100, paint);

            if( scene != null)
                scene.renderScene(c);

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
