package br.ufjf.dcc.finuka.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.ufjf.dcc.finuka.views.GameView;

public class MainActivity extends Activity {

    private GameView game;

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
