package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import net.spookygames.gdx.nativefilechooser.NativeFileChooser;
import net.spookygames.gdx.nativefilechooser.NativeFileChooserCallback;
import net.spookygames.gdx.nativefilechooser.NativeFileChooserConfiguration;

import java.io.File;
import java.io.FilenameFilter;


public class Menu extends InputAdapter implements Screen {
    private final NativeFileChooser fileChooser;
    protected Gra gra;
    protected SpriteBatch batch;
    protected ExtendViewport viewport;
    Camera camera;
    ShapeRenderer renderer;
    BitmapFont font;
    Texture play,play1,ranking,ranking1;

    String path;

    public Menu(Gra gra, NativeFileChooser fileChooser){
        this.gra=gra;
        this.fileChooser = fileChooser;
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
        batch.dispose();
        font.dispose();
        renderer.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        Rectangle startRect = new Rectangle(Constants.START_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2,
                Constants.START_W.y/Constants.MENU_SIZE*viewport.getWorldHeight(),
                Constants.SZER,
                Constants.WYS);
        if(startRect.contains(worldTouch)) {
            Gdx.app.log("Menu", "asdf");
//            gra.showChoiceScreen();
//            gra.showGameScreen("E:\\Java\\lullaby.mp3");
            chooseFile();
            gra.showGameScreen(path);
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

    public void chooseFile() {

        NativeFileChooserConfiguration conf = new NativeFileChooserConfiguration();
        conf.directory = Gdx.files.absolute(System.getProperty("user.home"));
        conf.mimeFilter = "audio/*";
        conf.nameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("mp3");
            }
        };
        conf.title = "Choose audio file";
        fileChooser.chooseFile(conf, new NativeFileChooserCallback() {
            @Override
            public void onFileChosen(FileHandle file) {
                // Do stuff with file, yay!
                Gdx.app.log("Choice", file.file().getAbsolutePath());
                path = file.file().getAbsolutePath();
            }

            @Override
            public void onCancellation() {
                // Warn user how rude it can be to cancel developer's effort
                Gdx.app.log("Choice", "To JEB SIE!!!");
                Gdx.app.exit();
            }

            @Override
            public void onError(Exception exception) {
                // Handle error (hint: use exception type)
                Gdx.app.log("Choice", "Huh?");
                Gdx.app.exit();
            }
        });
    }
}