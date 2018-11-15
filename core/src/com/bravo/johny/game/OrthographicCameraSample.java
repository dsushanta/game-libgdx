package com.bravo.johny.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bravo.johny.game.Utils.GdxUtils;
import com.bravo.johny.game.common.SampleBase;
import com.bravo.johny.game.common.SampleInfo;

public class OrthographicCameraSample extends SampleBase{

    private static final Logger log = new Logger(GdxModuleInfoSample.class.getCanonicalName(), Logger.DEBUG);
    private static final float WORLD_WIDTH = 10.8f;     // world units
    private static final float WORLD_HEIGHT = 7.2f;     // world units
    private static final float CAMERA_SPEED = 2.0f;     // world units
    private static final float CAMERA_ZOOM_SPEED = 2.0f;       // world units

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Texture texture;

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(OrthographicCameraSample.class);

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("raw/level-bg.png"));
    }

    @Override
    public void render() {
        queryInput();

        GdxUtils.clearScreen();

        // rendering
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        draw();
        batch.end();
    }

    private void queryInput() {
        // Delta-Time is time passed between two frames
        float deltaTime = Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.position.x -= CAMERA_SPEED * deltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.position.x += CAMERA_SPEED * deltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.position.y += CAMERA_SPEED * deltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.position.y -= CAMERA_SPEED * deltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            log.debug("Position : "+camera.position);
            log.debug("Zoom : "+camera.zoom);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.PAGE_UP)) {
            camera.zoom -= CAMERA_ZOOM_SPEED * deltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN)) {
            camera.zoom += CAMERA_ZOOM_SPEED * deltaTime;
        }

        camera.update();
    }

    private void draw() {
        batch.draw(texture,
                0,0,
                WORLD_WIDTH, WORLD_HEIGHT
        );
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}
