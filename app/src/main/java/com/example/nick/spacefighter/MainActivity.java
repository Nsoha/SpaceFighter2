package com.example.nick.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.sql.Array;
import java.util.Random;

import static android.R.attr.startX;
import static android.R.attr.x;
import static com.example.nick.spacefighter.R.id.canvas;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    public static int x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_GAME);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x -= (int) Math.pow(sensorEvent.values[0], 1);
            y += (int) Math.pow(sensorEvent.values[1], 1);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
class CustomView extends SurfaceView implements SurfaceHolder.Callback{


    protected Context context;
    private Bitmap enemy;
    private Bitmap bwEnemy;
    private Bitmap enemy2;
    private Bitmap bwEnemy2;
    private Bitmap enemy3;
    private Bitmap bwEnemy3;
    private Bitmap player;
    private Bitmap bwPlayer;
    private Bitmap playerdmg;
    private Bitmap bwPlayerdmg;
    private Bitmap shot;
    private Bitmap bwShot;
    private Bitmap star;
    private Bitmap bwStar;
    private Bitmap boom;
    private Bitmap bwBoom;
    DrawingThread thread;
    Paint text;
    int ex,ey,ex2,ey2,ex3,ey3,px,py,sx,sy,sx2,sy2;
    int score;
    boolean shot1 = false;
    boolean shot2 = false;
    boolean plyrdmg = false;
    int stX[] = new int[30];
    int stY[] = new int[30];
    int pyc;
    int pxc;
    int exc;
    int eyc;
    int exc2;
    int eyc2;
    int exc3;
    int eyc3;


    public CustomView(Context ctx, AttributeSet attrs) {
        super(ctx,attrs);
        context = ctx;

        enemy = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy);
        bwEnemy=enemy.copy(Bitmap.Config.ARGB_8888, true);
        bwEnemy = resizeBitmap(bwEnemy,100,100);

        player = BitmapFactory.decodeResource(context.getResources(),R.drawable.player);
        bwPlayer=player.copy(Bitmap.Config.ARGB_8888, true);
        bwPlayer = resizeBitmap(bwPlayer,100,100);

        playerdmg = BitmapFactory.decodeResource(context.getResources(),R.drawable.playerdmg);
        bwPlayerdmg=playerdmg.copy(Bitmap.Config.ARGB_8888, true);
        bwPlayerdmg = resizeBitmap(bwPlayerdmg,100,100);

        shot = BitmapFactory.decodeResource(context.getResources(),R.drawable.shot);
        bwShot=shot.copy(Bitmap.Config.ARGB_8888, true);
        bwShot = resizeBitmap(bwShot,25,50);

        star = BitmapFactory.decodeResource(context.getResources(),R.drawable.star);
        bwStar=star.copy(Bitmap.Config.ARGB_8888, true);
        bwStar = resizeBitmap(bwStar,10,10);

