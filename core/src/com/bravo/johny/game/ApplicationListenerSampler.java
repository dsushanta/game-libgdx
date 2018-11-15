package com.bravo.johny.game;

import com.bravo.johny.game.common.SampleBase;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;
import com.bravo.johny.game.common.SampleInfo;

public class ApplicationListenerSampler extends SampleBase {

	private static final Logger log = new Logger(ApplicationListener.class.getName(), Logger.DEBUG);
	private boolean interrupted = true;

	public static final SampleInfo SAMPLE_INFO = new SampleInfo(ApplicationListenerSampler.class);

	@Override
	public void create() {
		// used to initialize game and load resources
		Gdx.app.setLogLevel(Application.LOG_DEBUG);			// To enable logging which is disabled by default
		log.debug( "create()");
	}

	@Override
	public void resize(int width, int height) {
		// used to handle setting a new screen size
		log.debug( "resize() : Width X Height = "+width+" X "+height);
	}

	@Override
	public void render() {
		// used to update and render game elements. It is called 60 times per second
		if(interrupted) {
			log.debug("render()");
			interrupted = false;
		}
	}

	@Override
	public void pause() {
		// Used to save game state when it loses focus
		log.debug( "pause()");
		interrupted = true;
	}

	@Override
	public void resume() {
		// brings the game back from paused state and restores game state
		log.debug( "resume()");
		interrupted = true;
	}

	@Override
	public void dispose() {
		// used to free up resources and cleanup
		log.debug( "dispose()");
	}
}
