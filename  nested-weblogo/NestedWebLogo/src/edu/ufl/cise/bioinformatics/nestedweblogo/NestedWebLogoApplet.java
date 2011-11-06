package edu.ufl.cise.bioinformatics.nestedweblogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;


public class NestedWebLogoApplet extends JApplet {
	
	SequenceLogoDrawer display;
	
	@Override
	public void init() {
//		super.init();
		display = new SequenceLogoDrawer();
		display.init();
		display.setBackground(Color.white);
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

		applet.init();

		frame.setSize(550, 400);
		frame.setVisible(true);
	}

}
