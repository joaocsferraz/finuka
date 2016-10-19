package br.ufjf.dcc.finuka;

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
        return Math.sqrt(Math.pow(b.getX() - getX(), 2) + Math.pow(b.getY() - getY(), 2)) <= raio;

    }

    public float getRaio() {
        return raio;
    }

    public void setRaio(float raio) {
        this.raio = raio;
    }
}
