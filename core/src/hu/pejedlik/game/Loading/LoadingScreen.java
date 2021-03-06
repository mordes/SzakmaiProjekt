package hu.pejedlik.game.Loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;

import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Menu.MenuScreen;
//import hu.pejedlik.game.Music.MusicClass;
import hu.pejedlik.game.MyBaseClasses.MyLabel;
import hu.pejedlik.game.Music.MusicControlClass;
import hu.pejedlik.game.MyBaseClasses.MyScreen;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;
import hu.pejedlik.game.MyGdxGame;

import static hu.pejedlik.game.GlobalClasses.Assets.CHARS;
import static hu.pejedlik.game.GlobalClasses.Assets.SKIN;


public class LoadingScreen extends MyScreen {

	public static MusicControlClass music;
	private Stage stage;
	private OneSpriteStaticActor loadingImage, loadingLine;
	private float elapsedTime = 0;
	private MyLabel loadinglabel;


    public LoadingScreen(MyGdxGame game) {
		super(game);

		stage = new Stage();

		loadingImage = new OneSpriteStaticActor("load/jedlik.png");
		loadingImage.setSize(stage.getViewport().getWorldWidth(), (stage.getViewport().getWorldHeight() - 10));
		loadingImage.setPosition(stage.getViewport().getWorldWidth() / 2 - loadingImage.getWidth() / 2,
				stage.getViewport().getWorldHeight() / 2  - loadingImage.getHeight() / 2 + 10);
		stage.addActor(loadingImage);
		loadingImage.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		loadingLine = new OneSpriteStaticActor("load/blackline.png");
		loadingLine.setSize(0, loadingLine.getHeight() / 2);
		loadingLine.setPosition(0,0);
		stage.addActor(loadingLine);


		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
		p.color = Color.WHITE;
		p.size = (1280 * 32 / 1280);
		p.characters = CHARS;



    }
	BitmapFont bitmapFont = new BitmapFont();

    @Override
	public void show() {
	    Assets.manager.finishLoading();
		Assets.load();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		stage.act(delta);
		stage.draw();

		loadingLine.setWidth(Assets.manager.getProgress() * 100f * stage.getViewport().getWorldWidth() / 100);

		if (Assets.manager.update()) {
			Assets.afterLoaded();
			Assets.manager.get(SKIN).getFont("default-font").getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			//zene
			music = new MusicControlClass();
			music.playMusic();
			//zene vége
			game.setScreen(new MenuScreen(game),false);
		}

		elapsedTime+=delta;
	}

	@Override
	public void hide() {

	}

	@Override
	public void init() {
		setBackGroundColor(1f, 1f, 1f);
	}


}
