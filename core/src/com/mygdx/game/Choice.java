package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import net.spookygames.gdx.nativefilechooser.NativeFileChooser;
import net.spookygames.gdx.nativefilechooser.NativeFileChooserCallback;
import net.spookygames.gdx.nativefilechooser.NativeFileChooserConfiguration;

import java.io.File;
import java.io.FilenameFilter;

public class Choice implements Screen {
    Gra gra;
    protected SpriteBatch batch;
    Camera camera;
    protected ExtendViewport viewport;
    Texture play,ranking;

    public Choice(Gra gra, NativeFileChooser fileChooser) {
        this.gra = gra;

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
                gra.showGameScreen(file.file().getAbsolutePath());
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

    @Override
    public void show() {
        batch= new SpriteBatch();
        camera = new OrthographicCamera();
        viewport= new ExtendViewport(Constants.minWorldWidth,Constants.minWorldHeight,camera);
        play= new Texture("pl.png");
        ranking = new Texture("rank.png");

    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
//        batch.draw(play,Constants.START_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2,Constants.START_W.y/Constants.MENU_SIZE*viewport.getWorldHeight(),Constants.SZER,Constants.WYS);
        batch.draw(ranking,Constants.RANKING_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2,Constants.RANKING_W.y/Constants.MENU_SIZE*viewport.getWorldHeight(),Constants.SZER,Constants.WYS);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
    }
}
