package br.ufjf.dcc.finuka;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by LucasRezende on 27/07/2016.
 */
public class Mesa extends Objeto {

    private int id;
    private float atrito;
    private Cacapa cacapas[];
    private RectF rect;   //borda de madeira

    public Mesa() {
    }

    public Mesa(int x, int y, int height, int width) {
        super(x, y, height, width);
        criaCacapa();
    }




    private void criaCacapa() {
        cacapas = new Cacapa[6];
        cacapas[0] = new Cacapa(getX(),getY(),32);
        cacapas[1] = new Cacapa(getHeight(),getY(),32);
        cacapas[2] = new Cacapa(getX(),getWidth(),32);
        cacapas[3] = new Cacapa(getHeight(),getWidth(),32);
        cacapas[4] = new Cacapa(((getHeight()-getX())/2)+getX(),getY()-15,32);
        cacapas[5] = new Cacapa(((getHeight()-getX())/2)+getX(),getWidth()+15,32);
    }

    public void detectaponto(Bola b){
        for(int i = 0; i < cacapas.length; i++) {
            if (cacapas[i].collidecacapa(b)) {
                b.setX(20.0f);
                b.setY(20.0f);
                b.setAx(0.0f);
                b.setAy(0.0f);
                b.setVx(0.0f);
                b.setVy(0.0f);
            }
        }
    }

    public void  drawMesa(Canvas canvas, Paint paint){
        //madeira
        //paint.setColor(Color.rgb(66,46,40));
        //rect = new RectF(getX()-40,getY()-50,getHeight()+40,getWidth()+50);
        //canvas.drawRoundRect(rect,25,25,paint );
        //mesa
        paint.setColor(Color.GREEN);
        canvas.drawRect(this.getX(),this.getY(),this.getHeight(),this.getWidth(),paint);

        for(int i = 0; i < cacapas.length; i++){
           cacapas[i].draw(canvas,paint);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAtrito() {
        return atrito;
    }

    public void setAtrito(float atrito) {
        this.atrito = atrito;
    }

    public RectF getRect() {
        return rect;
    }

    public void setRect(RectF rect) {
        this.rect = rect;
    }

    public Cacapa[] getCacapas() {
        return cacapas;
    }

    public void setCacapas(Cacapa[] cacapas) {
        this.cacapas = cacapas;
    }


}
