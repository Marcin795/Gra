package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;


public class Menu extends InputAdapter implements Screen {
    protected Gra gra;
    protected SpriteBatch batch;
    protected ExtendViewport viewport;
    Camera camera;
    ShapeRenderer renderer;
    BitmapFont font;
    Texture play,play1,ranking,ranking1;




    public Menu(Gra gra){
        this.gra=gra;
    }

    @Override
    public void show() {
        batch= new SpriteBatch();
        renderer= new ShapeRenderer();
        camera = new OrthographicCamera();
        viewport= new ExtendViewport(Constants.minWorldWidth,Constants.minWorldHeight,camera);
        play= new Texture("pl.png");
        //play1= new Texture("play_1a.png");
        ranking = new Texture("rank.png");
        //ranking1 = new Texture("ranking_1a.png");
        font = new BitmapFont();
        font.getData().setScale(2);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        /*if(Gdx.input.getX()<Gdx.graphics.getWidth()*2/3&&Gdx.input.getX()>Gdx.graphics.getWidth()/3&&
                Gdx.input.getY()<Gdx.graphics.getHeight()*2/6&&Gdx.input.getY()>Gdx.graphics.getHeight()/6){
                batch.draw(play1,Constants.START_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2,Constants.START_W.y/Constants.MENU_SIZE*viewport.getWorldHeight(),Constants.SZER,Constants.WYS);
       }
        else{
            batch.draw(play,Constants.START_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2,Constants.START_W.y/Constants.MENU_SIZE*viewport.getWorldHeight(),Constants.SZER,Constants.WYS);
        }
        if(Gdx.input.getX()<Gdx.graphics.getWidth()*2/3&&Gdx.input.getX()>Gdx.graphics.getWidth()/3&&
                Gdx.input.getY()<Gdx.graphics.getHeight()*5/6&&Gdx.input.getY()>Gdx.graphics.getHeight()*3/6){
            batch.draw(ranking1,Constants.RANKING_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2,Constants.RANKING_W.y/Constants.MENU_SIZE*viewport.getWorldHeight(),Constants.SZER,Constants.WYS);
       }
        else{
            batch.draw(ranking,Constants.RANKING_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2,Constants.RANKING_W.y/Constants.MENU_SIZE*viewport.getWorldHeight(),Constants.SZER,Constants.WYS);

        }
        */
        batch.draw(play,Constants.START_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2,Constants.START_W.y/Constants.MENU_SIZE*viewport.getWorldHeight(),Constants.SZER,Constants.WYS);
        batch.draw(ranking,Constants.RANKING_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2,Constants.RANKING_W.y/Constants.MENU_SIZE*viewport.getWorldHeight(),Constants.SZER,Constants.WYS);


        batch.end();

    }

    @Override
    public void resize(int width, int height) {
    viewport.update(width,height,true);
    Gdx.app.log("MENU",viewport.getScreenHeight()+" "+viewport.getScreenWidth()+" ");
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

        Rectangle startRect = new Rectangle(Constants.START_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2,
                Constants.START_W.y/Constants.MENU_SIZE*viewport.getWorldHeight(),
                Constants.SZER,
                Constants.WYS);
        if(startRect.contains(worldTouch)) {
            //gra.showChoiceScreen();
            gra.showGameScreen();
        }
        Rectangle rankingRect = new Rectangle(Constants.RANKING_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2,
                Constants.RANKING_W.y/Constants.MENU_SIZE*viewport.getWorldHeight(),
                Constants.SZER,
                Constants.WYS);
        if(rankingRect.contains(worldTouch)) {
            gra.showRankingScreen();
        }

        return true;


    }
}
