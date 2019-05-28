package com.mygdx.game;

import com.badlogic.gdx.Game;

public class Gra extends Game {
   // Menu start;
    @Override
    public void create() {
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
    public void showScoreScreen(){

    }
}
