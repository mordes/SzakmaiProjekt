package hu.pejedlik.game.MyBaseClasses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import hu.pejedlik.game.MyGdxGame;


/**
 * Created by tuskeb on 2016. 10. 01..
 */
public class MyLabel extends Label implements InitableInterface{


    public MyLabel(CharSequence text, Skin skin) {
        super(text, skin);
        init();
    }

    @Override
    public void init() {

    }

    protected float elapsedtime =0;

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedtime += delta;

        //setFontScale(Math.abs((float)Math.sin(elapsedtime*2f))/2f+0.8f);
    }

    @Override
    public String toString() {
        return getText().toString();
    }
}
