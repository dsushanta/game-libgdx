package com.bravo.johny.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.bravo.johny.game.common.SampleBase;
import com.bravo.johny.game.common.SampleInfo;

public class GdxModuleInfoSample extends SampleBase {

	private static final Logger log = new Logger(GdxModuleInfoSample.class.getCanonicalName(), Logger.DEBUG);

	public static final SampleInfo SAMPLE_INFO = new SampleInfo(GdxModuleInfoSample.class);

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		log.debug("App : "+Gdx.app);
		log.debug("Audio : "+Gdx.audio);
		log.debug("Files : "+Gdx.files);
		log.debug("Graphics : "+Gdx.graphics);
		log.debug("Input : "+Gdx.input);
		log.debug("Net : "+Gdx.net);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
