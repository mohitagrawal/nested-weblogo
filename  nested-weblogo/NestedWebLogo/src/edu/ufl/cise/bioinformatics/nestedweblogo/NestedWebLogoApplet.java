package edu.ufl.cise.bioinformatics.nestedweblogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;


public class NestedWebLogoApplet extends JApplet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SequenceLogoDrawer display;
	
	@Override	
	public void init() {
		
		display = new SequenceLogoDrawer();
		
		display.init();		

		display.setBackground(Color.white);
		
		display.validate();
		
		getContentPane().add(display);
		
		setSize(800, 800);
//		setMinimumSize(new Dimension(100,100));
//		setMaximumSize(new Dimension(500,500));
	}
	
	public static void main(String[] argv) {

		JFrame frame = new JFrame("NestedWebLogo");
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		SequenceLogoDrawer logoDrawer = new SequenceLogoDrawer();
		logoDrawer.init();

		frame.getContentPane().add(logoDrawer);

		frame.setVisible(true);
		
//		frame.setSize(600, 600);
	}

}
