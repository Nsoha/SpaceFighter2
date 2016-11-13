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

    public long startTime;
    protected Context context;
    private Bitmap enemy;
    private Bitmap bwEnemy;
    private Bitmap enemy2;
    private Bitmap bwEnemy2;
    private Bitmap enemy3;
    private Bitmap bwEnemy3;
    private Bitmap enemy4;
    private Bitmap bwEnemy4;
    private Bitmap enemy5;
    private Bitmap bwEnemy5;
    private Bitmap enemy6;
    private Bitmap bwEnemy6;
    private Bitmap mothership;
    private Bitmap bwMothership;
    private Bitmap mothership2;
    private Bitmap bwMothership2;
    private Bitmap mothership3;
    private Bitmap bwMothership3;
    private Bitmap mothership4;
    private Bitmap bwMothership4;
    private Bitmap mothership5;
    private Bitmap bwMothership5;
    private Bitmap eshot;
    private Bitmap bwEshot;
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
    int ex,ey,ex2,ey2,ex3,ey3,ex4,ey4,ex5,ey5,ex6,ey6,msx,msy,px,py,sx,sy,sx2,sy2, esx1,esy1,esx2,esy2,esx3,esy3,esx4,esy4;
    int score;
    boolean shot1 = false;
    boolean shot2 = false;
    boolean eshot1 = false;
    boolean eshot2 = false;
    boolean eshot3 = false;
    boolean eshot4 = false;
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
    int exc4;
    int eyc4;
    int exc5;
    int eyc5;
    int exc6;
    int eyc6;
    int msxc;
    int msyc;
    boolean e1 = true;
    boolean e2 = true;
    boolean e3 = true;
    boolean e4 = true;
    boolean e5 = true;
    boolean e6 = true;
    boolean right = true;
    int msh = 25;

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

        enemy4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy4);
        bwEnemy4=enemy4.copy(Bitmap.Config.ARGB_8888, true);
        bwEnemy4 = resizeBitmap(bwEnemy4,100,100);

        enemy5 = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy5);
        bwEnemy5=enemy5.copy(Bitmap.Config.ARGB_8888, true);
        bwEnemy5 = resizeBitmap(bwEnemy5,100,100);

        enemy6 = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy6);
        bwEnemy6=enemy6.copy(Bitmap.Config.ARGB_8888, true);
        bwEnemy6 = resizeBitmap(bwEnemy6,100,100);

        boom = BitmapFactory.decodeResource(context.getResources(),R.drawable.boom);
        bwBoom=boom.copy(Bitmap.Config.ARGB_8888, true);
        bwBoom = resizeBitmap(bwBoom,100,100);

        mothership = BitmapFactory.decodeResource(context.getResources(),R.drawable.mothership);
        bwMothership=mothership.copy(Bitmap.Config.ARGB_8888, true);
        bwMothership = resizeBitmap(bwMothership,200,100);

        mothership2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.mothership2);
        bwMothership2=mothership2.copy(Bitmap.Config.ARGB_8888, true);
        bwMothership2 = resizeBitmap(bwMothership2,200,100);

        mothership3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.mothership3);
        bwMothership3=mothership3.copy(Bitmap.Config.ARGB_8888, true);
        bwMothership3 = resizeBitmap(bwMothership3,200,100);

        mothership4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.mothership4);
        bwMothership4=mothership4.copy(Bitmap.Config.ARGB_8888, true);
        bwMothership4 = resizeBitmap(bwMothership4,200,100);

        mothership5 = BitmapFactory.decodeResource(context.getResources(),R.drawable.mothership5);
        bwMothership5=mothership5.copy(Bitmap.Config.ARGB_8888, true);
        bwMothership5 = resizeBitmap(bwMothership5,200,100);

        eshot = BitmapFactory.decodeResource(context.getResources(),R.drawable.eshot);
        bwEshot=eshot.copy(Bitmap.Config.ARGB_8888, true);
        bwEshot = resizeBitmap(bwEshot,25,50);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        text=new Paint();
        text.setTextAlign(Paint.Align.LEFT);
        text.setColor(Color.WHITE);
        text.setTextSize(30);
        ex= 0;
        ey= -300;
        ex2= 0;
        ey2= -300;
        ex3= 0;
        ey3= -300;
        ex4= 0;
        ey4= -300;
        ex5= 0;
        ey5= -300;
        ex6= 0;
        ey6= -300;
        msx = 0;
        msy = -150;
        px= 0;
        py= 0;
        sx= 0;
        sy= 0;
        sx2= 0;
        sy2= 0;
        esx1 = 0;
        esy1 = 0;
        esx2 = 0;
        esy2 = 0;
        esx3 = 0;
        esy3 = 0;
        esx4 = 0;
        esy4 = 0;
        score = 0;

        startTime = System.currentTimeMillis();

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
        long timeNow = System.currentTimeMillis();
        long timeToGo = 120 - (timeNow - startTime) / 1000;
        pyc = MainActivity.y + 50;
        pxc = MainActivity.x + 50;
        exc = ex + 50;
        eyc = ey + 50;
        exc2 = ex2 + 50;
        eyc2 = ey2 + 50;
        exc3 = ex3 + 50;
        eyc3 = ey3 + 50;
        exc4 = ex4 + 50;
        eyc4 = ey4 + 50;
        exc5 = ex5 + 50;
        eyc5 = ey5 + 50;
        exc6 = ex6 + 50;
        eyc6 = ey6 + 50;
        msxc = msx + 100;
        msyc = msy + 50;
        plyrdmg = false;

        canvas.drawColor(Color.BLACK);

        for(int i = 0; i< stX.length; i++ ) {
            canvas.drawBitmap(bwStar, stX[i], stY[i], null);
            stY[i] += 30;
        }

    if(timeToGo >= 5) {


        canvas.drawBitmap(bwEnemy, ex, ey, null);
        canvas.drawBitmap(bwEnemy2, ex2, ey2, null);
        canvas.drawBitmap(bwEnemy3, ex3, ey3, null);
        canvas.drawBitmap(bwEnemy4, ex4, ey4, null);
        canvas.drawBitmap(bwEnemy5, ex5, ey5, null);
        canvas.drawBitmap(bwEnemy6, ex6, ey6, null);
        ey += 5;
        ey2 += 7;
        ey3 += 3;
        ey4 += 3;
        ey5 += 7;
        ey6 += 5;

        if (MainActivity.x + 50 > canvas.getWidth()) {
            MainActivity.x = canvas.getWidth() - 50;
        }
        if (MainActivity.x < -50) {
            MainActivity.x = -50;
        }

        if (MainActivity.y > canvas.getHeight() - 100) {
            MainActivity.y = canvas.getHeight() - 100;
        }
        if (MainActivity.y < -10) {
            MainActivity.y = -10;
        }
        canvas.drawBitmap(bwPlayer, MainActivity.x, MainActivity.y, null);
        canvas.drawText("Score: " + score, 0, 50, text);
        if (timeToGo >= 0) {
            canvas.drawText("Mothership: " + timeToGo, canvas.getWidth() / 2, 50, text);
        }
        if (ey > canvas.getHeight()) {
            ey = -200;
            ex = (int) (Math.random() * canvas.getWidth());
            score -= 3;
        }
        if (ex + 100 > canvas.getWidth()) {
            ey = -200;
            ex = (int) (Math.random() * canvas.getWidth());
        }
        if (ey2 > canvas.getHeight()) {
            ey2 = -300;
            ex2 = (int) (Math.random() * canvas.getWidth());
            score -= 5;
        }
        if (ex2 + 100 > canvas.getWidth()) {
            ey2 = -200;
            ex2 = (int) (Math.random() * canvas.getWidth());
        }
        if (ey3 > canvas.getHeight()) {
            ey3 = -200;
            ex3 = (int) (Math.random() * canvas.getWidth());
            score -= 10;
        }
        if (ex3 + 100 > canvas.getWidth()) {
            ey3 = -200;
            ex3 = (int) (Math.random() * canvas.getWidth());
        }
        if (ey4 > canvas.getHeight()) {
            ey4 = -200;
            ex4 = (int) (Math.random() * canvas.getWidth());
            score -= 10;
        }
        if (ex4 + 100 > canvas.getWidth()) {
            ey4 = -200;
            ex4 = (int) (Math.random() * canvas.getWidth());
        }
        if (ey5 > canvas.getHeight()) {
            ey5 = -200;
            ex5 = (int) (Math.random() * canvas.getWidth());
            score -= 5;
        }
        if (ex5 + 100 > canvas.getWidth()) {
            ey5 = -200;
            ex5 = (int) (Math.random() * canvas.getWidth());
        }
        if (ey6 > canvas.getHeight()) {
            ey6 = -200;
            ex6 = (int) (Math.random() * canvas.getWidth());
            score -= 3;
        }
        if (ex6 + 100 > canvas.getWidth()) {
            ey6 = -200;
            ex6 = (int) (Math.random() * canvas.getWidth());
        }

        double Ecollision = Math.sqrt((exc2 - exc) * (exc2 - exc) + (eyc2 - eyc) * (eyc2 - eyc));
        if (Ecollision < 75) {
            canvas.drawBitmap(bwBoom, ex, ey, null);
            canvas.drawBitmap(bwBoom, ex2, ey2, null);
            ey = -200;
            ex = (int) (Math.random() * canvas.getWidth());
            ey2 = -300;
            ex2 = (int) (Math.random() * canvas.getWidth());

        }
        double Ecollision2 = Math.sqrt((exc3 - exc) * (exc3 - exc) + (eyc3 - eyc) * (eyc3 - eyc));
        if (Ecollision2 < 75) {
            canvas.drawBitmap(bwBoom, ex, ey, null);
            canvas.drawBitmap(bwBoom, ex3, ey3, null);
            ey = -200;
            ex = (int) (Math.random() * canvas.getWidth());
            ey3 = -200;
            ex3 = (int) (Math.random() * canvas.getWidth());

        }
        double Ecollision3 = Math.sqrt((exc2 - exc3) * (exc2 - exc3) + (eyc2 - eyc3) * (eyc2 - eyc3));
        if (Ecollision3 < 75) {
            canvas.drawBitmap(bwBoom, ex3, ey3, null);
            canvas.drawBitmap(bwBoom, ex2, ey2, null);
            ey3 = -200;
            ex3 = (int) (Math.random() * canvas.getWidth());
            ey2 = -300;
            ex2 = (int) (Math.random() * canvas.getWidth());
        }
        double Ecollision4 = Math.sqrt((exc4 - exc) * (exc4 - exc) + (eyc4 - eyc) * (eyc4 - eyc));
        if (Ecollision4 < 75) {
            canvas.drawBitmap(bwBoom, ex, ey, null);
            canvas.drawBitmap(bwBoom, ex4, ey4, null);
            ey = -200;
            ex = (int) (Math.random() * canvas.getWidth());
            ey4 = -300;
            ex4 = (int) (Math.random() * canvas.getWidth());
        }
        double Ecollision5 = Math.sqrt((exc4 - exc2) * (exc4 - exc2) + (eyc4 - eyc2) * (eyc4 - eyc2));
        if (Ecollision5 < 75) {
            canvas.drawBitmap(bwBoom, ex2, ey2, null);
            canvas.drawBitmap(bwBoom, ex4, ey4, null);
            ey2 = -200;
            ex2 = (int) (Math.random() * canvas.getWidth());
            ey4 = -300;
            ex4 = (int) (Math.random() * canvas.getWidth());
        }
        double Ecollision6 = Math.sqrt((exc4 - exc3) * (exc4 - exc3) + (eyc4 - eyc3) * (eyc4 - eyc3));
        if (Ecollision6 < 75) {
            canvas.drawBitmap(bwBoom, ex3, ey3, null);
            canvas.drawBitmap(bwBoom, ex4, ey4, null);
            ey3 = -200;
            ex3 = (int) (Math.random() * canvas.getWidth());
            ey4 = -300;
            ex4 = (int) (Math.random() * canvas.getWidth());
        }
        double Ecollision7 = Math.sqrt((exc5 - exc) * (exc5 - exc) + (eyc5 - eyc) * (eyc5 - eyc));
        if (Ecollision7 < 75) {
            canvas.drawBitmap(bwBoom, ex, ey, null);
            canvas.drawBitmap(bwBoom, ex5, ey5, null);
            ey = -200;
            ex = (int) (Math.random() * canvas.getWidth());
            ey5 = -300;
            ex5 = (int) (Math.random() * canvas.getWidth());
        }
        double Ecollision8 = Math.sqrt((exc5 - exc2) * (exc5 - exc2) + (eyc5 - eyc2) * (eyc5 - eyc2));
        if (Ecollision8 < 75) {
            canvas.drawBitmap(bwBoom, ex2, ey2, null);
            canvas.drawBitmap(bwBoom, ex5, ey5, null);
            ey2 = -200;
            ex2 = (int) (Math.random() * canvas.getWidth());
            ey5 = -300;
            ex5 = (int) (Math.random() * canvas.getWidth());
        }
        double Ecollision9 = Math.sqrt((exc5 - exc3) * (exc5 - exc3) + (eyc5 - eyc3) * (eyc5 - eyc3));
        if (Ecollision9 < 75) {
            canvas.drawBitmap(bwBoom, ex3, ey3, null);
            canvas.drawBitmap(bwBoom, ex5, ey5, null);
            ey3 = -200;
            ex3 = (int) (Math.random() * canvas.getWidth());
            ey5 = -300;
            ex5 = (int) (Math.random() * canvas.getWidth());
        }
        double Ecollision10 = Math.sqrt((exc5 - exc4) * (exc5 - exc4) + (eyc5 - eyc4) * (eyc5 - eyc4));
        if (Ecollision10 < 75) {
            canvas.drawBitmap(bwBoom, ex4, ey4, null);
            canvas.drawBitmap(bwBoom, ex5, ey5, null);
            ey4 = -200;
            ex4 = (int) (Math.random() * canvas.getWidth());
            ey5 = -300;
            ex5 = (int) (Math.random() * canvas.getWidth());
        }
        double Ecollision11 = Math.sqrt((exc6 - exc) * (exc6 - exc) + (eyc6 - eyc) * (eyc6 - eyc));
        if (Ecollision11 < 75) {
            canvas.drawBitmap(bwBoom, ex6, ey6, null);
            canvas.drawBitmap(bwBoom, ex, ey, null);
            ey = -200;
            ex = (int) (Math.random() * canvas.getWidth());
            ey6 = -200;
            ex6 = (int) (Math.random() * canvas.getWidth());
        }
        double Ecollision12 = Math.sqrt((exc6 - exc2) * (exc6 - exc2) + (eyc6 - eyc2) * (eyc6 - eyc2));
        if (Ecollision12 < 75) {
            canvas.drawBitmap(bwBoom, ex6, ey6, null);
            canvas.drawBitmap(bwBoom, ex2, ey2, null);
            ey2 = -300;
            ex2 = (int) (Math.random() * canvas.getWidth());
            ey6 = -200;
            ex6 = (int) (Math.random() * canvas.getWidth());
        }
        double Ecollision13 = Math.sqrt((exc6 - exc3) * (exc6 - exc3) + (eyc6 - eyc3) * (eyc6 - eyc3));
        if (Ecollision13 < 75) {
            canvas.drawBitmap(bwBoom, ex6, ey6, null);
            canvas.drawBitmap(bwBoom, ex3, ey3, null);
            ey3 = -200;
            ex3 = (int) (Math.random() * canvas.getWidth());
            ey6 = -200;
            ex6 = (int) (Math.random() * canvas.getWidth());
        }
        double Ecollision14 = Math.sqrt((exc6 - exc4) * (exc6 - exc4) + (eyc6 - eyc4) * (eyc6 - eyc4));
        if (Ecollision14 < 75) {
            canvas.drawBitmap(bwBoom, ex6, ey6, null);
            canvas.drawBitmap(bwBoom, ex4, ey4, null);
            ey4 = -200;
            ex4 = (int) (Math.random() * canvas.getWidth());
            ey6 = -200;
            ex6 = (int) (Math.random() * canvas.getWidth());
        }
        double Ecollision15 = Math.sqrt((exc6 - exc5) * (exc6 - exc5) + (eyc6 - eyc5) * (eyc6 - eyc5));
        if (Ecollision15 < 75) {
            canvas.drawBitmap(bwBoom, ex6, ey6, null);
            canvas.drawBitmap(bwBoom, ex5, ey5, null);
            ey5 = -200;
            ex5 = (int) (Math.random() * canvas.getWidth());
            ey6 = -200;
            ex6 = (int) (Math.random() * canvas.getWidth());
        }

        double collision = Math.sqrt((pxc - exc) * (pxc - exc) + (pyc - eyc) * (pyc - eyc));
        if (collision < 75) {
            canvas.drawBitmap(bwBoom, ex, ey, null);
            ey = -200;
            ex = (int) (Math.random() * canvas.getWidth());
            score -= 5;
            canvas.drawBitmap(bwPlayerdmg, MainActivity.x, MainActivity.y, null);
            plyrdmg = true;
        }
        double collision2 = Math.sqrt((pxc - exc2) * (pxc - exc2) + (pyc - eyc2) * (pyc - eyc2));
        if (collision2 < 75) {
            canvas.drawBitmap(bwBoom, ex2, ey2, null);
            ey2 = -300;
            ex2 = (int) (Math.random() * canvas.getWidth());
            score -= 5;
            canvas.drawBitmap(bwPlayerdmg, MainActivity.x, MainActivity.y, null);
            plyrdmg = true;
        }
        double collision3 = Math.sqrt((pxc - exc3) * (pxc - exc3) + (pyc - eyc3) * (pyc - eyc3));
        if (collision3 < 75) {
            canvas.drawBitmap(bwBoom, ex3, ey3, null);
            ey3 = -200;
            ex3 = (int) (Math.random() * canvas.getWidth());
            score -= 5;
            canvas.drawBitmap(bwPlayerdmg, MainActivity.x, MainActivity.y, null);
            plyrdmg = true;
        }
        double collision4 = Math.sqrt((pxc - exc4) * (pxc - exc4) + (pyc - eyc4) * (pyc - eyc4));
        if (collision4 < 75) {
            canvas.drawBitmap(bwBoom, ex4, ey4, null);
            ey4 = -200;
            ex4 = (int) (Math.random() * canvas.getWidth());
            score -= 5;
            canvas.drawBitmap(bwPlayerdmg, MainActivity.x, MainActivity.y, null);
            plyrdmg = true;
        }
        double collision5 = Math.sqrt((pxc - exc5) * (pxc - exc5) + (pyc - eyc5) * (pyc - eyc5));
        if (collision5 < 75) {
            canvas.drawBitmap(bwBoom, ex5, ey5, null);
            ey5 = -300;
            ex5 = (int) (Math.random() * canvas.getWidth());
            score -= 5;
            canvas.drawBitmap(bwPlayerdmg, MainActivity.x, MainActivity.y, null);
            plyrdmg = true;
        }
        double collision6 = Math.sqrt((pxc - exc6) * (pxc - exc6) + (pyc - eyc6) * (pyc - eyc6));
        if (collision6 < 75) {
            canvas.drawBitmap(bwBoom, ex6, ey6, null);
            ey6 = -200;
            ex6 = (int) (Math.random() * canvas.getWidth());
            score -= 3;
            canvas.drawBitmap(bwPlayerdmg, MainActivity.x, MainActivity.y, null);
            plyrdmg = true;
        }

        if (shot1) {
            canvas.drawBitmap(bwShot, sx, sy, null);

            if (sy < 0) {
                shot1 = false;
            }

            double distance = Math.sqrt((sx - exc) * (sx - exc) + (sy - eyc) * (sy - eyc));
            if (distance < 50) {
                canvas.drawBitmap(bwBoom, ex, ey, null);
                ex = (int) (Math.random() * canvas.getWidth());
                ey = -100;
                shot1 = false;
                score += 3;
            }
            double distance2 = Math.sqrt((sx - exc2) * (sx - exc2) + (sy - eyc2) * (sy - eyc2));
            if (distance2 < 50) {
                canvas.drawBitmap(bwBoom, ex2, ey2, null);
                ex2 = (int) (Math.random() * canvas.getWidth());
                ey2 = -300;
                shot1 = false;
                score += 5;
            }
            double distance3 = Math.sqrt((sx - exc3) * (sx - exc3) + (sy - eyc3) * (sy - eyc3));
            if (distance3 < 50) {
                canvas.drawBitmap(bwBoom, ex3, ey3, null);
                ex3 = (int) (Math.random() * canvas.getWidth());
                ey3 = -100;
                shot1 = false;
                score += 10;
            }
            double distance4 = Math.sqrt((sx - exc4) * (sx - exc4) + (sy - eyc4) * (sy - eyc4));
            if (distance4 < 50) {
                canvas.drawBitmap(bwBoom, ex4, ey4, null);
                ex4 = (int) (Math.random() * canvas.getWidth());
                ey4 = -100;
                shot1 = false;
                score += 10;
            }
            double distance5 = Math.sqrt((sx - exc5) * (sx - exc5) + (sy - eyc5) * (sy - eyc5));
            if (distance5 < 50) {
                canvas.drawBitmap(bwBoom, ex5, ey5, null);
                ex5 = (int) (Math.random() * canvas.getWidth());
                ey5 = -300;
                shot1 = false;
                score += 5;
            }
            double distance6 = Math.sqrt((sx - exc6) * (sx - exc6) + (sy - eyc6) * (sy - eyc6));
            if (distance6 < 50) {
                canvas.drawBitmap(bwBoom, ex6, ey6, null);
                ex6 = (int) (Math.random() * canvas.getWidth());
                ey6 = -200;
                shot1 = false;
                score += 3;
            }

        }
        if (shot2) {
            canvas.drawBitmap(bwShot, sx2, sy2, null);

            if (sy2 < 0) {
                shot2 = false;
            }

            double distance = Math.sqrt((sx2 - exc) * (sx2 - exc) + (sy2 - eyc) * (sy2 - eyc));
            if (distance < 50) {
                canvas.drawBitmap(bwBoom, ex, ey, null);
                ex = (int) (Math.random() * canvas.getWidth());
                ey = -100;
                shot2 = false;
                score += 3;
            }
            double distance2 = Math.sqrt((sx2 - exc2) * (sx2 - exc2) + (sy2 - eyc2) * (sy2 - eyc2));
            if (distance2 < 50) {
                canvas.drawBitmap(bwBoom, ex2, ey2, null);
                ex2 = (int) (Math.random() * canvas.getWidth());
                ey2 = -300;
                shot2 = false;
                score += 5;
            }
            double distance3 = Math.sqrt((sx2 - exc3) * (sx2 - exc3) + (sy2 - eyc3) * (sy2 - eyc3));
            if (distance3 < 50) {
                canvas.drawBitmap(bwBoom, ex3, ey3, null);
                ex3 = (int) (Math.random() * canvas.getWidth());
                ey3 = -100;
                shot2 = false;
                score += 10;
            }
            double distance4 = Math.sqrt((sx2 - exc4) * (sx2 - exc4) + (sy2 - eyc4) * (sy2 - eyc4));
            if (distance4 < 50) {
                canvas.drawBitmap(bwBoom, ex4, ey4, null);
                ex4 = (int) (Math.random() * canvas.getWidth());
                ey4 = -100;
                shot2 = false;
                score += 10;
            }
            double distance5 = Math.sqrt((sx2 - exc5) * (sx2 - exc5) + (sy2 - eyc5) * (sy2 - eyc5));
            if (distance5 < 50) {
                canvas.drawBitmap(bwBoom, ex5, ey5, null);
                ex5 = (int) (Math.random() * canvas.getWidth());
                ey5 = -100;
                shot1 = false;
                score += 5;
            }
            double distance6 = Math.sqrt((sx2 - exc6) * (sx2 - exc6) + (sy2 - eyc6) * (sy2 - eyc6));
            if (distance6 < 50) {
                canvas.drawBitmap(bwBoom, ex6, ey6, null);
                ex6 = (int) (Math.random() * canvas.getWidth());
                ey6 = -200;
                shot2 = false;
                score += 3;
            }

        }

    }






       else if(timeToGo < 5 && (e1 || e2 || e3 || e4 || e5 ||e6)) {

            if(e1) {
                canvas.drawBitmap(bwEnemy, ex, ey, null);
                ey += 5;
            }
            if(e2) {
                canvas.drawBitmap(bwEnemy2, ex2, ey2, null);
                ey2 += 7;
            }
            if(e3) {
                canvas.drawBitmap(bwEnemy3, ex3, ey3, null);
                ey3 += 3;
            }
            if(e4) {
                canvas.drawBitmap(bwEnemy4, ex4, ey4, null);
                ey4 += 3;
            }
            if(e5) {
                canvas.drawBitmap(bwEnemy5, ex5, ey5, null);
                ey5 += 7;
            }
            if(e6) {
                canvas.drawBitmap(bwEnemy6, ex6, ey6, null);
                ey6 += 5;
            }

            if (MainActivity.x + 50 > canvas.getWidth()) {
                MainActivity.x = canvas.getWidth() - 50;
            }
            if (MainActivity.x < -50) {
                MainActivity.x = -50;
            }

            if (MainActivity.y > canvas.getHeight() - 100) {
                MainActivity.y = canvas.getHeight() - 100;
            }
            if (MainActivity.y < -10) {
                MainActivity.y = -10;
            }


            canvas.drawBitmap(bwPlayer, MainActivity.x, MainActivity.y, null);
            canvas.drawText("Score: " + score, 0, 50, text);
            if (timeToGo >= 0) {
                canvas.drawText("Mothership: " + timeToGo, canvas.getWidth() / 2, 50, text);
            }
            if (ey > canvas.getHeight()) {
                ey = -200;
                ex = 0;
                score -= 3;
                e1=false;
            }
            if (ex + 100 > canvas.getWidth()) {
                ey = -200;
                ex = (int) (Math.random() * canvas.getWidth());
            }
            if (ey2 > canvas.getHeight()) {
                ey2 = -300;
                ex2 = 0;
                score -= 5;
                e2=false;
            }
            if (ex2 + 100 > canvas.getWidth()) {
                ey2 = -200;
                ex2 = (int) (Math.random() * canvas.getWidth());
            }
            if (ey3 > canvas.getHeight()) {
                ey3 = -200;
                ex3 = 0;
                score -= 10;
                e3=false;
            }
            if (ex3 + 100 > canvas.getWidth()) {
                ey3 = -200;
                ex3 = (int) (Math.random() * canvas.getWidth());
            }
            if (ey4 > canvas.getHeight()) {
                ey4 = -200;
                ex4 = 0;
                score -= 10;
                e4=false;
            }
            if (ex4 + 100 > canvas.getWidth()) {
                ey4 = -200;
                ex4 = (int) (Math.random() * canvas.getWidth());
            }
            if (ey5 > canvas.getHeight()) {
                ey5 = -200;
                ex5 = 0;
                score -= 5;
                e5=false;
            }
            if (ex5 + 100 > canvas.getWidth()) {
                ey5 = -200;
                ex5 = (int) (Math.random() * canvas.getWidth());
            }
            if (ey6 > canvas.getHeight()) {
                ey6 = -200;
                ex6 = 0;
                score -= 3;
                e6=false;
            }
            if (ex6 + 100 > canvas.getWidth()) {
                ey6 = -200;
                ex6 = (int) (Math.random() * canvas.getWidth());
            }

            double Ecollision = Math.sqrt((exc2 - exc) * (exc2 - exc) + (eyc2 - eyc) * (eyc2 - eyc));
            if (Ecollision < 75) {
                canvas.drawBitmap(bwBoom, ex, ey, null);
                canvas.drawBitmap(bwBoom, ex2, ey2, null);
                ey = -200;
                ex = 0;
                ey2 = -300;
                ex2 = 0;
                e1=false;
                e2=false;

            }
            double Ecollision2 = Math.sqrt((exc3 - exc) * (exc3 - exc) + (eyc3 - eyc) * (eyc3 - eyc));
            if (Ecollision2 < 75) {
                canvas.drawBitmap(bwBoom, ex, ey, null);
                canvas.drawBitmap(bwBoom, ex3, ey3, null);
                ey = -200;
                ex = 0;
                ey3 = -200;
                ex3 = 0;
                e1=false;
                e3=false;

            }
            double Ecollision3 = Math.sqrt((exc2 - exc3) * (exc2 - exc3) + (eyc2 - eyc3) * (eyc2 - eyc3));
            if (Ecollision3 < 75) {
                canvas.drawBitmap(bwBoom, ex3, ey3, null);
                canvas.drawBitmap(bwBoom, ex2, ey2, null);
                ey3 = -200;
                ex3 = 0;
                ey2 = -300;
                ex2 = 0;
                e3=false;
                e2=false;
            }
            double Ecollision4 = Math.sqrt((exc4 - exc) * (exc4 - exc) + (eyc4 - eyc) * (eyc4 - eyc));
            if (Ecollision4 < 75) {
                canvas.drawBitmap(bwBoom, ex, ey, null);
                canvas.drawBitmap(bwBoom, ex4, ey4, null);
                ey = -200;
                ex = 0;
                ey4 = -300;
                ex4 = 0;
                e1=false;
                e4=false;
            }
            double Ecollision5 = Math.sqrt((exc4 - exc2) * (exc4 - exc2) + (eyc4 - eyc2) * (eyc4 - eyc2));
            if (Ecollision5 < 75) {
                canvas.drawBitmap(bwBoom, ex2, ey2, null);
                canvas.drawBitmap(bwBoom, ex4, ey4, null);
                ey2 = -200;
                ex2 = 0;
                ey4 = -300;
                ex4 = 0;
                e2=false;
                e4=false;
            }
            double Ecollision6 = Math.sqrt((exc4 - exc3) * (exc4 - exc3) + (eyc4 - eyc3) * (eyc4 - eyc3));
            if (Ecollision6 < 75) {
                canvas.drawBitmap(bwBoom, ex3, ey3, null);
                canvas.drawBitmap(bwBoom, ex4, ey4, null);
                ey3 = -200;
                ex3 = 0;
                ey4 = -300;
                ex4 = 0;
                e3=false;
                e4=false;
            }
            double Ecollision7 = Math.sqrt((exc5 - exc) * (exc5 - exc) + (eyc5 - eyc) * (eyc5 - eyc));
            if (Ecollision7 < 75) {
                canvas.drawBitmap(bwBoom, ex, ey, null);
                canvas.drawBitmap(bwBoom, ex5, ey5, null);
                ey = -200;
                ex = 0;
                ey5 = -300;
                ex5 = 0;
                e1=false;
                e5=false;
            }
            double Ecollision8 = Math.sqrt((exc5 - exc2) * (exc5 - exc2) + (eyc5 - eyc2) * (eyc5 - eyc2));
            if (Ecollision8 < 75) {
                canvas.drawBitmap(bwBoom, ex2, ey2, null);
                canvas.drawBitmap(bwBoom, ex5, ey5, null);
                ey2 = -200;
                ex2 = 0;
                ey5 = -300;
                ex5 = 0;
                e2=false;
                e5=false;
            }
            double Ecollision9 = Math.sqrt((exc5 - exc3) * (exc5 - exc3) + (eyc5 - eyc3) * (eyc5 - eyc3));
            if (Ecollision9 < 75) {
                canvas.drawBitmap(bwBoom, ex3, ey3, null);
                canvas.drawBitmap(bwBoom, ex5, ey5, null);
                ey3 = -200;
                ex3 = 0;
                ey5 = -300;
                ex5 = 0;
                e3=false;
                e5=false;
            }
            double Ecollision10 = Math.sqrt((exc5 - exc4) * (exc5 - exc4) + (eyc5 - eyc4) * (eyc5 - eyc4));
            if (Ecollision10 < 75) {
                canvas.drawBitmap(bwBoom, ex4, ey4, null);
                canvas.drawBitmap(bwBoom, ex5, ey5, null);
                ey4 = -200;
                ex4 = 0;
                ey5 = -300;
                ex5 = 0;
                e4=false;
                e5=false;
            }
            double Ecollision11 = Math.sqrt((exc6 - exc) * (exc6 - exc) + (eyc6 - eyc) * (eyc6 - eyc));
            if (Ecollision11 < 75) {
                canvas.drawBitmap(bwBoom, ex6, ey6, null);
                canvas.drawBitmap(bwBoom, ex, ey, null);
                ey = -200;
                ex = 0;
                ey6 = -200;
                ex6 = 0;
                e1=false;
                e6=false;
            }
            double Ecollision12 = Math.sqrt((exc6 - exc2) * (exc6 - exc2) + (eyc6 - eyc2) * (eyc6 - eyc2));
            if (Ecollision12 < 75) {
                canvas.drawBitmap(bwBoom, ex6, ey6, null);
                canvas.drawBitmap(bwBoom, ex2, ey2, null);
                ey2 = -300;
                ex2 = 0;
                ey6 = -200;
                ex6 = 0;
                e2=false;
                e6=false;
            }
            double Ecollision13 = Math.sqrt((exc6 - exc3) * (exc6 - exc3) + (eyc6 - eyc3) * (eyc6 - eyc3));
            if (Ecollision13 < 75) {
                canvas.drawBitmap(bwBoom, ex6, ey6, null);
                canvas.drawBitmap(bwBoom, ex3, ey3, null);
                ey3 = -200;
                ex3 = 0;
                ey6 = -200;
                ex6 = 0;
                e3=false;
                e6=false;
            }
            double Ecollision14 = Math.sqrt((exc6 - exc4) * (exc6 - exc4) + (eyc6 - eyc4) * (eyc6 - eyc4));
            if (Ecollision14 < 75) {
                canvas.drawBitmap(bwBoom, ex6, ey6, null);
                canvas.drawBitmap(bwBoom, ex4, ey4, null);
                ey4 = -200;
                ex4 = 0;
                ey6 = -200;
                ex6 = 0;
                e4=false;
                e6=false;
            }
            double Ecollision15 = Math.sqrt((exc6 - exc5) * (exc6 - exc5) + (eyc6 - eyc5) * (eyc6 - eyc5));
            if (Ecollision15 < 75) {
                canvas.drawBitmap(bwBoom, ex6, ey6, null);
                canvas.drawBitmap(bwBoom, ex5, ey5, null);
                ey5 = -200;
                ex5 = 0;
                ey6 = -200;
                ex6 = 0;
                e5=false;
                e6=false;
            }

            double collision = Math.sqrt((pxc - exc) * (pxc - exc) + (pyc - eyc) * (pyc - eyc));
            if (collision < 75) {
                canvas.drawBitmap(bwBoom, ex, ey, null);
                ey = -200;
                ex = 0;
                score -= 5;
                canvas.drawBitmap(bwPlayerdmg, MainActivity.x, MainActivity.y, null);
                plyrdmg = true;
                e1=false;
            }
            double collision2 = Math.sqrt((pxc - exc2) * (pxc - exc2) + (pyc - eyc2) * (pyc - eyc2));
            if (collision2 < 75) {
                canvas.drawBitmap(bwBoom, ex2, ey2, null);
                ey2 = -300;
                ex2 = 0;
                score -= 5;
                canvas.drawBitmap(bwPlayerdmg, MainActivity.x, MainActivity.y, null);
                plyrdmg = true;
                e2=false;
            }
            double collision3 = Math.sqrt((pxc - exc3) * (pxc - exc3) + (pyc - eyc3) * (pyc - eyc3));
            if (collision3 < 75) {
                canvas.drawBitmap(bwBoom, ex3, ey3, null);
                ey3 = -200;
                ex3 = 0;
                score -= 5;
                canvas.drawBitmap(bwPlayerdmg, MainActivity.x, MainActivity.y, null);
                plyrdmg = true;
                e3=false;
            }
            double collision4 = Math.sqrt((pxc - exc4) * (pxc - exc4) + (pyc - eyc4) * (pyc - eyc4));
            if (collision4 < 75) {
                canvas.drawBitmap(bwBoom, ex4, ey4, null);
                ey4 = -200;
                ex4 = 0;
                score -= 5;
                canvas.drawBitmap(bwPlayerdmg, MainActivity.x, MainActivity.y, null);
                plyrdmg = true;
                e4=false;
            }
            double collision5 = Math.sqrt((pxc - exc5) * (pxc - exc5) + (pyc - eyc5) * (pyc - eyc5));
            if (collision5 < 75) {
                canvas.drawBitmap(bwBoom, ex5, ey5, null);
                ey5 = -300;
                ex5 = 0;
                score -= 5;
                canvas.drawBitmap(bwPlayerdmg, MainActivity.x, MainActivity.y, null);
                plyrdmg = true;
                e5=false;
            }
            double collision6 = Math.sqrt((pxc - exc6) * (pxc - exc6) + (pyc - eyc6) * (pyc - eyc6));
            if (collision6 < 75) {
                canvas.drawBitmap(bwBoom, ex6, ey6, null);
                ey6 = -200;
                ex6 = 0;
                score -= 3;
                canvas.drawBitmap(bwPlayerdmg, MainActivity.x, MainActivity.y, null);
                plyrdmg = true;
                e6=false;
            }

            if (shot1) {
                canvas.drawBitmap(bwShot, sx, sy, null);

                if (sy < 0) {
                    shot1 = false;
                }

                double distance = Math.sqrt((sx - exc) * (sx - exc) + (sy - eyc) * (sy - eyc));
                if (distance < 50) {
                    canvas.drawBitmap(bwBoom, ex, ey, null);
                    ex = (int) (Math.random() * canvas.getWidth());
                    ey = -100;
                    shot1 = false;
                    score += 3;
                    e1=false;
                }
                double distance2 = Math.sqrt((sx - exc2) * (sx - exc2) + (sy - eyc2) * (sy - eyc2));
                if (distance2 < 50) {
                    canvas.drawBitmap(bwBoom, ex2, ey2, null);
                    ex2 = (int) (Math.random() * canvas.getWidth());
                    ey2 = -300;
                    shot1 = false;
                    score += 5;
                    e2=false;
                }
                double distance3 = Math.sqrt((sx - exc3) * (sx - exc3) + (sy - eyc3) * (sy - eyc3));
                if (distance3 < 50) {
                    canvas.drawBitmap(bwBoom, ex3, ey3, null);
                    ex3 = (int) (Math.random() * canvas.getWidth());
                    ey3 = -100;
                    shot1 = false;
                    score += 10;
                    e3=false;
                }
                double distance4 = Math.sqrt((sx - exc4) * (sx - exc4) + (sy - eyc4) * (sy - eyc4));
                if (distance4 < 50) {
                    canvas.drawBitmap(bwBoom, ex4, ey4, null);
                    ex4 = (int) (Math.random() * canvas.getWidth());
                    ey4 = -100;
                    shot1 = false;
                    score += 10;
                    e4=false;
                }
                double distance5 = Math.sqrt((sx - exc5) * (sx - exc5) + (sy - eyc5) * (sy - eyc5));
                if (distance5 < 50) {
                    canvas.drawBitmap(bwBoom, ex5, ey5, null);
                    ex5 = (int) (Math.random() * canvas.getWidth());
                    ey5 = -300;
                    shot1 = false;
                    score += 5;
                    e5=false;
                }
                double distance6 = Math.sqrt((sx - exc6) * (sx - exc6) + (sy - eyc6) * (sy - eyc6));
                if (distance6 < 50) {
                    canvas.drawBitmap(bwBoom, ex6, ey6, null);
                    ex6 = (int) (Math.random() * canvas.getWidth());
                    ey6 = -200;
                    shot1 = false;
                    score += 3;
                    e6=false;
                }

            }
            if (shot2) {
                canvas.drawBitmap(bwShot, sx2, sy2, null);

                if (sy2 < 0) {
                    shot2 = false;
                }

                double distance = Math.sqrt((sx2 - exc) * (sx2 - exc) + (sy2 - eyc) * (sy2 - eyc));
                if (distance < 50) {
                    canvas.drawBitmap(bwBoom, ex, ey, null);
                    ex = (int) (Math.random() * canvas.getWidth());
                    ey = -100;
                    shot2 = false;
                    score += 3;
                    e1=false;
                }
                double distance2 = Math.sqrt((sx2 - exc2) * (sx2 - exc2) + (sy2 - eyc2) * (sy2 - eyc2));
                if (distance2 < 50) {
                    canvas.drawBitmap(bwBoom, ex2, ey2, null);
                    ex2 = (int) (Math.random() * canvas.getWidth());
                    ey2 = -300;
                    shot2 = false;
                    score += 5;
                    e2=false;
                }
                double distance3 = Math.sqrt((sx2 - exc3) * (sx2 - exc3) + (sy2 - eyc3) * (sy2 - eyc3));
                if (distance3 < 50) {
                    canvas.drawBitmap(bwBoom, ex3, ey3, null);
                    ex3 = (int) (Math.random() * canvas.getWidth());
                    ey3 = -100;
                    shot2 = false;
                    score += 10;
                    e3=false;
                }
                double distance4 = Math.sqrt((sx2 - exc4) * (sx2 - exc4) + (sy2 - eyc4) * (sy2 - eyc4));
                if (distance4 < 50) {
                    canvas.drawBitmap(bwBoom, ex4, ey4, null);
                    ex4 = (int) (Math.random() * canvas.getWidth());
                    ey4 = -100;
                    shot2 = false;
                    score += 10;
                    e4=false;
                }
                double distance5 = Math.sqrt((sx2 - exc5) * (sx2 - exc5) + (sy2 - eyc5) * (sy2 - eyc5));
                if (distance5 < 50) {
                    canvas.drawBitmap(bwBoom, ex5, ey5, null);
                    ex5 = (int) (Math.random() * canvas.getWidth());
                    ey5 = -100;
                    shot1 = false;
                    score += 5;
                    e5=false;
                }
                double distance6 = Math.sqrt((sx2 - exc6) * (sx2 - exc6) + (sy2 - eyc6) * (sy2 - eyc6));
                if (distance6 < 50) {
                    canvas.drawBitmap(bwBoom, ex6, ey6, null);
                    ex6 = (int) (Math.random() * canvas.getWidth());
                    ey6 = -200;
                    shot2 = false;
                    score += 3;
                    e6=false;
                }

            }

        }
        else if(timeToGo < 5 && (!e1 && !e2 && !e3 && !e4 && !e5 && !e6)) {
        if (msy < 75) {
            msy += 5;
        }
        if (right) {
            msx += 5;
        }
        if (!right) {
            msx -= 5;
        }
        if (msx > canvas.getWidth() - 200) {
            right = false;
        }
        if (msx < 0) {
            right = true;
        }

        if (MainActivity.x + 50 > canvas.getWidth()) {
            MainActivity.x = canvas.getWidth() - 50;
        }
        if (MainActivity.x < -50) {
            MainActivity.x = -50;
        }

        if (MainActivity.y > canvas.getHeight() - 100) {
            MainActivity.y = canvas.getHeight() - 100;
        }
        if (MainActivity.y < -10) {
            MainActivity.y = -10;
        }
        if (MainActivity.y < msyc + 50 ) {
            MainActivity.y = msyc + 60;
            plyrdmg = true;
            score -= 20;
        }


        canvas.drawBitmap(bwPlayer, MainActivity.x, MainActivity.y, null);

        if(msh >= 20) {
            canvas.drawBitmap(bwMothership, msx, msy, null);
        }
        else if(msh >= 15 && msh < 20) {
            canvas.drawBitmap(bwMothership2, msx, msy, null);
        }
        else if(msh >= 10 && msh < 15) {
            canvas.drawBitmap(bwMothership3, msx, msy, null);
        }
        else if(msh >= 5 && msh < 10) {
            canvas.drawBitmap(bwMothership4, msx, msy, null);
        }
        else if(msh > 0 && msh < 5) {
            canvas.drawBitmap(bwMothership5, msx, msy, null);
        }
        if(msh > 0) {
            if (!eshot1 && esy2 > msyc + 300 && esy3 > msyc + 300 && esy4 > msyc + 300) {
                eshot1 = true;
                esx1 = msxc - 10;
                esy1 = msyc + 50;
            }
            if (eshot1 && !eshot2 && esy1 > msyc + 300 && esy3 > msyc + 300 && esy4 > msyc + 300) {
                eshot2 = true;
                esx2 = msxc - 10;
                esy2 = msyc + 50;
            }
            if (eshot1 && eshot2 && !eshot3 && esy1 > msyc + 300 && esy2 > msyc + 300 && esy4 > msyc + 300) {
                eshot3 = true;
                esx3 = msxc - 10;
                esy3 = msyc + 50;
            }
            if (eshot1 && eshot2 && eshot3 && !eshot4 && esy1 > msyc + 300 && esy2 > msyc + 300 && esy3 > msyc + 300) {
                eshot4 = true;
                esx4 = msxc - 10;
                esy4 = msyc + 50;
            }

        }

        if(eshot1){
            canvas.drawBitmap(bwEshot, esx1, esy1, null);
            if (esy1 > canvas.getHeight()) {
                eshot1 = false;
            }
            double distance = Math.sqrt((esx1 - pxc) * (esx1 - pxc) + (esy1 - pyc) * (esy1 - pyc));
            if (distance < 50) {
                eshot1 = false;
                score -= 20;
                plyrdmg = true;
            }
        }
        if(eshot2){
            canvas.drawBitmap(bwEshot, esx2, esy2, null);
            if (esy2 > canvas.getHeight()) {
                eshot2 = false;
            }
            double distance = Math.sqrt((esx2 - pxc) * (esx2 - pxc) + (esy2 - pyc) * (esy2 - pyc));
            if (distance < 50) {
                eshot2 = false;
                score -= 20;
                plyrdmg = true;
            }
        }

        if(eshot3){
            canvas.drawBitmap(bwEshot, esx3, esy3, null);
            if (esy3 > canvas.getHeight()) {
                eshot3 = false;
            }
            double distance = Math.sqrt((esx3 - pxc) * (esx3 - pxc) + (esy3 - pyc) * (esy3 - pyc));
            if (distance < 50) {
                eshot3 = false;
                score -= 20;
                plyrdmg = true;
            }
        }
        if(eshot4){
            canvas.drawBitmap(bwEshot, esx4, esy4, null);
            if (esy4 > canvas.getHeight()) {
                eshot4 = false;
            }
            double distance = Math.sqrt((esx4 - pxc) * (esx4 - pxc) + (esy4 - pyc) * (esy4 - pyc));
            if (distance < 50) {
                eshot4 = false;
                score -= 20;
                plyrdmg = true;
            }
        }



        canvas.drawText("Score: " + score, 0, 50, text);
        if (timeToGo >= 0) {
            canvas.drawText("Mothership: " + timeToGo, canvas.getWidth() / 2, 50, text);
        }

        if (shot1) {
            canvas.drawBitmap(bwShot, sx, sy, null);

            if (sy < 0) {
                shot1 = false;
            }
            double distance = Math.sqrt((sx - msxc) * (sx - msxc) + (sy - msyc) * (sy - msyc));
            if (distance < 75) {
                shot1 = false;
                msh--;
                if(msh == 0){
                    canvas.drawBitmap(bwBoom, msx + 50, msy, null);
                    score += 100;
                }
            }

        }

        if (shot2) {
            canvas.drawBitmap(bwShot, sx2, sy2, null);

            if (sy2 < 0) {
                shot2 = false;
            }
            double distance = Math.sqrt((sx2 - msxc) * (sx2 - msxc) + (sy2 - msyc) * (sy2 - msyc));
            if (distance < 75) {
                shot2 = false;
                msh--;
                if(msh == 0){
                    canvas.drawBitmap(bwBoom, msx + 50, msy, null);
                    score += 100;
                }
            }

        }

        esy1 +=20;
        esy2 +=20;
        esy3 +=20;
        esy4 +=20;
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
        if(!shot1&& sy2 < pyc - 500 && sy < pyc - 500) {
            sy = MainActivity.y - 50;
            sx = MainActivity.x + 40;
            shot1 = true;
            score--;
            return true;
        }
        else if(shot1 && !shot2 && sy < pyc - 500 && sy2 < pyc - 500) {
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
