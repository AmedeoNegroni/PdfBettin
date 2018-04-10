package it.ariadne;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class Finestra extends JFrame{

	private DetailPanel panel;

	public Finestra() {
		
		setLayout(new BorderLayout());
		
		panel = new DetailPanel();
		
		Container c = getContentPane();
		
		c.add(panel, BorderLayout.CENTER);
	
	}
	
}
