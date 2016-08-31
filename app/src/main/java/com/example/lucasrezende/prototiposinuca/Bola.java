package com.example.lucasrezende.prototiposinuca;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by LucasRezende on 27/07/2016.
 */
public class Bola extends Objeto {

    private int id;
    private float raio;
    private float constElastica;
    private Float peso;
    private int numero;
    private int cor;
    private float centro;

    public Bola(float x, float y, int id, float raio) {
        super(x,y,0,0);
        this.id = id;
        this.raio = raio;
    }

    public Bola() {
    }

    public  void moviment(double dt){
        setVx(getVx() + (getX()*getAx()));
        float xAntigo = getX();
        setX(getX() + (float) (getVx()*dt));
        setDeltax(getX()- xAntigo);

        setVy(getVy()+ getY()*getAy());
        float yAntigo = getY();
        setY( getY() + (float) (getVy()*dt));
        setDeltay(getY() - yAntigo);
    }


    public void  draw(Canvas canvas, Paint paint){
        if(id == 1){
            paint.setColor(Color.WHITE);
            canvas.drawCircle(getX(),getY(),raio,paint);
        }else {
            paint.setColor(Color.RED);
            canvas.drawCircle(getX(),getY(),raio,paint);
        }
    }

    public  void direcaoMesa(int i){
        if(i==1)
            setVy(getVy()*(-1));
        if(i==2)
            setVx(getVx()*(-1));
    }




    public int colideTable(Mesa mesa){
        if(mesa.getB_top().getWidth() >= getY()- raio)
            return 1;//colissao com o top;
        if(mesa.getB_bot().getY() <= getY()+raio)
            return 1;//colissao com o bot;
        if(mesa.getB_right().getHeight() >= getX()-raio)
            return 2;//colissao com right;
        if(mesa.getB_left().getX() <= getX()+raio)
            return 2;//colissao com o left;
        else
            return 0;
    }

    public void destroibola(){
        setX(20);
        setY(20);
        setAx(0);
        setAy(0);
        setVx(0);
        setVy(0);

    }

    public boolean colideBalls(Bola b){//verifica colisao entre duas bolas
        if(Math.sqrt(Math.pow(b.getX()-getX(),2)+Math.pow(b.getY()-getY(),2)) <= (b.getRaio()+raio))
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


    public void impacto(Bola b) {
        if(b.getVy()== 0 && b.getVx() ==0){
            b.setVx(getVx());
            b.setVy(getVy());
            setVy((-getVy()/10));
            setVx((-getVx()/10));
        }
    }
}
/*
* public void redireciona() {//teste
        setAy(getAy()*(-1));
        setAx(getAx()*(+1));
    }
    public void reacao(){//teste
        setVx(10);
        setAx(0.5f);
        setVy(10);
        setAy(0.5f);
    }

    public void redireciona_vert() {//teste
         setAy(getAy()*(-1));
    }
    public void redireciona_hori() {//teste
        setAx(getAx()*(-1));
    }
*
* */