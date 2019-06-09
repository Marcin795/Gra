package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.mygdx.game.Constants.BALL_SPEED;


/**
 * Przechowuje wszystkie kulki
 */
class Balls {

    DelayedRemovalArray<Ball> ballsList1,ballsList2,ballsList3;
    private Viewport viewport;
    boolean gameOver=false;

    Balls(Viewport viewport) {
        this.viewport = viewport;
    }

    void init() {
        ballsList1 = new DelayedRemovalArray<>(false, 100);
        ballsList2 = new DelayedRemovalArray<>(false, 100);
        ballsList3 = new DelayedRemovalArray<>(false, 100);
        FileHandle level = Gdx.files.internal("levels/test2.json");
        JsonValue root = new JsonReader().parse(level);
        float[] left = root.get("0").asFloatArray();
        float[] middle = root.get("1").asFloatArray();
        float[] right = root.get("2").asFloatArray();
        float L = viewport.getWorldWidth()/2 - 30.0f - 40.0f;
        float M = viewport.getWorldWidth()/2;
        float R = viewport.getWorldWidth()/2 + 30.0f + 40.0f;

        for(float f : left) {
            add(L, f * BALL_SPEED, ballsList1);
        }
        for(float f : middle) {
            add(M, f * BALL_SPEED, ballsList2);
        }
        for(float f : right) {
            add(R, f * BALL_SPEED, ballsList3);
        }

    }

    void update(float delta) {
        for(Ball ball : ballsList1) {
            ball.update(delta);
        }
        for(Ball ball : ballsList2) {
            ball.update(delta);
        }
        for(Ball ball : ballsList3) {
            ball.update(delta);
        }

        ballsList1.begin();
        for (int i = 0; i < ballsList1.size; i++) {
            if (ballsList1.get(i).position.y < -50) {
                ballsList1.removeIndex(i);
            }
        }
        ballsList1.end();
        ballsList2.begin();

        for (int i = 0; i < ballsList2.size; i++) {
            if (ballsList2.get(i).position.y < -50) {
                ballsList2.removeIndex(i);
            }
        }
        ballsList2.end();
        ballsList3.begin();
        for (int i = 0; i < ballsList3.size; i++) {
            if (ballsList3.get(i).position.y < -50) {
                ballsList3.removeIndex(i);
            }
        }
        ballsList3.end();
        if(ballsList1.isEmpty()&&ballsList2.isEmpty()&&ballsList3.isEmpty()){
            gameOver=true;
        }

    }

    void render(ShapeRenderer renderer) {
        renderer.setColor(Color.SCARLET);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        for(Ball ball : ballsList1) {
            ball.render(renderer);
        }
        for(Ball ball : ballsList2) {
            ball.render(renderer);
        }
        for(Ball ball : ballsList3) {
            ball.render(renderer);
        }
    }

    /**
     * Tworzy nowa kulke i zapisuja ja do jednej z 3 list
     * @param x wspolrzedna x kulki
     * @param y wspolrzedna y kulki
     * @param ballsList lista kulek
     */

    private void add(float x, float y, DelayedRemovalArray<Ball> ballsList) {
        ballsList.add(new Ball(new Vector2(x, y+25)));
    }
}
