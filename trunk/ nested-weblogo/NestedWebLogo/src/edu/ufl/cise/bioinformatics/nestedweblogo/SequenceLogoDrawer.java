package edu.ufl.cise.bioinformatics.nestedweblogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.WeblogoColumn;

public class SequenceLogoDrawer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String words = "Valour fate kinship darkness";
	String java = "Java TM";

	ArrayList<WeblogoColumn> columnList = new ArrayList<WeblogoColumn>();
	
	/**
	 * Inits the.
	 */
	public void init(){
		
		WeblogoColumn webLogo = new WeblogoColumn();

		LinkedHashMap<String, Float> charactersMap = new LinkedHashMap<String, Float>();
		
		charactersMap.put("A", 1.3f);
		charactersMap.put("C", 2.5f);
		charactersMap.put("G", 1.2f);
		charactersMap.put("T", 9.5f);
		
		webLogo.setCharactersMap(charactersMap);
		
		columnList = new ArrayList<WeblogoColumn>();
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		columnList.add(webLogo);
		
	}	
	
	@Override
	public void paint(Graphics g) {

		super.paintComponent(g);

		/*Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

		AttributedString as2 = new AttributedString("A");

		as2.addAttribute(TextAttribute.TRANSFORM,AffineTransform.getScaleInstance(1, 10));
		
		g2d.drawString(as2.getIterator(), 0, getHeight()-80);*/
		
		Graphics2D graphics2DObject = (Graphics2D) g;
		
		drawWebLogo(graphics2DObject, columnList, 50, getHeight()-50);
		
	}
	
	private void drawWebLogo(Graphics2D graphics2DObject, ArrayList<WeblogoColumn> columnList, int startingPositionX, int maximumHeight){
		for (WeblogoColumn weblogoColumn : columnList) {
			drawColumn(graphics2DObject, weblogoColumn, startingPositionX, maximumHeight);
			startingPositionX += 10;
		}
	}
	
	private void drawColumn(Graphics2D graphics2DObject, WeblogoColumn column, int x, int maxHeight){
		
		Map<String, Float> charactersInColumnMap = column.getCharactersMap();
		
		Set<String> characters = charactersInColumnMap.keySet();

		int height = maxHeight;
		
		for (String character : characters) {
			
			float characterHeight = charactersInColumnMap.get(character);
			
			AffineTransform attributeTransform = AffineTransform.getScaleInstance(1, characterHeight);
			
			drawChar(graphics2DObject, attributeTransform, character, x, height);
			
			height -= characterHeight * 10;
			
		}
	}
	
	private void drawChar(Graphics2D graphics2DObject, AffineTransform attributeTransform , String ch, int x, int y){
		
		AttributedString attributeString = new AttributedString(ch);
		
		attributeString.addAttribute(TextAttribute.TRANSFORM,attributeTransform);
		
		graphics2DObject.setColor(Color.BLACK);
		
		if(ch.equals("A")){
			graphics2DObject.setColor(Color.RED);	
		}
		
		if(ch.equals("T")){
			graphics2DObject.setColor(Color.BLUE);	
		}
		
		if(ch.equals("R")){
			graphics2DObject.setColor(Color.BLACK);	
		}
		
		if(ch.equals("C")){
			graphics2DObject.setColor(Color.CYAN);
		}
		
		graphics2DObject.drawString(attributeString.getIterator(), x, y);

		
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Text attributes");
		
		SequenceLogoDrawer charTest = new SequenceLogoDrawer();
		charTest.init();
		
		frame.add(charTest);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(620, 190);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
