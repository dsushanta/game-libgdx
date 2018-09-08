package com.bravo.johny.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bravo.johny.game.common.SampleBase;
import com.bravo.johny.game.common.SampleInfo;

import java.util.Arrays;

public class GdxReflectionSample extends SampleBase {

	private static final Logger log = new Logger(GdxModuleInfoSample.class.getCanonicalName(), Logger.DEBUG);

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private BitmapFont font;

	public static final SampleInfo SAMPLE_INFO = new SampleInfo(GdxReflectionSample.class);

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		camera = new OrthographicCamera();
		viewport = new FitViewport(1080, 780, camera);
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

		debugReflection(InputListeningSample.class);

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	@Override
	public void render() {


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

	private static void debugReflection(Class<?> clazz) {
		Field[] fields = ClassReflection.getDeclaredFields(clazz);
		Method[] methods = ClassReflection.getDeclaredMethods(clazz);

		log.debug("Class name = "+clazz.getName());

		log.debug("Field count = "+fields.length);
		for (Field field : fields)
			log.debug("Field name = "+field.getName()+" , Field Type = "+field.getType());

		log.debug("Method count = "+methods.length);
		for (Method method : methods)
			log.debug("Method name = "+method.getName()+" , Method parameter Type = "+ Arrays.asList(method.getParameterTypes()));
	}
}
