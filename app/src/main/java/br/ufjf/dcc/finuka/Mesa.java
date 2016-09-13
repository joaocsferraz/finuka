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
    private Bordas b_top;
    private Bordas b_bot;
    private Bordas b_top2;
    private Bordas b_bot2;
    private Bordas b_left;
    private Bordas b_right;
    private RectF rect;   //borda de madeira

    public Mesa() {
    }

    public Mesa(int x, int y, int height, int width){
        super(x, y, height, width);
        criaBordas();
        criaCacapa();
    }

    private void criaBordas() {
        b_bot = new Bordas(160,615,((getHeight()-getX())/2)+getX()-25,635);
        b_bot2 = new Bordas(((getHeight()-getX())/2)+getX()+25,615,1040,635);
        b_top = new Bordas(160,95,((getHeight()-getX())/2)+getX()-25,115);
        b_top2 = new Bordas(((getHeight()-getX())/2)+getX()+25,95,1040,115);
        b_right = new Bordas(115,140,135,590);
        b_left = new Bordas(1065,140,1085,590);
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

    public boolean detectaponto(Bola b){
        for(int i = 0; i < cacapas.length; i++){
            if(cacapas[i].collidecacapa(b))
                return true;
        }
        return false;
    }

    public void  drawMesa(Canvas canvas, Paint paint){
        //madeira
        paint.setColor(Color.rgb(66,46,40));
        rect = new RectF(getX()-40,getY()-50,getHeight()+40,getWidth()+50);
        canvas.drawRoundRect(rect,25,25,paint );
        //mesa
        paint.setColor(Color.GREEN);
        canvas.drawRect(getX(),getY(),getHeight(),getWidth(),paint);
        b_bot.drawEdge(canvas,paint);
        b_bot2.drawEdge(canvas,paint);
        b_left.drawEdge(canvas,paint);
        b_right.drawEdge(canvas,paint);
        b_top.drawEdge(canvas,paint);
        b_top2.drawEdge(canvas,paint);
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

    public Bordas getB_top() {
        return b_top;
    }

    public void setB_top(Bordas b_top) {
        this.b_top = b_top;
    }

    public Bordas getB_bot() {
        return b_bot;
    }

    public void setB_bot(Bordas b_bot) {
        this.b_bot = b_bot;
    }

    public Bordas getB_left() {
        return b_left;
    }

    public void setB_left(Bordas b_left) {
        this.b_left = b_left;
    }

    public Bordas getB_right() {
        return b_right;
    }

    public void setB_right(Bordas b_right) {
        this.b_right = b_right;
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

    public Bordas getB_top2() {
        return b_top2;
    }

    public void setB_top2(Bordas b_top2) {
        this.b_top2 = b_top2;
    }

    public Bordas getB_bot2() {
        return b_bot2;
    }

    public void setB_bot2(Bordas b_bot2) {
        this.b_bot2 = b_bot2;
    }
}
