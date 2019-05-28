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
        import com.badlogic.gdx.utils.viewport.ExtendViewport;
        import com.badlogic.gdx.utils.viewport.ScreenViewport;
        import com.badlogic.gdx.utils.viewport.Viewport;

public class End extends InputAdapter implements Screen {
    protected Gra gra;
    protected SpriteBatch batch;
    protected Viewport viewport;
    Camera camera;
    ShapeRenderer renderer;

    public End(Gra gra){
        this.gra=gra;
    }

    @Override
    public void show() {
        camera= new OrthographicCamera();
        viewport= new ExtendViewport(Constants.minWorldWidth,Constants.minWorldHeight,camera);
        batch= new SpriteBatch();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //renderer.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

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
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }
}
