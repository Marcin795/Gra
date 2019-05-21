package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Gra extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer renderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

//		batch.draw(img, 0, 0);
		batch.end();
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(Color.GOLD);
		renderer.rect(0, 0, 100, 100);
		renderer.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		renderer.dispose();
	}
}
