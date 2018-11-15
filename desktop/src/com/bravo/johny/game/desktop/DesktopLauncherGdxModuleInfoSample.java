package com.bravo.johny.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bravo.johny.game.GdxModuleInfoSample;

public class DesktopLauncherGdxModuleInfoSample {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.out.println(Gdx.app);
		new LwjglApplication(new GdxModuleInfoSample(), config);
		System.out.println(Gdx.app);
	}
}
