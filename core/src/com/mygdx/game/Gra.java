package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.util.Test;
import net.spookygames.gdx.nativefilechooser.NativeFileChooser;

public class Gra extends Game {

    NativeFileChooser fileChooser;

    public Gra(NativeFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    // Menu start;
    @Override
    public void create() {

//        Test test = new Test();
//        try {
//            test.test();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Gdx.app.log("Gra", "chyba dziala");
//        Gdx.app.exit();

        showStartScreen();
//        showChoiceScreen();
    }
    public void showStartScreen(){
        setScreen(new Menu(this, fileChooser));
    }
    public void showGameScreen(String absolutePath){
        setScreen(new com.mygdx.game.Game(this, absolutePath));
    }
    public void showRankingScreen(){
        setScreen(new Ranking(this));
    }
    public void showChoiceScreen(){
        setScreen(new Choice(this, fileChooser));
    }
}
