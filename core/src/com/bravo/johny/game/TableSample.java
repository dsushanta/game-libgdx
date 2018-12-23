package com.bravo.johny.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bravo.johny.game.Utils.GdxUtils;
import com.bravo.johny.game.common.CustomActor;
import com.bravo.johny.game.common.SampleBase;
import com.bravo.johny.game.common.SampleInfo;

/**
 * Created by bittu on 22,December,2018
 */
public class TableSample extends SampleBase {

    private static final Logger log = new Logger(TableSample.class.getCanonicalName(), Logger.DEBUG);
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(TableSample.class);

    private static final float WORLD_WIDTH = 1080f;
    private static final float WORLD_HEIGHT = 720f;

    private Viewport viewport;
    private Stage stage;
    private Texture texture;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        stage = new Stage(viewport);
        texture = new Texture(Gdx.files.internal("raw/custom-actor.png"));

        initUI();
    }

    private void initUI() {
        Table table = new Table();
        table.defaults().space(20);

        for(int i=0; i<4; i++) {
            CustomActor customActor = new CustomActor(new TextureRegion(texture));
            customActor.setSize(180,60);
            table.add(customActor);
            table.row();
        }
        table.center();
        table.setFillParent(true);
        table.pack();

        stage.addActor(table);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();
        stage.act();  // This method is more like our update method in render() (obstacle avoid)
        stage.draw();    /// This is will draw actors
    }

    @Override
    public void dispose() {
        stage.dispose();
        texture.dispose();
    }
}
