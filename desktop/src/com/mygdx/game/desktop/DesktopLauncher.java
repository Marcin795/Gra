package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Gra;
import net.spookygames.gdx.nativefilechooser.desktop.DesktopFileChooser;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Gra";
		config.height = 800;
		config.width = 600;
		new LwjglApplication(new Gra(new DesktopFileChooser()), config);
	}
}
