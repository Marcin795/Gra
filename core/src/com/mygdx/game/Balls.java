package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Balls {

    DelayedRemovalArray<Ball> ballsList1,ballsList2,ballsList3;
    Viewport viewport;
    Double time = 0.0d;
    FileHandle level;
    float[] left;
    float[] middle;
    float[] right;
    boolean gameOver=false;

    public Balls(Viewport viewport) {
        this.viewport = viewport;
//        init();
    }

    public void init() {
        //ballsList = new DelayedRemovalArray<Ball>(false, 100);
        ballsList1 = new DelayedRemovalArray<Ball>(false, 100);
        ballsList2 = new DelayedRemovalArray<Ball>(false, 100);
        ballsList3 = new DelayedRemovalArray<Ball>(false, 100);
        level = Gdx.files.internal("levels/test.json");
        Json json = new Json();
        JsonValue root = new JsonReader().parse(level);
        left = root.get("level").get("left").asFloatArray();
        middle = root.get("level").get("middle").asFloatArray();
        right = root.get("level").get("right").asFloatArray();
        float L = viewport.getWorldWidth()/2 - 30.0f - 40.0f;
        float M = viewport.getWorldWidth()/2;
        float R = viewport.getWorldWidth()/2 + 30.0f + 40.0f;

        for(float f : left) {
            add(L, f * 400,ballsList1);
        }
        for(float f : middle) {
            add(M, f * 400,ballsList2);
        }
        for(float f : right) {
            add(R, f * 400,ballsList3);
        }

        Gdx.app.log("Balls_left",left.length+"");
        Gdx.app.log("Balls_middle",middle.length+"");
        Gdx.app.log("Balls_right",right.length+"");
        Gdx.app.log("Balls", viewport.getScreenHeight() + " " + viewport.getScreenWidth());
        for (Float f : root.get("level").get("middle").asFloatArray()) {
            Gdx.app.log("Balls", f+"");
        }

    }

    public void update(float delta) {
        time += delta;
//        Gdx.app.log("Balls", "Delta: " + String.format("%.5f", delta) + " Sum: " + String.format("%.2f", time));  }
        /*if(ballsList.isEmpty()) {
            ballsList.add(new Ball(new Vector2(viewport.getWorldWidth() / 2, 2000), viewport));
        }*/
//        if(ballsList.size < 3) {
//            ballsList.add(new Ball(new Vector2(viewport.getWorldWidth()/2, viewport.getWorldHeight()-30), viewport));
//            ballsList.add(new Ball(new Vector2(viewport.getWorldWidth()/2 + 70, viewport.getWorldHeight()-130), viewport));
//            ballsList.add(new Ball(new Vector2(viewport.getWorldWidth()/2 - 70, viewport.getWorldHeight()-230), viewport));
//
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
        //Gdx.app.log("Balls",ballsList.toString());
        if(ballsList1.isEmpty()&&ballsList2.isEmpty()&&ballsList3.isEmpty()){
            gameOver=true;
        }

    }

    public void render(ShapeRenderer renderer) {
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


    void add(float x, float y, DelayedRemovalArray<Ball> ballsList) {
        ballsList.add(new Ball(new Vector2(x, y), viewport));
    }

}
