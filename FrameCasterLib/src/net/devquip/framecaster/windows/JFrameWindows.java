package net.devquip.framecaster.windows;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class JFrameWindows extends JFrame {
	
	public static void createJFrameWindows(int width, int height, String title, Container classPanel, boolean resizableFrame/*, String urlP*/) {
		
		JFrame mainFrame = new JFrame();
		ImageIcon icon;
		icon = new ImageIcon("/net/devquip/framecaster/res/main.png");
		
		mainFrame.setTitle(title);
		mainFrame.setSize(width, height);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setContentPane(classPanel);
		mainFrame.setResizable(resizableFrame);
		mainFrame.setIconImage(icon.getImage());
		
		mainFrame.setVisible(true);
	}
}