package com.mygdx.game;


import com.badlogic.gdx.*;
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
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.util.audio.PausablePlayer;

import java.io.FileInputStream;

import static com.mygdx.game.Constants.*;


/**
 * Ekran gry
 */
public class Game extends InputAdapter implements Screen {
    protected Gra gra;
    private SpriteBatch batch;
    private Viewport viewport;
    private Camera camera;
    private ShapeRenderer renderer;
    private Balls balls;
    private BallPaths ballPaths;
    private int score;
    private BitmapFont font;
    private Preferences prefs;
    private PausablePlayer player;
    private Circles circles;
    private End end;
    private String path;

    Game(Gra gra, String absolutePath){
        score = 0;
        this.gra = gra;
        this.path = absolutePath;
    }

    @Override
    public void show() {

        try {
            FileInputStream input = new FileInputStream(path);
            player = new PausablePlayer(input);
            // start playing
            player.play();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        viewport = new ExtendViewport(Constants.minWorldWidth,Constants.minWorldHeight,camera);
        font = new BitmapFont(Gdx.files.internal("czcionka2.fnt"));
        prefs = Gdx.app.getPreferences("ranking");
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

        Gdx.gl.glClearColor(BACKGROUND_R, BACKGROUND_G, BACKGROUND_B, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)||Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            if (!balls.ballsList1.isEmpty()) {
                for(Ball ball:balls.ballsList1){
                    if(ball.position.dst(circles.k1.x,circles.k1.y)<50&&!ball.click) {
                        score += 10;
                        ball.click = true;
                        break;
                    }
                }
            }
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.S)||Gdx.input.isKeyJustPressed(Input.Keys.UP)||Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            if(!balls.ballsList2.isEmpty()){
                for(Ball ball:balls.ballsList2){
                    if(ball.position.dst(circles.k2.x,circles.k2.y)<50&&!ball.click) {
                        score += 10;
                        ball.click = true;
                        break;
                    }
                }

            }
        }if(Gdx.input.isKeyJustPressed(Input.Keys.D)||Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
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
        balls.update(delta);

        renderer.begin();

        ballPaths.render(renderer);
        circles.render(renderer);
        balls.render(renderer);
        if(balls.gameOver){
            end.render(renderer);
            player.pause();
        }

        renderer.end();

        if(balls.gameOver){
            end.render(batch);
            end.addScore();
        }
        batch.begin();

        int best = prefs.getInteger("best");
        String napis1= "Score: "+score+"\n"+"Best: "+ best;
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

    /**
     * Obsluguje klikniecia myszy. Nalicza punkty, po kliknieciu jednego z trzech dolnych kolek w odpowiednim momencie. Gdy gra jest skonczona, wraca do ekranu startowego po kliknieciu przycisku "BACK".
     */
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));
        if(balls.gameOver){


            com.badlogic.gdx.math.Rectangle startRect = new Rectangle(Constants.START_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2, viewport.getWorldHeight() / 3,Constants.SZER,Constants.WYS/3);
            if(startRect.contains(worldTouch)) {
                gra.showStartScreen();

            }


        }
        else{
            if (worldTouch.dst(viewport.getWorldWidth()/2-Constants.WIDTH-Constants.SPACE,Constants.R) < Constants.R) {
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

            else if (worldTouch.dst(viewport.getWorldWidth()/2,Constants.R) < Constants.R) {

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

            else if (worldTouch.dst(viewport.getWorldWidth()/2+Constants.WIDTH+Constants.SPACE,Constants.R) < Constants.R) {
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
