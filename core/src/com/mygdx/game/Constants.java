package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {
    //gra
    public static final float WIDTH = 30.0f;
    public static final float SPACE = 40.0f;

    public static final int minWorldWidth = 600;
    public static final int minWorldHeight = 800;

    public static final Color START = new Color(0.2f, 0.2f, 1, 1);
    public static final Color RANKING = new Color(0.5f, 0.5f, 1, 1);
    public static final Color WYJDZ = new Color(0.7f, 0.7f, 1, 1);
    //przyciski
    public static final float MENU_SIZE = 600;
    public static final Vector2 START_W = new Vector2(MENU_SIZE/2, MENU_SIZE*3/5 );
    public static final Vector2 RANKING_W = new Vector2(MENU_SIZE/2, MENU_SIZE *1/5);
    //nazwy przyciskow
    public static final Vector2 START_N = new Vector2(MENU_SIZE*6/10, MENU_SIZE*3/5 );
    public static final Vector2 RANKING_N = new Vector2(MENU_SIZE*6/10, MENU_SIZE *2/5);
    public static final Vector2 WYJDZ_N = new Vector2(MENU_SIZE*6/10, MENU_SIZE * 1/5);

    public static final float SZER = minWorldWidth/3;
    public static final float WYS = minWorldHeight/4;

    public static final float RANK_SZER = minWorldWidth/2;


    public static final int BALL_SPEED = 800;
}
