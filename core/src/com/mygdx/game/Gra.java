package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Gra extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer renderer;
	Camera camera;
	Viewport viewport;

	@Override
	public void create () {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		img = new Texture("badlogic.jpg");
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(400, 400, camera);
	}

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
	public void render () {
	    viewport.apply();
	    batch.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setProjectionMatrix(viewport.getCamera().combined);

		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

//		batch.draw(img, 0, 0);
		batch.end();
//		renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setAutoShapeType(true);
		renderer.begin();
		renderer.setColor(Color.GOLD);
        renderer.set(ShapeRenderer.ShapeType.Filled);
		renderer.rect(-200, -200, 200, 200);
        renderer.setColor(Color.RED);
//        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.rect(0, 0, 200, 200);

        renderer.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		renderer.dispose();
	}
}
