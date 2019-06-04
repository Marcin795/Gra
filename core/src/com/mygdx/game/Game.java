package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends InputAdapter implements Screen {
    protected Gra gra;
    protected SpriteBatch batch;
    protected Viewport viewport;
    Camera camera;
    ShapeRenderer renderer;
    Balls balls;
    BallPaths ballPaths;


    public Game(Gra gra){
        this.gra = gra;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        viewport = new ExtendViewport(Constants.minWorldWidth,Constants.minWorldHeight,camera);
        Gdx.input.setInputProcessor(this);
        balls = new Balls(viewport);
        ballPaths = new BallPaths(viewport);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setAutoShapeType(true);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        balls.update(delta);

        renderer.begin();
        batch.begin();

        ballPaths.render(renderer);

        balls.render(renderer);


        renderer.end();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        Gdx.app.log("Game", viewport.getScreenWidth()+" resize");
        camera.update();

        balls.init();
        ballPaths.init();
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
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }
}
