package com.bravo.johny.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bravo.johny.game.Utils.GdxUtils;
import com.bravo.johny.game.common.CustomActor;
import com.bravo.johny.game.common.SampleBase;
import com.bravo.johny.game.common.SampleInfo;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by bittu on 22,December,2018
 */
public class ActionsSample extends SampleBase {

    private static final Logger log = new Logger(ActionsSample.class.getCanonicalName(), Logger.DEBUG);
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ActionsSample.class);

    private static final float WORLD_WIDTH = 1080f;
    private static final float WORLD_HEIGHT = 720f;

    private CustomActor customActor;
    private Viewport viewport;
    private Stage stage;
    private Texture texture;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        stage = new Stage(viewport);
        texture = new Texture(Gdx.files.internal("raw/custom-actor.png"));
        customActor = new CustomActor(new TextureRegion(texture));
        customActor.setSize(160, 80);
        customActor.setPosition(
                (WORLD_WIDTH-customActor.getWidth())/2f,
                (WORLD_HEIGHT-customActor.getHeight())/2f
        );
        customActor.setRotation(90f);
        stage.addActor(customActor);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        customActor.clearActions();
        if(keycode == Input.Keys.NUM_1)
            customActor.addAction(rotateBy(-90f));
        else if(keycode == Input.Keys.NUM_2)
            customActor.addAction(fadeOut(2f));
        else if(keycode == Input.Keys.NUM_3) {
            Action action = fadeIn(2f);
            customActor.addAction(action);
        }
        else if(keycode == Input.Keys.NUM_4)
            customActor.addAction(scaleTo(1.5f, 1.5f, 3f));
        else if(keycode == Input.Keys.NUM_5)
            customActor.addAction(scaleTo(0.5f, 0.5f, 3f));
        else if(keycode == Input.Keys.NUM_6)
            customActor.addAction(moveBy(2f, 0.5f, 2f));
        else if(keycode == Input.Keys.NUM_7) {
            Action action = sequence(
                    fadeOut(1.5f),
                    fadeIn(1f)
            );
            customActor.addAction(action);
        }
        else if(keycode == Input.Keys.NUM_8) {
            Action action = parallel(
                    moveTo(200f, 200f, 2f),
                    rotateBy(180f, 2f)
            );
            customActor.addAction(action);
        }

        return true;
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
