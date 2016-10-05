package br.ufjf.dcc.finuka;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bola extends Objeto {

    private Integer id;
    private float raio;
    private float constanteElastica;
    private Float peso;
    private Integer numero;
    private Integer cor;
    private float centro;

    public Bola(float x, float y, Integer id, float raio) {
        super(x, y, 0.0f, 0.0f);
        this.id = id;
        this.raio = raio;
        this.setAx(0.0f);
        this.setAy(0.0f);
    }

    public Bola() {
        this(0.0f, 0.0f, 0, 1.0f);
    }

    public  void movimenta(float dt){
            /*this.setVx(this.getVx() + this.getAx()*dt-this.getVx()*dt*0.2f);
        this.setVy(this.getVy() + this.getAy()*dt-this.getVy()*dt*0.2f);
        */
        float atrito = 0.98f;
        if(this.getVx() >0.0f && this.getVy()>0.0f)
        {this.setVx(this.getVx() + this.getAx()*dt-atrito*dt);
            this.setVy(this.getVy() + this.getAy()*dt-atrito*dt);}
        if(this.getVx() >0.0f && this.getVy()<=0.0f)
        {this.setVx(this.getVx() + this.getAx()*dt-atrito*dt);
            this.setVy(0);}
        if(this.getVx() <=0.0f && this.getVy()>0.0f)
        {this.setVx(0.0f);
            this.setVy(this.getVy() + this.getAy()*dt-atrito*dt);}
        if(this.getVx() <=0.0f && this.getVy()<=0.0f)
        {this.setVx(0.0f);
            this.setVy(0.0f);}


        this.setX(this.getX()+this.getVx()*dt);
        this.setY(this.getY()+this.getVy()*dt);
    }


    public void  draw(Canvas canvas, Paint paint){
        if(id == 1){
            paint.setColor(Color.GRAY);
            canvas.drawCircle(getX()-raio,getY()-raio,2*raio,paint);
        }else {
            paint.setColor(Color.RED);
            canvas.drawCircle(getX()-raio,getY()-raio,2*raio,paint);
        }
    }

    public void checkTableCollide(Mesa mesa){
        if(mesa.getY() > getY()-raio){
            this.setVy(-this.getVy());
            this.setY(mesa.getY()+raio);
            //colissao com o top;
        }
        if(mesa.getWidth() < getY()+raio){
            this.setVy(-this.getVy());
            this.setY(mesa.getWidth()-raio);
            //colissao com o bot;
        }
        if(mesa.getX() > getX()-raio){
            this.setVx(-this.getVx());
            this.setX(mesa.getX()+raio);
            //colissao com right;
        }
        if(mesa.getHeight() < getX()+raio){
            this.setVx(-this.getVx());
            this.setX(mesa.getHeight()-raio);
            //colissao com o left;
        }
    }


    public boolean colideBalls(Bola b){//verifica colisao entre duas bolas
        return Math.sqrt(Math.pow(b.getX() - this.getX(), 2) + Math.pow(b.getY() - this.getY(), 2)) <= (b.getRaio() + this.raio);
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

    @Override
    public String toString() {
        return "Bola{" +
                " raio=" + raio +
                " ax=" + getAx()+
                " ay=" + getAy()+
                " ax=" + getVx()+
                " vy=" + getVy()+
                " x=" + getX()+
                " y=" + getY()+
                '}';
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