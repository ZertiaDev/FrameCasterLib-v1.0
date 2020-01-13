package net.devquip.framecaster.windows.lwjglManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class WindowManager {
	
	public static final int FRAME_CAP = 60;
	static boolean running = false;
	
	public static void render() {
		int x = 16; 
		int y = 16; 
		int size = 16;
	/*	glBegin(GL_QUADS);
			glColor3f(0.5f, 1.0f, 0.8f);
			glVertex2f(x, y);
			glVertex2f(x + size, y);
			glVertex2f(x + size, y + size);
			glVertex2f(x, y + size);
		glEnd();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); */
	//	drawQuads(32, 32, 32);
	}
	
	public static void loop() {
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
				render();
				Display.update();
				frames++;
				frameBeforeTime += frameNS;
			}
			
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
			String texte_date = sdf.format(new Date());
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("[" + texte_date + "] " + ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void start() {
		running = true;
		run();
	}
	
	public static void update() {
			if(Keyboard.isKeyDown(Keyboard.KEY_E) || Keyboard.isKeyDown(Keyboard.KEY_Z)) { System.out.println("Update"); }
	}
	
	public static void run() {
		loop();
		System.exit(0);
	}
	
	public static File log =  new File("/logs/");
	public static void writeLog(String msg){
	 
	        Calendar c = Calendar.getInstance();
	        int heure = c.get(Calendar.HOUR_OF_DAY);
	        int minutes = c.get(Calendar.MINUTE);
	        int secondes = c.get(Calendar.SECOND);
	 
	        String h = ThereNeedsToZero(heure);
	        String m = ThereNeedsToZero(minutes);
	        String s = ThereNeedsToZero(secondes);
	        String prefix = "[" + h + ":" + m + ":" + s + "] ";
	        FileWriter fw = null;
	        try {
	            fw = new FileWriter(log, true);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	        
	        BufferedWriter output = new BufferedWriter(fw);
	         
	        try {
	            output.write(prefix + msg + "\n");
	            output.flush();
	            output.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public static String ThereNeedsToZero(int i){
		String a = Integer.toString(i);
	    if(i < 10){
	    	a = 0 + a;
	    }
	    return a;
	}
}