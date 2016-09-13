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
        this.setVx(this.getVx() + this.getAx()*dt);
        this.setVy(this.getVy() + this.getAy()*dt);

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

    public  void direcaoMesa(int i){
        if(i==1)
            setVy(getVy()*(-1));
        if(i==2)
            setVx(getVx()*(-1));
    }




    public Integer checkTableCollide(Mesa mesa){
        if(mesa.getY() > getY()-raio){
            this.setVy(-this.getVy());
            this.setY(mesa.getY()+raio);
            return 1;//colissao com o top;
        }
        if(mesa.getWidth() < getY()+raio){
            this.setVy(-this.getVy());
            this.setY(mesa.getWidth()-raio);
            return 1;//colissao com o bot;
        }
        if(mesa.getX() > getX()-raio){
            this.setVx(-this.getVx());
            this.setX(mesa.getX()+raio);
            return 2;//colissao com right;
        }
        if(mesa.getHeight() < getX()+raio){
            this.setVx(-this.getVx());
            this.setX(mesa.getHeight()-raio);
            return 2;//colissao com o left;
        }
        else
            return 0;
    }

    public void destroiBola(){
        setX(20.0f);
        setY(20.0f);
        setAx(0.0f);
        setAy(0.0f);
        setVx(0.0f);
        setVy(0.0f);

    }

    public boolean colideBalls(Bola b){//verifica colisao entre duas bolas
        return Math.sqrt(Math.pow(b.getX() - getX(), 2) + Math.pow(b.getY() - getY(), 2)) <= (b.getRaio() + raio);
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