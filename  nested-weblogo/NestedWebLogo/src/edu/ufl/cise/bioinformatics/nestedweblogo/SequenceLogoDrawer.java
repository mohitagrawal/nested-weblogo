package edu.ufl.cise.bioinformatics.nestedweblogo;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
//import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.NestedWebLogoCreator;
import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.WeblogoColumn;
import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.WeblogoDataStructure;
import edu.ufl.cise.bioinformatics.nestedweblogo.utils.Utilities;

public class SequenceLogoDrawer extends JPanel implements MouseWheelListener, ActionListener {
	
	private String webLogoFilePath = "C:\\Users\\sagar\\MyStudy\\BioInformatics\\nestedsample.txt";
	
	
	
	private static final long serialVersionUID = 1L;
	
	private static final int X_INTERVAL = 15;
	
	String words = "Valour fate kinship darkness";
	String java = "Java TM";

//	ArrayList<WeblogoColumn> columnList = new ArrayList<WeblogoColumn>();
	
	private int preferredSizeWidth = 400;
	private int preferredSizeHeight = 400;
	
	private int widthIncrementFactor = 50;
	private int heightIncrementFactor = 50;
	
	private WeblogoDataStructure mainWebLogo = null;
	
	private boolean is3DHeatMap = false;
	
	private int heightFactor = 10;
	
	private enum LineType {
		HORIZONTAL, VERTICAL
	}
	
	
	
	/**
	 * Inits the.
	 */
	public void init(){
//		test();
		
	    Utilities utilities = new Utilities();


		WeblogoDataStructure dataStructure =  utilities.getWeblogoDS(webLogoFilePath);
		mainWebLogo = dataStructure;
		mainWebLogo.setNestedWeblogoMap(null);
		
		/*NestedWebLogoCreator creator = new NestedWebLogoCreator(webLogoFilePath);
		NestedWebLogoDataStructure nestedLogo =  creator.getNestedLogo(2, 4, 5, 7, "A.*");
		mainWebLogo.addEntryToNestedWebLogoMap(nestedLogo);

		nestedLogo =  creator.getNestedLogo(2, 4, 5, 7, "T.*");
		mainWebLogo.addEntryToNestedWebLogoMap(nestedLogo);*/
		
//		nestedLogo =  creator.getNestedLogo(2, 4, 5, 7, "TA.*");
//		mainWebLogo.addEntryToNestedWebLogoMap(nestedLogo);
		
	}	
	
