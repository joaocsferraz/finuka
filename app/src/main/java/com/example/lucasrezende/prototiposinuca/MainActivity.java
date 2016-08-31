package com.example.lucasrezende.prototiposinuca;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private  GameView game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        game = new GameView(this);
        game.setBackgroundColor(getResources().getColor(android.R.color.white));
        setContentView(game);
    }

    protected void onDestroy(){
        super.onDestroy();
        game.release();
    }
}
