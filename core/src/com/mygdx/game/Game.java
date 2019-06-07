package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;
import java.util.Arrays;
import java.util.Hashtable;

public class Game extends InputAdapter implements Screen {
    protected Gra gra;
    protected SpriteBatch batch;
    protected Viewport viewport;
    Camera camera;
    ShapeRenderer renderer;
    Balls balls;
    BallPaths ballPaths;
    int score=0, best;
    BitmapFont font;
    Preferences prefs;
    Json json;
    Music music;
    Circles circles;


    End end;
    public Game(Gra gra){

        this.gra = gra;
    }

    @Override
    public void show() {
        music = Gdx.audio.newMusic(Gdx.files.absolute("surfacing.mp3"));
        music.play();
        music.setVolume(0.1f);
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        viewport = new ExtendViewport(Constants.minWorldWidth,Constants.minWorldHeight,camera);
        font = new BitmapFont(Gdx.files.internal("czcionka2.fnt"));
        prefs = Gdx.app.getPreferences("ranking");
        json = new Json();
        end = new End(gra);
        Gdx.input.setInputProcessor(this);
        balls = new Balls(viewport);
        ballPaths = new BallPaths(viewport);
        circles = new Circles(viewport);

    }

    @Override
    public void render(float delta) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setAutoShapeType(true);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        balls.update(delta);




        renderer.begin();

        ballPaths.render(renderer);
        circles.render(renderer);
        balls.render(renderer);
        if(balls.gameOver){
            //Gdx.app.log("Game","tak");
            end.render(renderer);
            music.stop();
        }

        renderer.end();

        if(balls.gameOver){
            //Gdx.app.log("Game","tak");
            end.render(batch);
            end.addScore();
        }
        batch.begin();

        best=prefs.getInteger("best");
        String napis1= "Score: "+score+"\n"+"Best: "+best;
        font.setColor(Color.WHITE);
        font.draw(batch, napis1, 20, viewport.getWorldHeight() - 20,0,Align.left,false);

        prefs.putInteger("score",score);
        prefs.flush();





        batch.end();


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        camera.update();
        balls.init();
        ballPaths.init();
        end.viewport.update(width, height, true);

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));
        if(balls.gameOver){


            com.badlogic.gdx.math.Rectangle startRect = new Rectangle(Constants.START_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2, viewport.getWorldHeight() / 3,Constants.SZER,Constants.WYS/3);
            if(startRect.contains(worldTouch)) {
                gra.showStartScreen();

            }


        }
        else{
            if (worldTouch.dst(viewport.getWorldWidth()/2-Constants.WIDTH-Constants.SPACE,25) < 25) {
                if (!balls.ballsList1.isEmpty()) {
                    for(Ball ball:balls.ballsList1){
                        if(ball.position.dst(circles.k1.x,circles.k1.y)<50&&!ball.click) {
                            score += 10;
                            ball.click = true;
                            break;
                        }
                    }
                }
            }

            else if (worldTouch.dst(viewport.getWorldWidth()/2,25) < 25) {

                Gdx.app.log("odl",balls.ballsList2+"");
                if(!balls.ballsList2.isEmpty()){
                    for(Ball ball:balls.ballsList2){
                        if(ball.position.dst(circles.k2.x,circles.k2.y)<50&&!ball.click) {
                            score += 10;
                            ball.click = true;
                            break;
                        }
                    }

                }

            }

            else if (worldTouch.dst(viewport.getWorldWidth()/2+Constants.WIDTH+Constants.SPACE,25) < 25) {
                if(!balls.ballsList3.isEmpty()) {
                    for(Ball ball:balls.ballsList3){
                        if(ball.position.dst(circles.k3.x,circles.k3.y)<50&&!ball.click) {
                            score += 10;
                            ball.click = true;
                            break;
                        }
                    }
                }
            }


        }

        return true;

    }

}