	public void test(){
		this.setPreferredSize(new Dimension(800, 800));
		
		WeblogoDataStructure webLogo = WeblogoDataStructure.getInstance();
		
		LinkedHashMap<String, Double> charactersMap = new LinkedHashMap<String, Double>();
		charactersMap.put("A", 1.0);
		charactersMap.put("T", 2.0);
		charactersMap.put("G", 3.0);
		charactersMap.put("C", 4.0);
		
		
		ArrayList<WeblogoColumn> columnList = new ArrayList<WeblogoColumn>();
		
		WeblogoColumn column = new WeblogoColumn();
		column.setCharactersMap(charactersMap);
		
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		
		webLogo.setColumnList(columnList);
		
		columnList = new ArrayList<WeblogoColumn>();
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		columnList.add(column);
		
		WeblogoDataStructure innerWebLogo = WeblogoDataStructure.getInstance();
		innerWebLogo.setColumnList(columnList);
		
		NestedWebLogoDataStructure nestedWebLogoDataStructure = new NestedWebLogoDataStructure();
		nestedWebLogoDataStructure.setSourceWebLogo(innerWebLogo);
		nestedWebLogoDataStructure.setTargetWebLogo(innerWebLogo);
		nestedWebLogoDataStructure.setSourceWebLogoStartPosition(2);
		nestedWebLogoDataStructure.setSourceWebLogoEndPosition(9);
		
		nestedWebLogoDataStructure.setTargetWebLogoStartPosition(12);
		nestedWebLogoDataStructure.setTargetWebLogoEndPosition(19);
		
		nestedWebLogoDataStructure.setWildCardPattern("ABCD");
		webLogo.addEntryToNestedWebLogoMap(nestedWebLogoDataStructure);
		
		
		WeblogoDataStructure innerInnerWebLogo = WeblogoDataStructure.getInstance();
		
		columnList = new ArrayList<WeblogoColumn>();
		charactersMap = new LinkedHashMap<String, Double>();
		charactersMap.put("A", 1.0);
		charactersMap.put("T", 2.0);
		column = new WeblogoColumn();
		column.setCharactersMap(charactersMap);
		columnList.add(column);
		columnList.add(column);
		innerInnerWebLogo.setColumnList(columnList);
		
		NestedWebLogoDataStructure innerNestedWebLogoDataStructure = new NestedWebLogoDataStructure();
		innerNestedWebLogoDataStructure.setSourceWebLogo(innerInnerWebLogo);
		innerNestedWebLogoDataStructure.setTargetWebLogo(innerInnerWebLogo);
		innerNestedWebLogoDataStructure.setSourceWebLogoStartPosition(2);
		innerNestedWebLogoDataStructure.setSourceWebLogoEndPosition(3);
		
		innerNestedWebLogoDataStructure.setTargetWebLogoStartPosition(5);
		innerNestedWebLogoDataStructure.setTargetWebLogoEndPosition(6);
		
		innerNestedWebLogoDataStructure.setWildCardPattern("ABCD");
		innerWebLogo.addEntryToNestedWebLogoMap(innerNestedWebLogoDataStructure);
		innerNestedWebLogoDataStructure.setWildCardPattern("XYZW");
		innerWebLogo.addEntryToNestedWebLogoMap(innerNestedWebLogoDataStructure);

		
		mainWebLogo = webLogo;
	}
	
	@Override
	public void paint(Graphics g) {

		setBackground(Color.WHITE);
		
		super.paintComponent(g);
		
		
//		System.out.println("Painting: "+preferredSizeWidth + "   "+preferredSizeHeight);
		
		Graphics2D graphics2DObject = (Graphics2D) g;
		
		int bottomLeftXPosition = 60;
		int topLeftXPosition = 60;
		int topLeftYPosition = 20;
		int bottomLeftYPosition = getHeight()-20;
		int bottomRightYPosition = getHeight()-20;
		int bottomRightXPosition = getWidth()-100;
		int maximumAvailableWidth = getWidth()-100;
		float maximumAvailableHeight = getHeight()-topLeftYPosition;
		
//		drawWebLogo(graphics2DObject, columnList, bottomLeftXPosition , bottomLeftYPosition  , maximumAvailableWidth, maximumAvailableHeight);
		
//		drawWebLogo(graphics2DObject, columnList, bottomLeftXPosition , maximumAvailableHeight/4+40 , maximumAvailableWidth/4 , maximumAvailableHeight/4);
		
		recurssivelyDrawWebLogo(graphics2DObject, mainWebLogo, bottomLeftXPosition, bottomLeftYPosition, maximumAvailableWidth, maximumAvailableHeight,is3DHeatMap);
		
		drawYAxis(graphics2DObject, bottomLeftXPosition, bottomLeftYPosition, topLeftXPosition, topLeftYPosition, true);
		
		drawXAxis(graphics2DObject, bottomLeftXPosition, bottomLeftYPosition , bottomRightXPosition , bottomRightYPosition , maximumAvailableWidth/mainWebLogo.getColumnList().size());
								
	}
	
