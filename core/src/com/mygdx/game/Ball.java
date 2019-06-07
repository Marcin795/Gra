package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Ball {

    Vector2 position;
    Vector2 velocity;
    Viewport viewport;
    long spawnTime;
    boolean click=false;

    public Ball(Vector2 position, Viewport viewport) {
        this.position = position;
        this.viewport = viewport;
        this.velocity = new Vector2(0, -400);
        spawnTime = System.nanoTime();
    }

    public void update(float delta) {
        position.mulAdd(velocity, delta);
//        if(position.y < 0 || position.y > viewport.getWorldHeight()) {
//            velocity.y = -velocity.y;
//        }
        if(position.y < 100 && position.y > 25&& position.x==viewport.getWorldWidth()/2) {

            //Gdx.app.log("Ball", "" + (System.nanoTime() - spawnTime));
            //Gdx.app.log("Ball_Y", "" + position.y);


        }

    }

    public void render(ShapeRenderer renderer) {

        renderer.circle(position.x, position.y, 25);
    }


}
