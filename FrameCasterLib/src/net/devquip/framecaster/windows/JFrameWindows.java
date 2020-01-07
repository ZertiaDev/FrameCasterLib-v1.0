package net.devquip.framecaster.windows;

import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class JFrameWindows extends JFrame {
	
	public static void createLWJGLWindows(int width, int height, String title, Container classPanel, boolean resizableFrame, String urlP) {
		
		JFrame mainFrame = new JFrame();
		ImageIcon icon;
		icon = new ImageIcon(urlP);
		
		mainFrame.setTitle(title);
		mainFrame.setSize(width, height);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setContentPane(classPanel);
		mainFrame.setResizable(resizableFrame);
		mainFrame.setIconImage(icon.getImage());
		
		mainFrame.setVisible(true);
	}
}