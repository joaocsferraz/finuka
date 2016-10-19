package br.ufjf.dcc.finuka;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by LucasRezende on 27/07/2016.
 */
public class Taco extends Objeto {

    private float base;
    private float force;
    private float precisao;
    private int   id;
    private boolean click;

    public Taco() {
    }

    public Taco(int x, int y, int height, int width) {
        super(x, y, height, width);
    }

    public void  draw(Canvas canvas, Paint paint,Bola branca){
        paint.setColor(Color.rgb(226,118,45));
        canvas.drawRect(getX(),getY(),getX() + getWidth(),getY() + getHeight(),paint);
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

}
