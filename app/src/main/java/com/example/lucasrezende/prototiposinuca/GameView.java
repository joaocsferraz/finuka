package com.example.lucasrezende.prototiposinuca;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements Runnable {

    private static final int interval = 10;
    private boolean running = true;
    private Paint paint;
    private long timeInicio = Long.MAX_VALUE;
    private long timeAnterior = Long.MAX_VALUE;
    private double dt;
    private double fps = 0;

    private Taco player;
    private Bola branca;
    private Bola[] bolas;
    private Mesa mesa;

    private Bola teste;

    public GameView(Context context) {
        super(context);
        paint = new Paint();
        Thread gameloop = new Thread(this);
        gameloop.setPriority(Thread.MIN_PRIORITY);
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
            dt = (timeInicio - timeAnterior)/ 1000.0;
           update(dt);
            timeAnterior = timeInicio;
        }
    }

    private void update(double dt) {
        fps = 1/dt;   //hud de fps//
        postInvalidate();    //dispara metodo draw
    }



    public void  draw(Canvas canvas){
        if(mesa==null)
            startGame();
        super.draw(canvas);
        paint.setColor(Color.RED);
        paint.setTextSize(40);

        //desenha mesa
        mesa.drawMesa(canvas,paint);

        //desenha taco
        player.draw(canvas,paint,branca);

        //desenha a bola branca
        branca.draw(canvas,paint);

        //desenha bola teste
        teste.draw(canvas,paint);//teste
        teste.direcaoMesa(teste.colideTable(mesa));


        if(teste.colideBalls(branca)) {
              teste.impacto(branca);
        }

        if(mesa.detectaponto(teste)){
            teste.destroibola();
        }


        teste.moviment(dt);
        branca.moviment(dt);


        //desenha bolas
        /*
          for(int i = 1; i < bolas.length; i++){
            if (bolas[i].colideTable(b_top, b_bot, b_left, b_right)) {
                bolas[i].redireciona();
            }
            bolas[i].moviment(dt);
            bolas[i].draw(canvas,paint);
        }
        */
        //desenha fps
        canvas.drawText((int)fps + "fps",200,200,paint);
    }

    public void  startGame(){
        //cria um todos os objetos
        mesa = new Mesa(130,110,900,600);
        branca = new Bola(350.0f,400.0f,1,19);
        bolas = new Bola[8];
        teste = new Bola(400.0f,450.0f,0,19);
        teste.setVy(500);
        teste.setVx(600);
        teste.setAy(0);
        teste.setAx(0);
       // for(int i = 0; i < bolas.length; i++){
         //   float x = (float) (Math.random()*1070 ) + 130;
         //   float y = (float) (Math.random()*620 ) + 110;
         //   bolas[i] = new Bola(x,y,0,15);
         //   bolas[i].setVy((float)Math.random()*210);
         //   bolas[i].setAy(0.5f);

      //  }
        player = new Taco(350,450,500,15);
    }

    ///////////////////////////////Eventos////////////////////////////////



    public  boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();


        if(action == MotionEvent.ACTION_DOWN ){
            if(player.ColidePoints(x,y)){
                player.setClick(true);
                return true;
            }
        }
        if(action == MotionEvent.ACTION_UP ){
           player.setClick(false);
        }
        if(action == MotionEvent.ACTION_MOVE){
           if(player.isClick()){
               player.setX(x-25);
               player.setY(y-25);
           }
        }
        return  super.onTouchEvent(event);
    }

    public void release(){
        running = false;
    }

}