	public void recurssivelyDrawWebLogo(Graphics2D graphics2DObject, WeblogoDataStructure webLogo, float bottomLeftXPosition, float bottomLeftYPosition, float maximumAvailableWidth, float maximumAvailableHeight, boolean heatMap){
		drawMainWebLogo(graphics2DObject, webLogo, bottomLeftXPosition , bottomLeftYPosition  , maximumAvailableWidth, maximumAvailableHeight,heatMap);
		drawNestedWebLogo(graphics2DObject, webLogo, bottomLeftXPosition, bottomLeftYPosition, maximumAvailableWidth, webLogo.getLogoBoundry().getBottomYPosition()-webLogo.getLogoBoundry().getTopYPosition(),heatMap);
	}
	
	
	public int[][] getValidIntervalForMainWebLogo(WeblogoDataStructure webLogo){

		Map<String,NestedWebLogoDataStructure> nestedWeblogoMap = webLogo.getNestedWeblogoMap();
		
		//  Start position of main sequence
		int startPosition = 1;
		
		//  End position of main sequence
		int endPosition = webLogo.getColumnList().size();		
		
		Set<String> keys =  nestedWeblogoMap.keySet();
		
		int intervalSet[][] = new int[keys.size()*2][2];
		
		int count = 0;
		
		for (String key : keys) {
//			System.out.println("Key: " +key);
			NestedWebLogoDataStructure nestedWebLogo = nestedWeblogoMap.get(key);

			intervalSet[count][0] = nestedWebLogo.getSourceWebLogoStartPosition();
			intervalSet[count][1] = nestedWebLogo.getSourceWebLogoEndPosition();
			
			count++;
		
			intervalSet[count][0] = nestedWebLogo.getTargetWebLogoStartPosition();
			intervalSet[count][1] = nestedWebLogo.getTargetWebLogoEndPosition();
			
			count++;
		}
		
		for(int i=0 ; i<intervalSet.length ; i++){
			for(int j=i+1 ; j<intervalSet.length ; j++){
				if(intervalSet[i][0] > intervalSet[j][0]){
					int temp = intervalSet[i][0];
					intervalSet[i][0] = intervalSet[j][0];
					intervalSet[j][0] = temp;
				}
			}
		}
		
		int[][] mainWebLogoInterval = new int[intervalSet.length+1][2];
		
		mainWebLogoInterval[0][0] = startPosition;
		 
		
		for(int i=0 ; i<intervalSet.length ; i++){
			mainWebLogoInterval[i][1] = intervalSet[i][0]-1;
			mainWebLogoInterval[i+1][0] = intervalSet[i][1]+1;
		}
		
		mainWebLogoInterval[intervalSet.length][1] = endPosition;
		
		return mainWebLogoInterval;
	}
	
	public void drawMainWebLogo(Graphics2D graphics2DObject, WeblogoDataStructure webLogo, float bottomLeftXPosition, float bottomLeftYPosition, float maximumWidth, float maximumHeight,boolean heatMap){
		// Get the interval
		
		if(webLogo.getNestedWeblogoMap() == null || webLogo.getNestedWeblogoMap().size() == 0){
			if(heatMap == true){
				draw3DWebLogo(graphics2DObject, webLogo, bottomLeftXPosition , bottomLeftYPosition  , maximumWidth, maximumHeight,1,webLogo.getColumnList().size());
			}else{
				drawWebLogo(graphics2DObject, webLogo, bottomLeftXPosition , bottomLeftYPosition  , maximumWidth, maximumHeight,1,webLogo.getColumnList().size());
			}			
		}else{
			int[][] validInterval = getValidIntervalForMainWebLogo(webLogo);
			
			for(int i=0 ; i<validInterval.length ; i++){
				if(heatMap == true){
					draw3DWebLogo(graphics2DObject, webLogo, bottomLeftXPosition , bottomLeftYPosition  , maximumWidth, maximumHeight,validInterval[i][0],validInterval[i][1]);
				}else{
					drawWebLogo(graphics2DObject, webLogo, bottomLeftXPosition , bottomLeftYPosition  , maximumWidth, maximumHeight,validInterval[i][0],validInterval[i][1]);
				}
			}
		}
		// draw web logo in that interval only
	}
	
