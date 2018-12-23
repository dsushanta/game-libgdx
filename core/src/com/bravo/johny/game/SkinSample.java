package com.bravo.johny.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
public class SkinSample extends SampleBase {

    private static final Logger log = new Logger(SkinSample.class.getCanonicalName(), Logger.DEBUG);
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(SkinSample.class);

    private static final float WORLD_WIDTH = 1080f;
    private static final float WORLD_HEIGHT = 720f;
    private static final String UI_SKIN = "ui/uiskin.json";

    private Viewport viewport;
    private Stage stage;
    private AssetManager assetManager;
    private Skin skin;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        assetManager = new AssetManager();
        assetManager.getLogger().setLevel(Logger.DEBUG);

        assetManager.load(UI_SKIN, Skin.class);
        assetManager.finishLoading();

        skin = assetManager.get(UI_SKIN);

        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        stage = new Stage(viewport);

        Gdx.input.setInputProcessor(stage);

        initUI();

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
        assetManager.dispose();
    }

    private void initUI() {
        Table table = new Table();
        table.defaults().pad(20);

        for(int i=0; i<4; i++) {
            TextButton textButton = new TextButton("Button "+i, skin);
            textButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    log.debug("Event = "+event+" : Actor = "+actor);
                }
            });
            table.add(textButton);
        }
        table.row();

        for(int i=0; i<2; i++) {
            CheckBox checkBox = new CheckBox("CheckBox "+i, skin, "custom");
            checkBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    log.debug("Event = "+event+" : Actor = "+actor);
                }
            });
            table.add(checkBox);
        }
        table.center();
        table.setFillParent(true);
        table.pack();

        stage.addActor(table);
    }
}
