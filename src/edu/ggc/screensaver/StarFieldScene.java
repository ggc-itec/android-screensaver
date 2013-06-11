package edu.ggc.screensaver;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

/**
 *  StarFieldScene
 *  Written by Taylor J. Schieber
 *
 */
public class StarFieldScene extends Scene {

    private int starCount;
    private double [] x;
    private double [] y;
    private int width;
    private int height;
    private Point [] p;
    private double [] oy;
    private double [] ox;
    private double [] incs;
    private double [] r;
    private Random random;

    public StarFieldScene(int width, int height) {
        this.width = width;
        this.height = height;
        random = new Random();

        starCount = 50;
        //"number" is the number of stars, and is only used when setting initial conditions.

        x = new double[starCount];
        for(int i=0;i<x.length;i=i+1) {
            x[i] = random.nextInt(width+1);
        }
        //this for-loop sets the original values for x for each "star."

        y = new double[starCount];
        for(int i=0;i<y.length;i=i+1) {
            y[i] = random.nextInt(height+1);
        }
        //this for-loop sets the original values for y for each "star."

        incs = new double[starCount];
        for(int i=0;i<incs.length;i=i+1) {
            incs[i] = 1;
        }
        //this sets the "increment values" (essentially speed) to 1 for each "star."

        p = new Point[starCount];
        for(int i=0;i<p.length;i=i+1) {
            p[i] = new Point((int)x[i],(int)y[i]);
        }
        //this is what actually designates the stars as points, allowing them to be drawn.

        r = new double[starCount];
        for(int i=0;i<r.length;i=i+1) {
            r[i] = Math.sqrt( Math.pow((p[i].x-(width/2.0)),2) + Math.pow((p[i].y-(height/2.0)),2) );
        }
        //this sets the distances from each star to the center of the DrawingPanel. This is used later for some trig stuff.

        ox = new double[starCount];
        for(int i=0;i<ox.length;i=i+1) {
            ox[i] = (p[i].x-(width/2.0))/r[i];
        }
        //ox represents the component of incs (speed) that is in the x-direction.
        //It is based on the fact that cos(theta) = (adj)/(hyp), which in this case is = (x[i] - xo)/(r[i]).

        oy = new double[starCount];
        for(int i=0;i<oy.length;i=i+1) {
            oy[i] = (p[i].y-(height/2.0))/r[i];
        }
        //oy represents the component of incs (speed) that is in the y-direction.
        //It is based on the fact that sin(theta) = (opp)/(hyp), which in this case is = (y[i] - yo)/(r[i]).

        //Both of the vector-component values above are used to ensure that each star is moving /away/ from the center.

        int[] red = new int[starCount];
        for(int i=0;i<red.length;i=i+1) {
            red[i] = random.nextInt(156)+100;
        }
        //sets the red-value for each star's color.

        int[] green = new int[starCount];
        for(int i=0;i<green.length;i=i+1) {
            green[i] = random.nextInt(101)+155;
        }
        //sets the green-value for each star's color.

        int[] blue = new int[starCount];
        for(int i=0;i<blue.length;i=i+1) {
            blue[i] = random.nextInt(156)+100;
        }
        //sets the blue-value for each star's color.

//        Color[] color = new Color[starCount];
//        for(int i=0;i<color.length;i=i+1) {
//            color[i] = new Color(red[i],green[i],blue[i]);
//        }
        //sets each star's color based on their red/green/blue values.
        //each star is only /slightly/ different in color. you have great eyesight if you can see the differences!

    }

    @Override
    public void renderScene(Canvas canvas) {

        canvas.drawColor(Color.BLACK);
        //c.fillRect(0,0,xwindow,ywindow);

        for(int i=0;i<p.length;i=i+1) {

            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            canvas.drawText(".", (int) p[i].x, (int) p[i].y, paint);

            //each "star" is actually not a drawn shape at all. they're actually just periods!
            //instead of drawing an oval, i chose to simply draw the String "." on each point to represent stars.
            //using periods allows for more visible stars on the screen at a time, since they're smaller.
            //an interesting (if not hilarious) side-effect of the use of periods is that if one changes the string
            //in the g.drawString statement you can have floating words. It can be quite comical!

            p[i].x = (int)(p[i].x + incs[i]*ox[i]);
            p[i].y = (int)(p[i].y + incs[i]*oy[i]);

            //increments each star's position based on its speed and vector components.

            incs[i]=incs[i]+1;

            //increments the increment value (speed) for each star. This allows each star to get faster over time.

            if (p[i].x>width || p[i].x<0) {
                p[i].x = random.nextInt(width+1);
                p[i].y = random.nextInt(height+1);
                incs[i]=0;
            }
            else if (p[i].y>height || p[i].y<0) {
                p[i].x = random.nextInt(width+1);
                p[i].y = random.nextInt(height+1);
                incs[i]=0;
            }
            //these logic statements are intended to catch when stars pass off-screen, and generates "new" stars at a random
            //point when they do.

            r[i] = Math.sqrt(Math.pow((p[i].x-(width/2.0)),2)+Math.pow((p[i].y-(height/2.0)),2));
            ox[i] = (p[i].x-(width/2.0))/r[i];
            oy[i] = (p[i].y-(height/2.0))/r[i];
            //re-calculates the values of r, ox, and oy for each star.
        }
    }
}
