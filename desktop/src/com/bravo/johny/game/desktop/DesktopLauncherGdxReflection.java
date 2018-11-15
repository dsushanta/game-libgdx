package com.bravo.johny.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bravo.johny.game.GdxReflectionSample;

public class DesktopLauncherGdxReflection {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GdxReflectionSample(), config);
	}
}
