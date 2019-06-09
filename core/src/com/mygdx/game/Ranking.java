package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Arrays;
import java.util.Collections;


/**
 * Obsługuje ranking
 */
public class Ranking extends InputAdapter implements Screen {
    protected Gra gra;
    private SpriteBatch batch;
    private Viewport viewport;
    private Camera camera;
    private BitmapFont font,font_r;
    private Texture back;
    private Preferences prefs;
    private Json json;
    private int ranking;

    Ranking(Gra gra){
        this.gra=gra;
        ranking = 9;
    }

    @Override
    public void show() {
        camera= new OrthographicCamera();
        viewport= new ExtendViewport(Constants.minWorldWidth,Constants.minWorldHeight,camera);
        batch= new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("czcionka2.fnt"));
        font_r = new BitmapFont(Gdx.files.internal("czcionka_rank.fnt"));
        back= new Texture("back.png");
        prefs = Gdx.app.getPreferences("ranking");
        json = new Json();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        font.draw(batch,"HIGH SCORES",viewport.getWorldWidth()/2,viewport.getWorldHeight()*0.8f,0, Align.center,false);
        batch.draw(back,0,0,Constants.SZER,Constants.WYS);
        if(!prefs.getString("values").isEmpty()){

            Integer[] values = json.fromJson(Integer[].class, prefs.getString("values"));
            Arrays.sort(values, Collections.reverseOrder());
            if(values.length<=ranking){
                ranking= values.length-1;
            }
            for(int i=0;i<=ranking;i++){

                font_r.draw(batch, i+1+".", Constants.RANKING_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.RANK_SZER/2, viewport.getWorldHeight()*0.7f-35*i,0,Align.center,false);
                font_r.draw(batch, ""+ values[i], viewport.getWorldWidth()/2, viewport.getWorldHeight()*0.7f-35*i,0,Align.center,false);

            }
            values = Arrays.copyOf(values, ranking + 1);
            prefs.putString("values", json.toJson(values));
        }


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
    }

    @Override
    public void dispose() {

    }
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        Rectangle startRect = new Rectangle(0, 0, Constants.SZER, Constants.WYS);
        if(startRect.contains(worldTouch)) {
            gra.showStartScreen();
        }

        return true;


    }
}


