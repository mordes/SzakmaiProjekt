package hu.pejedlik.game.Graph;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import hu.pejedlik.game.MyBaseClasses.MyScreen;
import hu.pejedlik.game.MyGdxGame;

/**
 * Created by Felhasznalo on 2017. 10. 28..
 */

public class SituationParameterScreen extends MyScreen {
    protected SituationParameterStage param;
    private InputMultiplexer im;
    public SituationParameterScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        param.act(delta);
        param.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

    }
    @Override
    public void dispose() {
        param.dispose();
        super.dispose();
    }

    @Override
    public void init() {
        im = new InputMultiplexer();
        param  = new SituationParameterStage(new ExtendViewport(1280,720,new OrthographicCamera(1280,720)), spriteBatch, game,im);
        im.addProcessor(param);
        Gdx.input.setInputProcessor(im);
        setBackGroundColor(1f, 1f, 1f);
    }
}
