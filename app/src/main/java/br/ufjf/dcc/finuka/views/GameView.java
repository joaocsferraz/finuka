package br.ufjf.dcc.finuka.views;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
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
    private DisplayMetrics dm = new DisplayMetrics();
    private Taco taco;
    private Bola branca;
    private Bola[] bolas;
    private Mesa mesa;

    public GameView(Context context) {
        super(context);
        paint = new Paint();
        dm = Resources.getSystem().getDisplayMetrics();
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
public float getEscala(){
	//Pega a polegada do aparelho e seleciona uma escala do canvas para desenhar.
int largura = dm.widthPixels;
int altura = dm.heightPixels;
float larg = (float) largura/(float)dm.xdpi;
float alt = (float) altura/(float)dm.ydpi;
float x = (float) Math.pow(larg,2);
float y = (float) Math.pow(alt,2);
float polegadas = (float) Math.sqrt(x+y);
	if (polegadas<=3.0f)
         return 0.3f;
     else if(polegadas>3.0f && polegadas<=3.3f)
         return 0.28f;
     else if(polegadas>3.3f && polegadas<=3.6f)
         return 0.38f;
     else if(polegadas>3.6f && polegadas<=3.9f)
         return 0.42f;
     else if(polegadas>3.9f && polegadas<=4.2f)
         return 0.47f;
	 else if(polegadas>4.2f && polegadas<=4.5f)
         return 0.62f;
     else if(polegadas>4.5f && polegadas<=4.8f)
         return 0.65f;
	 else if(polegadas>4.8f && polegadas<=5.1f)
         return 0.68f;
	 else if(polegadas>5.1f && polegadas<=5.4f)
         return 0.71f;
	 else if(polegadas>5.7f && polegadas<=6.0f)
         return 0.74f;
	 
	 else
         return 0.90f;
}
	
    public void  draw(Canvas canvas){
        super.draw(canvas);
        float escalaDp = getEscala();
        canvas.scale(escalaDp,escalaDp);
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

        //Tentativa de pegar o x e y do aparelho, ainda não consegui implementar com sucesso (por isso está comentado - d'oh)
        /*Display mdisp = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        int maxX= mdispSize.x;
        int maxY= mdispSize.y;*/

        //Alterei o x e y para que a mesa fique mais centralizada, ainda é preciso achar uma maneira de deixar esse x e y
        //relativo a cada aparelho
        mesa = new Mesa(220,75,600,900);
        branca = new Bola(350.0f,400.0f,1,16);
        branca.setVx(225.0f);
        branca.setVy(225.0f);
        bolas = new Bola[8];
        for(int i=0;i<8;i++){
            bolas[i] =  new Bola(200.0f+42*i,250.0f,0,16);
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
