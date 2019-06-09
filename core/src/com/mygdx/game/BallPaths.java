package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

class BallPaths {

    private ArrayList<Float> positions;
    private Viewport viewport;

    BallPaths(Viewport viewport) {
        this.viewport = viewport;
    }

    void render(ShapeRenderer renderer) {
        renderer.setColor(Color.FOREST);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        for(Float p : positions) {
            renderer.rectLine(p, 0, p, viewport.getWorldHeight(), Constants.WIDTH);
        }
    }

    void init() {
        positions = new ArrayList<>();
        positions.add(viewport.getWorldWidth()/2);
        positions.add(viewport.getWorldWidth()/2 - Constants.WIDTH - Constants.SPACE);
        positions.add(viewport.getWorldWidth()/2 + Constants.WIDTH + Constants.SPACE);
    }
}
