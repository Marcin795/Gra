package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.Constants.BALL_SPEED;

class Ball {

    Vector2 position;
    private Vector2 velocity;
    boolean click;

    Ball(Vector2 position) {
        click = false;
        this.position = position;
        this.velocity = new Vector2(0, -BALL_SPEED);
    }

    void update(float delta) {
        position.mulAdd(velocity, delta);
    }

    void render(ShapeRenderer renderer) {
        renderer.circle(position.x, position.y, 25);
    }

}
