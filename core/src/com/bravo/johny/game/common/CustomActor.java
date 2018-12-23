package com.bravo.johny.game.common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Logger;

/**
 * Created by bittu on 22,December,2018
 */
public class CustomActor extends Actor {

    private static final Logger log = new Logger(CustomActor.class.getCanonicalName(), Logger.DEBUG);
    private final TextureRegion region;

    public CustomActor(TextureRegion region) {
        this.region = region;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color);

        batch.draw(region,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );

        /*log.debug("getX : "+getX());
        log.debug("getY : "+getY());
        log.debug("getOriginalX : "+getOriginX());
        log.debug("getOriginalY : "+getOriginY());
        log.debug("getWidth : "+getWidth());
        log.debug("getHeight : "+getHeight());
        log.debug("getScaleX : "+getScaleX());
        log.debug("getScaleY : "+getScaleY());
        log.debug("getRotation : "+getRotation());*/
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
