package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.mygdx.game.Constants.BUTTON_COLOR;


class Circles {
    private Viewport viewport;
    Circle k1,k2,k3;

    Circles(Viewport viewport) {
        this.viewport = viewport;
    }

    void render(ShapeRenderer renderer) {
        renderer.setColor(BUTTON_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);

        renderer.circle(viewport.getWorldWidth()/2,Constants.R,Constants.R);
        renderer.circle(viewport.getWorldWidth()/2- Constants.WIDTH - Constants.SPACE,Constants.R,Constants.R);
        renderer.circle(viewport.getWorldWidth()/2+ Constants.WIDTH + Constants.SPACE, Constants.R,Constants.R);
        k2= new Circle(viewport.getWorldWidth()/2,Constants.R,Constants.R);
        k1= new Circle(viewport.getWorldWidth()/2- Constants.WIDTH - Constants.SPACE,Constants.R,Constants.R);
        k3= new Circle(viewport.getWorldWidth()/2+ Constants.WIDTH + Constants.SPACE, Constants.R,Constants.R);

    }
}
