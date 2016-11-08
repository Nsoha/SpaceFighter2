package com.example.nick.spacefighter;


import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static com.example.nick.spacefighter.R.id.canvas;


public class CustomView extends SurfaceView implements SurfaceHolder.Callback {

    protected Context context;
    private Bitmap enemy;
    private Bitmap bwEnemy;
    private Bitmap player;
    private Bitmap bwPlayer;
    private Bitmap shot;
    private Bitmap bwShot;
    DrawingThread thread;
    Paint text;
    int ex,ey,px,py,sx,sy;
    int score;
    boolean shot1 = false;

    public CustomView(Context ctx, AttributeSet attrs) {
        super(ctx,attrs);
        context = ctx;

        enemy = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy);
        bwEnemy=enemy.copy(Bitmap.Config.ARGB_8888, true);
        bwEnemy = resizeBitmap(bwEnemy,200,200);

        player = BitmapFactory.decodeResource(context.getResources(),R.drawable.player);
        bwPlayer=player.copy(Bitmap.Config.ARGB_8888, true);
        bwPlayer = resizeBitmap(bwPlayer,200,200);

        shot = BitmapFactory.decodeResource(context.getResources(),R.drawable.shot);
        bwShot=shot.copy(Bitmap.Config.ARGB_8888, true);
        bwShot = resizeBitmap(bwShot,50,100);

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
        canvas.drawBitmap(bwEnemy,ex,ey,null);
        canvas.drawBitmap(bwPlayer,px,py,null);
        canvas.drawText("Score: " + score,canvas.getWidth() / 2 - 125,75,text);
        ey+=10;
        if(ey > canvas.getHeight()){
            ey =-200;
            ex =(int) (Math.random() * canvas.getWidth());
        }

        if(shot1 != false) {
            canvas.drawBitmap(bwShot, sx, sy, null);
            sy-=30;
            if(sy < 0){
                shot1 = false;
            }

            double distance = Math.sqrt((sx - ex) * (sx - ex) + (sy - ey) * (sy - ey));
            if (distance < 50) {
                ex = (int) (Math.random() * canvas.getWidth());
                ey = - 200;
                shot1 = false;
                score++;
            }

        }


    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v("touch event", event.getX() + "," + event.getY());

        double distance = Math.sqrt((px - event.getX()) * (px - event.getX()) + (py - event.getY()) * (py - event.getY()));
        if (distance < 50) {
           if(shot1 = false) {
                sy = py - 100;
                sx = px + 80;
                shot1 = true;
            }

     }
        else if (event.getX() < px + 51){
            px -= 20;
        }
        else if (event.getX() > px + 149) {
            px += 20;
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