        enemy2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy2);
        bwEnemy2=enemy2.copy(Bitmap.Config.ARGB_8888, true);
        bwEnemy2 = resizeBitmap(bwEnemy2,100,100);

        enemy3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy3);
        bwEnemy3=enemy3.copy(Bitmap.Config.ARGB_8888, true);
        bwEnemy3 = resizeBitmap(bwEnemy3,100,100);

        boom = BitmapFactory.decodeResource(context.getResources(),R.drawable.boom);
        bwBoom=boom.copy(Bitmap.Config.ARGB_8888, true);
        bwBoom = resizeBitmap(bwBoom,100,100);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        text=new Paint();
        text.setTextAlign(Paint.Align.LEFT);
        text.setColor(Color.WHITE);
        text.setTextSize(48);
        ex= 0;
        ey= -300;
        ex2= 0;
        ey2= -300;
        ex3= 0;
        ey3= -300;
        px= 0;
        py= 0;
        sx= 0;
        sy= 0;
        sx2= 0;
        sy2= 0;
        score = 0;


        for (int j = 0; j < stY.length; j++) {
            stY[j] = 2000;

        }

    }


    public Bitmap resizeBitmap(Bitmap b, int newWidth, int newHeight) {
        int w = b.getWidth();
        int h = b.getHeight();
        float scaleWidth = ((float) newWidth) / w;
        float scaleHeight = ((float) newHeight) / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);


        Bitmap resizedBitmap = Bitmap.createBitmap(
                b, 0, 0, w, h, matrix, false);
        b.recycle();
        return resizedBitmap;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.setRunning(false);
        boolean waitingForDeath = true;
        while(waitingForDeath) {
            try {
                thread.join();
                waitingForDeath = false;
            }
            catch (Exception e) {
                Log.v("Thread Exception", "Waiting on drawing thread to die: " + e.getMessage());
            }
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread= new DrawingThread(holder, context, this);
        thread.setRunning(true);
        thread.start();
    }


    public void customDraw(Canvas canvas) {
        pyc = MainActivity.y + 50;
        pxc = MainActivity.x + 50;
        exc = ex + 50;
        eyc = ey + 50;
        exc2 = ex2 + 50;
        eyc2 = ey2 + 50;
        exc3 = ex3 + 50;
        eyc3 = ey3 + 50;
        plyrdmg = false;
        canvas.drawColor(Color.BLACK);

        for(int i = 0; i< stX.length; i++ ) {
            canvas.drawBitmap(bwStar, stX[i], stY[i], null);
            stY[i] += 30;
        }

        canvas.drawBitmap(bwEnemy,ex,ey,null);
        canvas.drawBitmap(bwEnemy2,ex2,ey2,null);
        canvas.drawBitmap(bwEnemy3,ex3,ey3,null);
        canvas.drawText("Score: " + score,canvas.getWidth() / 2 - 125,75,text);
        ey+=5;
        ey2+=7;
        ey3+=3;

        if(MainActivity.x + 50 > canvas.getWidth()){
            MainActivity.x =canvas.getWidth()-50;
        }
        if(MainActivity.x < -50){
            MainActivity.x = -50;
        }

        if(MainActivity.y > canvas.getHeight() - 100){
            MainActivity.y = canvas.getHeight() - 100;
        }
        if(MainActivity.y < -10){
            MainActivity.y = -10;
        }
        canvas.drawBitmap(bwPlayer,MainActivity.x,MainActivity.y,null);

        if(ey > canvas.getHeight()){
            ey =-200;
            ex =(int) (Math.random() * canvas.getWidth());
        }
        if(ex + 100 > canvas.getWidth()){
            ey =-200;
            ex =(int) (Math.random() * canvas.getWidth());
        }
        if(ey2 > canvas.getHeight()){
            ey2 =-300;
            ex2 =(int) (Math.random() * canvas.getWidth());
        }
        if(ex2 + 100 > canvas.getWidth()){
            ey2 =-200;
            ex2 =(int) (Math.random() * canvas.getWidth());
        }
        if(ey3 > canvas.getHeight()){
            ey3 =-200;
            ex3 =(int) (Math.random() * canvas.getWidth());
        }
        if(ex3 + 100 > canvas.getWidth()){
            ey3 =-200;
            ex3 =(int) (Math.random() * canvas.getWidth());
        }
        double Ecollision = Math.sqrt((exc2 - exc) * (exc2 - exc) + (eyc2 - eyc) * (eyc2 - eyc));
        if(Ecollision<75){
            canvas.drawBitmap(bwBoom,ex,ey,null);
            canvas.drawBitmap(bwBoom,ex2,ey2,null);
            ey =-200;
            ex =(int) (Math.random() * canvas.getWidth());
            ey2 =-300;
            ex2 =(int) (Math.random() * canvas.getWidth());

        }
        double Ecollision2 = Math.sqrt((exc3 - exc) * (exc3 - exc) + (eyc3 - eyc) * (eyc3 - eyc));
        if(Ecollision2<75){
            canvas.drawBitmap(bwBoom,ex,ey,null);
            canvas.drawBitmap(bwBoom,ex3,ey3,null);
            ey =-200;
            ex =(int) (Math.random() * canvas.getWidth());
            ey3 =-200;
            ex3 =(int) (Math.random() * canvas.getWidth());

        }
        double Ecollision3 = Math.sqrt((exc2 - exc3) * (exc2 - exc3) + (eyc2 - eyc3) * (eyc2 - eyc3));
        if(Ecollision3<75){
            canvas.drawBitmap(bwBoom,ex3,ey3,null);
            canvas.drawBitmap(bwBoom,ex2,ey2,null);
            ey3 =-200;
            ex3 =(int) (Math.random() * canvas.getWidth());
            ey2 =-300;
            ex2 =(int) (Math.random() * canvas.getWidth());

        }
        double collision = Math.sqrt((pxc - exc) * (pxc - exc) + (pyc - eyc) * (pyc - eyc));
        if(collision<75){
            canvas.drawBitmap(bwBoom,ex,ey,null);
            ey =-200;
            ex =(int) (Math.random() * canvas.getWidth());
            score -= 5;
            canvas.drawBitmap(bwPlayerdmg,MainActivity.x,MainActivity.y,null);
            plyrdmg = true;
        }
        double collision2 = Math.sqrt((pxc - exc2) * (pxc - exc2) + (pyc - eyc2) * (pyc - eyc2));
        if(collision2<75){
            canvas.drawBitmap(bwBoom,ex2,ey2,null);
            ey2 =-300;
            ex2 =(int) (Math.random() * canvas.getWidth());
            score -= 5;
            canvas.drawBitmap(bwPlayerdmg,MainActivity.x,MainActivity.y,null);
            plyrdmg = true;
        }
        double collision3 = Math.sqrt((pxc - exc3) * (pxc - exc3) + (pyc - eyc3) * (pyc - eyc3));
        if(collision3<75){
            canvas.drawBitmap(bwBoom,ex3,ey3,null);
            ey3 =-200;
            ex3 =(int) (Math.random() * canvas.getWidth());
            score -= 5;
            canvas.drawBitmap(bwPlayerdmg,MainActivity.x,MainActivity.y,null);
            plyrdmg = true;
        }

        if(shot1) {
            canvas.drawBitmap(bwShot, sx, sy, null);

            if(sy < 0){
                shot1 = false;
            }

            double distance = Math.sqrt((sx - exc) * (sx - exc) + (sy - eyc) * (sy - eyc));
            if (distance < 50) {
                canvas.drawBitmap(bwBoom,ex,ey,null);
                ex = (int) (Math.random() * canvas.getWidth());
                ey = - 100;
                shot1 = false;
                score+=3;
            }
            double distance2 = Math.sqrt((sx - exc2) * (sx - exc2) + (sy - eyc2) * (sy - eyc2));
            if (distance2 < 50) {
                canvas.drawBitmap(bwBoom,ex2,ey2,null);
                ex2 = (int) (Math.random() * canvas.getWidth());
                ey2 = - 300;
                shot1 = false;
                score+=5;
            }
            double distance3 = Math.sqrt((sx - exc3) * (sx - exc3) + (sy - eyc3) * (sy - eyc3));
            if (distance3 < 50) {
                canvas.drawBitmap(bwBoom,ex3,ey3,null);
                ex3 = (int) (Math.random() * canvas.getWidth());
                ey3 = - 100;
                shot1 = false;
                score+=10;
            }

        }
        if(shot2) {
            canvas.drawBitmap(bwShot, sx2, sy2, null);

            if(sy2 < 0){
                shot2 = false;
            }

            double distance = Math.sqrt((sx2 - exc) * (sx2 - exc) + (sy2 - eyc) * (sy2 - eyc));
            if (distance < 50) {
                canvas.drawBitmap(bwBoom,ex,ey,null);
                ex = (int) (Math.random() * canvas.getWidth());
                ey = - 100;
                shot2 = false;
                score+=3;
            }
            double distance2 = Math.sqrt((sx2 - exc2) * (sx2 - exc2) + (sy2 - eyc2) * (sy2 - eyc2));
            if (distance2 < 50) {
                canvas.drawBitmap(bwBoom,ex2,ey2,null);
                ex2 = (int) (Math.random() * canvas.getWidth());
                ey2 = - 300;
                shot2 = false;
                score+=5;
            }
            double distance3 = Math.sqrt((sx2 - exc3) * (sx2 - exc3) + (sy2 - eyc3) * (sy2 - eyc3));
            if (distance3 < 50) {
                canvas.drawBitmap(bwBoom,ex3,ey3,null);
                ex3 = (int) (Math.random() * canvas.getWidth());
                ey3 = - 100;
                shot2 = false;
                score+=10;
            }

        }
        for(int i = 0; i< stX.length; i++ ) {
        if (stY[i] > canvas.getHeight()) {
            stY[i] = (int) (Math.random() * canvas.getHeight()) - canvas.getHeight();
            stX[i] = (int) (Math.random() * canvas.getWidth());
        }
        }

        sy2-=30;
        sy-=30;
        if(plyrdmg){
            canvas.drawBitmap(bwPlayerdmg,MainActivity.x,MainActivity.y,null);
        }
    }





    @Override
    public boolean onTouchEvent(MotionEvent event ) {

        Log.v("touch event", event.getX() + "," + event.getY());
        if(!shot1&& sy2 < pyc - 500) {
            sy = MainActivity.y - 50;
            sx = MainActivity.x + 40;
            shot1 = true;
            score--;
            return true;
        }
        else if(shot1 && !shot2 && sy < pyc - 500) {
            sy2 = MainActivity.y - 50;
            sx2 = MainActivity.x + 40;
            score--;
            shot2 = true;
        }

        return true;
    }


    class DrawingThread extends Thread {
        private boolean running;
        private Canvas canvas;
        private SurfaceHolder holder;
        private Context context;
        private CustomView view;

        private int FRAME_RATE = 30;
        private double delay = 1.0 / FRAME_RATE * 1000;
        private long time;

        public DrawingThread(SurfaceHolder holder, Context c, CustomView v) {
            this.holder=holder;
            context = c;
            view = v;
            time = System.currentTimeMillis();
        }

        void setRunning(boolean r) {
            running = r;
        }

        @Override
        public void run() {
            super.run();
            while(running){
                if(System.currentTimeMillis() - time > delay) {
                    time = System.currentTimeMillis();
                    canvas = holder.lockCanvas();
                    if(canvas!=null){
                        view.customDraw(canvas);

                        holder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }



    }

}
