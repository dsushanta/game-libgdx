package com.bravo.johny.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bravo.johny.game.Utils.GdxUtils;
import com.bravo.johny.game.common.SampleBase;
import com.bravo.johny.game.common.SampleInfo;

public class InputListeningSample extends SampleBase {

	private static final Logger log = new Logger(InputListeningSample.class.getSimpleName(), Logger.DEBUG);
	private static final int MAX_MESSAGE_COUNT = 15;
	private final Array<String> messages = new Array<String>();

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private BitmapFont font;

	public static final SampleInfo SAMPLE_INFO = new SampleInfo(InputListeningSample.class);

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		camera = new OrthographicCamera();
		viewport = new FitViewport(1080, 780, camera);
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	@Override
	public void render() {

		GdxUtils.clearScreen();

		batch.setProjectionMatrix(camera.combined);    // tells the batch which camera to use
		batch.begin();
		draw();
		batch.end();
	}

	private void draw() {
		for (int i=0; i<messages.size; i++) {
			font.draw(batch,
					messages.get(i),
					20f,
					720 - 40f * (i+1)
			);
		}
	}

	private void addMessage(String message) {
		messages.add(message);
		if(messages.size > MAX_MESSAGE_COUNT)
			messages.removeIndex(0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		String message = "KeyDown : Keycode = "+keycode;
		log.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		String message = "KeyUp : Keycode = "+keycode;
		log.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		String message = "KeyTyped : Keycode = "+character;
		log.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		String message = "TouchDown : ScreenX = "+screenX+" ScreenY = "+screenY;
		log.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		String message = "TouchUp : ScreenX = "+screenX+" ScreenY = "+screenY;
		log.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		String message = "TouchDragged : ScreenX = "+screenX+" ScreenY = "+screenY;
		log.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		String message = "mouseMoved : ScreenX = "+screenX+" ScreenY = "+screenY;
		log.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		String message = "Scrolled : Amount = "+amount;
		log.debug(message);
		addMessage(message);
		return true;
	}
}
