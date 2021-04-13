package jforsythe.aabbcollision;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Box {
    float x, y, w, h;
    int color;
    Paint paint = new Paint();
    Paint textPaint = new Paint();
    String name;

    public Box(float x, float y, float w, float h, int color, String name) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.name = name;
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(color);
        textPaint.setTextSize(14);
    }

    public void draw(Canvas canvas){
        canvas.drawText("x,y", x+5, y+15, textPaint);
        canvas.drawCircle(x,y,2,paint);

        canvas.drawText("x+w", x + w - 30, y + 15, textPaint);
        canvas.drawCircle(x+w,y,2,paint);

        canvas.drawText("y+h", x + 5, y+h - 10, textPaint);
        canvas.drawCircle(x, y+h, 2, paint);

        canvas.drawText(name, x + w/2 - 8, y + h /2 + 5, textPaint);

        canvas.drawRect(x,y,x+ w, y+ h, paint);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
