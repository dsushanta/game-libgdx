package com.bravo.johny.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bravo.johny.game.common.SampleBase;
import com.bravo.johny.game.common.SampleInfo;

public class InputPollingSample extends SampleBase {

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private BitmapFont font;

	public static final SampleInfo SAMPLE_INFO = new SampleInfo(InputPollingSample.class);

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		camera = new OrthographicCamera();
		viewport = new FitViewport(1080, 780, camera);
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	@Override
	public void render() {

		// Clear screen
		Gdx.gl.glClearColor(0,0,0,1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);    // tells the batch which camera to use
		batch.begin();
		draw();
		batch.end();
	}

	private void draw() {
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		boolean rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);
		font.draw(batch,
				"Mouse/Touch : X = "+mouseX+" Y = "+mouseY,
				20f,
				720 - 20f);
		font.draw(batch,
				leftPressed ? "Left button was pressed" : "Left button was not pressed",
				20f,
				720 - 60f);
		font.draw(batch,
				rightPressed ? "Right button was pressed" : "Right button was not pressed",
				20f,
				720 - 100f);

		boolean wPressed = Gdx.input.isKeyPressed(Input.Keys.W);
		boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);

		font.draw(batch,
				wPressed ? "W was pressed" : "W was not pressed",
				20f,
				720 - 140f);
		font.draw(batch,
				sPressed ? "S was pressed" : "S was not pressed",
				20f,
				720 - 180f);
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
}
