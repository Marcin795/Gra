package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.*;

import static javafx.application.Platform.exit;

public class Menu extends InputAdapter implements Screen {
    protected Gra gra;
    protected SpriteBatch batch;
    protected Viewport viewport;
    Camera camera;
    ShapeRenderer renderer;
    BitmapFont font;
    int minWorldWidth=Gdx.graphics.getWidth();
    int minWorldHeight=Gdx.graphics.getHeight();

    public Menu(Gra gra){
        this.gra=gra;
    }

    @Override
    public void show() {
        batch= new SpriteBatch();
        renderer= new ShapeRenderer();
        camera= new OrthographicCamera();
        viewport= new ExtendViewport(minWorldWidth,minWorldHeight,camera);


        font = new BitmapFont();
        font.getData().setScale(2);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //renderer.setProjectionMatrix(viewport.getCamera().combined);


        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLUE);
        renderer.circle(minWorldWidth*3/6,minWorldHeight*4/6,90,1000);
        renderer.setColor(Color.GREEN);
        renderer.circle(minWorldWidth*2/6,minWorldHeight*2/6,90,1000);
        renderer.setColor(Color.RED);
        renderer.circle(minWorldWidth*4/6,minWorldHeight*2/6,90,1000);
        renderer.end();
        batch.begin();
        font.draw(batch, "START", minWorldWidth*3/6,minWorldHeight*4/6, 0, Align.center, false);
        font.draw(batch, "RANKING", minWorldWidth*2/6,minWorldHeight*2/6, 0, Align.center, false);
        font.draw(batch, "WYJDZ", minWorldWidth*4/6,minWorldHeight*2/6, 0, Align.center, false);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    viewport.update(width,height,true);
    camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        if (worldTouch.dst(new Vector2(minWorldWidth*3/6,minWorldHeight*4/6)) < 480f/9) {
            gra.showGameScreen();
        }

        if (worldTouch.dst(new Vector2(minWorldWidth*2/6,minWorldHeight*2/6)) < 480f/9) {
            gra.showRankingScreen();
        }

        else if (worldTouch.dst(new Vector2(minWorldWidth*4/6,minWorldHeight*2/6)) < 480f/9) {
            Gdx.app.exit();
        }

        return true;
    }
}
