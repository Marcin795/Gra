package com.mygdx.game;

import com.badlogic.gdx.Game;
import net.spookygames.gdx.nativefilechooser.NativeFileChooser;


/**
 * Główna klasa gry
 */
public class Gra extends Game {

    private NativeFileChooser fileChooser;

    public Gra(NativeFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    @Override
    public void create() {
        showStartScreen();
    }

    /**
     * Ustawia ekran startowy.
     */
    void showStartScreen(){
        setScreen(new Menu(this, fileChooser));
    }

    /**
     * Ustawia ekran gry.
     */
    void showGameScreen(String absolutePath){
        setScreen(new com.mygdx.game.Game(this, absolutePath));
    }

    /**
     * Ustawia ekran rankingu.
     */
    void showRankingScreen(){
        setScreen(new Ranking(this));
    }
}
