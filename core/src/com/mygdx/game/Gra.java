package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.util.Test;

public class Gra extends Game {
   // Menu start;
    @Override
    public void create() {


        Test test = new Test();
        try {
            test.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gdx.app.log("Gra", "chyba dziala");
//        Gdx.app.exit();

        showStartScreen();
    }
    public void showStartScreen(){
        setScreen(new Menu(this));
    }
    public void showGameScreen(){
        setScreen(new com.mygdx.game.Game(this));
    }
    public void showRankingScreen(){
        setScreen(new Ranking(this));
    }
    public void showChoiceScreen(){
//        setScreen(new Choice(this));
    }
}
