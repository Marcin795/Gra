package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Stałe
 */
public class Constants {
    static final float WIDTH = 30.0f;
    static final float SPACE = 40.0f;

    static final int minWorldWidth = 600;
    static final int minWorldHeight = 800;

    static final float MENU_SIZE = 600;

    static final Vector2 START_W = new Vector2(MENU_SIZE/2, MENU_SIZE*3/5 );
    static final Vector2 RANKING_W = new Vector2(MENU_SIZE/2, MENU_SIZE *1/5);

    static final float SZER = minWorldWidth/3;
    static final float WYS = minWorldHeight/4;

    static final float RANK_SZER = minWorldWidth/2;


    static final int BALL_SPEED = 800;

    public static final String LEVEL_PATH = "levels/test2.json";
    static final Color BUTTON_COLOR = new Color(Color.RED);
    static final Color FONT_COLOR = new Color(Color.WHITE);
    static final Color END_OVERLAY_BACKGROUND_COLOR = new Color(Color.BLACK);
    static final float BACKGROUND_R = 0;
    static final float BACKGROUND_G = 0;
    static final float BACKGROUND_B = 0;
}
