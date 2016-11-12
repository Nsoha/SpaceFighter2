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
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = (int) Math.pow(sensorEvent.values[0], 3) + 250;
            y = (int) Math.pow(sensorEvent.values[1], 3) + 400;

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
    private Bitmap player;
    private Bitmap bwPlayer;
    private Bitmap shot;
    private Bitmap bwShot;
    private Bitmap star;
    private Bitmap bwStar;
    DrawingThread thread;
    Paint text;
    float ex,ey,px,py,sx,sy;
    int score;
    boolean shot1 = false;
    int stX[] = new int[50];
    int stY[] = new int[50];



    public CustomView(Context ctx, AttributeSet attrs) {
        super(ctx,attrs);
        context = ctx;

        enemy = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy);
        bwEnemy=enemy.copy(Bitmap.Config.ARGB_8888, true);
        bwEnemy = resizeBitmap(bwEnemy,100,100);

        player = BitmapFactory.decodeResource(context.getResources(),R.drawable.player);
        bwPlayer=player.copy(Bitmap.Config.ARGB_8888, true);
        bwPlayer = resizeBitmap(bwPlayer,100,100);

        shot = BitmapFactory.decodeResource(context.getResources(),R.drawable.shot);
        bwShot=shot.copy(Bitmap.Config.ARGB_8888, true);
        bwShot = resizeBitmap(bwShot,25,50);

        star = BitmapFactory.decodeResource(context.getResources(),R.drawable.star);
        bwStar=star.copy(Bitmap.Config.ARGB_8888, true);
        bwStar = resizeBitmap(bwStar,10,10);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        text=new Paint();
        text.setTextAlign(Paint.Align.LEFT);
        text.setColor(Color.WHITE);
        text.setTextSize(48);
        ex= 0;
        ey= 0;
        px= 0;
        py= 0;
        sx= 0;
        sy= 0;
        score = 0;


        for(int i = 0; i < stX.length; i++) {
            stX[i] = (int) (Math.random());
        }

        for (int j = 0; j < stY.length; j++) {
            stY[j] = (int) (Math.random());

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

        py = canvas.getHeight() - 200;


        canvas.drawColor(Color.BLACK);

        for(int i = 0; i< stX.length; i++ ) {
            canvas.drawBitmap(bwStar, stX[i], stY[i], null);
            stY[i] += 30;
        }

        canvas.drawBitmap(bwEnemy,ex,ey,null);
        canvas.drawText("Score: " + score,canvas.getWidth() / 2 - 125,75,text);
        ey+=5;


        if(MainActivity.x + 50 > canvas.getWidth()){
            MainActivity.x =canvas.getWidth()-50;
        }
        if(MainActivity.x < -50){
            MainActivity.x = -50;
        }

        if(MainActivity.y > canvas.getHeight() - 50){
            MainActivity.y = canvas.getHeight() - 50;
        }
        if(MainActivity.y < -10){
            MainActivity.y = -10;
        }
        canvas.drawBitmap(bwPlayer,MainActivity.x,MainActivity.y,null);

        if(ey > canvas.getHeight()){
            ey =-200;
            ex =(int) (Math.random() * canvas.getWidth());
        }
        if(ex + 200 > canvas.getWidth()){
            ey =-200;
            ex =(int) (Math.random() * canvas.getWidth());
        }

        if(shot1) {
            canvas.drawBitmap(bwShot, sx, sy, null);
            sy-=30;
            if(sy < 0){
                shot1 = false;
            }

            double distance = Math.sqrt((sx - ex) * (sx - ex) + (sy - ey) * (sy - ey));
            if (distance < 100) {
                ex = (int) (Math.random() * canvas.getWidth());
                ey = - 100;
                shot1 = false;
                score++;
            }

        }
        for(int i = 0; i< stX.length; i++ ) {
        if (stY[i] > canvas.getHeight()) {
            stY[i] = (int) (Math.random() * canvas.getHeight()) - canvas.getHeight();
            stX[i] = (int) (Math.random() * canvas.getWidth());
        }
        }


    }





    @Override
    public boolean onTouchEvent(MotionEvent event ) {
        Log.v("touch event", event.getX() + "," + event.getY());
            if(!shot1) {
                sy = MainActivity.y - 50;
                sx = MainActivity.x + 40;
                shot1 = true;
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
