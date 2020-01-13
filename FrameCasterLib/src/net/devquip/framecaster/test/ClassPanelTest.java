package net.devquip.framecaster.test;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ClassPanelTest extends JPanel {
	
	static JLabel labelTest = new JLabel("ts");
	
	public ClassPanelTest() {
		this.setLayout(null);
		
		labelTest.setBounds(100, 200, 100, 200);
		this.add(labelTest);
	}
}