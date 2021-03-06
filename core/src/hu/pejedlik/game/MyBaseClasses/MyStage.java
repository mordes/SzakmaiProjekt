package hu.pejedlik.game.MyBaseClasses;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Graph.SituationParameterScreen;
import hu.pejedlik.game.MyGdxGame;
import hu.pejedlik.game.Settings.SettingsScreen;


/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyStage extends Stage implements InitableInterface {
    public final MyGdxGame game;
    protected OneSpriteStaticActor settingsButton;

    public MyStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch);
        this.game = game;
        setCameraResetToCenterOfScreen();
        init();
    }

    public void addBackEventStackListener()    {
        addListener(new InputListener() {

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                //System.out.println("Lenyomott gomb: "+Input.Keys.toString(keycode));
                if(keycode == Input.Keys.BACK || keycode == Input.Keys.BACKSPACE) {
                    if (game.getScreen() instanceof SituationParameterScreen) {
                        Assets.unloadReadImages();

                    }
                    game.dispose();
                    game.setScreenBackByStackPop();
                }
                return true;
            }
        });
    }

    public Actor getLastAdded() {
        return getActors().get(getActors().size-1);
    }

    public void setCameraZoomXY(float x, float y, float zoom)
    {
        OrthographicCamera c = (OrthographicCamera)getCamera();
        c.zoom=zoom;
        c.position.set(x,y,0);
        c.update();
    }

    private float cameraTargetX = 0;
    private float cameraTargetY = 0;
    private float cameraTargetZoom = 0;
    private float cameraMoveSpeed = 0;

    public void setCameraMoveToXY(float x, float y, float zoom, float speed)
    {
        cameraTargetX = x;
        cameraTargetY = y;
        cameraTargetZoom = zoom;
        cameraMoveSpeed = speed;
    }

    public void setCameraResetToCenterOfScreen()
    {
        OrthographicCamera c = (OrthographicCamera)getCamera();
        ExtendViewport v = (ExtendViewport)getViewport();
        c.setToOrtho(false, getViewport().getWorldWidth(), getViewport().getWorldHeight());
        c.translate((v.getWorldWidth() -  v.getMinWorldWidth() / 2) < 0 ? 0 : -((v.getWorldWidth()  - v.getMinWorldWidth()) / 2),
                ((v.getWorldHeight()  - v.getMinWorldHeight()) / 2) < 0 ? 0 : -((v.getWorldHeight() - v.getMinWorldHeight()) / 2));
        c.update();
    }
    public void setCameraResetToLeftBottomOfScreen(){
        OrthographicCamera c = (OrthographicCamera)getCamera();
        Viewport v = getViewport();
        setCameraZoomXY(v.getWorldWidth()/2, v.getWorldHeight()/2,1);
        c.update();

    }

    public void resize(int screenWidth, int screenHeight){
        getViewport().update(screenWidth, screenHeight, true);
        resized();
    }

    protected void resized(){
        setCameraResetToCenterOfScreen();
    };

    @Override
    public void act(float delta) {
        super.act(delta);
        OrthographicCamera c = (OrthographicCamera) getCamera();
        if (cameraTargetX != c.position.x || cameraTargetY != c.position.y || cameraTargetZoom != c.zoom) {
            if (Math.abs(c.position.x - cameraTargetX) < cameraMoveSpeed * delta) {
                c.position.x = (c.position.x + cameraTargetX) / 2;
            } else {
                if (c.position.x < cameraTargetX) {
                    c.position.x += cameraMoveSpeed * delta;
                } else {
                    c.position.x -= cameraMoveSpeed * delta;
                }
            }
            if (Math.abs(c.position.y - cameraTargetY) < cameraMoveSpeed * delta) {
                c.position.y = (c.position.y + cameraTargetY) / 2;
            } else {
                if (c.position.y < cameraTargetY) {
                    c.position.y += cameraMoveSpeed * delta;
                } else {
                    c.position.y -= cameraMoveSpeed * delta;
                }
            }
            if (Math.abs(c.zoom - cameraTargetZoom) < cameraMoveSpeed * delta) {
                c.zoom = (c.zoom + cameraTargetZoom) / 2;
            } else {
                if (c.zoom < cameraTargetZoom) {
                    c.zoom += cameraMoveSpeed * delta;
                } else {
                    c.zoom -= cameraMoveSpeed * delta;

                }
                c.update();

            }

        }
    }

    @Override
    public void init() {
        settingsButton = new OneSpriteStaticActor(Assets.manager.get(Assets.SETTINGSBUTTON_TEXTURE));
        settingsButton.addListener(new ClickListener()
        {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(game),true);
                super.clicked(event, x, y);
            }
        });
        settingsButton.setSize(getViewport().getWorldWidth() / 13,getViewport().getWorldWidth() / 13);
        settingsButton.setPosition(getViewport().getWorldWidth() - settingsButton.getWidth(),
                getViewport().getWorldHeight() - settingsButton.getHeight());
        settingsButton.setZIndex(1);
        this.addActor(settingsButton);
    }

    public OneSpriteStaticActor getSettingsButton() {
        return settingsButton;
    }

    public void setSettingsButton(OneSpriteStaticActor settingsButton) {
        this.settingsButton = settingsButton;
    }

    public void setSettingsButtonRemove() {
        this.settingsButton.remove();
    }
}
