package net.devquip.framecaster;

import net.devquip.framecaster.render.Render;
import net.devquip.framecaster.windows.LWJGLWindows;
import net.devquip.framecaster.windows.lwjglManager.WindowManager;

public class MainLib {
	
	public static void main(String[] args) {
		LWJGLWindows.createLwjglWindows(1080, 720, "test", false);
		MainLib m = new MainLib();
		WindowManager wM = new WindowManager();
		
		wM.start();
	//	JFrameWindows.createJFrameWindows(700, 700, "test", new ClassPanelTest(), true);
	}
}