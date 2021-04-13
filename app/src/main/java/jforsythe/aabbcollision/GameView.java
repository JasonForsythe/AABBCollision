package jforsythe.aabbcollision;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private boolean isPlaying = true;
    private Thread thread;
    private boolean collision = false;
    private Paint textPaint = new Paint();
    private Boolean[] checks = new Boolean[4];

    private Box box1, box2;

    public GameView(Context context) {
        super(context);
        Resources res = getResources();
        int screenWidth = res.getDisplayMetrics().widthPixels;
        int screenHeight = res.getDisplayMetrics().heightPixels;

        box1 = new Box(50,screenHeight - 200, 100,100, Color.RED, "b1");
        box2 = new Box(screenWidth / 2 - 50, screenHeight / 2 - 50, 100, 100, Color.BLUE, "b2");

        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(12);
    }

    @Override
    public void run() {
        while(isPlaying){
            update();
            draw();
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();
        box1.setX(x - box1.getW() / 2);
        box1.setY(y - box1.getH() / 2);

        return true;
    }

    private void draw() {
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawColor(Color.WHITE);

            canvas.drawText("Collision: " + collision, 10,15,textPaint);
            canvas.drawText("b1 x < b2 x+w: " + checks[0], 10, 30, textPaint);
            canvas.drawText("b1 x+w > b2 x: " + checks[1], 10, 45, textPaint);
            canvas.drawText("b1 y < b2 y+h:" + checks[2], 10, 60, textPaint);
            canvas.drawText("b1 y+h > b2 y: " + checks[3], 10, 75, textPaint);


            box1.draw(canvas);
            box2.draw(canvas);

            getHolder().unlockCanvasAndPost(canvas);
        }

    }

    private boolean checkCollision(Box b1, Box b2){
        // Set flags for each part of the collision detection
        checks[0] = b1.getX() < b2.getX() + b2.getW();
        checks[1] = b1.getX() + b1.getW() > b2.getX();
        checks[2] = b1.getY() < b2.getY() + b2.getH();
        checks[3] = b1.getY() + b1.getH() > b2.getY();

        // Actual collision detection
        return b1.getX() < b2.getX() + b2.getW() &&
                b1.getX() + b1.getW() > b2.getX() &&
                b1.getY() < b2.getY() + b2.getH() &&
                b1.getY() + b1.getH() > b2.getY();
    }

    private void update() {
        collision = checkCollision(box2, box1);

    }

    public void pause(){
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }


}
