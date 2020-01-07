package net.devquip.framecaster;

import org.lwjgl.opengl.Display;

import net.devquip.framecaster.calculs.Calculs;
import net.devquip.framecaster.test.ClassPanelTest;
import net.devquip.framecaster.windows.JFrameWindows;
import net.devquip.framecaster.windows.LWJGLWindows;

public class MainLib {
	
	public static final int FRAME_CAP = 60;
	boolean running = false;
	
	LWJGLWindows lwW;
	JFrameWindows jfW;
	
	public void start() {
		running = true;
		run();
	}
	
	public void update() {
		
	}
	
	public void loop() {
		long tickBeforeTime = System.nanoTime();
		long frameBeforeTime = System.nanoTime();
		double tickNS = 1000000000.0 / 60.0;
		double frameNS = 1000000000.0 / (double) FRAME_CAP;
		int frames = 0;
		int ticks = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			running = !Display.isCloseRequested();
			
			long now = System.nanoTime();
			if(now - tickBeforeTime > tickNS) {
				update();
				ticks++;
				tickBeforeTime += tickNS;
			} 
			
			if(now - frameBeforeTime > frameNS){
				Display.update();
				frames++;
				frameBeforeTime += frameNS;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void run() {
		loop();
		System.exit(0);
	}
	
	public static void main(String[] args) {
	//	LWJGLWindows.createLwjglWindows(700, 700, "test", true);
		MainLib m = new MainLib();
	//	m.start();
		JFrameWindows.createJFrameWindows(700, 700, "test", new ClassPanelTest(), true);
		Calculs.calculMul3(2, 5, 4);
	}
}