	public void drawNestedWebLogo(Graphics2D graphics2DObject, WeblogoDataStructure webLogo, float bottomLeftXPosition, float bottomLeftYPosition, float maximumWidth, float maximumHeight, boolean heatMap){
		
		Map<String,NestedWebLogoDataStructure> nestedWeblogoMap = webLogo.getNestedWeblogoMap();
		
		if(nestedWeblogoMap == null){
			return;
		}
		
		HashMap<String,Integer> columnGroupMap = new HashMap<String, Integer>();
		
		HashMap<String,Integer> rankOfWebLogoInColumnMap = new HashMap<String, Integer>();		
		
		Set<String> keys = nestedWeblogoMap.keySet();
		
		ArrayList<WeblogoColumn> columnList = webLogo.getColumnList();
		
		for (String key : keys) {
			NestedWebLogoDataStructure nestedWebLogo =  nestedWeblogoMap.get(key);
			
			String columnKey = nestedWebLogo.getSourceWebLogoStartPosition()+":"+nestedWebLogo.getSourceWebLogoEndPosition();
			
			int numberOfWeblogoInColumn = 1;
			
			if(columnGroupMap.containsKey(columnKey)){
				numberOfWeblogoInColumn = columnGroupMap.get(columnKey) + 1;
			}

			columnGroupMap.put(columnKey, numberOfWeblogoInColumn);
			rankOfWebLogoInColumnMap.put(key, numberOfWeblogoInColumn);
			
		}
		
		
		for (String key : keys) {
			NestedWebLogoDataStructure nestedWebLogo =  nestedWeblogoMap.get(key);
			
			String columnKey = nestedWebLogo.getSourceWebLogoStartPosition()+":"+nestedWebLogo.getSourceWebLogoEndPosition();
			
			int numberOfWeblogoInColumn = 1;
			
			if(columnGroupMap.containsKey(columnKey)){
				numberOfWeblogoInColumn = columnGroupMap.get(columnKey);
			}else{
				System.out.println("Error....");
				break;
			}

			numberOfWeblogoInColumn = columnGroupMap.get(columnKey);
			int rankOfWebLogoInColumn = rankOfWebLogoInColumnMap.get(key);
			
			float startYPosition = bottomLeftYPosition - ((rankOfWebLogoInColumn-1) * (maximumHeight / numberOfWeblogoInColumn));
			
			
			
			float startXPosition = 	bottomLeftXPosition  +  ( (nestedWebLogo.getSourceWebLogoStartPosition()-1) * (maximumWidth/columnList.size()));
			
			float newMaximumWidth = ( (nestedWebLogo.getSourceWebLogoEndPosition()-nestedWebLogo.getSourceWebLogoStartPosition()+1) * (maximumWidth/columnList.size()));
			float newMaximumHeight = ((1) * (maximumHeight / numberOfWeblogoInColumn));
			
//			drawWebLogo(graphics2DObject, nestedWebLogo.getSourceWebLogo().getColumnList(), startXPosition, startYPosition, newMaximumWidth, newMaximumHeight, 1, nestedWebLogo.getSourceWebLogo().getColumnList().size());
			recurssivelyDrawWebLogo(graphics2DObject, nestedWebLogo.getSourceWebLogo(), startXPosition, startYPosition, newMaximumWidth, newMaximumHeight,heatMap);
			
			//__________________________________________________________________________________________________________________________________
			
			
			startXPosition = 	bottomLeftXPosition  +  ( (nestedWebLogo.getTargetWebLogoStartPosition()-1) * (maximumWidth/columnList.size()));
			
			newMaximumWidth = ( (nestedWebLogo.getTargetWebLogoEndPosition()-nestedWebLogo.getTargetWebLogoStartPosition()+1) * (maximumWidth/columnList.size()));

			newMaximumHeight = ((1) * (maximumHeight / numberOfWeblogoInColumn));
			
//			drawWebLogo(graphics2DObject, nestedWebLogo.getTargetWebLogo().getColumnList(), startXPosition, startYPosition, newMaximumWidth, newMaximumHeight, 1, nestedWebLogo.getTargetWebLogo().getColumnList().size());
			recurssivelyDrawWebLogo(graphics2DObject, nestedWebLogo.getTargetWebLogo(), startXPosition, startYPosition, newMaximumWidth, newMaximumHeight,heatMap);
			
		}
		
	}
	
