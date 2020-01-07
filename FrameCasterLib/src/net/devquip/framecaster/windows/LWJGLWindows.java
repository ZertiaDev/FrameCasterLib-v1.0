package net.devquip.framecaster.windows;

import java.nio.ByteBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public class LWJGLWindows {
	
	public static void createLwjglWindows(int width, int height, String title, boolean resizable) {
		try {
			Display.setTitle(title);
			Display.setDisplayMode(new org.lwjgl.opengl.DisplayMode(width, height));
			Display.setResizable(resizable);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
}