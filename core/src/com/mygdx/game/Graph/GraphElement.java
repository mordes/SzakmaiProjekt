package com.mygdx.game.Graph;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Hegedüs Csongor on 10/28/2017.
 */

public class GraphElement extends Actor {

    public int row, coll;
    private String situationId;


    public GraphElement(String s, int row,int coll, float worldWidth, float worldHeight) {
        situationId = s.split(" ")[0];
        this.row = row;
        this.coll = coll;
        this.setWidth(50);
        this.setHeight(50);
        this.setX((worldWidth / 2 + 35) + coll * 60); // approximately
        this.setY((worldHeight / 2 - 25)- row * 60);

    }

    public String getSituationId() {
        return situationId;
    }

    public void setSituationId(String situationId) {
        this.situationId = situationId;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public String toString() {
        return situationId;
    }
}
