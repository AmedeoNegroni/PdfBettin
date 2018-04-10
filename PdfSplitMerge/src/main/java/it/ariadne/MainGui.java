package it.ariadne;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainGui {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame mainFrame = new Finestra();
				
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setSize(700, 400);
				mainFrame.setVisible(true);
			}
		});
		
	}

}
