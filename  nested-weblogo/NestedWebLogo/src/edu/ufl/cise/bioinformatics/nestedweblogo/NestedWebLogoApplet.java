package edu.ufl.cise.bioinformatics.nestedweblogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


public class NestedWebLogoApplet extends JApplet {
	
	SequenceLogoDrawer display;
	
	@Override	
	public void init() {
		
		display = new SequenceLogoDrawer();
		
		display.init();		

		display.setBackground(Color.white);
		
		display.validate();
		
		getContentPane().add(display);
	}
	
	public static void main(String[] argv) {

		JFrame frame = new JFrame("NestedWebLogo");
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		JApplet applet = new NestedWebLogoApplet();

		frame.getContentPane().add(BorderLayout.CENTER, applet);

		frame.setVisible(true);
	}

}
