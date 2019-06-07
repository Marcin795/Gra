package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.mygdx.game.Constants.BALL_SPEED;

public class Ball {

    Vector2 position;
    Vector2 velocity;
    Viewport viewport;
    long spawnTime;
    boolean click=false;

    public Ball(Vector2 position, Viewport viewport) {
        this.position = position;
        this.viewport = viewport;
        this.velocity = new Vector2(0, -BALL_SPEED);
        spawnTime = System.nanoTime();
    }

    public void update(float delta) {
        position.mulAdd(velocity, delta);
    }

    public void render(ShapeRenderer renderer) {
        renderer.circle(position.x, position.y, 25);
    }

}
