package net.devquip.framecaster.render;

import static org.lwjgl.opengl.GL11.*;

public class Render {
	
	public static void drawQuads(int x, int y, int size) {
		glBegin(GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x + size, y);
			glVertex2f(x + size, y + size);
			glVertex2f(x, y + size);
		glEnd();
	}
}