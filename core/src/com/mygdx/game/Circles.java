package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.*;

public class Circles {
    Viewport viewport;
    Circle k1,k2,k3;

    public Circles(Viewport viewport) {
        this.viewport = viewport;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.RED);
        renderer.set(ShapeRenderer.ShapeType.Filled);

        renderer.circle(viewport.getWorldWidth()/2,25,25);
        renderer.circle(viewport.getWorldWidth()/2- Constants.WIDTH - Constants.SPACE,25,25);
        renderer.circle(viewport.getWorldWidth()/2+ Constants.WIDTH + Constants.SPACE, 25,25);
        k2= new Circle(viewport.getWorldWidth()/2,25,25);
        k1= new Circle(viewport.getWorldWidth()/2- Constants.WIDTH - Constants.SPACE,25,25);
        k3= new Circle(viewport.getWorldWidth()/2+ Constants.WIDTH + Constants.SPACE, 25,25);

    }
}
