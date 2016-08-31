package com.example.lucasrezende.prototiposinuca;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by LucasRezende on 09/08/2016.
 */
public class Bordas extends Objeto {

    private int id;
    private RectF rect;


    public Bordas() {
    }

    public Bordas(float x, float y, float height, float width) {
        super(x, y, height, width);
    }

    public void drawEdge(Canvas canvas, Paint paint){
        paint.setColor(Color.rgb(0,100,0));
        rect = new RectF(getX(),getY(),getHeight(),getWidth());
        canvas.drawRoundRect(rect,25,25,paint );
    }

}


