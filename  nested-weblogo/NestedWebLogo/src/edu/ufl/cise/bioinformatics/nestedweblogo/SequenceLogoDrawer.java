package edu.ufl.cise.bioinformatics.nestedweblogo;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Stroke;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
import javax.swing.JScrollPane;

import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.WeblogoColumn;
import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.WeblogoDataStructure;

public class SequenceLogoDrawer extends JPanel implements MouseWheelListener {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private static final int X_INTERVAL = 15;
//	private static final int Y_INTERVAL = 20;
	
	String words = "Valour fate kinship darkness";
	String java = "Java TM";

	ArrayList<WeblogoColumn> columnList = new ArrayList<WeblogoColumn>();
	
	private int preferredSizeWidth = 800;
	private int preferredSizeHeight = 800;
	
	private enum LineType {
		HORIZONTAL, VERTICAL
	}
	
	
	
	/**
	 * Inits the.
	 */
	public void init(){
		this.setPreferredSize(new Dimension(800, 800));
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
		charactersMap.put("T", 1.0);
//		charactersMap.put("N", 4.0);

		
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
		

//		System.out.println("Painting: "+preferredSizeWidth + "   "+preferredSizeHeight);
		
		Graphics2D graphics2DObject = (Graphics2D) g;
		
		int bottomLeftXPosition = 60;
		int topLeftXPosition = 60;
		int topLeftYPosition = 42;
		int bottomLeftYPosition = getHeight()-42;
		int bottomRightYPosition = getHeight()-42;
		int bottomRightXPosition = getWidth()-100;
		int maximumAvailableWidth = getWidth()-100;
		float maximumAvailableHeight = getHeight()-50;
		
		drawWebLogo(graphics2DObject, columnList, bottomLeftXPosition , bottomLeftYPosition  , maximumAvailableWidth, maximumAvailableHeight);
		
		drawWebLogo(graphics2DObject, columnList, bottomLeftXPosition , maximumAvailableHeight/4+40 , maximumAvailableWidth/4 , maximumAvailableHeight/4);
		
		drawYAxis(graphics2DObject, bottomLeftXPosition, bottomLeftYPosition, topLeftXPosition, topLeftYPosition );
		
		drawXAxis(graphics2DObject, bottomLeftXPosition, bottomLeftYPosition , bottomRightXPosition , bottomRightYPosition);
								
	}
	
	private void drawBorder(Graphics2D graphics2DObject, LogoBoundry logoBoundry){
		
		Color originalColor = graphics2DObject.getColor();
		
		graphics2DObject.setColor(Color.RED);
		Stroke originalStroke = graphics2DObject.getStroke();
		
		graphics2DObject.setStroke(new BasicStroke(4));
		
		Line2D line = new Line2D.Double(logoBoundry.getLeftXPosition(),logoBoundry.getTopYPosition(),logoBoundry.getLeftXPosition(),logoBoundry.getBottomYPosition());
		graphics2DObject.draw(line);
		
		line = new Line2D.Double(logoBoundry.getLeftXPosition(),logoBoundry.getBottomYPosition(),logoBoundry.getRightXPosition(),logoBoundry.getBottomYPosition());		
		graphics2DObject.draw(line);
		
		line = new Line2D.Double(logoBoundry.getRightXPosition(),logoBoundry.getBottomYPosition(),logoBoundry.getRightXPosition(),logoBoundry.getTopYPosition());		
		graphics2DObject.draw(line);
		
		line = new Line2D.Double(logoBoundry.getRightXPosition(),logoBoundry.getTopYPosition(),logoBoundry.getLeftXPosition(),logoBoundry.getTopYPosition());		
		graphics2DObject.draw(line);
		
		graphics2DObject.setStroke(originalStroke);
		graphics2DObject.setColor(originalColor);
	}
	
	private void drawXAxis(Graphics2D graphics2DObject, int bottomLeftXPosition, int topLeftYPosition, int bottomRightXPosition, int bottomRightYPosition){
		Line2D line = new Line2D.Double(bottomLeftXPosition,topLeftYPosition,bottomRightXPosition,bottomRightYPosition);		
		graphics2DObject.draw(line);
	}
	
	private void drawYAxis(Graphics2D graphics2DObject, int bottomLeftXPosition, int bottomLeftYPosition, int topLeftXPosition, int topLeftYPosition){
		Line2D line = new Line2D.Double(bottomLeftXPosition,bottomLeftYPosition,topLeftXPosition,topLeftYPosition);		
		graphics2DObject.draw(line);
	}

	public float getHorizontalScalingFactor(float maximumWidth, int numberOfColumn){
		if(numberOfColumn != 0){
			return maximumWidth/numberOfColumn * 9;
		}else{
			return 1f;
		}
		
	}
	
