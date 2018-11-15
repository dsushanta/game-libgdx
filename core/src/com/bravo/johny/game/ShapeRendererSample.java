package com.bravo.johny.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bravo.johny.game.Utils.GdxUtils;
import com.bravo.johny.game.common.SampleBase;
import com.bravo.johny.game.common.SampleInfo;

public class ShapeRendererSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ShapeRendererSample.class);

    private static final float WORLD_WIDTH = 20f;
    private static final float WORLD_HEIGHT = 40f;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private boolean drawGrid = true;
    private boolean drawCircles = true;
    private boolean drawRectangles = true;
    private boolean drawPoints = true;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        // NOTE : not centering camera
        viewport.update(width, height);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        renderer.setProjectionMatrix(camera.combined);  // this is to inform our renderer about camera config

        if(drawGrid)
            drawGrid();
    }

    private void drawGrid() {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);

        int world_width = (int) WORLD_WIDTH;
        int world_height = (int) WORLD_HEIGHT;

        for (int x=-world_width; x<world_width; x++) {
            renderer.line(x, -world_height, x, world_height);
        }
        for (int y=-world_height; y<world_height; y++) {
            renderer.line(-world_width, y, world_height, y);
        }

        renderer.setColor(Color.RED);
        renderer.line(0, -world_height, 0, world_height);
        renderer.setColor(Color.GREEN);
        renderer.line(-world_width, 0, world_width, 0);

        renderer.end();
    }


    @Override
    public void dispose() {
        renderer.dispose();
    }
}
