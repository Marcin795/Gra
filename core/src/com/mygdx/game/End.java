package com.mygdx.game;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.InputAdapter;
        import com.badlogic.gdx.Preferences;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.graphics.*;
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

        import java.util.Arrays;
        import java.util.Map;

public class End extends InputAdapter{
    protected Gra gra;
    protected Viewport viewport;
    BitmapFont font,font1;
    Preferences prefs;
    Texture end;
    Json json;
    int[] values;
    boolean rank=true;


    public End(Gra gra){
        this.gra=gra;
        this.viewport = new ExtendViewport(Constants.minWorldWidth, Constants.minWorldHeight);
        font = new BitmapFont(Gdx.files.internal("czcionka2.fnt"));
        font1 = new BitmapFont(Gdx.files.internal("czcionka_rank.fnt"));
        prefs = Gdx.app.getPreferences("ranking");
        end = new Texture("back1.png");
        json = new Json();
        values= new int[]{};


    }
    public void render(SpriteBatch batch) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        font.setColor(Color.WHITE);
        font1.setColor(Color.WHITE);
        batch.draw(end,Constants.START_W.x/Constants.MENU_SIZE*viewport.getWorldWidth()-Constants.SZER/2, viewport.getWorldHeight() / 3,Constants.SZER,Constants.WYS/3);

        int name = prefs.getInteger("score");
        font.draw(batch, "KONIEC", viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 1.75f, 0, Align.center, false);
        font1.draw(batch,"Wynik: "+name , viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0, Align.center, false);
        batch.end();

    }
    public void render(ShapeRenderer renderer) {

        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.set(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(new Color(0, 0, 0, 1f));
        renderer.rect(Constants.START_W.x / Constants.MENU_SIZE * viewport.getWorldWidth() / 2,
                Constants.START_W.y / Constants.MENU_SIZE * viewport.getWorldHeight() / 2,
                viewport.getWorldWidth() / 2,
                viewport.getWorldHeight() / 3);


    }
    public void addScore(){

        if(rank) {
            Gdx.app.log("End","tak");
            if(prefs.getString("values").isEmpty()){
                prefs.putString("values", json.toJson(values));

            }

            values = json.fromJson(int[].class, prefs.getString("values"));

            values = addElement(values, prefs.getInteger("score"));

            Gdx.app.log("Game", values + "");
            prefs.putString("values", json.toJson(values));


            if (prefs.getInteger("score") > prefs.getInteger("best")) {
                prefs.putInteger("best", prefs.getInteger("score"));

            }
            prefs.flush();
            rank=false;
        }
    }
    static int[] addElement(int[] a, int e) {
        a  = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = e;
        return a;
    }
}