	private LogoBoundry getLogoBoundry(ArrayList<WeblogoColumn> columnList, float leftXPosition, float bottomYPosition, float maximumWidth, float maximumHeight){
		
		LogoBoundry boundry = new LogoBoundry();
		
		float rightXPosition = leftXPosition;
		float topYPosition = bottomYPosition;
		
		for (WeblogoColumn weblogoColumn : columnList) {
			Map<String, Double> charactersInColumnMap = weblogoColumn.getCharactersMap();

			Set<String> characters = charactersInColumnMap.keySet();
			
			float heightFactor = maximumHeight/100.0f;
			
			float currentColumnSize = bottomYPosition;
			
			for (String character : characters) {
				
				double characterHeight = charactersInColumnMap.get(character);
				
				characterHeight *= heightFactor;
				
				currentColumnSize -= characterHeight * 8.6;	
			
			}
			
			if(currentColumnSize < topYPosition){
				topYPosition = currentColumnSize;
			}
			
			rightXPosition  += (maximumWidth/(columnList.size()));
		}
		
		System.out.println("leftXPosition  : "+leftXPosition);
		System.out.println("rightXPosition : "+rightXPosition);
		System.out.println("bottomYPosition: "+bottomYPosition);
		System.out.println("topYPosition   : "+topYPosition);
		
		boundry.setLeftXPosition(leftXPosition);
		boundry.setRightXPosition(rightXPosition);
		boundry.setBottomYPosition(bottomYPosition);
		boundry.setTopYPosition(topYPosition);
		
		return boundry;
	}
	
	private void drawWebLogo(Graphics2D graphics2DObject, ArrayList<WeblogoColumn> columnList, float bottomLeftXPosition, float bottomLeftYPosition, float maximumWidth, float maximumHeight){
		
		LogoBoundry logoBoundry = getLogoBoundry(columnList, bottomLeftXPosition , bottomLeftYPosition  , maximumWidth, maximumHeight);
		
		for (WeblogoColumn weblogoColumn : columnList) {
			
			drawColumn(graphics2DObject, weblogoColumn, bottomLeftXPosition, bottomLeftYPosition, maximumWidth/(columnList.size() * 9) , maximumHeight);
			
			bottomLeftXPosition  += (maximumWidth/(columnList.size()));
			
		}			
		drawBorder(graphics2DObject, logoBoundry);
	}
	
	private void drawColumn(Graphics2D graphics2DObject, WeblogoColumn column, float bottomLeftXPosition, float bottomLeftYPosition, float horizontalScalingFactor, float maximumHeight){
		
		Map<String, Double> charactersInColumnMap = column.getCharactersMap();
		
		Set<String> characters = charactersInColumnMap.keySet();
		
		float heightFactor = maximumHeight/100.0f;
		
		for (String character : characters) {
			
//			System.out.println("character: "+character);
			
			double characterHeight = charactersInColumnMap.get(character);
			
			characterHeight *= heightFactor;
			
			AffineTransform attributeTransform = AffineTransform.getScaleInstance(horizontalScalingFactor, characterHeight);
			
			drawChar(graphics2DObject, attributeTransform, character, bottomLeftXPosition, bottomLeftYPosition);
			
			bottomLeftYPosition -= characterHeight * 8.6;	
		
//			System.out.println("X: "+bottomLeftXPosition);
//			System.out.println("Y: "+bottomLeftYPosition);
			
		}
	}
	
	@Override
	   public Dimension getPreferredSize() {
	      return new Dimension(preferredSizeWidth, preferredSizeHeight);
	   }

	
	private void drawChar(Graphics2D graphics2DObject, AffineTransform attributeTransform , String ch, float bottomLeftXPosition, float bottomLeftYPosition){
		
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
		
		graphics2DObject.drawString(attributeString.getIterator(), bottomLeftXPosition, bottomLeftYPosition);

		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent mouseEvent) {		
		System.out.println("mouseEvent.getWheelRotation() : "+mouseEvent.getWheelRotation());
		
		if(mouseEvent.getWheelRotation() > 0){
			preferredSizeWidth += 50;
			preferredSizeHeight += 50;
		}else{
			preferredSizeWidth -= 50;
			preferredSizeHeight -= 50;
		}
		
		this.setSize(new Dimension(preferredSizeWidth,preferredSizeHeight));
		this.repaint();
		
		/*System.out.println("mouseEvent.getScrollAmount() : "+mouseEvent.getScrollAmount());
		System.out.println("mouseEvent.getScrollType() : "+mouseEvent.getScrollType());
		System.out.println("mouseEvent.getUnitsToScroll() : "+mouseEvent.getUnitsToScroll());*/
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Text attributes");
		
		SequenceLogoDrawer charTest = new SequenceLogoDrawer();
		charTest.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
		Font font = charTest.getFont(); 
//		System.out.println("font size: "+font.getSize2D());
		charTest.init();
//		charTest.setPreferredSize(new Dimension(800, 800));
		charTest.addMouseWheelListener(charTest);
		
		JScrollPane pane = new JScrollPane(charTest,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.setAutoscrolls(true);
//		charTest.add(pane);
		
//		frame.add(charTest,BorderLayout.CENTER);
		frame.add(pane,BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(new Dimension(600, 600));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
//		frame.
	}
}
