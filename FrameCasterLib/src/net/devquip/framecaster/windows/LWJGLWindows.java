package net.devquip.framecaster.windows;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public class LWJGLWindows {
	
	public static void createLwjglWindows(int width, int height, String title) {
		try {
			Display.setTitle(title);
			Display.setDisplayMode(new org.lwjgl.opengl.DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
}