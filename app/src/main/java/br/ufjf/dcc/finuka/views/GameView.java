package br.ufjf.dcc.finuka.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import br.ufjf.dcc.finuka.Bola;
import br.ufjf.dcc.finuka.Mesa;
import br.ufjf.dcc.finuka.Taco;

public class GameView extends View implements Runnable {

    private static final int interval = 5;
    private boolean running = true;
    private Paint paint;
    private long timeInicio = Long.MAX_VALUE;
    private long timeAnterior = Long.MAX_VALUE;
    private float dt;
    private float fps = 0;

    private Taco taco;
    private Bola branca;
    private Bola[] bolas;
    private Mesa mesa;

    public GameView(Context context) {
        super(context);
        paint = new Paint();
        Thread gameloop = new Thread(this);
        gameloop.setPriority(Thread.MIN_PRIORITY);
        //if(mesa==null) {
            startGame();
        //}

        gameloop.start();
    }

    @Override
    public void run() {
        while (running){
            try {
                Thread.sleep(interval);
            }catch (InterruptedException e) {
                Log.e("jogo","gameloop finalizado");
            }
            timeInicio = SystemClock.uptimeMillis();
            dt = (timeInicio - timeAnterior)/ 1000.0f;
           update(dt);
            timeAnterior = timeInicio;
        }
    }

    private void update(float dt) {
        fps = 1/dt ;   //hud de fps//
        postInvalidate();    //dispara metodo draw
    }



    public void  draw(Canvas canvas){
        super.draw(canvas);
        paint.setColor(Color.RED);
        paint.setTextSize(40);

        for (int i=0; i<bolas.length; i++){
            bolas[i].movimenta(dt);
            bolas[i].checkTableCollide(mesa);
            //mesa.detectaponto(bolas[i]);
            for(int j = i;j<bolas.length;j++)
            {
               if( bolas[i].colideBalls(bolas[j]))
               {

               }
            }
        }
        branca.movimenta(dt);
        branca.checkTableCollide(mesa);
        //desenha mesa
        mesa.drawMesa(canvas,paint);

        //desenha taco
        taco.draw(canvas,paint,branca);

        //desenha a bola branca
        branca.draw(canvas,paint);


/*
        if(bolaDeTeste.colideBalls(branca)) {
              bolaDeTeste.impacto(branca);
        }

        if(mesa.detectaponto(bolaDeTeste)){
            bolaDeTeste.destroiBola();
        }
*/



        //desenha bolas
        for(int i = 1; i < bolas.length; i++){
            //if (bolas[i].checkTableCollide(b_top, b_bot, b_left, b_right)) {
            //    bolas[i].redireciona();
            //}
            bolas[i].draw(canvas,paint);
        }

        //desenha fps
        canvas.drawText((int)fps + "fps dt:"+dt,200,200,paint);
    }

    public void  startGame(){
        //cria um todos os objetos
        mesa = new Mesa(130,110,1080,615);//900,600
        branca = new Bola(350.0f,400.0f,1,10);
        branca.setVx(225.0f);
        branca.setVy(225.0f);
        bolas = new Bola[8];
        for(int i=0;i<8;i++){
            bolas[i] =  new Bola(400.0f+42*i,450.0f,0,10);
            bolas[i].setVx(100*(i+1));
            bolas[i].setVy(100*(8-i));
        }
       // for(int i = 0; i < bolas.length; i++){
         //   float x = (float) (Math.random()*1070 ) + 130;
         //   float y = (float) (Math.random()*620 ) + 110;
         //   bolas[i] = new Bola(x,y,0,15);
         //   bolas[i].setVy((float)Math.random()*210);
         //   bolas[i].setAy(0.5f);

      //  }
        taco = new Taco(350,450,500,15);
    }

    ///////////////////////////////Eventos////////////////////////////////



    public  boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();


        if(action == MotionEvent.ACTION_DOWN ){
            if(taco.isPointCollided(x,y)){
                taco.setClick(true);
                return true;
            }
        }
        if(action == MotionEvent.ACTION_UP ){
           taco.setClick(false);
        }
        if(action == MotionEvent.ACTION_MOVE){
           if(taco.isClick()){
               taco.setX(x-25);
               taco.setY(y-25);
           }
        }
        return  super.onTouchEvent(event);
    }

    public void release(){
        running = false;
    }

}
