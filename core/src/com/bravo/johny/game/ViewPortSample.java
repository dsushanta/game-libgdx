package com.bravo.johny.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bravo.johny.game.Utils.GdxUtils;
import com.bravo.johny.game.common.SampleBase;
import com.bravo.johny.game.common.SampleInfo;

public class ViewPortSample extends SampleBase {

    private static final Logger log = new Logger(GdxModuleInfoSample.class.getCanonicalName(), Logger.DEBUG);
    private static final float WORLD_WIDTH = 800.0f;
    private static final float WORLD_HEIGHT = 600.0f;

    private OrthographicCamera camera;
    private Viewport currentViewPort;
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;

    private int currentViewPortIndex;
    private String currentViewPortName;


    private ArrayMap<String, Viewport> viewPorts = new ArrayMap<String, Viewport>();

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ViewPortSample.class);

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("raw/level-bg-small.png"));
        font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

        createViewPorts();
        selectNextViewPort();

        Gdx.input.setInputProcessor(this);
    }

    private void selectNextViewPort() {
        currentViewPortIndex = (currentViewPortIndex + 1) % viewPorts.size;
        currentViewPort = viewPorts.getValueAt(currentViewPortIndex);
        currentViewPort.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        currentViewPortName = viewPorts.getKeyAt(currentViewPortIndex);

        log.debug("Selected viewport : "+currentViewPortName);
    }

    private void createViewPorts() {
        viewPorts.put(StretchViewport.class.getSimpleName(),
                new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
        );
        viewPorts.put(FitViewport.class.getSimpleName(),
                new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
        );
        viewPorts.put(FillViewport.class.getSimpleName(),
                new FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
        );
        viewPorts.put(ScreenViewport.class.getSimpleName(),
                new ScreenViewport(camera)
        );
        viewPorts.put(ExtendViewport.class.getSimpleName(),
                new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
        );
        currentViewPortIndex = -1;
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        draw();
        batch.end();
    }

    private void draw() {
        batch.draw(texture, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        font.draw(batch, currentViewPortName, 50, 100);
    }

    @Override
    public void resize(int width, int height) {
        currentViewPort.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        font.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        selectNextViewPort();
        return true;
    }
}