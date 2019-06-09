package com.mygdx.game.util;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

/**
 * Przechowuje czasy kulek przed zapisaniem
 */
public class Level implements Json.Serializable{
    ArrayList<ArrayList<Float>> lanes;

    Level() {
        lanes = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            lanes.add(new ArrayList<>());
        }
    }

    @Override
    public void write(Json json) {
        for(int i = 0; i < 3; i++) {
            json.writeArrayStart(i+"");
            for(int j = 0; j < lanes.get(i).size(); j++) {
                json.writeValue(lanes.get(i).get(j));
            }
            json.writeArrayEnd();
        }
    }

    @Override
    public void read(Json json, JsonValue jsonData) {

    }
}
