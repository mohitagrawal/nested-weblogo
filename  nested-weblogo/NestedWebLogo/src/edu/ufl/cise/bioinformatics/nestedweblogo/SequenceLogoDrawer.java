package edu.ufl.cise.bioinformatics.nestedweblogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.WeblogoColumn;
import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.WeblogoDataStructure;

public class SequenceLogoDrawer extends JPanel {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private static final int X_INTERVAL = 15;
//	private static final int Y_INTERVAL = 20;
	
	String words = "Valour fate kinship darkness";
	String java = "Java TM";

	ArrayList<WeblogoColumn> columnList = new ArrayList<WeblogoColumn>();
	
	private enum LineType {
		HORIZONTAL, VERTICAL
	}
	
	
	
	/**
	 * Inits the.
	 */
	public void init(){
		
		String seq1 = "AGTC";
        String seq2 = "ATGC";
        String seq3 = "TAGT";
        
        String[] seqarray = new String[3];
        seqarray[0] = seq1;
        seqarray[1] = seq2;
        seqarray[2] = seq3;
		
		WeblogoDataStructure weblogoData = new WeblogoDataStructure();
		weblogoData.calculateHeight(seqarray);
		
		WeblogoColumn webLogo = new WeblogoColumn();

		TreeMap<String, Double> charactersMap = new TreeMap<String, Double>();
		
	/*	charactersMap.put("A", 9.4);
		charactersMap.put("C", 1.3);
		charactersMap.put("G", 19.1);
		charactersMap.put("T", 2.5);*/
		
		charactersMap.put("A", 1.0);
		charactersMap.put("C", 2.0);
		charactersMap.put("G", 3.0);
		charactersMap.put("T", 4.0);
		
		webLogo.setCharactersMap(charactersMap);
		
		columnList = weblogoData.getColumnList();
		
//		columnList = new ArrayList<WeblogoColumn>();
		
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
		
		drawWebLogo(graphics2DObject, columnList, 60, getHeight()-42, getWidth()-50);
		drawXYAxis(graphics2DObject, 60, 40 , 60 , getHeight()-42,LineType.VERTICAL);
		drawXYAxis(graphics2DObject, 40, getHeight()-42 , getWidth()-20 , getHeight()-42,LineType.HORIZONTAL);
	}
	
	private void drawXYAxis(Graphics2D graphics2DObject, int startingPositionX, int startingPositionY, int maxX, int maxY, LineType type){
		
		Line2D line = new Line2D.Double(startingPositionX,startingPositionY,maxX,maxY);
		
		graphics2DObject.draw(line);
		
		if(type == LineType.VERTICAL){
			int position = 10;
			
			/*for(int i = maxY ; i>startingPositionY ; i-=10, position+=10){
				Line2D smallHorizontalLine = new Line2D.Double(startingPositionX-2,i,startingPositionX+2,i);				
				graphics2DObject.draw(smallHorizontalLine);
				drawChar(graphics2DObject, null, position+"", startingPositionX-30, i);
				
			}*/
		}else if(type == LineType.HORIZONTAL){
			
			int position = 1;
			
			/*for(int i=startingPositionX+X_INTERVAL ; i<maxX && position<=columnList.size(); i+=X_INTERVAL , position++){
				Line2D smallVerticalLine = new Line2D.Double(i,startingPositionY-2,i,startingPositionY+2);				
				graphics2DObject.draw(smallVerticalLine);
				drawChar(graphics2DObject, null, position+"", i, startingPositionY+15);
				
			}*/
		}
		
		
	}
		
	
	private void drawWebLogo(Graphics2D graphics2DObject, ArrayList<WeblogoColumn> columnList, float startingPositionX, int maximumHeight, int maximumWidth){
		for (WeblogoColumn weblogoColumn : columnList) {
			drawColumn(graphics2DObject, weblogoColumn, startingPositionX, maximumHeight, maximumWidth/(columnList.size() * 9) );
//			startingPositionX += X_INTERVAL*4;
			startingPositionX  += (maximumWidth/(columnList.size()));
			
			System.out.println("startingPositionX: "+startingPositionX);
		}
	}
	
	private void drawColumn(Graphics2D graphics2DObject, WeblogoColumn column, float xPosition, float maxHeight, float horizontalScalingFactor){
		
		Map<String, Double> charactersInColumnMap = column.getCharactersMap();
		
		Set<String> characters = charactersInColumnMap.keySet();

		float height = maxHeight;
		
		for (String character : characters) {
			
			double characterHeight = charactersInColumnMap.get(character);
			
			AffineTransform attributeTransform = AffineTransform.getScaleInstance(horizontalScalingFactor, characterHeight);
			
			drawChar(graphics2DObject, attributeTransform, character, xPosition, height);
			
			height -= characterHeight * 8.6;			
		
			System.out.println("X: "+xPosition);
			System.out.println("Y: "+height);
			
		}
	}
	
	private void drawChar(Graphics2D graphics2DObject, AffineTransform attributeTransform , String ch, float x, float y){
		
		AttributedString attributeString = new AttributedString(ch);
		
		if(attributeTransform != null){
			attributeString.addAttribute(TextAttribute.TRANSFORM,attributeTransform);
		}		
		
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
		charTest.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
		Font font = charTest.getFont(); 
		System.out.println("font size: "+font.getSize2D());
		charTest.init();
		
		frame.add(charTest);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(620, 480);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
