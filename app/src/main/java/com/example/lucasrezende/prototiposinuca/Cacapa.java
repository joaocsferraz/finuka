package com.example.lucasrezende.prototiposinuca;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by LucasRezende on 10/08/2016.
 */
public class Cacapa extends Objeto {

    private  float raio;

    public Cacapa() {
    }

    public Cacapa(float x, float y, float raio) {
        super(x, y,0,0);
        this.raio = raio;
    }

    public void  draw(Canvas canvas, Paint paint){
            paint.setColor(Color.BLACK);

            canvas.drawCircle(getX(),getY(),raio,paint);
    }

    public boolean collidecacapa(Bola b){
        if(Math.sqrt(Math.pow(b.getX()- getX(),2)+Math.pow(b.getY()- getY(),2)) <= raio)
            return true;
        else
            return false;

    }

    public float getRaio() {
        return raio;
    }

    public void setRaio(float raio) {
        this.raio = raio;
    }
}
