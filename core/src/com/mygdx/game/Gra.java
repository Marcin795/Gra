package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import net.spookygames.gdx.nativefilechooser.NativeFileChooser;

public class Gra extends Game {

    private NativeFileChooser fileChooser;

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

        showStartScreen();
    }
    void showStartScreen(){
        setScreen(new Menu(this, fileChooser));
    }
    void showGameScreen(String absolutePath){
        setScreen(new com.mygdx.game.Game(this, absolutePath));
    }
    void showRankingScreen(){
        setScreen(new Ranking(this));
    }
}
