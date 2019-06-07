package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class BallPaths {

    ArrayList<Float> positions;
    Viewport viewport;

    public BallPaths(Viewport viewport) {
        this.viewport = viewport;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.FOREST);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        for(Float p : positions) {
            renderer.rectLine(p, 0, p, viewport.getWorldHeight(), Constants.WIDTH);
        }
    }

    public void init() {
        positions = new ArrayList<Float>();
        positions.add(viewport.getWorldWidth()/2);
        positions.add(viewport.getWorldWidth()/2 - Constants.WIDTH - Constants.SPACE);
        positions.add(viewport.getWorldWidth()/2 + Constants.WIDTH + Constants.SPACE);
    }
}