	private void drawBorder(Graphics2D graphics2DObject, LogoBoundry logoBoundry){
		
		Color originalColor = graphics2DObject.getColor();
		
		graphics2DObject.setColor(Color.BLACK);
		Stroke originalStroke = graphics2DObject.getStroke();
		
		graphics2DObject.setStroke(new BasicStroke(2));
		
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
	
	private LogoBoundry getLogoBoundry(ArrayList<WeblogoColumn> columnList, float leftXPosition, float bottomYPosition, float maximumWidth, float maximumHeight){
		
		LogoBoundry boundry = new LogoBoundry();
		
		float rightXPosition = leftXPosition;
		float topYPosition = bottomYPosition;
		
		for (WeblogoColumn weblogoColumn : columnList) {
			Map<String, Double> charactersInColumnMap = weblogoColumn.getCharactersMap();

			Set<String> characters = charactersInColumnMap.keySet();
			
			

			float totalHeight = 0;
			
			for (String character : characters) {
				
				Double characterHeight = charactersInColumnMap.get(character);
				
				if(characterHeight == null){
					continue;
				}
				
				totalHeight += characterHeight ;
				
			}
			
			double heightFactor = maximumHeight/(2.5*8.6);
			
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
		
		boundry.setLeftXPosition(leftXPosition);
		boundry.setRightXPosition(rightXPosition);
		boundry.setBottomYPosition(bottomYPosition);
		boundry.setTopYPosition(topYPosition);
		
		return boundry;
	}
	
	private LogoBoundry get3DLogoBoundry(ArrayList<WeblogoColumn> columnList, float leftXPosition, float bottomYPosition, float maximumWidth, float maximumHeight){
		
		LogoBoundry boundry = new LogoBoundry();
		
		float rightXPosition = leftXPosition;
		float topYPosition = bottomYPosition;
		
		for (WeblogoColumn weblogoColumn : columnList) {
			Map<String, Double> charactersInColumnMap = weblogoColumn.getCharactersMap();

			Set<String> characters = charactersInColumnMap.keySet();
										
			double heightFactor = maximumWidth/(columnList.size());
			
			float currentColumnSize = bottomYPosition;
			
			currentColumnSize -= (characters.size()*heightFactor);
						
			if(currentColumnSize < topYPosition){
				topYPosition = currentColumnSize;
			}
			
			rightXPosition  += (maximumWidth/(columnList.size()));
		}
		
		boundry.setLeftXPosition(leftXPosition);
		boundry.setRightXPosition(rightXPosition);
		boundry.setBottomYPosition(bottomYPosition);
		boundry.setTopYPosition(topYPosition);
		
		return boundry;
	}
	
	private void drawWebLogo(Graphics2D graphics2DObject, WeblogoDataStructure webLogo, float bottomLeftXPosition, float bottomLeftYPosition, float maximumWidth, float maximumHeight, int startPosition, int endPosition){
		
		ArrayList<WeblogoColumn> columnList = webLogo.getColumnList();
		
		LogoBoundry logoBoundry = getLogoBoundry(columnList, bottomLeftXPosition , bottomLeftYPosition  , maximumWidth, maximumHeight);

		webLogo.setLogoBoundry(logoBoundry);
		
		int count = 1;
		
		for (WeblogoColumn weblogoColumn : columnList) {
			
			if(startPosition<=count && count<=endPosition){
				drawColumn(graphics2DObject, weblogoColumn, bottomLeftXPosition, bottomLeftYPosition, maximumWidth/(columnList.size() * 9) , maximumHeight);
			}					
			count++;
			bottomLeftXPosition  += (maximumWidth/(columnList.size()));
			
		}			
		drawBorder(graphics2DObject, logoBoundry);
	}
	
	private void drawColumn(Graphics2D graphics2DObject, WeblogoColumn column, float bottomLeftXPosition, float bottomLeftYPosition, float horizontalScalingFactor, float maximumHeight){
		
		Map<String, Double> charactersInColumnMap = column.getCharactersMap();
		
		Set<String> characters = charactersInColumnMap.keySet();
		
		
		
		float totalHeight = 0;
		
		for (String character : characters) {
			
			double characterHeight = charactersInColumnMap.get(character);
			
			totalHeight += characterHeight ;
			
		}
		
		double heightFactor = maximumHeight/(2.5*8.6);
//		heightFactor = heightFactor / 8.6f;
		
//		heightFactor = 50.0f;
		
		for (String character : characters) {
			
			double characterHeight = charactersInColumnMap.get(character);
			
			characterHeight *= heightFactor;
			
			AffineTransform attributeTransform = AffineTransform.getScaleInstance(horizontalScalingFactor, characterHeight);
			
			drawChar(graphics2DObject, attributeTransform, character, bottomLeftXPosition, bottomLeftYPosition);
			
			bottomLeftYPosition -= characterHeight * 8.6;	
		
		}
	}
	
	
	private void drawChar(Graphics2D graphics2DObject, AffineTransform attributeTransform , String ch, float bottomLeftXPosition, float bottomLeftYPosition){
		Color oldColor = graphics2DObject.getColor();
		AttributedString attributeString = new AttributedString(ch);
		
		if(attributeTransform != null){
			attributeString.addAttribute(TextAttribute.TRANSFORM,attributeTransform);
		}		
		
		graphics2DObject.setColor(Color.BLACK);
		
		if(ch.equals("A")){
			graphics2DObject.setColor(Color.GREEN);	
		}
		
		if(ch.equals("T")){
			graphics2DObject.setColor(Color.RED);	
		}
		
		if(ch.equals("G")){
			graphics2DObject.setColor(Color.YELLOW);	
		}
		
		if(ch.equals("C")){
			graphics2DObject.setColor(Color.BLUE);
		}
		
		graphics2DObject.drawString(attributeString.getIterator(), bottomLeftXPosition, bottomLeftYPosition);
		graphics2DObject.setColor(oldColor);
		
	}

	private void draw3DWebLogo(Graphics2D graphics2DObject,WeblogoDataStructure webLogo , float bottomLeftXPosition, float bottomLeftYPosition, float maximumWidth, float maximumHeight, int startPosition, int endPosition){
		
		ArrayList<WeblogoColumn> columnList = webLogo.getColumnList();
		
		LogoBoundry logoBoundry = get3DLogoBoundry(columnList, bottomLeftXPosition , bottomLeftYPosition  , maximumWidth, maximumHeight);

		webLogo.setLogoBoundry(logoBoundry);
		int count = 1;
		
		for (WeblogoColumn weblogoColumn : columnList) {
			
			if(startPosition<=count && count<=endPosition){
				draw3DColumn(graphics2DObject, weblogoColumn, bottomLeftXPosition, bottomLeftYPosition, maximumWidth/(columnList.size() ) , maximumHeight);
			}					
			count++;
			bottomLeftXPosition  += (maximumWidth/(columnList.size()));
			
		}			
		drawBorder(graphics2DObject, logoBoundry);
	}
	
	private void draw3DColumn(Graphics2D graphics2DObject, WeblogoColumn column, float bottomLeftXPosition, float bottomLeftYPosition, float columnWidth, float maximumHeight){
		
		Map<String, Double> charactersInColumnMap = column.getCharactersMap();
		
		Set<String> characters = charactersInColumnMap.keySet();
		
		
		for (String character : characters) {
			
			float characterHeight = columnWidth;
			
			draw3DBlock(graphics2DObject, character, bottomLeftXPosition, bottomLeftYPosition,columnWidth,characterHeight,charactersInColumnMap.get(character));
			
			bottomLeftYPosition -= characterHeight;	
		
		}
	}
	
	
	private void draw3DBlock(Graphics2D graphics2DObject, String ch, float bottomLeftXPosition, float bottomLeftYPosition, float width, float height,double characterHeight){
		
		Color originalColor = graphics2DObject.getColor();
				 
		
		graphics2DObject.setColor(Color.BLACK);
		
		if(ch.equals("A")){
			// green
//			Color newColor = new Color(127,255,127);			
			Color newColor = new Color(0,(int)(250-(50+(characterHeight*80))),0);
			graphics2DObject.setColor(newColor);	
		}
		
		if(ch.equals("T")){
			//red
			Color newColor = new Color((int)(250-(50+(characterHeight*80))),0,0);
			graphics2DObject.setColor(newColor);
//			graphics2DObject.setColor(Color.RED);	
		}
		
		if(ch.equals("G")){
			//yellow
			Color newColor = new Color((int)(250-(50+(characterHeight*80))),(int)(250-(50+(characterHeight*80))),0);
			graphics2DObject.setColor(newColor);
//			graphics2DObject.setColor(Color.YELLOW);	
		}
		
		if(ch.equals("C")){
			// blue
			Color newColor = new Color(0,0,(int)(250-(50+(characterHeight*80))));
			graphics2DObject.setColor(newColor);
//			graphics2DObject.setColor(Color.BLUE);
		}
		
//		graphics2DObject.drawString(attributeString.getIterator(), bottomLeftXPosition, bottomLeftYPosition);
		graphics2DObject.fill3DRect((int)bottomLeftXPosition, (int)(bottomLeftYPosition-height), (int)width,(int) height, true);
//		graphics2DObject.drawString("Temp", bottomLeftXPosition, bottomLeftYPosition);
		
		graphics2DObject.setColor(originalColor);
	}

	
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent mouseEvent) {		
		
		float ratio = 1.0f;

		if(getWidth() > getHeight()){
			ratio = getWidth()/getHeight();
			heightIncrementFactor = (int)((getHeight()/50));
			widthIncrementFactor = (int)((heightIncrementFactor)*ratio) ;
		}else{
			ratio = getHeight()/getWidth();
			heightIncrementFactor = (int)((getHeight()/50)*ratio);
		}
		
		if(mouseEvent.getWheelRotation() > 0){
			preferredSizeWidth += widthIncrementFactor;
			preferredSizeHeight += heightIncrementFactor;
		}else{
			preferredSizeWidth -= widthIncrementFactor;
			preferredSizeHeight -= heightIncrementFactor;
		}
		
//		System.out.println(preferredSizeWidth+" : "+preferredSizeHeight);
		
		this.setSize(new Dimension(preferredSizeWidth,preferredSizeHeight));
		this.repaint();
		
		/*System.out.println("mouseEvent.getScrollAmount() : "+mouseEvent.getScrollAmount());
		System.out.println("mouseEvent.getScrollType() : "+mouseEvent.getScrollType());
		System.out.println("mouseEvent.getUnitsToScroll() : "+mouseEvent.getUnitsToScroll());*/
	}
	
	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame("Nested Web Logo");
		
		SequenceLogoDrawer charTest = new SequenceLogoDrawer();
		charTest.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
		Font font = charTest.getFont(); 
//		System.out.println("font size: "+font.getSize2D());
		charTest.init();
		
//		int[][] arr = charTest.getValidIntervalForMainWebLogo();
		
				
		charTest.addMouseWheelListener(charTest);
		
		JScrollPane pane = new JScrollPane(charTest,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.setAutoscrolls(true);
//		charTest.add(pane);
		
//		frame.add(charTest,BorderLayout.CENTER);
		frame.add(pane,BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		frame.setSize(new Dimension(600, 400));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
		// Create the menu bar
		JMenuBar menuBar = new JMenuBar();

		// Create a menu
		JMenu menu = new JMenu("Weblogo");
		menuBar.add(menu);
		
		// Create a menu item
		JMenuItem item = new JMenuItem("Create");
		item.addActionListener(charTest);		
		menu.add(item);
		item = new JMenuItem("HeatMap");
		item.addActionListener(charTest);		
		menu.add(item);
		item = new JMenuItem("NestedWebLogo");
		item.addActionListener(charTest);		
		menu.add(item);
		
		menu = new JMenu("Nested Weblogo");
		menuBar.add(menu);
		
		item = new JMenuItem("Add");
		item.addActionListener(charTest);
		menu.add(item);
		
		menu = new JMenu("Help");
		menuBar.add(menu);
		
		item = new JMenuItem("About");
		item.addActionListener(charTest);
		menu.add(item);

		// Install the menu bar in the frame
		frame.setJMenuBar(menuBar);
		
//		frame.
	}

	public void getFastaFilePathFromInputBox(){
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "fasta", "fasta","txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getAbsolutePath());
	    }
	    
	    Utilities utilities = new Utilities();
	    
	    String path = chooser.getSelectedFile().getAbsolutePath();
	    path = path.replace('\\', '/');
	    
	    System.out.println("Modified path: "+path);
	    webLogoFilePath = path;
	    WeblogoDataStructure dataStructure =  utilities.getWeblogoDS(chooser.getSelectedFile().getAbsolutePath());
	    mainWebLogo = dataStructure;
	    this.repaint();
	}
	
	public void getWildCardPattern(){
		
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception: "+ex);
        }
        //</editor-fold>

        /* Create and display the form 
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                
            }
        });*/
//        System.out.println("Befor calling");
        new NestedWebLogoInputer(this).setVisible(true);
//        System.out.println("After calling");
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object obj = event.getSource();

		if(obj instanceof JMenuItem){

			
			JMenuItem item = (JMenuItem) obj;
			
			System.out.println("Item : "+item.getText());
			
			if(item.getText().equalsIgnoreCase("add")){
				getWildCardPattern();
			}
			
			if(item.getText().equalsIgnoreCase("HeatMap")){
				if(is3DHeatMap == false){
					is3DHeatMap = true;				
					repaint();
				}				
			}
			
			if(item.getText().equalsIgnoreCase("NestedWebLogo")){
				if(is3DHeatMap == true){
					is3DHeatMap = false;
					repaint();
				}				
			}
			
			if(item.getText().equalsIgnoreCase("Create")){
				getFastaFilePathFromInputBox();
			}					
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(preferredSizeWidth, preferredSizeHeight);
	}
	

	private void drawXAxis(Graphics2D graphics2DObject, int bottomLeftXPosition, int topLeftYPosition, int bottomRightXPosition, int bottomRightYPosition,float interval){
		Line2D line = new Line2D.Double(bottomLeftXPosition,topLeftYPosition,bottomRightXPosition,bottomRightYPosition);		
		graphics2DObject.draw(line);
		for(int i=bottomLeftXPosition ; i<bottomRightXPosition ; i++){
			line = new Line2D.Double(i,bottomRightYPosition-5,i,bottomRightYPosition+5);
			i+=interval;
			graphics2DObject.draw(line);
		}
	}
	
	private void drawYAxis(Graphics2D graphics2DObject, int bottomLeftXPosition, int bottomLeftYPosition, int topLeftXPosition, int topLeftYPosition, boolean isDNASequence){		
		Line2D line = new Line2D.Double(bottomLeftXPosition,bottomLeftYPosition,topLeftXPosition,topLeftYPosition);
		graphics2DObject.draw(line);

		for(int i=1 ; i<=2 ; i++){
			double newYCorrdinate = bottomLeftYPosition - i*((bottomLeftYPosition-topLeftYPosition)/2.5);
			line = new Line2D.Double(bottomLeftXPosition-5,newYCorrdinate,bottomLeftXPosition+5,newYCorrdinate);
			graphics2DObject.draw(line);
		}
		
		line = new Line2D.Double(bottomLeftXPosition-5,topLeftYPosition,bottomLeftXPosition+5,topLeftYPosition);
		graphics2DObject.draw(line);

	}

	public float getHorizontalScalingFactor(float maximumWidth, int numberOfColumn){
		if(numberOfColumn != 0){
			return maximumWidth/numberOfColumn * 9;
		}else{
			return 1f;
		}
		
	}
	
}
